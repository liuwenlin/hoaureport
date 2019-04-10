package com.hoau.hoaureport.module.baseinfo.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;

/**
 * @author：涂婷婷
 * @create：2015年7月24日 上午10:55:02
 * @description：
 */
public class EmployeeVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2719021870354921278L;
	
	/**
	 *员工实体对象
	 */
	private EmployeeEntity employeeEntity;
	
	/**
	 *员工对象集合
	 */
	private List<EmployeeEntity> employeeList;

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	public List<EmployeeEntity> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeEntity> employeeList) {
		this.employeeList = employeeList;
	}
	
	

}
