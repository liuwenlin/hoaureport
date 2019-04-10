package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.AttendanceInfo;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthAttendanceInfo;


/**
 * 出勤信息管理服务接口
 * ClassName: IAttendanceService 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
public interface IAttendanceService {

	
	List<MonthAttendanceInfo> queryMonthAttendanceInfo(AttendanceInfo param,int start,int limit);
	
	/**
	 * 
	 * @Description:查询每月出勤信息总记录
	 * @param 出勤信息实例
	 * @return 每月出勤信息总记录 
	 * @author 文洁
	 * @date 2016年9月23日
	 */
	long queryMonthAttendanceInfoCount(AttendanceInfo param);
}
