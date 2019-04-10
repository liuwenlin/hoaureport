package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatDeliverIntimeRate;

/**
 * 平台-送货及时率目标值Mapper
 * ClassName: PlatDeliverIntimeRateMapper 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
@Repository
public interface PlatDeliverIntimeRateMapper {
    int deleteByPrimaryKey(Long platIntimerateId);

    int insert(PlatDeliverIntimeRate record);

    int insertSelective(PlatDeliverIntimeRate record);

    PlatDeliverIntimeRate selectByPrimaryKey(Long platIntimerateId);

    int updateByPrimaryKeySelective(PlatDeliverIntimeRate record);

    int updateByPrimaryKey(PlatDeliverIntimeRate record);
    /**
     * 
     * @Description:根据条件查询 平台-送货及时率信息
     * @param 平台-送货及时率实例
     * @param 行限制实例  
     * @return List<PlatDeliverIntimeRate> 
     * @author 文洁
     * @date 2016年10月17日
     */ 
    List<PlatDeliverIntimeRate> queryPlatDeliverIntimeRateByCondition(PlatDeliverIntimeRate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 平台-送货及时率实例
     * @return long 
     * @author 文洁
     * @date 2016年10月17日
     */
    long queryPlatDeliverIntimeRateCountByCondition(PlatDeliverIntimeRate param);
}