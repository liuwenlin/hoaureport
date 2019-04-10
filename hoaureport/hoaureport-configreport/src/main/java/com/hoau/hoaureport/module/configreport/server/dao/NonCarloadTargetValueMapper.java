package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.NonCarloadTargetValue;
/**
 * 非整车合计
 * @author 刘镇松
 *
 */
@Repository
public interface NonCarloadTargetValueMapper {
    int insert(NonCarloadTargetValue record);

    int insertSelective(NonCarloadTargetValue record);
    
    int updateByPrimaryKeySelective(NonCarloadTargetValue record);

    /**
     * 根据条件查询 
     * @return
     */
    List<NonCarloadTargetValue> queryByCondition(NonCarloadTargetValue param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @return
     */
    Long queryCountByCondition(NonCarloadTargetValue param);
}