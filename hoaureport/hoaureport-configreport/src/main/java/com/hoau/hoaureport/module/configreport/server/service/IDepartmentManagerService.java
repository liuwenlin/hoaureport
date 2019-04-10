package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.ReportDepartmentManager;
import com.hoau.hoaureport.module.configreport.shared.vo.DepartmentManagerVo;

/**
 * 
 * @author 刘镇松
 *	
 */
public interface IDepartmentManagerService {

	List<ReportDepartmentManager> queryDepartmentManager(ReportDepartmentManager param,int start,int limit);
	
	void updateDepartmentManager(ReportDepartmentManager param);
	
	void insertDepartmentManager(ReportDepartmentManager param);
	
	 Long countByCondition(ReportDepartmentManager record);
	 
	  Map<String,Object>  bathImplDepartmentManager(String path) throws Exception;
}
