package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.configreport.server.dao.SpecialStationInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.ISpecialStationManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.SpecialStationInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 
 *
 * @Descripation:场站特许接口的实现类
 * @author: 梁令
 * @date:2016年11月22日 上午11:09:39
 *
 */
@Service
public class SpecialStationManagerService implements ISpecialStationManageService{
    @Resource
    SpecialStationInfoMapper specialStationInfoMapper;
	
    /**
	 * 查询场站信息
	 * @return
	 */
	public List<SpecialStationInfo> querySpecialStationInfo(
			SpecialStationInfo param, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return specialStationInfoMapper.querySpecialStationInfoByCondition(param,rowBounds);
	}

	/**
	 * 查询场站的总数量
	 */
	public Long querySpecialStationCount(SpecialStationInfo param) {
		return specialStationInfoMapper.querySpecialStationCountByCondition(param);
	}
	
	/**
	 * 新增场站信息
	 */
    @Transactional
	@Override
	public void addSpecialStationInfo(SpecialStationInfo info) {
		 specialStationInfoMapper.insertSelective(info);
		
	}
    
    /**
     * 更新场站信息
     */
    @Transactional
	@Override
	public void updateSpecialStationInfo(SpecialStationInfo info) {
          specialStationInfoMapper.updateByPrimaryKeySelective(info);		
	}
    
    /**
     * 修改即作废原数据，将新数据保存
     */
    @Transactional
	@Override
	public void repealAndAddInfo(SpecialStationInfo param, String empCode) {
		  SpecialStationInfo oldInfo = new SpecialStationInfo();//
		  oldInfo.setSpecialStationsId(param.getSpecialStationsId());//获取选中数据的ID
		  oldInfo.setInvalidTime(new Date());
		  oldInfo.setModifyTime(new Date());
		  oldInfo.setActive("N");
		  oldInfo.setModifyUserCode(empCode);
		  updateSpecialStationInfo(oldInfo);
		  //获取到修改的数据
		  SpecialStationInfo newInfo = new SpecialStationInfo();
		  newInfo.setStationsShortName(param.getStationsShortName());
		  newInfo.setStationsName(param.getStationsName());
		  newInfo.setActive("Y");
		  newInfo.setEffectedTime(new Date());
		  newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		  newInfo.setTheArea(param.getTheArea());
		  newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		  newInfo.setCreateTime(new Date());
		  newInfo.setCreateUserCode(empCode);
		  newInfo.setModifyTime(new Date());
		  newInfo.setModifyUserCode(empCode);
		  //将修改的数据加入数据库
		  addSpecialStationInfo(newInfo);
	}
}
