package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：高佳
 * @create：2015年6月13日 下午1:30:10
 * @description：角色相关异常
 */
public class RoleException extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String ROLE_CODE_NULL = "bse.org.RoleCodeNullException";
	public static final String ROLE_NAME_NULL = "bse.org.RoleNameNullException";
	public static final String ROLE_EXISTL = "bse.org.RoleExistException";
	public RoleException(String errCode){
		super(errCode);
		super.errCode = errCode;
	}	
	
	public RoleException(String code,String msg){
		super(code,msg);
	}
	
	
}
