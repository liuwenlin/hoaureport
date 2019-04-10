package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;


/**
 * @author：高佳
 * @create：2015年6月10日 上午8:43:24
 * @description：员工信息Dao
 */
@Repository
public interface EmployeeDao {
	/**
	 * 根据工号查询用户信息
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月6日
	 * @update 
	 */
	EmployeeEntity queryEmployeeInfoByCode(Map<String, String> map);
	
	/**
	 * 查询用户信息条数
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月25日
	 * @update 
	 */
	Long queryEmployeeByEntity(EmployeeEntity employeeEntity);

	
	/**
	 * 查询员工信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月24日
	 * @update 
	 */
	List<EmployeeEntity> queryEmployeeList(EmployeeEntity employeeEntity,RowBounds rowBounds);
	
	/**
	 * 统计记录数
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月24日
	 * @update 
	 */
	Long queryEmployeeCount(EmployeeEntity employeeEntity);
}
