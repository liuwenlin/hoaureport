package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：涂婷婷
 * @create：2015年7月21日 上午9:06:12
 * @description：用户部门数据权限相关异常
 */
public class UserOrgDateAutException extends BusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名不能为空
	 */
	public static final String USERNAME_NULL = "bse.user.UserNameNullException";

	/**
	 * 部门编码不能为空
	 */
	public static final String ORGCODE_NULL = "bse.user.OrgCodeNullException";

	/**
	 * 用户部门已存在
	 */
	public static final String USERORG_EXISTL = "bse.user.UserOrgIsExistException";

	public UserOrgDateAutException(String code) {
		super(code);
		super.errCode = code;
	}

	public UserOrgDateAutException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public UserOrgDateAutException(String code, String msg) {
		super(code, msg);
	}

}
