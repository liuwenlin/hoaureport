package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferTimeNode;
/**
 * 下转移时间
 * ClassName: DownTransferTimeNodeMapper 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public interface DownTransferTimeNodeMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(DownTransferTimeNode record);

    int insertSelective(DownTransferTimeNode record);

    DownTransferTimeNode selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(DownTransferTimeNode record);

    int updateByPrimaryKey(DownTransferTimeNode record);
    
    
    /**
     * 根据条件查询下转移时间节点信息
     * @param DownTransferTimeNodeVo
     * @return
     */
    List<DownTransferTimeNode> queryDownTransferTimeNodeByCondition(DownTransferTimeNode param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param DownTransferTimeNodeVo
     * @return
     */
    Long queryDownTransferTimeNodeCountByCondition(DownTransferTimeNode param);
}