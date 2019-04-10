package com.hoau.hoaureport.module.configreport.shared.vo;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.AttendanceInfo;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthAttendanceInfo;


/**
 * 场站记工-出勤VO
 * ClassName: AttendanceVo 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
public class AttendanceVo {
	/**出勤信息*/
	private AttendanceInfo attendanceInfo;
	
	/**当月出勤信息*/
	private List<MonthAttendanceInfo> monthAttendanceInfoList;
	
	//下面是getters和setters
	public AttendanceInfo getAttendanceInfo() {
		return attendanceInfo;
	}
	public void setAttendanceInfo(AttendanceInfo attendanceInfo) {
		this.attendanceInfo = attendanceInfo;
	}
	public List<MonthAttendanceInfo> getMonthAttendanceInfoList() {
		return monthAttendanceInfoList;
	}
	public void setMonthAttendanceInfoList(List<MonthAttendanceInfo> monthAttendanceInfoList) {
		this.monthAttendanceInfoList = monthAttendanceInfoList;
	}
}
