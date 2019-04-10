package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.LinePlanSuspended;

public interface LinePlanSuspendedMapper {
    int deleteByPrimaryKey(Long linePlanId);

    int insert(LinePlanSuspended record);

    int insertSelective(LinePlanSuspended record);

    LinePlanSuspended selectByPrimaryKey(Long linePlanId);

    int updateByPrimaryKeySelective(LinePlanSuspended record);

    int updateByPrimaryKey(LinePlanSuspended record);
    
    /***查询线路规划时间信息*/
    List<LinePlanSuspended> queryLinePlanByCondition (LinePlanSuspended param,RowBounds rowBounds);
    /*** 查询总的记录条数*/
    Long queryLinePlanCountByCondition(LinePlanSuspended param);
    /**
     * 作废所有数据
     */
    int repealAll();
}