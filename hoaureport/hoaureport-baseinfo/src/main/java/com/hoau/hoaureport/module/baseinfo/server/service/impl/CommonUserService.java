package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonUserService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonUserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserSearchConditionEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.CommonUserDao;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午7:06:30
 * @description：
 */
@Service
public class CommonUserService implements ICommonUserService {

	@Autowired
	private CommonUserDao commonUserDao;
	
	@Override
	public List<CommonUserEntity> queryUserByParam(UserSearchConditionEntity userSearchConditionEntity){
		RowBounds row = new RowBounds(userSearchConditionEntity.getStart(),userSearchConditionEntity.getLimit());
		return commonUserDao.queryUserByParam(userSearchConditionEntity, row);
		
	}

	@Override
	public Long queryCountUserByParam(UserSearchConditionEntity userSearchConditionEntity) {
		return commonUserDao.countUserByParam(userSearchConditionEntity);
	}
}
