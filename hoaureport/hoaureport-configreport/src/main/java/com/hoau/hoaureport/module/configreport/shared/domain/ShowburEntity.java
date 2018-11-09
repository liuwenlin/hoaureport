package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.Serializable;

public class ShowburEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ssid;
	private String xaxis;
	private String belongtable;
	private String chartype;
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getXaxis() {
		return xaxis;
	}
	public void setXaxis(String xaxis) {
		this.xaxis = xaxis;
	}
	public String getBelongtable() {
		return belongtable;
	}
	public void setBelongtable(String belongtable) {
		this.belongtable = belongtable;
	}
	public String getChartype() {
		return chartype;
	}
	public void setChartype(String chartype) {
		this.chartype = chartype;
	}
	
	

}
