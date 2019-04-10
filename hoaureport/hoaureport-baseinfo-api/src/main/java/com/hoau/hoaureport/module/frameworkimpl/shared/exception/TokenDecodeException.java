package com.hoau.hoaureport.module.frameworkimpl.shared.exception;

import com.hoau.hbdp.framework.exception.GeneralException;

/**
 * token解密异常
 * @author 高佳
 * @date 2015年6月23日
 */
public class TokenDecodeException extends GeneralException {
	
	private static final long serialVersionUID = -2593714308471864839L;
	
	public static final String ERROR_CODE = "errror.common.tokenDecode.failure";

	public TokenDecodeException(Throwable cause) {
		super(cause);
		this.errCode = ERROR_CODE;
	}

	public TokenDecodeException() {
		super();
		this.errCode = ERROR_CODE;
	}

}
