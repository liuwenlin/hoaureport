package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.ReportDepartmentManager;
import com.hoau.hoaureport.module.configreport.shared.domain.UnitpriceMonitorWeekday;
import com.hoau.hoaureport.module.configreport.shared.vo.DepartmentManagerVo;

/**
 * 
 * @author 刘镇松
 *	
 */
public interface IWeekDayService {

	List<UnitpriceMonitorWeekday> queryWeekDay(UnitpriceMonitorWeekday param,int start,int limit);
	
	void updateWeekDay(UnitpriceMonitorWeekday param);
	
	void insertWeekDay(UnitpriceMonitorWeekday param);
	
	 Long countByCondition(UnitpriceMonitorWeekday record);
	 
	  Map<String,Object>  bathImplWeekDay(String path) throws Exception;
}
