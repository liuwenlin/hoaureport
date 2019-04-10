package com.hoau.hoaureport.module.configreport.server.service;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatFullRate;

import java.util.List;
import java.util.Map;

/**
 * 长途满载率service
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
public interface IPlatFullRateService {

    /**
     * 查询满载率目标值
     * @return
     */
    List<PlatFullRate> queryPlatFullRateList(PlatFullRate platFullRate, int start, int limit);

    /**
     *
     * @Description:查询记录总数
     */
    Long queryPlatNextdayReachRateCount(PlatFullRate param);

    Map<String,Object> bathImplPlatNextdayReachRate(String path) throws Exception;
}
