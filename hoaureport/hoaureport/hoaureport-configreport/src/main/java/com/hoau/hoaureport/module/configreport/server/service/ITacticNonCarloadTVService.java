package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.TacticNonCarloadTv;

public interface ITacticNonCarloadTVService {

	List<TacticNonCarloadTv> queryInfo(TacticNonCarloadTv param,int start,int limit);

	Long queryCount(TacticNonCarloadTv param);
	
	void addInfo(TacticNonCarloadTv info);
	
	void updateInfo(TacticNonCarloadTv info);
	
	boolean isExist(TacticNonCarloadTv param);
	
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	
	public void addOrUpdateInfo(TacticNonCarloadTv info,Map<String,Long> countMap) throws Exception;
	
}
