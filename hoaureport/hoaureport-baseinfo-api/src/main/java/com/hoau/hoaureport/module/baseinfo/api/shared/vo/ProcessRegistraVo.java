package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ProcessRegistraEntity;

/**
 * @author：295073
 * @create：2016年1月11日 下午5:03:04
 * @description：处理登记vo类
 */
public class ProcessRegistraVo {
	
	/**
	 *查询参数
	 */
	private ProcessRegistraParams processRegistraParams;
	
	/**
	 *新增修改参数
	 */
	private ProcessRegistraEntity processRegistraEntity;
	
	/**
	 *查询结果、要删除参数
	 */
	private List<ProcessRegistraEntity>  processRegistraList;

	public ProcessRegistraParams getProcessRegistraParams() {
		return processRegistraParams;
	}

	public void setProcessRegistraParams(ProcessRegistraParams processRegistraParams) {
		this.processRegistraParams = processRegistraParams;
	}

	public ProcessRegistraEntity getProcessRegistraEntity() {
		return processRegistraEntity;
	}

	public void setProcessRegistraEntity(ProcessRegistraEntity processRegistraEntity) {
		this.processRegistraEntity = processRegistraEntity;
	}

	public List<ProcessRegistraEntity> getProcessRegistraList() {
		return processRegistraList;
	}

	public void setProcessRegistraList(
			List<ProcessRegistraEntity> processRegistraList) {
		this.processRegistraList = processRegistraList;
	}

}
