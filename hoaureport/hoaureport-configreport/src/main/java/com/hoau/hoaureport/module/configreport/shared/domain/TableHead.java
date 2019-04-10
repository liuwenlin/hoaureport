package com.hoau.hoaureport.module.configreport.shared.domain;

public class TableHead {

	private String header;

	private String dataIndex;

	private int width;

	private String xtype;

	public TableHead() {
	}

	public TableHead(String header, int i) {
		this.header = header;
		this.dataIndex = "str" + i;
		this.width = 120;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

}