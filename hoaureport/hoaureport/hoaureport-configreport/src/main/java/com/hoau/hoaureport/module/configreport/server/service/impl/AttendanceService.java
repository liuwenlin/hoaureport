package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.configreport.server.dao.AttendanceInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IAttendanceService;
import com.hoau.hoaureport.module.configreport.shared.domain.AttendanceInfo;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthAttendanceInfo;

/**
 * 出勤信息管理服务实现类
 * ClassName: AttendanceService 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
@Service
public class AttendanceService implements IAttendanceService{

	@Resource
	AttendanceInfoMapper attendanceInfoMapper;
	
	/**
	 * 查询每月出勤信息
	 */
	@Override
	public List<MonthAttendanceInfo> queryMonthAttendanceInfo(
			AttendanceInfo param, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return attendanceInfoMapper.queryMonthAttendanceInfoByCondition(param, rowBounds);
	}

	/**
	 * 查询每月出勤信息记录数
	 */
	@Override
	public long queryMonthAttendanceInfoCount(AttendanceInfo param) {
		return attendanceInfoMapper.queryMonthAttendanceInfoCountByCondition(param);
	}
	
}
