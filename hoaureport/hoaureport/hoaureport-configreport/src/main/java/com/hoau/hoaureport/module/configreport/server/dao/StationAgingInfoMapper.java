package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAgingInfo;

public interface StationAgingInfoMapper {
    int deleteByPrimaryKey(Long StationAgingId);

    int insert(StationAgingInfo record);

    int insertSelective(StationAgingInfo record);

    StationAgingInfo selectByPrimaryKey(Long StationAgingId);

    int updateByPrimaryKeySelective(StationAgingInfo record);

    int updateByPrimaryKey(StationAgingInfo record);
   /**
    * 根据条件查询场站辖区(时效)信息
    * @param param
    * @param rowBounds
    * @return
    */
    List<StationAgingInfo> queryStationAgingInfoByCondition(StationAgingInfo param,RowBounds rowBounds);
    /**
     * 查询场站辖区(时效)总的记录条数
     * @param param
     * @return
     */
    Long queryStationAgingCountByCondition(StationAgingInfo param);
}