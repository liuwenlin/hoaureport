package com.hoau.hoaureport.module.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigureReportUtil {

	/**
	 * 返回状态编码
	 */
	public static final int RETURN_SUCCESS = 1; // 返回成功
	public static final int RETURN_FAIL = 2; // 返回失败
	public static final String SBUSERVICE_EXCEPTION_UNJSON = "转换JSON异常";
	public static final int BUSERVICE_EXCEPTION_UNJSON = 909;
	public static final int SYSTEM_EXCEPTION_CODE = 606; //
	public static final String SYSTEM_EXCEPTION_NAME = "系统异常";
	
	public static final String KEY_CURRENT_DEPTCODE = "BUTTERFLY_KEY_CURRENT_DEPTCODE";

	/**
	 * 自定义查询sql 最大条数
	 * */
	public static int MAX_NUM = 50000;

	public static int QUERY_MAX_NUM = 100;

	private static SimpleDateFormat DATE_NO_SEP_FORMAT = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 返回当前日期无分隔符格式化字符串 格式为yyyyMMdd
	 * 
	 * @return
	 */
	public static String getNowDateStrNoSep() {
		return DATE_NO_SEP_FORMAT.format(new Date());
	}
}
