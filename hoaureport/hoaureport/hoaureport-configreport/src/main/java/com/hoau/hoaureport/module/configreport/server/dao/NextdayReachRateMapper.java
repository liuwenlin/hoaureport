package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.NextdayReachRate;
/**
 * 
 * ClassName: NextdayReachRateMapper 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
@Repository
public interface NextdayReachRateMapper {
    int deleteByPrimaryKey(String reachRateId);

    int insert(NextdayReachRate record);

    int insertSelective(NextdayReachRate record);

    NextdayReachRate selectByPrimaryKey(Long reachRateId);

    int updateByPrimaryKeySelective(NextdayReachRate record);

    int updateByPrimaryKey(NextdayReachRate record);
    /**
     * 
     * @Description:根据条件查询 次日送达率信息
     * @param 次日送达率信息实例
     * @param 行限制实例  
     * @return List<NextdayReachRate> 
     * @author 文洁
     * @date 2016年8月23日
     */
    List<NextdayReachRate> queryNextdayReachRateByCondition(NextdayReachRate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 次日送达率信息实例
     * @return long 
     * @author 文洁
     * @date 2016年8月23日
     */
    long queryNextdayReachRateCountByCondition(NextdayReachRate param);
}