package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAreaInfo;

/**
 * 场站辖区DAO
 * ClassName: StationAreaInfoMapper 
 * @author 文洁
 * @date 2016年8月17日
 * @version V1.0
 */
@Repository
public interface StationAreaInfoMapper {
    int deleteByPrimaryKey(Long stationAreaId);

    int insert(StationAreaInfo record);

    int insertSelective(StationAreaInfo record);

    StationAreaInfo selectByPrimaryKey(Long stationAreaId);

    int updateByPrimaryKeySelective(StationAreaInfo record);

    int updateByPrimaryKey(StationAreaInfo record);
    
    /**
     * 
     * @Description:根据条件查询 场站辖区信息
     * @param 场站辖区实例
     * @param 行限制实例
     * @return List<StationAreaInfo> 
     * @author 文洁
     * @date 2016年8月17日
     */
    List<StationAreaInfo> queryStationAreaInfoByCondition(StationAreaInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param 场站辖区实例
     * @return
     */
    Long queryStationAreaCountByCondition(StationAreaInfo param);
    int repealAllStationArea();
}