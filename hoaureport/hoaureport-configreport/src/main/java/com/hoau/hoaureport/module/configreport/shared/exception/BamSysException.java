package com.hoau.hoaureport.module.configreport.shared.exception;

public class BamSysException extends RuntimeException implements IBamException {

	private static final long serialVersionUID = 1L;
	private String errCode;// 异常代码
	private String errPath;
	private String message;// 异常信息

	public BamSysException() {

	}

	public BamSysException(String errCode, String errPath, Exception e) {
		super(e.getCause());
		this.errCode = errCode;
		this.errPath = errPath;
		this.message = e.getMessage();
	}

	@Override
	public String getErrCode() {
		return errCode;
	}

	@Override
	public String getErrId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public String getNativeMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getErrorArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getErrPath() {
		return errPath;
	}

	public void setErrPath(String errPath) {
		this.errPath = errPath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
