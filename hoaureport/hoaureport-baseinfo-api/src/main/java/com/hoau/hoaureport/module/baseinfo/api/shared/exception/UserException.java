package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：高佳
 * @create：2015年6月6日 下午4:28:12
 * @description：用户相关异常
 */
public class UserException extends BusinessException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名不能为空
	 */
	public static final String USERNAME_NULL = "bse.user.UserNameNullException";

	/**
	 * 员工工号不能为空
	 */
	public static final String EMPCODE_NULL = "bse.user.EmpCodeNullException";

	/**
	 * 员工姓名不能为空
	 */
	public static final String EMPNAME_NULL = "bse.user.EmpNameNullException";

	/**
	 * 员工工号已存在
	 */
	public static final String EMPCODE_EXIST = "bse.user.EmpCodeIsExistException";

	/**
	 * 登录名不能为空
	 */
	public static final String LOGINNAME_NULL = "bse.user.LoginNameNullException";

	/**
	 * 用户ID不能为空
	 */
	public static final String USER_ID_NULL = "bse.user.UserIdNullException";

	/**
	 * 用户对象不能为空
	 */
	public static final String USER_IS_NULL = "bse.user.UserIsNullException";

	/**
	 * 用户密码不能为空
	 */
	public static final String PASSWORD_NULL = "bse.user.UserPasswordNullException";

	/**
	 * 是否本公司员工不能为空
	 */
	public static final String IS_EMP_NULL = "bse.user.UserIsEmpNullException";

	/**
	 * 用户状态不能为空
	 */
	public static final String USER_STATUS_NULL = "bse.user.UserStatusNullException";

	/**
	 * 用户已经存在
	 */
	public static final String USER_EXIST = "bse.user.UserIsExistException";

	/**
	 * 用户编码不能为空
	 */
	public static final String USER_CODE_NULL = "bse.user.UserCodeNullException";

	/**
	 * 用户员工信息为空
	 */
	public static final String EMP_NULL = "bse.user.EmpNullException";
	/**
	 * 当前登录用户对应的职员为空
	 */
	public static final String CURRENT_USER_EMP_NULL = "bse.user.currentUserEmpNull";

	/**
	 * 当前登录用户直属部门为空
	 */
	public static final String CURRENT_USER_EMP_ORG_NULL = "bse.user.currentUserEmpOrgNull";

	/**
	 * 用户未配置角色信息
	 */
	public static final String CURRENT_USER_NO_ROLE = "bse.user.currentUserNoRoleInfo";

	public UserException(String code) {
		super(code);
		super.errCode = code;
	}

	public UserException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public UserException(String code, String msg) {
		super(code, msg);
	}

}
