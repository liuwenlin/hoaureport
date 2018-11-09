package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;

public class ExportEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -35797219071926698L;
	private boolean type;
	private long times;
	private String message;
	public ExportEntity(){
		
	}
	public ExportEntity(boolean type,long times){
		this.type=type;
		this.times=times;
	}
	public ExportEntity(boolean type,long times,String message){
		this.type=type;
		this.times=times;
		this.message=message;
	}
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	
	

}
