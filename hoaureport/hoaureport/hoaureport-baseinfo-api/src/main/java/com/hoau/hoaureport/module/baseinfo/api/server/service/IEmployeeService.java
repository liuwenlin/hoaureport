package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;

/**
 * @author：李旭锋
 * @create：2015年6月12日 上午8:32:23
 * @description：
 */
public interface IEmployeeService {
	

	/**
	 * 查询总数
	 * @param employeeEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月12日
	 * @update 
	 */
	public Long queryEmployeeByEntity(
			EmployeeEntity employeeEntity);
	
	/**
	 * 根据员工code查询员工信息
	 * @param empCode
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	EmployeeEntity queryEmployeeByEmpCode(String empCode);
	
	
	/**
	 * 查询员工信息
	 * @param limit
	 * @param start
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月27日
	 * @update 
	 */
	List<EmployeeEntity> queryEmployeeList(EmployeeEntity employeeEntity,int limit, int start);

	/**
	 * 查询员工记录数
	 * @param employeeEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月27日
	 * @update 
	 */
	Long queryEmployeeCount(EmployeeEntity employeeEntity);
}
