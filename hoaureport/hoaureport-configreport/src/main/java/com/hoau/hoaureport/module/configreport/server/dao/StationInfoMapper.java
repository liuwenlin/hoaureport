package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.StationInfo;

@Repository
public interface StationInfoMapper {
    int deleteByPrimaryKey(String stationsId);

    int insert(StationInfo record);

    int insertSelective(StationInfo record);

    StationInfo selectByPrimaryKey(String stationsId);

    int updateByPrimaryKeySelective(StationInfo record);

    int updateByPrimaryKey(StationInfo record);
    /**
     * 根据条件查询 场站信息
     * @param stationVo
     * @return
     */
    List<StationInfo> queryStationInfoByCondition(StationInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param stationVo
     * @return
     */
    Long queryStationCountByCondition(StationInfo param);
}