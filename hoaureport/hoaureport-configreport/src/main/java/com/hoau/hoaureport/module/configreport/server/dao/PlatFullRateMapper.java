package com.hoau.hoaureport.module.configreport.server.dao;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatFullRate;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatNextdayReachRate;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
public interface PlatFullRateMapper {

    int insert(PlatFullRate record);

    int update(PlatFullRate record);
    /**
     *
     * @Description:根据条件查询 满载率目标值
     */
    List<PlatFullRate> queryPlatFullRate(PlatFullRate param, RowBounds rowBounds);

    /**
     *
     * @Description:查询总记录数
     */
    long queryPlatFullRateCount(PlatFullRate param);
}
