package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.configreport.server.dao.PlatformInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IPlatformManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatformInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 
 * ClassName: PlatformManageService 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
@Service
public class PlatformManageService implements IPlatformManageService{
	@Resource
	PlatformInfoMapper platformInfoMapper;
	/**
	 * 查询场站信息
	 * @return
	 */
	public List<PlatformInfo> queryPlatformInfo(PlatformInfo param,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
	return 	platformInfoMapper.queryPlatformInfoByCondition(param,rowBounds);
	}
	/***
	 * 查询场站数量
	 */
	public Long queryPlatformCount(PlatformInfo param){
		return platformInfoMapper.queryPlatformCountByCondition(param);
	}
	
	@Transactional
	@Override
	public void addPlatformInfo(PlatformInfo info) {
		platformInfoMapper.insertSelective(info);
	}
	@Transactional
	@Override
	public void updatePlatformInfo(PlatformInfo info) {
		platformInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	@Transactional
	@Override
	public void repealAndAddInfo(PlatformInfo param, String empCode) {
		
		PlatformInfo oldInfo = new PlatformInfo();
		oldInfo.setPlatformId(param.getPlatformId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updatePlatformInfo(oldInfo);
		//另外插入修改数据
		PlatformInfo newInfo = new PlatformInfo();
		newInfo.setPlatformName(param.getPlatformName());
		newInfo.setPlatformShortName(param.getPlatformShortName());
		newInfo.setIsHeadQuartersPlatform(param.getIsHeadQuartersPlatform());
		newInfo.setTheFleet(param.getTheFleet());
		newInfo.setTheRoadArea(param.getTheRoadArea());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		addPlatformInfo(newInfo);
	}
}
