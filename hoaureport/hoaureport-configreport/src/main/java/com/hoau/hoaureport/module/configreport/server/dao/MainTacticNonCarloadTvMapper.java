package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.MainTacticNonCarloadTv;

/**
 * 直营非整车合计
 * @author Administrator
 *
 */
@Repository
public interface MainTacticNonCarloadTvMapper {
    int insert(MainTacticNonCarloadTv record);

    int insertSelective(MainTacticNonCarloadTv record);
    
    int updateByPrimaryKeySelective(MainTacticNonCarloadTv record);

    /**
     * 根据条件查询 
     * @return
     */
    List<MainTacticNonCarloadTv> queryByCondition(MainTacticNonCarloadTv param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @return
     */
    Long queryCountByCondition(MainTacticNonCarloadTv param);
}