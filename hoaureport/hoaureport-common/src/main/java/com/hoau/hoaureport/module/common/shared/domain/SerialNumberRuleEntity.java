package com.hoau.hoaureport.module.common.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 业务编号实体
 * @author 高佳
 * @date 2015年8月19日
 */
public class SerialNumberRuleEntity extends BaseEntity {

	private static final long serialVersionUID = 8616199538409383700L;
	/**编码*/
	private String code;
	/**名称*/
	private String name;
	/**当前日期*/
	private Date currentTime;
	/**当前值*/
	private Long currentNum;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Long getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Long currentNum) {
		this.currentNum = currentNum;
	}
}