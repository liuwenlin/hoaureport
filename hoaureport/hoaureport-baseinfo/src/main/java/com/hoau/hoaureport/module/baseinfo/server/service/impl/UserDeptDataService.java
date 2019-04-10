package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserDeptDataService;

/**
 * @author：高佳
 * @create：2015年6月15日 上午10:15:41
 * @description：用户部门service
 */
@Service
public class UserDeptDataService implements IUserDeptDataService{

	@Override
	public List<String> queryUserDeptDataByCode(String userName) {
		return new ArrayList<String>();
	}

}
