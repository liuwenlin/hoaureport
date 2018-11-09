package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.ResourceDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年6月8日 下午7:16:18
 * @description：
 */
@Component
public class ResourceCodeCacheProvider implements ITTLCacheProvider<ResourceEntity> {
	@Autowired
	private ResourceDao resourceDao;
	@Override
	public ResourceEntity get(String key) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", key);
		map.put("resActive", BaseinfoConstants.ACTIVE);
		map.put("parentActive", BaseinfoConstants.ACTIVE);
		return resourceDao.queryResourceByCode(map);
	}

}
