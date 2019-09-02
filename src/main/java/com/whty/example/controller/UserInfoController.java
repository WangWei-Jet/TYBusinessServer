package com.whty.example.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eidlink.idocr.sdk.constants.PublicParam;
import com.eidlink.idocr.sdk.pojo.request.IdCardCheckParam;
import com.eidlink.idocr.sdk.pojo.request.ImageCheckParam;
import com.eidlink.idocr.sdk.pojo.result.CommonResult;
import com.eidlink.idocr.sdk.service.EidlinkService;
import com.google.gson.Gson;
import com.whty.example.entity.IDCard;
import com.whty.example.pojo.FacecompareRecord;
import com.whty.example.pojo.FacecompareRecordAdditionWithBLOBs;
import com.whty.example.pojo.IdCardInfo;
import com.whty.example.pojo.IdCardInfoExample;
import com.whty.example.pojo.User;
import com.whty.example.pojo.UserExample;
import com.whty.example.service.FacecompareRecordAdditionService;
import com.whty.example.service.FacecompareRecordService;
import com.whty.example.service.IdCardInfoService;
import com.whty.example.service.UsersService;
import com.whty.example.utils.FileTransfer;
import com.whty.example.utils.GPMethods;
import com.whty.example.utils.IdCardErrorCode;
import com.whty.example.utils.JNALibrary;
import com.whty.example.utils.Utils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	public UsersService userService;

	@Autowired
	public IdCardInfoService idCardInfoService;

	@Autowired
	public FacecompareRecordService facecompareRecordService;

	@Autowired
	public FacecompareRecordAdditionService facecompareRecordAdditionService;

	@Value("${eidlink_ip}")
	private String remoteIp;

	@Value("${eidlink_port}")
	private int remotePort;

	@Value("${app_id}")
	private String appId;

	@Value("${cid}")
	private String cid;

	@Value("${app_key}")
	private String appKey;

	@Value("${cert_path}")
	private String cert_path;

	@Value("${cert_pwd}")
	private String cert_pwd;

	@Value("${key_store_type}")
	private String key_store_type;

	@Value("${backup_identification}")
	private boolean backup_identification;

	@Value("${identification_directory_path_prefix}")
	private String identificationDirectoryPathPrefix;

	/**
	 * 查询用户信息
	 * 
	 * @return
	 */
	@Produces({ MediaType.TEXT_PLAIN, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public void showUserInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("查询用户信息");
		logger.debug("查询用户信息");
		List<User> users = null;
		try {
			String userIdStr = request.getParameter("userId");
			if (userIdStr != null && userIdStr.trim().length() > 0) {
				int userId = Integer.valueOf(userIdStr);
				UserExample userExample = new UserExample();
				userExample.createCriteria().andUserIdEqualTo(userId);
				users = userService.selectByExample(userExample);
			} else {
				String userName = request.getParameter("userName");
				String userPwd = request.getParameter("userPwd");
				if (userName != null && userPwd != null) {
					UserExample userExample = new UserExample();
					userExample.createCriteria().andAccountEqualTo(userName).andPasswordEqualTo(userPwd);
					users = userService.selectByExample(userExample);
				}
			}
		} catch (NumberFormatException numberEx) {
			logger.error("查询的用户id有误", numberEx);
			writeTextResult("查询的用户id有误", response);
			return;
		} catch (Exception e) {
			logger.error("查询用户信息异常", e);
			writeTextResult("查询用户信息异常", response);
			return;
		}
		if (users != null && users.size() > 0) {
			logger.info("查询到用户信息");
		} else {
			logger.info("未查询到用户信息");
			writeTextResult("未查询到用户信息", response);
			return;
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("remarks", "查询成功");
		writeTextResult("查询成功:用户名" + users.get(0).getAccount(), response);
		return;
	}

	/**
	 * 查询用户信息
	 * 
	 * @return
	 */
	@Produces({ MediaType.TEXT_PLAIN, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/showIDCardInfo", method = RequestMethod.POST)
	public void showIDCardInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("查询身份证信息");
		logger.debug("查询身份证信息");
		List<IdCardInfo> idCardInfos = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			String userIdStr = request.getParameter("userId");
			logger.debug("请求身份证号:" + userIdStr);
			if (userIdStr != null && userIdStr.trim().length() > 0) {
				IdCardInfoExample idCardExample = new IdCardInfoExample();
				idCardExample.createCriteria().andIdnumEqualTo(userIdStr);
				idCardInfos = idCardInfoService.selectByExample(idCardExample);
			}
		} catch (Exception e) {
			logger.error("查询身份证信息异常:" + e.getMessage());
			result.put("responseCode", "01");
			result.put("remarks", "查询身份证信息异常");
			writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
			return;
		}

		if (idCardInfos != null && idCardInfos.size() > 0) {
			logger.info("查询到有效数据");
		} else {
			logger.info("未查询到对应的身份证信息");
			// writeTextResult("未查询到信息", response);
			result.put("responseCode", "01");
			result.put("remarks", "未查询到对应的身份证信息");
			writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
			return;
		}
		result.put("responseCode", "00");
		result.put("remarks", "查询成功");
		Gson gson = new Gson();
		String resMsg = gson.toJson(idCardInfos.get(0));
		result.put("idCardInfo", resMsg);
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/getAppId", method = RequestMethod.GET)
	public void getAppId(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("获取app_id");
		logger.debug("获取app_id");
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("app_id", appId);
		result.put("remarks", "查询成功");
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/getCid", method = RequestMethod.GET)
	public void getCid(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("获取cid");
		Map<String, String> result = new HashMap<String, String>();
		result.put("responseCode", "00");
		result.put("cid", cid);
		result.put("remarks", "查询成功");
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		return;
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/getIdentificationInfo", method = RequestMethod.GET)
	public void getIdentificationInfo(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("收到查询身份信息请求");
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String requestId = request.getParameter("ReqID");
			logger.debug("请求数据传入的ReqID:" + requestId);
			// String appId = request.getParameter("APPID");
			CommonResult result = idCardCheckTest(requestId);
			// 00:返回正确结果
			if ("00".equals(result.getResult())) {
				logger.info("身份信息查询成功");
				String ciphertext = result.getCiphertext(); // 身份密文信息,明文信息
				resultMap.put("IDCardInfo", ciphertext);
				Gson gson = new Gson();
				IDCard idCard = gson.fromJson(ciphertext, IDCard.class);
				logger.info("身份证拥有者:" + idCard.getName());
				String dn = result.getDn(); // 身份dn
				resultMap.put("dn", dn);
				String picture = result.getPicture(); // 压缩后的照片
				logger.info("压缩后的照片:" + picture);
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				String timeStr = format.format(new Date());
				String storePath = Utils.transferDataToImage(picture,
						identificationDirectoryPathPrefix + idCard.getIdnum(),
						idCard.getIdnum() + "_" + requestId + "_" + timeStr + ".wlt");
				resultMap.put("picture", Utils.getImageStr(storePath));
				if (backup_identification) {
					try {
						/**
						 * 存储入数据库
						 */
						IdCardInfo idCardInfo = new IdCardInfo();
						idCardInfo.setName(idCard.getName());
						idCardInfo.setSex(idCard.getSex());
						idCardInfo.setNation(idCard.getNation());
						idCardInfo.setBirthdate(idCard.getBirthDate());
						idCardInfo.setAddress(idCard.getAddress());
						idCardInfo.setIdnum(idCard.getIdnum());
						idCardInfo.setSigningorganization(idCard.getSigningOrganization());
						idCardInfo.setBegintime(idCard.getBeginTime());
						idCardInfo.setEndtime(idCard.getEndTime());
						idCardInfo.setPicturepath(storePath);

						IdCardInfoExample idCardInfoExample = new IdCardInfoExample();
						idCardInfoExample.createCriteria().andIdnumEqualTo(idCard.getIdnum());
						List<IdCardInfo> idCardInfos = idCardInfoService.selectByExample(idCardInfoExample);
						if (idCardInfos != null && idCardInfos.size() > 0) {
							logger.info("数据库中已存在" + idCardInfos.size() + "条记录");
							logger.info("准备更新数据");
							idCardInfo.setLastquerytime(new Date());
							idCardInfo.setQuerycount((idCardInfos.get(0).getQuerycount() == null) ? 2
									: idCardInfos.get(0).getQuerycount() + 1);
							idCardInfoService.updateByExampleSelective(idCardInfo, idCardInfoExample);
						} else {
							logger.info("数据库中未查询到相关数据");
							logger.info("准备录入信息");
							idCardInfo.setQuerycount(1);
							idCardInfoService.insertSelective(idCardInfo);
						}
						logger.info("数据保存成功");
					} catch (Exception ee) {
						ee.printStackTrace();
						logger.warn("数据写入数据库出现异常");
					}
				}
			}
			String resultCode = result.getResult(); // 错误码
			String resultDetail = result.getResult_detail();// 错误详细码
			String remark = IdCardErrorCode.getErrorCodeDescription(resultDetail);
			logger.info("resultCode:" + resultCode + "\nresultDetal:" + resultDetail + "\nremark:" + remark);

			resultMap.put("resultCode", resultCode);
			resultMap.put("resultDetail", resultDetail);
			resultMap.put("remark", remark);
		} catch (Exception e) {
			logger.error("获取身份信息异常", e);
		}
		writeJSONResult(resultMap, response, "yyyy-MM-dd HH:mm");

		return;
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/facecomp", method = RequestMethod.POST)
	public void facecomp(HttpServletRequest request, HttpServletResponse response, String reqID,
			@RequestParam MultipartFile image) {
		logger.debug("收到人脸识别请求");
		CommonResult result = null;
		byte[] bs = null;
		try {
			// 获取输入流
			InputStream in = image.getInputStream();
			// // 获取文件字节流数据
			bs = new byte[(int) in.available()];
			in.read(bs);
			// 关闭io资源
			in.close();
			// BASE64编码
			String imageStr = new String(Base64.encode(bs));
			// logger.debug("reqID:" + reqID + ",image:" + imageStr);
			result = facecompCheck(reqID, imageStr);

		} catch (Exception e) {
			result = new CommonResult();
			result.setResult("04");
			result.setResult_msg("0400000");
			logger.error("获取头像比对结果失败", e);
		}
		try {
			String jsonMsg = JSONObject.fromObject(result).toString();
			// 获取需要保存数据库的数据
			String resultCode = result.getResult();
			String requestId = result.getReqID();
			String idCardInfo = result.getCiphertext();
			String appEidCode = result.getAppEidCode();
			// picture是加了密的
			String picture = result.getPicture();
			String resultDetail = result.getResult_detail();
			String resultMsg = result.getResult_msg();
			String similarity = result.getSimilarity();
			String resultTime = result.getResult_time();
			String dn = result.getDn();

			Gson gson = new Gson();
			IDCard idCard = null;
			if (idCardInfo != null && idCardInfo.trim().length() > 0) {
				idCard = gson.fromJson(idCardInfo, IDCard.class);
			}
			String pictureStoreFolder = identificationDirectoryPathPrefix + "FaceCompareRecord/"
					+ new SimpleDateFormat("yyyyMMdd").format(new Date());
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeStr = format.format(new Date());
			String pictureStorePath = null;
			if (picture != null) {
				logger.info("压缩后的照片:" + picture);
				pictureStorePath = Utils.transferDataToImage(picture, pictureStoreFolder + "/" + timeStr.substring(8),
						idCard.getIdnum() + "_" + requestId + "_" + timeStr + ".wlt");
			}
			String scenePictureStorePath = null;
			if (idCard != null) {
				scenePictureStorePath = pictureStoreFolder + "/" + timeStr.substring(8) + "/" + "scene_"
						+ idCard.getIdnum() + "_" + requestId + "_" + timeStr + ".png";
			}
			File sceneFile = new File(scenePictureStorePath);
			image.transferTo(sceneFile);
			FacecompareRecord facecompareRecord = new FacecompareRecord();
			facecompareRecord.setResultcode(resultCode);
			facecompareRecord.setRequestid(requestId);
			if (idCard != null) {
				facecompareRecord.setIdcardnumber(idCard.getIdnum());
			}
			facecompareRecord.setAppeidcode(appEidCode);
			facecompareRecord.setResultdetail(resultDetail);
			facecompareRecord.setResultmsg(resultMsg);
			facecompareRecord.setSimilarity(similarity);
			Date resultDateTime = null;
			try {
				// 20180605194525
				resultDateTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(resultTime);
			} catch (Exception e) {
				logger.error("查询结果日期转换出错", e);
			}
			facecompareRecord.setResulttime(resultDateTime);
			facecompareRecord.setDn(dn);
			facecompareRecord.setPicturepath(pictureStorePath);
			facecompareRecord.setScenepicturepath(scenePictureStorePath);
			//
			FacecompareRecordAdditionWithBLOBs facecompareRecordAddtionWithBLOBs = new FacecompareRecordAdditionWithBLOBs();
			// facecompareRecordAddtionWithBLOBs.setFacecomparerecordid(facecompareRecord.getId());
			facecompareRecordAddtionWithBLOBs.setScenepicture(bs);
			if (picture != null) {
				facecompareRecordAddtionWithBLOBs.setPicture(GPMethods.str2bytes(picture));
			}
			// 保存记录到数据库
			saveRecord(facecompareRecord, facecompareRecordAddtionWithBLOBs);
			logger.debug("返回结果:" + jsonMsg);
		} catch (Exception e) {
			logger.error("异常", e);
		}
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("library path:" + System.getProperty("java.library.path"));
		FileTransfer fileTransfer = new FileTransfer();
		int result = fileTransfer.GetBmp("C:\\pic.wlt", 2);
		logger.debug("调用结束:" + result);
	}

	@Produces({ MediaType.APPLICATION_JSON, "text/html;charset=UTF-8" })
	@RequestMapping(value = "/testJna")
	public void testJna(HttpServletRequest request, HttpServletResponse response) {
		int result = JNALibrary.INSTANTCE.GetBmp("C:/pic.wlt", 2);
		logger.debug("调用结束:" + result);
	}

	private CommonResult facecompCheck(String reqID, String image) {
		PublicParam publicParam = new PublicParam();
		ImageCheckParam imageCheckParam = new ImageCheckParam(publicParam, reqID, image);
		CommonResult result = EidlinkService.facecompCheck(imageCheckParam);
		return result;
	}

	private void saveRecord(FacecompareRecord facecompareRecord,
			FacecompareRecordAdditionWithBLOBs facecompareRecordAddtionWithBLOBs) {
		int affactedRow = facecompareRecordService.insertSelectiveWithIdBack(facecompareRecord);
		logger.info("保存人脸识别记录到数据库的操作结果:" + (affactedRow > 0 ? true : false));
		//
		facecompareRecordAddtionWithBLOBs.setFacecomparerecordid(facecompareRecord.getId());
		affactedRow = facecompareRecordAdditionService.insertSelective(facecompareRecordAddtionWithBLOBs);
		logger.info("保存人脸识别记录图片到数据库的操作结果:" + (affactedRow > 0 ? true : false));
	}

	/**
	 * 身份信息查询接口
	 */
	public CommonResult idCardCheckTest(String reqID) {
		// String reqID = "041311093B93E41C";
		PublicParam publicParam = new PublicParam();
		IdCardCheckParam idCardCheckParam = new IdCardCheckParam(publicParam, reqID);
		CommonResult result = EidlinkService.idCardCheck(idCardCheckParam);

		return result;

	}
}
