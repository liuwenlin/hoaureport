package com.hoau.hoaureport.module.configreport.shared.domain;

public class Field {

	private String name;

	private String type;

	public Field() {
	}

	public Field(String name) {
		this.name = name;
		this.type = "String";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}