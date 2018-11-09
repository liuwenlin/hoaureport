package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.SpecialStationInfo;

@Repository
public interface SpecialStationInfoMapper {
    int deleteByPrimaryKey(String specialStationsId);

    int insert(SpecialStationInfo record);

    int insertSelective(SpecialStationInfo record);

    SpecialStationInfo selectByPrimaryKey(String specialStationsId);

    int updateByPrimaryKeySelective(SpecialStationInfo record);

    int updateByPrimaryKey(SpecialStationInfo record);
    /**
     * 根据条件查询 场站(特许)信息
     * @param specialStationVo
     * @return
     */
    List<SpecialStationInfo> querySpecialStationInfoByCondition(SpecialStationInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param specialStationVo
     * @return
     */
    Long querySpecialStationCountByCondition(SpecialStationInfo param);
}