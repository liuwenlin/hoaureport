package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.StationTimeInfo;

/** 
 *@Description:场站（时效） mapper
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
public interface StationTimeInfoMapper {
	 int deleteByPrimaryKey(Long stationTimeId);

	    int insert(StationTimeInfo record);

	    int insertSelective(StationTimeInfo record);

	    StationTimeInfo selectByPrimaryKey(Long stationTimeId);

	    int updateByPrimaryKeySelective(StationTimeInfo record);

	    int updateByPrimaryKey(StationTimeInfo record);
	    /**
	     * 根据条件查询场站(时效)信息
	     * @param param
	     * @param rowBounds
	     * @return
	     */
	    List<StationTimeInfo> queryStationTimeInfoByCondition(StationTimeInfo param,RowBounds rowBounds);
	    /**
	     * 查询场站(时效)总的记录条数
	     * @param param
	     * @return
	     */
	    Long queryStationAgingCountByCondition(StationTimeInfo param);
	}

