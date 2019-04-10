package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.configreport.server.dao.StationTimeInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IStationTimeInfoManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationTimeInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/** 
 *@Description:场站（时效） 接口实现类
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
@Service
public class StationTimeInfoManageService implements IStationTimeInfoManageService{
    
	@Resource
	StationTimeInfoMapper stattionTimeInfoMapper;
	
	/**
    * @Title: queryStationTimeInfo 
    * @Description: 查询场站(时效)信息
    * @author 梁令
    * @date 2017年1月11日 下午3:21:00
    */
	@Override
	public List<StationTimeInfo> queryStationTimeInfo(StationTimeInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds();
		return stattionTimeInfoMapper.queryStationTimeInfoByCondition(param, rowBounds);
	}
    /**
     * 
     * @Title: queryStationTimeInfoCount 
     * @Description:  查询场站(时效)总的记录条数
     * @author 梁令
     * @date 2017年1月11日 下午3:21:56
     */
	@Override
	public Long queryStationTimeInfoCount(StationTimeInfo param) {
		
		return null;
	}
    /**
     * 
     * @Title: addStationTimeInfo 
     * @Description: 新增场站(时效)信息
     * @author 梁令
     * @date 2017年1月11日 下午3:22:41
     */
	@Override
	public void addStationTimeInfo(StationTimeInfo info) {
		stattionTimeInfoMapper.insertSelective(info);
		
	}
    /**
     * 
     * @Title: updateStationTimeInfo 
     * @Description: 更新场站-时效信息
     * @author 梁令
     * @date 2017年1月11日 下午3:23:17
     */
	@Override
	public void updateStationTimeInfo(StationTimeInfo info) {
		stattionTimeInfoMapper.updateByPrimaryKeySelective(info);
	
	}
    
	/**
     * @Title: repealAndAddInfo 
     * @Description: 修改(即作废原数据，就修改的数据保存)、作废原数据
     * @author 梁令
     * @date 2017年1月11日 下午3:24:09
     */
	@Override
	public void repealAndAddInfo(StationTimeInfo param, String empCode) {
		//作废原数据
		StationTimeInfo oldInfo = new StationTimeInfo();
		oldInfo.setStationTimeId(param.getStationTimeId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updateStationTimeInfo(oldInfo);
		
		//插入修改数据
		StationTimeInfo newInfo = new StationTimeInfo();
		newInfo.setShortName(param.getShortName());
		newInfo.setStationName(param.getStationName());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);
        newInfo.setModifyTime(new Date());
        newInfo.setModifyUserCode(empCode);
        //插入新数据
        addStationTimeInfo(newInfo);
	}
}
