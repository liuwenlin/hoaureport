package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.SalesLine;
/**
 * 销售线路
 * ClassName: SalesLineMapper 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
public interface SalesLineMapper {
    int deleteByPrimaryKey(Long slId);

    int insert(SalesLine record); 

    int insertSelective(SalesLine record);

    SalesLine selectByPrimaryKey(Long slId);

    int updateByPrimaryKeySelective(SalesLine record);

    int updateByPrimaryKey(SalesLine record);
    
    /**
     * 根据条件查询 
     * @param PlatformAreaVo
     * @return
     */
    List<SalesLine> querySalesLineByCondition(SalesLine param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param PlatformAreaVo
     * @return
     */
    Long querySalesLineCountByCondition(SalesLine param);
    /**
     * 作废所有的销售路线
     */
    int repealAllSalesLine(); 
}