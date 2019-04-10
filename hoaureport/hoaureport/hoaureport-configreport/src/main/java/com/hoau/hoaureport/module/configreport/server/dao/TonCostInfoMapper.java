package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.TonCostInfo;
/**
 * 
 * ClassName: TonCostInfoMapper 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
@Repository
public interface TonCostInfoMapper {
    int deleteByPrimaryKey(String tonCostId);

    int insert(TonCostInfo record);

    int insertSelective(TonCostInfo record);

    TonCostInfo selectByPrimaryKey(Long tonCostId);

    int updateByPrimaryKeySelective(TonCostInfo record);

    int updateByPrimaryKey(TonCostInfo record);
    
    /**
     * 
     * @Description:根据条件查询 吨成本信息
     * @param 吨成本信息实例
     * @param 行限制实例  
     * @return List<TonCostInfo> 
     * @author 文洁
     * @date 2016年8月23日
     */
    List<TonCostInfo> queryTonCostInfoByCondition(TonCostInfo param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 吨成本信息实例
     * @return long 
     * @author 文洁
     * @date 2016年8月23日
     */
    long queryTonCostInfoCountByCondition(TonCostInfo param);
}