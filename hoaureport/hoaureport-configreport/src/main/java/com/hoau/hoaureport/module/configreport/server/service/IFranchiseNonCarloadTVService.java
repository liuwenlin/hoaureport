package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.FranchiseNonCarloadTv;

public interface IFranchiseNonCarloadTVService {

	List<FranchiseNonCarloadTv> queryInfo(FranchiseNonCarloadTv param,int start,int limit);

	Long queryCount(FranchiseNonCarloadTv param);
	
	void addInfo(FranchiseNonCarloadTv info);
	
	void updateInfo(FranchiseNonCarloadTv info);
	
	boolean isExist(FranchiseNonCarloadTv param);
	
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	
	public void addOrUpdateInfo(FranchiseNonCarloadTv info,Map<String,Long> countMap) throws Exception;
	
}
