package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonEmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeSearchConditionEntity;

/**
 * @author：李旭锋
 * @create：2015年7月22日 上午10:09:49
 * @description：
 */
@Repository
public interface CommonEmployeeDao {

	/**
	 * 根据条件查询
	 * @param employeeSearchConditionEntity
	 * @param rowBounds
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月22日
	 * @update 
	 */
	public List<CommonEmployeeEntity> queryEmployeeByParam(
			EmployeeSearchConditionEntity employeeSearchConditionEntity,
			RowBounds rowBounds);
	
	/**
	 * 根据条件查询用户信息数量
	 * @param employeeSearchConditionEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月22日
	 * @update 
	 */
	public Long queryCountEmployeeByParam(EmployeeSearchConditionEntity employeeSearchConditionEntity);

}
