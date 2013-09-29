package org.sbbs.security.webapp.action;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.sbbs.base.webapp.action.BaseDwzAction;
import org.sbbs.security.shiro.IncorrectCaptchaException;
import org.sbbs.security.utils.Exceptions;

public class LoginAction extends BaseDwzAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String username;
	private String msg;

	public String getUsername() {
		return username;
	}

	public String getMsg() {
		return msg;
	}

	public String login() {
		String errorString = (String) this.getRequest().getAttribute(
				FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (errorString == null) {

			return SUCCESS;
		} else {
			
			this.msg = parseException();
			this.username = (String) this.getRequest().getAttribute(
					FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
			return SUCCESS;
		}
	}

	/*public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			Map<String, Object> map, HttpServletRequest request) {


		map.put("msg", msg);
		map.put("username", username);

		return SUCCESS;
	}*/

	private String parseException() {
		String errorString = (String) this.getRequest().getAttribute(
				FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		Class<?> error = null;
		try {
			if (errorString != null) {
				error = Class.forName(errorString);
			}
		} catch (ClassNotFoundException e) {
			LOG.error(Exceptions.getStackTraceAsString(e));
		}

		String msg = "其他错误！";
		if (error != null) {
			if (error.equals(UnknownAccountException.class))
				msg = "未知帐号错误！";
			else if (error.equals(IncorrectCredentialsException.class))
				msg = "密码错误！";
			else if (error.equals(IncorrectCaptchaException.class))
				msg = "验证码错误！";
			else if (error.equals(AuthenticationException.class))
				msg = "认证失败！";
			else if (error.equals(DisabledAccountException.class))
				msg = "账号被冻结！";
		}
		return "登录失败，" + msg;
	}
}
