package com.hoau.hoaureport.module.baseinfo.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author：李旭锋
 * @create：2015年6月8日 下午3:56:01
 * @description：
 */
public class DataDictionaryValueException  extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DATADICTIONVALUE_NO_HAVA = "bse.datadictionaryvalue.no.hava";
	
	public static final String DATADICTIONVALUE_VALUECODE_ISNULL = "bse.datadictionaryvalue.ValueCodeNullException";
	
	public static final String DATADICTIONVALUE_VALUENAME_ISNULL = "bse.datadictionaryvalue.ValueNameNullException";
	
	public static final String DATADICTIONVALUE_VALUESEQ_ISNULL = "bse.datadictionaryvalue.ValueseqNullException";
	
	public DataDictionaryValueException(String errCode) {
		super(errCode);
		super.errCode = errCode;
	}

	public DataDictionaryValueException(String errCode, Object... para) {
		super(errCode, para);
		super.errCode = errCode;
	}
}