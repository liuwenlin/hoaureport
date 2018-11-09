package com.hoau.hoaureport.module.configreport.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

public class MonthReportSqlException extends BusinessException{

	private static final long serialVersionUID = -7414462575037393847L;

	public MonthReportSqlException(String code){
		super();
		this.errCode=code;
	}
	public MonthReportSqlException(String code,String msg){
		super(code,msg);
	}
	public MonthReportSqlException(String code,String msg,Throwable cause){
		super(code,msg,cause);
	}
}
