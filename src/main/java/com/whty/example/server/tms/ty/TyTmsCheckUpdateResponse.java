package com.whty.example.server.tms.ty;

import com.whty.example.utils.GPMethods;

/**
 * 检查更新响应报文
 * <p>
 * Title:TyTmsCheckUpdateResponse
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2018年5月18日 下午2:17:16
 * </p>
 * <p>
 * 
 * @author wangwei01
 *         </p>
 */
public class TyTmsCheckUpdateResponse extends TyTmsBaseResponse {

	/**
	 * @param request
	 */
	public TyTmsCheckUpdateResponse(TyTmsBaseRequest request) {
		super(request);
	}

	public String packValidateFailMessage(String responseCode) {
		String softTaskId = GPMethods.generateZero(8);
		String softVersion = GPMethods.generateSpace(20);
		String softLength = GPMethods.generateZero(8);
		String paramTaskId = GPMethods.generateZero(8);
		String paramVersion = GPMethods.generateSpace(20);
		String paramLength = GPMethods.generateZero(8);
		String data = responseCode + softTaskId + softVersion + softLength + paramTaskId + paramVersion + paramLength;
		data = GPMethods.bytesToHexString(data.getBytes());
		String lrc = GPMethods.doLrc(data);
		return data + lrc;
	}

	public String packValidateSuccessMessage() {
		TyTmsCheckUpdateRequest checkUpdateRequest = null;
		try {
			checkUpdateRequest = (TyTmsCheckUpdateRequest) request;
		} catch (Exception e) {
			logger.error("传入的请求有误{}", e);
			return null;
		}
		// 获取最新设备信息
		TyTmsTerminalInfo latestTerminalInfo = checkUpdateRequest.getTyTmsBusinessProxy().getDeviceLatestInfo(
				checkUpdateRequest.getDeviceSn(), checkUpdateRequest.getDeviceTerminalNo(),
				checkUpdateRequest.getSoftwareVersion());
		if (latestTerminalInfo == null) {
			// 获取最新设备信息失败
			return packValidateFailMessage(ErrorCode.UNKOWN_TERMINAL);
		}
		// 软件任务id
		String softTaskId = latestTerminalInfo.getSoftTaskId();
		// 新软件版本号
		String softVersion = latestTerminalInfo.getSoftVersion();
		// 软件长度
		String softLength = latestTerminalInfo.getSoftLength();
		// 参数任务id
		String paramTaskId = GPMethods.generateZero(8);
		// 新参数版本号
		String paramVersion = GPMethods.generateSpace(20);
		// 参数长度
		String paramLength = GPMethods.generateZero(8);
		String data = "00" + softTaskId + softVersion + softLength + paramTaskId + paramVersion + paramLength;
		data = GPMethods.bytesToHexString(data.getBytes());
		String lrc = GPMethods.doLrc(data);
		return data + lrc;
	}

}
