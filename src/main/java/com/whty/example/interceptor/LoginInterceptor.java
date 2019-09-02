
package com.whty.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 验证用户登陆拦截器
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	// @Autowired
	// BaseLogsService baseLogsService;
	//
	// @Autowired
	// private BaseModulesService baseModulesService;

	/**
	 * 验证用户登录拦截器
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("登录拦截器验证用户身份");
		logger.debug("登录拦截器验证用户身份");
		// // 如果session中没有user对象
		// if (null == request.getSession().getAttribute("userName")) {
		// String requestedWith = request.getHeader("x-requested-with");
		// // ajax请求
		// if (requestedWith != null && "XMLHttpRequest".equals(requestedWith))
		// {
		// // response.setHeader("session-status", "timeout");
		//
		// PrintWriter wirter = response.getWriter();
		// wirter.write("timeout");
		// wirter.flush();
		// wirter.close();
		//
		// } else {
		// // 普通页面请求
		// response.sendRedirect(request.getContextPath() + "/");
		// }
		// return false;
		// } else {
		// String url = request.getRequestURI();
		// // System.err.println("==========================="+url);
		// if (url.indexOf("show") >= 0) {
		// String menuId = request.getParameter("menu_id");
		// // System.err.println("==========================="+menuId);
		// // 判断用户是否有次权限
		// String userId = (String)
		// request.getSession().getAttribute(Constant.BaseUsersConstant.USERID);
		// List<BaseModules> modules =
		// this.baseModulesService.selectMyModules(userId);
		// for (BaseModules module : modules) {
		// if (Constant.LeafTypeConstant.LEAFTYPESON == module.getLeafType()
		// && menuId.equalsIgnoreCase(module.getModuleId())) {
		// return true;
		// }
		// }
		// response.sendRedirect(request.getContextPath() + "/");
		// return false;
		// }
		// }
		return true;

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

}
