package com.hoau.hoaureport.module.baseinfo.server.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.sso.module.api.server.service.impl.SSOResourceService;
import com.hoau.sso.module.api.shared.domain.UserOrgResUriCodesEntity;

/**
 * @author：高佳
 * @create：2015年6月9日 下午7:52:48
 * @description：用户部门角色权限缓存数据提供者
 */
@Component
public class UserOrgRoleResCacheProvider implements ITTLCacheProvider<List<Set<String>>>{
	/*@Autowired
	private UserDao userDao;*/
	@Autowired
	private SSOResourceService ssoResourceService;
	@Override
	public List<Set<String>> get(String key) {
		String[] strs = key.split("#");
		String userCode = strs[0];
		String deptCode  = strs[1];
		List<Set<String>> listInfo = queryOrgResCodeUrisByCode(userCode, deptCode);
		return listInfo;
	}
	
	private List<Set<String>> queryOrgResCodeUrisByCode(String userCode,String deptCode){
		List<Set<String>> result = new ArrayList<Set<String>>();
		/*Map<String,Object> map = new HashMap<String,Object>();
		Set<String> uriSet = new HashSet<String>();
		Set<String> codeSet = new HashSet<String>();
		map.put("userCode", userCode);
		map.put("deptCode", deptCode);
		map.put("orgRoleActive", BaseinfoConstants.ACTIVE);
		map.put("roleResActive", BaseinfoConstants.ACTIVE);
		map.put("resActive", BaseinfoConstants.ACTIVE);
		List<UserOrgResCodeUrisEntity> orgCodeAndRoleCodes = userDao.queryOrgResCodeUrisByCode(map);
		for(UserOrgResCodeUrisEntity entity : orgCodeAndRoleCodes){
			uriSet.add(entity.getResUri());
			codeSet.add(entity.getResCode());
		}*/
		// 查询当前部门的权限信息
		UserOrgResUriCodesEntity userOrgResUriCodesEntity = ssoResourceService.queryResourceByUserName(userCode, deptCode);
		if(userOrgResUriCodesEntity != null){
			result.add(userOrgResUriCodesEntity.getResCodes());
			result.add(userOrgResUriCodesEntity.getResUris());
		}
		return result;
	}
}
