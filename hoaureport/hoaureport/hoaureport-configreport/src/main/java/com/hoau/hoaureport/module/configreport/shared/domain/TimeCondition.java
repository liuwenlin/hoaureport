package com.hoau.hoaureport.module.configreport.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class TimeCondition extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 查询条件的开始时间
	private Date startTime;

	// 查询条件的结束时间
	private Date endTime;

	// 查询条件的退出开始时间
	private Date logoutBeginTime;

	// 查询条件的退出结束时间
	private Date logoutEndTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getLogoutBeginTime() {
		return logoutBeginTime;
	}

	public void setLogoutBeginTime(Date logoutBeginTime) {
		this.logoutBeginTime = logoutBeginTime;
	}

	public Date getLogoutEndTime() {
		return logoutEndTime;
	}

	public void setLogoutEndTime(Date logoutEndTime) {
		this.logoutEndTime = logoutEndTime;
	}

}
