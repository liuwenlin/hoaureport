package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.GraphBaseInfo;
/**
 * 时效Dao
 * @author 273503
 *
 */
@Repository
public interface TimelinessDaoMapper {
    List<GraphBaseInfo> queryList(String ssr);

	List<GraphBaseInfo> queryNextDayList(String ssr);
    
	
}
