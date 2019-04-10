package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：295073
 * @create：2016年1月11日 下午3:37:06
 * @description：
 */
public class AbnormalProjectEntity implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	
		/**
		 *主键
		 */
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
		 *异常描述
		 */
		private String abnormalDescribe;
		
		/**
		 *异常原因
		 */
		private String abnormalReaso;
		
		/**
		 *异常可用
		 */
		private String abnormalAvailable;
		
		/**
		 *解决方案描述
		 */
		private String solution;
		
		/**
		 *异常备注
		 */
		private String abnormalRemarks;

		/**
		 *创建人
		 */
		private String createUser;
		
		/**
		 *创建时间
		 */
		private Date createTime;

		/**
		 *修改人
		 */
		private String modifyUser;
		
		/**
		 *修改时间
		 */
		private Date modifyTime;
		
		/**
		 *版本
		 */
		private String version;
		
		/**
		 *是否有效
		 */
		private String active;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public String getAbnormalDescribe() {
			return abnormalDescribe;
		}

		public void setAbnormalDescribe(String abnormalDescribe) {
			this.abnormalDescribe = abnormalDescribe;
		}

		public String getAbnormalReaso() {
			return abnormalReaso;
		}

		public void setAbnormalReaso(String abnormalReaso) {
			this.abnormalReaso = abnormalReaso;
		}

		public String getAbnormalAvailable() {
			return abnormalAvailable;
		}

		public void setAbnormalAvailable(String abnormalAvailable) {
			this.abnormalAvailable = abnormalAvailable;
		}

		public String getAbnormalRemarks() {
			return abnormalRemarks;
		}

		public void setAbnormalRemarks(String abnormalRemarks) {
			this.abnormalRemarks = abnormalRemarks;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getModifyUser() {
			return modifyUser;
		}

		public void setModifyUser(String modifyUser) {
			this.modifyUser = modifyUser;
		}

		public Date getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}

		public String getOperationState() {
			return operationState;
		}

		public void setOperationState(String operationState) {
			this.operationState = operationState;
		}

		public String getSolution() {
			return solution;
		}

		public void setSolution(String solution) {
			this.solution = solution;
		}
		
}
