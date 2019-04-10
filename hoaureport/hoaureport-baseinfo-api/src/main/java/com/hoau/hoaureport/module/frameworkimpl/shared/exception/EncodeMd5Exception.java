package com.hoau.hoaureport.module.frameworkimpl.shared.exception;

import com.hoau.hbdp.framework.exception.GeneralException;


/**
 * md5编码异常
 * @author 高佳
 * @date 2015年6月23日
 */
public class EncodeMd5Exception extends GeneralException {

	private static final long serialVersionUID = 6829001589862824918L;
	
	public static final String ERROR_CODE = "errror.common.passwordEncodeMd5Code.failure";

	public EncodeMd5Exception(Throwable t) {
		super(t);
		this.errCode = "errror.common.passwordEncodeMd5Code.failure";
	}

	public EncodeMd5Exception() {
	}
}
