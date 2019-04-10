package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.PlatformEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.OrgAdministrativeInfoDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author 张贞献
 * @date 2015-7-24
 * @description：
 */
@Component
public class PlatformCacheProvider implements ITTLCacheProvider<PlatformEntity> {
	@Autowired
	private OrgAdministrativeInfoDao orgAdministrativeInfoDao;
	@Override
	public PlatformEntity get(String key) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", key);
		map.put("active",BaseinfoConstants.ACTIVE);
		return orgAdministrativeInfoDao.queryPlatformEntityByCode(map);
	}

}
