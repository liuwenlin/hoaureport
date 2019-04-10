package com.hoau.hoaureport.module.configreport.shared.domain;

public class ResultMessage {

	private int returnCode;

	private String message;

	public ResultMessage() {
	}

	public ResultMessage(int returnCode, String message) {
		this.returnCode = returnCode;
		this.message = message;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
