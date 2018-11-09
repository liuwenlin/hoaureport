package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.UnitpriceMonitorWeekday;

public interface UnitpriceMonitorWeekdayMapper {
    int deleteByPrimaryKey(String weekdayDate);

    int insert(UnitpriceMonitorWeekday record);

    int insertSelective(UnitpriceMonitorWeekday record);

    UnitpriceMonitorWeekday selectByPrimaryKey(String weekdayDate);

    int updateByPrimaryKeySelective(UnitpriceMonitorWeekday record);

    int updateByPrimaryKey(UnitpriceMonitorWeekday record);
    
    Long countByCondition(UnitpriceMonitorWeekday record);
     
    List<UnitpriceMonitorWeekday> queryUnitpriceMonitorWeekday(UnitpriceMonitorWeekday record,RowBounds rowBounds);
}