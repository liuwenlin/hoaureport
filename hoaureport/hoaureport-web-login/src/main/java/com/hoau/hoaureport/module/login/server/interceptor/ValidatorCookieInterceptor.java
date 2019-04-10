package com.hoau.hoaureport.module.login.server.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import com.hoau.hbdp.framework.server.context.RequestContext;
import com.hoau.hbdp.framework.server.sso.SSOConstant;
import com.hoau.hbdp.framework.server.web.interceptor.AbstractInterceptor;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.frameworkimpl.shared.domain.Cookie;
import com.hoau.hoaureport.module.login.server.service.IGatedLaunchService;
import com.hoau.hoaureport.module.util.define.SymbolConstants;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 验证Cookie 1.没有session 1.1 cookie赋值给session 1.2 没有cookie，throw
 * UserNotLoginException异常 2.有session 2.1 没有cookie，重新生成cookie 2.2
 * 有cookie，更新cookie中的时间戳
 * 
 * @author 高佳
 * @date 2015年6月9日
 */
public class ValidatorCookieInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -6961254917655976427L;
	public static final Log LOGGER = LogFactory.getLog(ValidatorCookieInterceptor.class);
	@Autowired
	private IGatedLaunchService gatedLaunchService;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		final String url = RequestContext.getCurrentContext()
				.getRemoteRequestURL();
		// 跳过登录和主页面加载的请求
		if (!isChecked(url)) {
			return invocation.invoke();
		}
		Object target = invocation.getAction();
		String methodName = invocation.getProxy().getMethod();
		Method method = ReflectionUtils.findMethod(target.getClass(),
				methodName);
		LOGGER.info(target.getClass().getName()+methodName);
		if (method.isAnnotationPresent(CookieNonCheckRequired.class)) {
			return invocation.invoke();
		}
		// 如果请求是SSO登录，则无需进行Cookie to session的操作
		HttpServletRequest request = ServletActionContext.getRequest();
		String ssoToken = request.getParameter(SSOConstant.TOKEN_NAME);
		if (StringUtils.isBlank(ssoToken)) {
			// 当前服务session中没有user对象,从cookie中初始化user到session中
			Cookie.cookieToSession();
		}

		// 重新生成cookie或修改cookie中时间戳
		Cookie.saveCookie();
		HoauReportUserContext.getCurrentUser().loadAccess();
		//设置灰度发布版本
		gatedLaunchService.setGatedLaunchVersion();
		return invocation.invoke();
	}

	/**
	 * 判断是否是受控的url
	 * 
	 * @param actionUrl
	 * @return
	 * @author 高佳
	 * @date 2015年6月9日
	 * @update
	 */
	private boolean isChecked(String actionUrl) {
		String url;
		if (actionUrl.indexOf(SymbolConstants.EN_QUESTION) != -1) {
			url = actionUrl.substring(0,
					actionUrl.indexOf(SymbolConstants.EN_QUESTION));
		} else if (actionUrl.indexOf(SymbolConstants.EN_SEMICOLON) != -1) {
			url = actionUrl.substring(0,
					actionUrl.indexOf(SymbolConstants.EN_SEMICOLON));
		} else {
			url = actionUrl;
		}
		// TODO 不受控页面应可配置
		/*if (url.endsWith("index.action")) {
			
			 * // 当遇到需要跳过的url，检查是否存在cookie javax.servlet.http.Cookie cookie =
			 * Cookie.getCookie(); if (Cookie.getCookie() != null) { String
			 * paramToken = cookie.getValue(); Token token =
			 * TokenMarshal.unMarshal(paramToken); if (token.expired()) { return
			 * false; } // 如果cookie存在，验证cookie后再往下走 return true; } else {
			 * 
			 * if (UserContext.getCurrentUser() != null) {
			 * UserContext.setCurrentUser(null); } return false; }
			 
			if (UserContext.getCurrentUser() != null) {
				UserContext.setCurrentUser(null);
			}
			return false;
		}*/
		return true;
	}
}
