package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.configreport.server.dao.StationInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IStationManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 
 * @author 刘镇松
 *
 */
@Service
public class StationManageService implements IStationManageService{
	@Resource
	StationInfoMapper stationInfoMapper;
	/**
	 * 查询场站信息
	 * @return
	 */
	public List<StationInfo> queryStationInfo(StationInfo param,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
	return 	stationInfoMapper.queryStationInfoByCondition(param,rowBounds);
	}
	/***
	 * 查询场站数量
	 */
	public Long queryStationCount(StationInfo param){
		return stationInfoMapper.queryStationCountByCondition(param);
	}
	
	@Transactional
	@Override
	public void addStationInfo(StationInfo info) {
		stationInfoMapper.insertSelective(info);
	}
	@Transactional
	@Override
	public void updateStationInfo(StationInfo info) {
		stationInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	@Transactional
	@Override
	public void repealAndAddInfo(StationInfo param, String empCode) {
		//作废原数据
		StationInfo oldInfo = new StationInfo();
		oldInfo.setStationsId(param.getStationsId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updateStationInfo(oldInfo);
		
		//另外插入修改数据
		StationInfo newInfo = new StationInfo();
		newInfo.setStationsName(param.getStationsName());
		newInfo.setStationsShortName(param.getStationsShortName());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		
		newInfo.setLeader(param.getLeader());
		newInfo.setPhone(param.getPhone());
		newInfo.setAddress(param.getAddress());
		newInfo.setWarehouseArea(param.getWarehouseArea());
		newInfo.setDeliveryArea(param.getDeliveryArea());
		newInfo.setArrivalArea(param.getArrivalArea());
		
		addStationInfo(newInfo);
	}
}
