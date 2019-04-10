package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.SignedClient;

public interface ISignedClientService {

	List<SignedClient> queryInfo(SignedClient param,int start,int limit);

	Long queryCount(SignedClient param);
	
	void addInfo(SignedClient info);
	
	void updateInfo(SignedClient info); 
	
	boolean isExist(SignedClient param);
	
	public  Map<String,Object>  bathImplPlatformAreaInfo(String path) throws Exception;
	
	public void addOrUpdateInfo(SignedClient info,Map<String,Long> countMap) throws Exception;
}
