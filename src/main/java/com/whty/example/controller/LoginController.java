package com.whty.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.whty.example.pojo.DeviceInfo;
import com.whty.example.pojo.DeviceInfoExample;
import com.whty.example.service.DeviceInfoService;

@Controller
public class LoginController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	DeviceInfoService deviceInfoService;

	@Value("${ctxPath}")
	private String ctx;

	@RequestMapping(value = "${ctxPath}", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// String msg = (String) request.getParameter("errorMsg");
		// request.setAttribute("errorMsg", "");
		logger.info("进入首页");
		return "Login/login";
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "index/index";
	}

	@RequestMapping(value = "deviceInfo", method = RequestMethod.GET)
	public String deviceInfo(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入设备信息页面");
		return "DeviceInfo/deviceInfo";
	}

	@RequestMapping(value = "deviceQueryPage", method = RequestMethod.GET)
	public String deviceQueryPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入设备查询页面");
		return "DeviceInfo/deviceQuery";
	}

	@RequestMapping(value = "mainPage", method = RequestMethod.GET)
	public String mainPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入系统主界面");
		return "Main/mainPage";
	}

	@RequestMapping(value = "userManagePage", method = RequestMethod.GET)
	public String userManagePage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入用户管理界面");
		return "UserInfo/userInfo";
	}

	@RequestMapping(value = "userInfoQueryPage", method = RequestMethod.GET)
	public String userInfoQueryPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入用户信息查询界面");
		return "UserInfo/userInfoQuery";
	}

	@RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
	public void queryUserInfo(HttpServletRequest request, HttpServletResponse response) {
		logger.info("准备查询用户信息");
		return;
	}

	@RequestMapping(value = "mobileParamPage", method = RequestMethod.GET)
	public String mobileParamPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入手机管理界面");
		return "Mobile/mobileParamInfo";
	}

	@RequestMapping(value = "mobileParamQueryPage", method = RequestMethod.GET)
	public String mobileParamQueryPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入手机参数查询界面");
		return "Mobile/mobileParamQuery";
	}

	@RequestMapping(value = "contactPage", method = RequestMethod.GET)
	public String contactPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入联系方式界面");
		return "About/aboutUs";
	}

	/**
	 * 查询设备信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deviceQuery", method = RequestMethod.POST)
	public void deviceQuery(HttpServletRequest request, HttpServletResponse response) {
		logger.info("后台查询设备信息");
		List<DeviceInfo> devices = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			String deviceSn = request.getParameter("deviceSn");
			if (deviceSn != null && deviceSn.trim().length() > 0) {

				DeviceInfoExample deviceExample = new DeviceInfoExample();

				deviceExample.createCriteria().andDevicesnEqualTo(deviceSn);

				devices = deviceInfoService.selectByExample(deviceExample);
			}

		} catch (Exception e) {
			logger.error("查询设备信息异常:" + e.getMessage());
			result.put("responseCode", "01");
			result.put("remarks", "查询设备信息异常");
			writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
			return;
		}

		if (devices != null && devices.size() > 0) {
			logger.info("查询到设备信息");
		} else {
			logger.info("未查询到设备信息");
			result.put("responseCode", "01");
			result.put("remarks", "未查询到设备信息");
			writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
			return;
		}
		result.put("responseCode", "00");
		result.put("remarks", "查询成功");
		Gson gson = new Gson();
		// response.getWriter().write(gson.toJson(result));
		String resMsg = gson.toJson(devices.get(0));
		result.put("deviceInfo", resMsg);
		writeJSONResult(result, response, "yyyy-MM-dd HH:mm");
		// writeTextResult("查询成功:设备SN=>" + devices.get(0).getDevicesn(),
		// response);
		return;
	}

	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	// public ModelAndView doLogin(HttpServletRequest request,
	// HttpServletResponse response) {
	// logger.debug("login....");
	// ModelAndView mv = new ModelAndView("login");
	// String errorMsg = "";
	// String userName = request.getParameter("username");
	// String passWord = request.getParameter("password");
	// passWord = BaseUserPswUtil.encryptPsw(userName,
	// MD5Util.MD5(MD5Util.MD5(passWord)));
	// String yzm = request.getParameter("yzm");
	// String sessionYzm = (String) request.getSession().getAttribute(
	// Constant.RANDOM_CODE);
	// if (CheckEmpty.isEmpty(sessionYzm)) {
	// mv.addObject("errorMsg", "验证码过期");
	// return mv;
	// }
	// if (!sessionYzm.equalsIgnoreCase(yzm)) {
	// mv.addObject("errorMsg", "验证码错误");
	// return mv;
	// }
	// UsernamePasswordToken userToken = new UsernamePasswordToken(userName,
	// passWord);
	// Subject subject = SecurityUtils.getSubject();
	// try {
	// subject.login(userToken);
	// } catch (UnknownAccountException e) {
	// errorMsg = "用户名/密码错误";
	// mv.addObject("errorMsg", errorMsg);
	// return mv;
	// } catch (IncorrectCredentialsException e) {
	// errorMsg = "用户名/密码错误";
	// mv.addObject("errorMsg", errorMsg);
	// return mv;
	// } catch (LockedAccountException e) {
	// errorMsg = "用户已被禁用";
	// mv.addObject("errorMsg", errorMsg);
	// return mv;
	// } catch (AuthenticationException e) {
	// // 其他错误，比如锁定，如果想单独处理请单独catch处理
	// errorMsg = "其他错误";
	// e.printStackTrace();
	// logger.error("错误日志呢:???"+e.getMessage());
	// logger.error(e.getLocalizedMessage());
	// mv.addObject("errorMsg", errorMsg);
	// return mv;
	// }
	// if (subject.isAuthenticated()) {
	// String userId = (String) subject.getSession().getAttribute("userId");
	// BaseUsers user = baseUsersService.selectByPrimaryKey(userId);
	// BaseUserRoleExample userRoleExample = new BaseUserRoleExample();
	// com.whty.mam.pojo.BaseUserRoleExample.Criteria cra = userRoleExample
	// .createCriteria();
	// cra.andUserIdEqualTo(userId);
	// cra.andRoleIdEqualTo("AP_APPLY_ROLE");
	// PageList<BaseUserRoleKey> list = baseUserRoleService
	// .selectByExample(userRoleExample, new PageBounds());
	// if (user.getUserLastLoginTime() == null && list.size() > 0) {
	// mv.addObject("warring", "您是第一次登录，请修改您的密码");
	// }
	// BaseUsers baseUsers = new BaseUsers();
	// baseUsers.setUserId(userId);
	// baseUsers.setUserLastLoginIp(this.getIpAddr(request));
	// baseUsers.setUserLastLoginTime(new Date());
	// baseUsersService.updateSelectiveByPrimaryKey(baseUsers);
	// String menuJsonStr = buildMenuStr(getMenuTrees(userId),request);
	// request.getSession().setAttribute("menustr", menuJsonStr);
	// request.getSession().setAttribute("userName", userName);
	// //request.getSession().setMaxInactiveInterval(1800);// 设置超时时间
	// request.getSession().setMaxInactiveInterval(86400);// 设置超时时间
	// mv.setViewName("index/index");
	// return mv;
	// } else {
	// mv.addObject("errorMsg", errorMsg);
	// return mv;
	// }
	// }

	/**
	 * 取得客户端真实ip
	 * 
	 * @param request
	 * @return 客户端真实ip
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		logger.debug("1- X-Forwarded-For ip={}", ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.debug("2- Proxy-Client-IP ip={}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.debug("3- WL-Proxy-Client-IP ip={}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.debug("4- HTTP_CLIENT_IP ip={}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.debug("5- HTTP_X_FORWARDED_FOR ip={}", ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			logger.debug("6- getRemoteAddr ip={}", ip);
		}
		logger.debug("finally ip={}", ip);
		return ip;
	}

	// @RequestMapping(value = "/logout")
	// public String logout(HttpServletRequest request,
	// HttpServletResponse response) {
	// Subject subject = SecurityUtils.getSubject();
	// request.getSession().removeAttribute("userName");
	// subject.logout();
	// return "login";
	// }

	@RequestMapping(value = "/clearSession")
	public String clearSession(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userName");
		return "login";
	}

	// @RequestMapping(value = "/getUserName")
	// public void getUserName(HttpServletRequest request,
	// HttpServletResponse response) {
	// String userNanme = (String) request.getSession().getAttribute(
	// "userName");
	// if (CheckEmpty.isEmpty(userNanme)) {
	// writeJSONResult(new BaseResponseDto(false, ""), response);
	// } else {
	// writeJSONResult(new BaseResponseDto(true, ""), response);
	// }
	// }

	// private static Menu covertModuleToMenu(BaseModules baseModules) {
	// Menu menu = new Menu();
	// menu.setId(baseModules.getModuleId());
	// menu.setIcon(baseModules.getIconCss());
	// menu.setOrder(baseModules.getDisplayIndex());
	// menu.setParentId(baseModules.getParentId());
	// menu.setUrl(baseModules.getModuleUrl());
	// menu.setName(baseModules.getModuleName());
	// return menu;
	//
	// }

	// private List<Menu> getMenuTrees(String userId) {
	// List<BaseModules> listMoudle = baseModulesService
	// .selectMyModules(userId);
	// List<Menu> list = new ArrayList<Menu>();
	// for (int i = 0; i < listMoudle.size(); i++) {
	// BaseModules bm = listMoudle.get(i);
	// if ("0".equals(bm.getParentId())) {
	// Menu menu=new Menu();
	// menu=covertModuleToMenu(bm);
	// List<Menu> chList=new ArrayList<Menu>();
	// for (int j = 0; j < listMoudle.size(); j++) {
	// if (listMoudle.get(j).getParentId().equals(bm.getModuleId()) &&
	// listMoudle.get(j).getLeafType()==1) {
	// chList.add(covertModuleToMenu(listMoudle.get(j)));
	// }
	// }
	// menu.setSubMenus(chList);
	// list.add(menu);
	// }
	// }
	//
	// return list;
	// }

	// private String buildMenuStr(List<Menu> menus, HttpServletRequest request)
	// {
	// String basePath = request.getScheme() + "://" + request.getServerName()
	// + ":" + request.getServerPort() + request.getContextPath();
	// StringBuffer targetStrBuffer = new StringBuffer();
	// for (int i = 0; i < menus.size(); i++) {
	// Menu menu = menus.get(i);
	// boolean hasMemo = "".equals(menu.getMemo());
	// boolean hasUrl = "".equals(menu.getUrl());
	// String tooltip = " class='tooltips' data-container='body'
	// data-placement='right' data-html='true' data-original-title='%s'";
	// // 处理默认打开首页
	// // if(menu.getId().equals("menu_1")){
	// // tooltip =
	// // " class='start active tooltips' data-container='body'
	// // data-placement='right' data-html='true'
	// // data-original-title='%s'";
	// // }
	// String menuStrFormatWithSingle = "<li id='%s' "
	// + (hasMemo ? "%s" : tooltip) + " > <a href='"
	// + (hasUrl ? "javascript:;%s" : "%s")
	// + "'> <i class='%s'></i> "
	// + "<span class='title'>%s</span><span></span></a></li>";
	// String menuStrFormatWithChildren = "<li id='%s' title='%s'> <a href='"
	// + (hasUrl ? "javascript:;%s" : "%s")
	// + "' > <i class='%s'></i> "
	// + "<span class='title'>%s</span><span class='arrow'>"
	// + "</span></a>"
	// + "<ul class='sub-menu'>"
	// + "%s"
	// + "</ul></li>";
	// targetStrBuffer.append(String.format(
	// menu.isHasChildren() ? menuStrFormatWithChildren
	// : menuStrFormatWithSingle, menu.getId(), menu
	// .getMemo(), basePath + menu.getUrl(), menu
	// .getIcon(), menu.getName(),
	// buildMenuStr(menu.getSubMenus(), request)));
	// }
	//
	// return targetStrBuffer.toString();
	// }
}
