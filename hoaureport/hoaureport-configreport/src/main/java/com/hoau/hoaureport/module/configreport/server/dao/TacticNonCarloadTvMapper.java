package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.TacticNonCarloadTv;
/**
 * 战略非整车合计
 * @author 刘镇松
 *
 */
@Repository
public interface TacticNonCarloadTvMapper {
    int insert(TacticNonCarloadTv record);

    int insertSelective(TacticNonCarloadTv record);
    
    int updateByPrimaryKeySelective(TacticNonCarloadTv record);

    /**
     * 根据条件查询 
     * @return
     */
    List<TacticNonCarloadTv> queryByCondition(TacticNonCarloadTv param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @return
     */
    Long queryCountByCondition(TacticNonCarloadTv param);
}