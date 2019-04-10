package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgAdministrativeInfoEntity;
import com.hoau.hoaureport.module.baseinfo.server.dao.OrgAdministrativeInfoDao;
import com.hoau.hoaureport.module.util.BaseinfoConstants;


/**
 * @author：高佳
 * @create：2015年6月12日 下午1:00:09
 * @description：组织编码缓存数据提供者
 */
@Component
public class OrganizationOrgCodeCacheProvider implements ITTLCacheProvider<OrgAdministrativeInfoEntity>{
	@Autowired
	private OrgAdministrativeInfoDao orgAdministrativeInfoDao;
	@Override
	public OrgAdministrativeInfoEntity get(String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("active",BaseinfoConstants.ACTIVE);
		OrgAdministrativeInfoEntity org = orgAdministrativeInfoDao.queryOrgAdministrativeInfoByCode(map);
		return org;
	}

}
