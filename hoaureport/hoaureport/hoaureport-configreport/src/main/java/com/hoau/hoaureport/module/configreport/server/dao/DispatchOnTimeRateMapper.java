package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.DispatchOnTimeRate;
/**
 * 发货准点率目标值Mapper
 * ClassName: DispatchOnTimeRateMapper 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
@Repository
public interface DispatchOnTimeRateMapper {
    int deleteByPrimaryKey(String dispatchOnTimeRateId);

    int insert(DispatchOnTimeRate record);

    int insertSelective(DispatchOnTimeRate record);

    DispatchOnTimeRate selectByPrimaryKey(Long dispatchOnTimeRateId);

    int updateByPrimaryKeySelective(DispatchOnTimeRate record);

    int updateByPrimaryKey(DispatchOnTimeRate record);
    
    /**
     * 
     * @Description:根据条件查询 次日送达率信息
     * @param 次日送达率信息实例
     * @param 行限制实例  
     * @return List<DispatchOnTimeRate> 
     * @author 文洁
     * @date 2016年11月9日
     */
    List<DispatchOnTimeRate> queryDispatchOnTimeRateByCondition(DispatchOnTimeRate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 次日送达率信息实例
     * @return long 
     * @author 文洁
     * @date 2016年11月9日
     */
    long queryDispatchOnTimeRateCountByCondition(DispatchOnTimeRate param);
}