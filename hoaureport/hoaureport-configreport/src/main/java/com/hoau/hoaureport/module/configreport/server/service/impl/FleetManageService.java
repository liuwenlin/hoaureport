package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.configreport.server.dao.FleetInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IFleetManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.FleetInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 
 * ClassName: FleetManageService 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
@Service
public class FleetManageService implements IFleetManageService{
	@Resource
	FleetInfoMapper fleetInfoMapper;
	/**
	 * 查询场站信息
	 * @return
	 */
	public List<FleetInfo> queryFleetInfo(FleetInfo param,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
	return 	fleetInfoMapper.queryFleetInfoByCondition(param,rowBounds);
	}
	/***
	 * 查询场站数量
	 */
	public Long queryFleetCount(FleetInfo param){
		return fleetInfoMapper.queryFleetCountByCondition(param);
	}
	
	@Transactional
	@Override
	public void addFleetInfo(FleetInfo info) {
		fleetInfoMapper.insertSelective(info);
	}
	@Transactional
	@Override
	public void updateFleetInfo(FleetInfo info) {
		fleetInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	@Transactional
	@Override
	public void repealAndAddInfo(FleetInfo param, String empCode) {
		
		FleetInfo oldInfo = new FleetInfo();
		oldInfo.setFleetId(param.getFleetId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updateFleetInfo(oldInfo);
		//另外插入修改数据
		FleetInfo newInfo = new FleetInfo();
		newInfo.setFleetName(param.getFleetName());
		newInfo.setFleetShortName(param.getFleetShortName());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		addFleetInfo(newInfo);
	}
}
