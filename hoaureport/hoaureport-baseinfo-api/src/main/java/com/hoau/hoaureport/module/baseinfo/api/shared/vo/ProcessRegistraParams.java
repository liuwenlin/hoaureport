package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:52:54
 * @description：
 */
public class ProcessRegistraParams implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	private int offset;

	private int limit;
	
	private String active;

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
