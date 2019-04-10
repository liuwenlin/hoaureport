package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.UpTransferTimeNode;
/**
 * 上转移时间
 * ClassName: UpTransferTimeNodeMapper 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public interface UpTransferTimeNodeMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(UpTransferTimeNode record);

    int insertSelective(UpTransferTimeNode record);

    UpTransferTimeNode selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(UpTransferTimeNode record);

    int updateByPrimaryKey(UpTransferTimeNode record);
    
    /**
     * 根据条件查询 上转移时间节点信息
     * @param UpTransferTimeNodeVo
     * @return
     */
    List<UpTransferTimeNode> queryUpTransferTimeNodeByCondition(UpTransferTimeNode param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param UpTransferTimeNodeVo
     * @return
     */
    Long queryUpTransferTimeNodeCountByCondition(UpTransferTimeNode param);
}