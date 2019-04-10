package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.FranchiseNonCarloadTv;
/**
 * 特许经营非整车
 * @author 刘镇松
 *
 */
@Repository
public interface FranchiseNonCarloadTvMapper {
    int insert(FranchiseNonCarloadTv record);

    int insertSelective(FranchiseNonCarloadTv record);
    
    int updateByPrimaryKeySelective(FranchiseNonCarloadTv record);

    /**
     * 根据条件查询 
     * @return
     */
    List<FranchiseNonCarloadTv> queryByCondition(FranchiseNonCarloadTv param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @return
     */
    Long queryCountByCondition(FranchiseNonCarloadTv param);
}