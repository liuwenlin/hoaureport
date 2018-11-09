package com.hoau.hoaureport.module.baseinfo.api.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:51:38
 * @description：处理登记维护entity
 */
public class ProcessRegistraEntity implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 *主键
	 */
	private String id;
	
	/**
	 *处理登记名称
	 */
	private String processRegistraName;
	
	/**
	 *处理登记代号
	 */
	private String processRegistraCode;
	
	/**
	 *处理登记可用
	 */
	private String processRegistraAvailable;
	
	/**
	 *处理登记描述
	 */
	private String processRegistraDescrip;

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
	 *数据是否有效
	 */
	private String active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessRegistraName() {
		return processRegistraName;
	}

	public void setProcessRegistraName(String processRegistraName) {
		this.processRegistraName = processRegistraName;
	}

	public String getProcessRegistraCode() {
		return processRegistraCode;
	}

	public void setProcessRegistraCode(String processRegistraCode) {
		this.processRegistraCode = processRegistraCode;
	}

	public String getProcessRegistraAvailable() {
		return processRegistraAvailable;
	}

	public void setProcessRegistraAvailable(String processRegistraAvailable) {
		this.processRegistraAvailable = processRegistraAvailable;
	}

	public String getProcessRegistraDescrip() {
		return processRegistraDescrip;
	}

	public void setProcessRegistraDescrip(String processRegistraDescrip) {
		this.processRegistraDescrip = processRegistraDescrip;
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
	
	
}
