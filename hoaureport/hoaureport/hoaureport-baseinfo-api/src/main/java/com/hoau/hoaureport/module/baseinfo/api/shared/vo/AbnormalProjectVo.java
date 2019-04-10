package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.AbnormalProjectEntity;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:24:35
 * @description：异常项目维护vo
 */
public class AbnormalProjectVo {

	/**
	 *查询参数
	 */
	private AbnormalProjectParams abnormalProjectParams;
	
	/**
	 *新增、修改实体类
	 */
	private AbnormalProjectEntity abnormalProjectEntity;
	
	/**
	 *查询结果
	 */
	private List<AbnormalProjectEntity> abnormalProjectList;

	public AbnormalProjectParams getAbnormalProjectParams() {
		return abnormalProjectParams;
	}

	public void setAbnormalProjectParams(AbnormalProjectParams abnormalProjectParams) {
		this.abnormalProjectParams = abnormalProjectParams;
	}

	public AbnormalProjectEntity getAbnormalProjectEntity() {
		return abnormalProjectEntity;
	}

	public void setAbnormalProjectEntity(AbnormalProjectEntity abnormalProjectEntity) {
		this.abnormalProjectEntity = abnormalProjectEntity;
	}

	public List<AbnormalProjectEntity> getAbnormalProjectList() {
		return abnormalProjectList;
	}

	public void setAbnormalProjectList(
			List<AbnormalProjectEntity> abnormalProjectList) {
		this.abnormalProjectList = abnormalProjectList;
	}
	
	
	
}
