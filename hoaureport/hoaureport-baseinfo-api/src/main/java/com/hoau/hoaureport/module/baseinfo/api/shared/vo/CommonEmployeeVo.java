package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonEmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeSearchConditionEntity;


/**
 * @author：李旭锋
 * @create：2015年7月22日 上午10:03:02
 * @description：
 */
public class CommonEmployeeVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2719021870354921278L;
	
	private CommonEmployeeEntity commonEmployeeEntity;
	
	private List<CommonEmployeeEntity> commonEmployeeList;
	
	private EmployeeSearchConditionEntity employeeSearchConditionEntity;

	public CommonEmployeeEntity getCommonEmployeeEntity() {
		return commonEmployeeEntity;
	}

	public void setCommonEmployeeEntity(CommonEmployeeEntity commonEmployeeEntity) {
		this.commonEmployeeEntity = commonEmployeeEntity;
	}

	public List<CommonEmployeeEntity> getCommonEmployeeList() {
		return commonEmployeeList;
	}

	public void setCommonEmployeeList(List<CommonEmployeeEntity> commonEmployeeList) {
		this.commonEmployeeList = commonEmployeeList;
	}

	public EmployeeSearchConditionEntity getEmployeeSearchConditionEntity() {
		return employeeSearchConditionEntity;
	}

	public void setEmployeeSearchConditionEntity(
			EmployeeSearchConditionEntity employeeSearchConditionEntity) {
		this.employeeSearchConditionEntity = employeeSearchConditionEntity;
	}
	
	

}
