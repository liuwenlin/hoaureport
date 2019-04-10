package com.hoau.hoaureport.module.frameworkimpl.shared.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.entity.IUser;
import com.hoau.hbdp.framework.exception.security.UserNotLoginException;
import com.hoau.hbdp.framework.server.Definitions;
import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.sso.SSOConstant;
import com.hoau.hoaureport.module.frameworkimpl.server.util.TokenMarshal;
import com.hoau.hoaureport.module.util.BaseinfoConstants;


/**
 * Cookie操作类 主要功能： 1.生成Cookie {@link #saveCookie()} 2.Cookie数据到Session
 * {@link #cookieToSession()}
 * @author 高佳
 * @date 2015年6月23日
 */
public final class Cookie {

	private Cookie() {
	}

	private static Cookie cookie;

	public static Cookie getInstance() {
		if (cookie == null) {
			cookie = new Cookie();
		}
		return cookie;
	}

	/**
	 * 获取Token字符串
	 * @return
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static String getTokenParam() {
		String userName = (String) SessionContext.getSession().getObject(
				Definitions.KEY_USER);
		String empCode = (String) SessionContext.getSession().getObject(
				BaseinfoConstants.FRAMEWORK_KEY_EMPCODE);
		String currentDeptCode = (String) SessionContext.getSession()
				.getObject(BaseinfoConstants.KEY_CURRENT_DEPTCODE);
		String currentDeptName = (String) SessionContext.getSession()
				.getObject(BaseinfoConstants.KEY_CURRENT_DEPTNAME);
		Token token = new Token(SessionContext.getSession().getId(), userName,
				empCode, currentDeptCode,currentDeptName, SessionContext.getSession()
						.getMaxInactiveInterval());
		return TokenMarshal.marshal(token);
	}

	/**
	 * 保存cookie 主要功能： 1.根据session重新生成cookie 2.修改cookie的时间戳
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static void saveCookie() {
		HttpServletResponse response = ServletActionContext.getResponse();

		String tokenParam = getTokenParam();
		javax.servlet.http.Cookie cookie = getCookie();
		if (cookie != null) {
			// 修改cookie时间戳
			cookie.setValue(tokenParam);
		} else {
			// 重新new一个Cookie
			cookie = new javax.servlet.http.Cookie(SSOConstant.TOKEN_NAME,
					tokenParam);
		}
		cookie.setPath("/");// 同一个域名所有url cookie共享
		// cookie.setMaxAge(5*60);不写入磁盘，只写入内存，只有在当前页面有用,浏览器关闭立即失效
		response.addCookie(cookie);
	}

	/**
	 * 失效Cookie
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static void invalidateCookie() {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 失效掉token的cookie
		javax.servlet.http.Cookie cookie_token = getCookie();
		if (cookie_token != null) {
			cookie_token.setMaxAge(0);// 设置为0立即删除
			response.addCookie(cookie_token);
		}
		javax.servlet.http.Cookie cookie_jsession = getCookie(SSOConstant.JSESSIONID);
		if (cookie_jsession != null) {
			cookie_jsession.setMaxAge(0);// 设置为0立即删除
			response.addCookie(cookie_jsession);
		}
	}

	/**
	 * 获取HttpCookie对象,token对应的cookie
	 * @return
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static javax.servlet.http.Cookie getCookie() {
		return getCookie(SSOConstant.TOKEN_NAME);
	}

	/**
	 * 获取HttpCookie对象,根据传入的cookie的name值获取
	 * @param name
	 * @return
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static javax.servlet.http.Cookie getCookie(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	/**
	 * Cookie数据到Session 主要功能： 1.Cookie不存在，Throw UserNotLoginException异常
	 * 2.Cookie存在，赋值到Session
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	public static void cookieToSession() {
		javax.servlet.http.Cookie cookie = getCookie();
		if (cookie != null) {
			String paramToken = cookie.getValue();
			if (StringUtils.isBlank(paramToken)) {
				throw new UserNotLoginException();// 用户未登录
			} else {
				Token token = TokenMarshal.unMarshal(paramToken);
				if (token != null && !token.expired()) {
					Cookie.getInstance().tokenToSession(token);
				} else {
					throw new UserNotLoginException();// 用户未登录
				}
			}
		} else {
			throw new UserNotLoginException();// 用户未登录
		}
	}

	/**
	 * token的内容复制到session中
	 * @param token
	 * @author 高佳
	 * @date 2015年6月23日
	 * @update 
	 */
	@SuppressWarnings("unchecked")
	private void tokenToSession(Token token) {
		SessionContext.getSession().setObject(BaseinfoConstants.FRAMEWORK_KEY_EMPCODE,
				token.getEmpCode());
		SessionContext.getSession().setObject(Definitions.KEY_USER,
				token.getUserId());
		SessionContext.getSession().setObject(BaseinfoConstants.KEY_CURRENT_DEPTCODE,
				token.getCurrentDeptCode());
		SessionContext.getSession().setObject(BaseinfoConstants.KEY_CURRENT_DEPTNAME,
				token.getCurrentDeptName());
		ICache<String, IUser> userCache = CacheManager.getInstance().getCache(
				IUser.class.getName());
		UserContext.setCurrentUser(userCache.get(token.getUserId()));
	}

}
