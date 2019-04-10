package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonEmployeeService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonEmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeSearchConditionEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.CommonEmployeeDao;

/**
 * @author：李旭锋
 * @create：2015年7月22日 上午11:32:32
 * @description：
 */
@Service
public class CommonEmployeeService implements ICommonEmployeeService {

	@Autowired
	private CommonEmployeeDao commonEmployeeDao;

	@Override
	public List<CommonEmployeeEntity> queryEmployeeByParam(
			EmployeeSearchConditionEntity employeeSearchConditionEntity) {
		RowBounds rowBounds = new RowBounds(
				employeeSearchConditionEntity.getStart(),
				employeeSearchConditionEntity.getLimit());
		return commonEmployeeDao.queryEmployeeByParam(
				employeeSearchConditionEntity, rowBounds);
	}

	@Override
	public Long queryCountEmployeeByParam(
			EmployeeSearchConditionEntity employeeSearchConditionEntity) {
		return commonEmployeeDao.queryCountEmployeeByParam(employeeSearchConditionEntity);
	}

}
