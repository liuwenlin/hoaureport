package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:10:02
 * @description：异常项目查询参数实体类
 */
public class AbnormalProjectParams implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	/**
	 *异常名称
	 */
	private String abnormalName;
	
	/**
	 *异常代号
	 */
	private String abnormalCode;
	
	/**
	 *异常类型
	 */
	private String abnormalType;
	
	/**
	 *订单详情中询单异常,揽收异常查询功能:异常描述模糊查询
	 */
	private String abnormalDescribe;
	
	/**
	 *责任方
	 */
	private String responsibleParty;
	
	/**
	 *订单状态
	 */
	private String orderState;
	
	/**
	 *操作状态
	 */
	private String operationState;
	
	/**
	 *异常可用
	 */
	private String abnormalAvailable;
	
	private String active;
	
	private int offset;

	private int limit;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getAbnormalName() {
		return abnormalName;
	}

	public void setAbnormalName(String abnormalName) {
		this.abnormalName = abnormalName;
	}

	public String getAbnormalCode() {
		return abnormalCode;
	}

	public void setAbnormalCode(String abnormalCode) {
		this.abnormalCode = abnormalCode;
	}

	public String getAbnormalType() {
		return abnormalType;
	}

	public void setAbnormalType(String abnormalType) {
		this.abnormalType = abnormalType;
	}
	
	public String getAbnormalDescribe() {
		return abnormalDescribe;
	}

	public void setAbnormalDescribe(String abnormalDescribe) {
		this.abnormalDescribe = abnormalDescribe;
	}

	public String getResponsibleParty() {
		return responsibleParty;
	}

	public void setResponsibleParty(String responsibleParty) {
		this.responsibleParty = responsibleParty;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOperationState() {
		return operationState;
	}

	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}

	public String getAbnormalAvailable() {
		return abnormalAvailable;
	}

	public void setAbnormalAvailable(String abnormalAvailable) {
		this.abnormalAvailable = abnormalAvailable;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
