package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.MainTacticNonCarloadTv;

public interface IMainTacticNonCarloadTVService {

	List<MainTacticNonCarloadTv> queryInfo(MainTacticNonCarloadTv param,int start,int limit);

	Long queryCount(MainTacticNonCarloadTv param);
	
	void addInfo(MainTacticNonCarloadTv info);
	
	void updateInfo(MainTacticNonCarloadTv info);
	
	boolean isExist(MainTacticNonCarloadTv param);
	
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	
	public void addOrUpdateInfo(MainTacticNonCarloadTv info,Map<String,Long> countMap) throws Exception;
	
}
