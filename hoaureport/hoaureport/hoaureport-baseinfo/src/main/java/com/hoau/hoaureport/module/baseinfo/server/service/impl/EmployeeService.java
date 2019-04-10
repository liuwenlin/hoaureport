package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.baseinfo.api.server.service.IEmployeeService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.EmployeeDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;


/**
 * @author：李旭锋
 * @create：2015年6月12日 上午8:37:21
 * @description：
 */
@Service
public class EmployeeService implements IEmployeeService{
	
	
	@Autowired 
	private EmployeeDao employeeDao;
	

	/**
	 * 查询总数
	 * @param employeeEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月12日
	 * @update 
	 */
	@Override
	public Long queryEmployeeByEntity(EmployeeEntity employeeEntity) {
		return employeeDao.queryEmployeeByEntity(employeeEntity);
	}
	
	/**
	 * 根据员工code查询员工信息
	 * @param empCode
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	@Override
	public EmployeeEntity queryEmployeeByEmpCode(String empCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("empCode", empCode);
		map.put("empActive", BaseinfoConstants.ACTIVE);
		map.put("orgActive", BaseinfoConstants.ACTIVE);
		return employeeDao.queryEmployeeInfoByCode(map);
	}




	@Override
	public List<EmployeeEntity> queryEmployeeList(EmployeeEntity employeeEntity,int limit, int start) {
		employeeEntity.setActive(BaseinfoConstants.ACTIVE);
			RowBounds rowBounds =new RowBounds(start,limit);
 		return employeeDao.queryEmployeeList(employeeEntity,rowBounds);
	}

	@Override
	public Long queryEmployeeCount(EmployeeEntity employeeEntity) {
		return employeeDao.queryEmployeeCount(employeeEntity);
	}
	

}
