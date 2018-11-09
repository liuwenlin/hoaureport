package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.Map;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgEntity;

/**
 * @author 龙海仁
 * @create：2016年1月14日 下午4:59:31
 * @description：
 */
public interface IOrgService {

	/**
	 * 根据物流代码查组织
	 * @param logistCode
	 * @return
	 * @author 龙海仁
	 * @date 2016年1月14日下午5:00:43
	 * @update 
	 */
	public OrgEntity queryOrg(String logistCode);
	
	/**
	 * 查询平台名称和编码
	 * @param code
	 * @return
	 * Map<String,Object>
	 * @author 丁勇
	 * @date 2016年3月1日
	 */
	public Map<String,Object> queryPlatform(String code);
}
