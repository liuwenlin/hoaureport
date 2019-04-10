package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hbdp.framework.entity.IUser;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.SSOEntityConvert;
import com.hoau.hoaureport.module.baseinfo.server.dao.EmployeeDao;
import com.hoau.sso.module.api.server.service.impl.SSOUserService;

/**
 * @author：高佳
 * @create：2015年6月6日 下午3:48:47
 * @description：用户缓存数据提供者
 */
@Component
public class UserCacheProvider implements ITTLCacheProvider<IUser> {
	/*@Autowired
	private UserDao userDao;*/
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SSOUserService ssoUserService;
	
	@Override
	public IUser get(String key) {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("userName", key);
		map.put("userActive",BaseinfoConstants.ACTIVE);
		map.put("empActive", BaseinfoConstants.ACTIVE);
		map.put("orgActive", BaseinfoConstants.ACTIVE);
		map.put("userRoleOrgActive", BaseinfoConstants.ACTIVE);
		map.put("roleResActive", BaseinfoConstants.ACTIVE);
		UserEntity user = userDao.queryUserWithRoleIdAndOrgIdByUserName(map);
		if(user != null){
			map = new HashMap<String, String>();
			map.put("empCode", user.getEmpCode());
			map.put("empActive",BaseinfoConstants.ACTIVE);
			map.put("orgActive",BaseinfoConstants.ACTIVE);
			EmployeeEntity emp = employeeDao.queryEmployeeInfoByCode(map);
			user.setEmployee(emp);
		}else{
			throw new UserException(UserException.CURRENT_USER_NO_ROLE);
		}*/
		return SSOEntityConvert.userConvert(ssoUserService.queryUserByLoginName(key));
	}
	
}
