package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.AttendanceInfo;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthAttendanceInfo;

public interface AttendanceInfoMapper {
    
    /**
     * 
     * @Description:根据条件查询每月出勤信息
     * @param 出勤信息实例
     * @param 行限制实例
     * @return List<MonthAttendanceInfo>每月出勤信息 
     * @author 文洁
     * @date 2016年9月23日
     */
     List<MonthAttendanceInfo> queryMonthAttendanceInfoByCondition(AttendanceInfo param,RowBounds rowBounds);
     
     /**
      * 
      * @Description:查询符合条件的每月出勤信息记录总数
      * @param 出勤信息实例
      * @return Long 
      * @author 文洁
      * @date 2016年9月23日
      */
     Long queryMonthAttendanceInfoCountByCondition(AttendanceInfo param);
}