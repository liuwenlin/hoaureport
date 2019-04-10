package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatNextdayReachRate;

/**
 * 平台次日送到率
 * ClassName: PlatNextdayReachRateMapper 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
public interface PlatNextdayReachRateMapper {
    int deleteByPrimaryKey(Long platReachRateId);

    int insert(PlatNextdayReachRate record);

    int insertSelective(PlatNextdayReachRate record);

    PlatNextdayReachRate selectByPrimaryKey(Long platReachRateId);

    int updateByPrimaryKeySelective(PlatNextdayReachRate record);

    int updateByPrimaryKey(PlatNextdayReachRate record);
    /**
     * 
     * @Description:根据条件查询 平台次日送到率
     * @param 平台次日送到率实例
     * @param 行限制实例  
     * @return List<PlatNextdayReachRate> 
     * @author 文洁
     * @date 2016年10月17日
     */
    List<PlatNextdayReachRate> queryPlatNextdayReachRateByCondition(PlatNextdayReachRate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 平台次日送到率实例
     * @return long 
     * @author 文洁
     * @date 2016年10月17日
     */
    long queryPlatNextdayReachRateCountByCondition(PlatNextdayReachRate param);
}