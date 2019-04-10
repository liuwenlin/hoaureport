package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetInfo;
/**
 * 车队
 * ClassName: FleetInfoMapper 
 * @author 刘镇�?
 * @date 2016�?�?7�?
 * @version V1.0
 */
@Repository
public interface FleetInfoMapper {
    int deleteByPrimaryKey(Long fleetId);

	int insert(FleetInfo record);

	int insertSelective(FleetInfo record);

	FleetInfo selectByPrimaryKey(Long fleetId);

	int updateByPrimaryKeySelective(FleetInfo record);

	int updateByPrimaryKey(FleetInfo record);

	int deleteByPrimaryKey(String fleetId);
    /**
     * 根据条件查询 场站信息
     * @param FleetVo
     * @return
     */
    List<FleetInfo> queryFleetInfoByCondition(FleetInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param FleetVo
     * @return
     */
    Long queryFleetCountByCondition(FleetInfo param);
}