package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：高佳
 * @create：2015年6月5日 下午4:05:18
 * @description：数据字典异常类
 */
public class DataDictionaryException extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DATADICTION_HAVA_LEAF = "bse.datadictionary.hava.leaf";
	
	public static final String DATADICTION_SIZE_O = "bse.datadiction.size.is0";
	
	//词条编码
	public static final String DATADICTION_TERMSCODE_ISNULL = "bse.datadictionary.TermsCodeNullException";
	
	//词条名称
	public static final String DATADICTION_TERMSNAME_ISNULL = "bse.datadictionary.TermsNameNullException";
	
	
	public DataDictionaryException(String errCode) {
		super(errCode);
		super.errCode = errCode;
	}

	public DataDictionaryException(String errCode, Object... para) {
		super(errCode, para);
		super.errCode = errCode;
	}
}
