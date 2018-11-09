package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferOnTimeRate;
/**
 * 下转准点率目标值Mapper
 * ClassName: DownTransferOnTimeRateMapper 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
@Repository
public interface DownTransferOnTimeRateMapper {
    int deleteByPrimaryKey(String dispatchOnTimeRateId);

    int insert(DownTransferOnTimeRate record);

    int insertSelective(DownTransferOnTimeRate record);

    DownTransferOnTimeRate selectByPrimaryKey(Long dispatchOnTimeRateId);

    int updateByPrimaryKeySelective(DownTransferOnTimeRate record);

    int updateByPrimaryKey(DownTransferOnTimeRate record);
    
    /**
     * 
     * @Description:根据条件查询 次日送达率信息
     * @param 次日送达率信息实例
     * @param 行限制实例  
     * @return List<DownTransferOnTimeRate> 
     * @author 文洁
     * @date 2016年11月9日
     */
    List<DownTransferOnTimeRate> queryDownTransferOnTimeRateByCondition(DownTransferOnTimeRate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 次日送达率信息实例
     * @return long 
     * @author 文洁
     * @date 2016年11月9日
     */
    long queryDownTransferOnTimeRateCountByCondition(DownTransferOnTimeRate param);
}