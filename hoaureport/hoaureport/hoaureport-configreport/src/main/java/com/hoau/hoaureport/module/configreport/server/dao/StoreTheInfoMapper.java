package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.StoreTheInfo;
/**
 * 
 * ClassName: StoreTheInfoMapper 
 * @author 刘镇松
 * @date 2016年9月12日
 * @version V1.0
 */
@Repository
public interface StoreTheInfoMapper {
    int deleteByPrimaryKey(String storeId);

    int insert(StoreTheInfo record);

    int insertSelective(StoreTheInfo record);

    StoreTheInfo selectByPrimaryKey(String storeId);

    int updateByPrimaryKeySelective(StoreTheInfo record);

    int updateByPrimaryKey(StoreTheInfo record);
    
    /**
     * 根据条件查询 场站信息
     * @param stationVo
     * @return
     */
    List<StoreTheInfo> queryStoreTheInfoByCondition(StoreTheInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param stationVo
     * @return
     */
    Long queryStoreTheInfoCountByCondition(StoreTheInfo param);
    
    /**
     * 根据明确的条件（不支持模糊查询）查询 场站信息
     * @param stationVo
     * @return
     */
    List<StoreTheInfo> queryStoreTheInfoByExplicitCondition(StoreTheInfo param,RowBounds rowBounds);
    int repealAllStoreTheInfo();
}