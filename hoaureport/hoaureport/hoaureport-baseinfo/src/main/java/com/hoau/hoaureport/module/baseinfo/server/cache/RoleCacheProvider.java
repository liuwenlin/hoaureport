package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.RoleDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年6月12日 下午1:53:56
 * @description：角色缓存数据提供者
 */
@Component
public class RoleCacheProvider implements ITTLCacheProvider<RoleEntity>{
	@Autowired
	private RoleDao roleDao;
	@Override
	public RoleEntity get(String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("roleActive", BaseinfoConstants.ACTIVE);
		map.put("resActive", BaseinfoConstants.ACTIVE);
		map.put("rrActive", BaseinfoConstants.ACTIVE);
		return roleDao.queryRoleResourceByCode(map);
	}

}
