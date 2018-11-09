package com.hoau.hoaureport.module.common.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

 /**
 * @author 288697
 * job信息实体类
 */
public class JobProcessEntity extends BaseEntity{

	/**
	 * */
	private static final long serialVersionUID = 1L;	

	/**
	 * 任务名称
	 */
	private String name;
	
	/**
	 * 任务编号
	 */
	private String code;
	
	 /**
	 * job启动时间
	 */
	private Date jobStartTime;
	
	/**
	 * job启动结束时间
	 */
	private Date jobEndTime;
	
	/**
	 * job耗时（ms）
	 */
	private Long interval;
	
	/**
	 * 执行结果
	 */
	private String status;
	
	/**
	 * 异常信息
	 */
	private String errorInfo;
	
	public Date getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public Date getJobEndTime() {
		return jobEndTime;
	}

	public void setJobEndTime(Date jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
