package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.NonCarloadTargetValue;

public interface INonCarloadTVService {

	List<NonCarloadTargetValue> queryInfo(NonCarloadTargetValue param,int start,int limit);

	Long queryCount(NonCarloadTargetValue param);
	
	void addInfo(NonCarloadTargetValue info);
	
	void updateInfo(NonCarloadTargetValue info);
	
	boolean isExist(NonCarloadTargetValue param);
	
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	
	public void addOrUpdateInfo(NonCarloadTargetValue info,Map<String,Long> countMap) throws Exception;
	
}
