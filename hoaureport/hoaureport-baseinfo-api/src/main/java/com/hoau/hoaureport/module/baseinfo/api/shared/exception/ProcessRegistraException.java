package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：李旭锋
 * @create：2016年1月15日 下午3:06:59
 * @description：处理登记维护异常
 */
public class ProcessRegistraException extends BusinessException{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ProcessRegistraException(String errCode) {
		super(errCode);
		super.errCode = errCode;
	}

	public ProcessRegistraException(String errCode, Object... para) {
		super(errCode, para);
		super.errCode = errCode;
	}
}
