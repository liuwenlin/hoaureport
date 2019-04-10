package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.configreport.server.dao.StationAgingInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IStationAgingManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationAgingInfo;
import com.hoau.hoaureport.module.util.DateUtils;

/** 
 *@Description:场站辖区(时效)接口 实现类
 *@author : 梁令
 *@date 创建时间：2017年1月6日 下午3:49:00 
 */
@Service
public class StationAgingManageService implements IStationAgingManageService {
	
	@Resource 
	StationAgingInfoMapper stationAgingInfoMapper;
	
	/**
	 * 查询场站辖区(时效)信息
	 */
    @Override
	public List<StationAgingInfo> queryStationAgingInfo(StationAgingInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return stationAgingInfoMapper.queryStationAgingInfoByCondition(param, rowBounds);
	}
    /**
     * 查询总的记录条数
     */
	@Override
	public Long queryStationAgingInfoCount(StationAgingInfo param) {
		return stationAgingInfoMapper.queryStationAgingCountByCondition(param);
	}
    /**
     * 新增场站辖区(时效)信息
     * @param info
     */
	@Transactional
	@Override
	public void addStationAgingInfo(StationAgingInfo info) {
          stationAgingInfoMapper.insertSelective(info);
	}
    /**
     * 更新场站辖区(时效)信息
     * @param info
     */
	@Transactional
	@Override
	public void updateStationAgingInfo(StationAgingInfo info) {
		stationAgingInfoMapper.updateByPrimaryKeySelective(info);
		
	}
    
	/**
	 * 作废原数据，将修改的数据保存
	 * @param param
	 * @param empCode
	 */
	@Transactional
	@Override
	public void repealAndAddInfo(StationAgingInfo param, String empCode) {
		//作废原数据
		StationAgingInfo oldInfo = new StationAgingInfo();
		oldInfo.setStationAgingId(param.getStationAgingId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updateStationAgingInfo(oldInfo);
		
		//插入修改后的数据
		StationAgingInfo newInfo =  new StationAgingInfo();
		newInfo.setOperatingUnitCode(param.getOperatingUnitCode());
		newInfo.setShortName(param.getShortName());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartmemt(param.getTheBusinessDepartmemt());
		newInfo.setActive("Y");//设置是否有效
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		addStationAgingInfo(newInfo);
				
	}
}
