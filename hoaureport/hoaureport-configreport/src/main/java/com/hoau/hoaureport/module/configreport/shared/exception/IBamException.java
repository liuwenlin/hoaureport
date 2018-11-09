package com.hoau.hoaureport.module.configreport.shared.exception;

public interface IBamException {
	
	/**
	 * 返回异常编码
	 * 
	 * @return
	 */
	public String getErrCode();

	/**
	 * 返回异常ID
	 * 
	 * @return
	 */
	public String getErrId();

	/**
	 * 返回异常消息
	 * 
	 * @return
	 */
	public String getMessage();

	/**
	 * 返回默认的消息
	 * 
	 * @return
	 */
	public String getDefaultMessage();

	/**
	 * 返回本地消息
	 * 
	 * @return
	 */
	public String getNativeMessage();

	/**
	 * 返回异常参数
	 * 
	 * @return
	 */
	public Object[] getErrorArguments();
}
