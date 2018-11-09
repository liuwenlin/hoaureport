package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.FleetAreaInfo;
@Repository
public interface FleetAreaInfoMapper {
    int deleteByPrimaryKey(Long fleetAreaId);

    int insert(FleetAreaInfo record);

    int insertSelective(FleetAreaInfo record);

    FleetAreaInfo selectByPrimaryKey(Long fleetAreaId);

    int updateByPrimaryKeySelective(FleetAreaInfo record);

    int updateByPrimaryKey(FleetAreaInfo record);
    
    /**
     * 
     * @Description:根据条件查询 车队辖区信息
     * @param 车队辖区实例
     * @param 行限制实例
     * @return List<FleetAreaInfo> 
     * @author 文洁
     * @date 2016年8月18日
     */
    List<FleetAreaInfo> queryFleetAreaInfoByCondition(FleetAreaInfo param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 车队辖区实例
     * @return Long 
     * @author 文洁
     * @date 2016年8月18日
     */
    Long queryFleetAreaCountByCondition(FleetAreaInfo param);
    int repealAllFleetArea();
}