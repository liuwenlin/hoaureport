package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.SignedClient;
/**
 * 
 *签约客户
 */
@Repository
public interface SignedClientMapper {
    int deleteByPrimaryKey(String userCode);

    int insert(SignedClient record);

    int insertSelective(SignedClient record);

    SignedClient selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(SignedClient record);

    int updateByPrimaryKey(SignedClient record);
    
    /**
     * 根据条件查询 
     * @return
     */
    List<SignedClient> queryByCondition(SignedClient param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @return
     */
    Long queryCountByCondition(SignedClient param);
}