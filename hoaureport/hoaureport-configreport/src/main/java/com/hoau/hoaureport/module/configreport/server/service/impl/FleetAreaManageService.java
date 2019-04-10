package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.FleetAreaInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IFleetAreaManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.FleetAreaInfo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 车队辖区管理服务实现类
 * ClassName: FleetAreaManageService 
 * @author 文洁
 * @date 2016年8月18日
 * @version V1.0
 */
@Service
public class FleetAreaManageService implements IFleetAreaManageService{
	private static final Logger log = LoggerFactory.getLogger(FleetAreaInfo.class);
	@Resource
	FleetAreaInfoMapper FleetAreaInfoMapper;
	
	/**
	 * 查询车队辖区信息
	 */
	@Override
	public List<FleetAreaInfo> queryFleetAreaInfo(FleetAreaInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return FleetAreaInfoMapper.queryFleetAreaInfoByCondition(param, rowBounds);
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryFleetAreaCount(FleetAreaInfo param) {
		return FleetAreaInfoMapper.queryFleetAreaCountByCondition(param);
	}

	/**
	 * 增加车队辖区
	 */
	@Transactional
	@Override
	public void addFleetAreaInfo(FleetAreaInfo record) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		
		record.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		record.setEffectedTime(new Date());
		record.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		record.setCreateTime(new Date());
		record.setCreateUserCode(currUserCode);
		record.setModifyTime(new Date());
		record.setModifyUserCode(currUserCode);
		FleetAreaInfoMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新车队辖区信息
	 */
	@Transactional
	@Override
	public void updateFleetAreaInfo(FleetAreaInfo record) {
		FleetAreaInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddFleetAreaInfo(FleetAreaInfo param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealFleetAreaInfo(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addFleetAreaInfo(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealFleetAreaInfo(FleetAreaInfo param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		FleetAreaInfo oldInfo = new FleetAreaInfo();
		oldInfo.setFleetAreaId(param.getFleetAreaId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateFleetAreaInfo(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(FleetAreaInfo param) {
		//设置判断为已存在的条件
		FleetAreaInfo exitCondition = new FleetAreaInfo();
		exitCondition.setAreaOperationUnitShortname(param.getAreaOperationUnitShortname());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		List<FleetAreaInfo> fleetAreaInfos = this.queryFleetAreaInfo(exitCondition,0,10);
		return fleetAreaInfos.size() > 0;
	}


	@Override
	public Map<String, Object> bathImplFleetAreaInfo(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<FleetAreaInfo> fleetAreaInfos = new ArrayList<FleetAreaInfo>();

	  if(list.size() > 0){
		  Map<String, String> map = null;
		  for(int i = 0;i<list.size();i++){
			  map = list.get(i); 
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(0+"")))){
				  retuMap.put("error", "第"+(++i)+"行,辖区作业单位简称不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属事业部不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(1+"")))){
				  retuMap.put("error", "第"+(++i)+"行,归属车队名称不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属大区不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			
		  } 
	  }
	for (Map<String, String> map : list) {
		FleetAreaInfo bean = new FleetAreaInfo();
		try {
			bean.setAreaOperationUnitShortname(StringUtil.trim(map.get(0+"")));			
			bean.setTheFleetShortName(StringUtil.trim(map.get(1+"")));
			bean.setTheArea(StringUtil.trim(map.get(2+"")));
			bean.setTheBusinessDepartment(StringUtil.trim(map.get(3+"")));
		} catch (Exception e) {
			bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
			log.error("批量导入模版数据异常，业务需要仅作提示", e);
		} finally {
			fleetAreaInfos.add(bean);
		}
	}
	//设置增加条数,覆盖条数
	Map<String, Long> countMap = new HashMap<String, Long>();
	countMap.put("addSize", new Long(0));// 增加条数
	countMap.put("coverSize", new Long(0));// 覆盖条数
	Long beforAddSize=null;
	Long beforCoverSize=null;
	for (int i = 0; i < fleetAreaInfos.size(); i++) {
		FleetAreaInfo info = fleetAreaInfos.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdateFleetAreaInfo(info, countMap);
			}
		} catch (Exception e) {
			log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
			countMap.put("addSize", beforAddSize);
			countMap.put("coverSize", beforCoverSize);
		}
	}
	
	retuMap.put("addSize", countMap.get("addSize"));
	retuMap.put("coverSize", countMap.get("coverSize"));
	retuMap.put("sumSize", fleetAreaInfos.size());
	retuMap.put("error", "");
	return retuMap;
	}

  
	/**
	 * 
	 * @Description: 新增或覆盖车队辖区信息数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@Override
	public void addOrUpdateFleetAreaInfo(FleetAreaInfo info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		FleetAreaInfo queryParam = new FleetAreaInfo();
		queryParam.setAreaOperationUnitShortname(info.getAreaOperationUnitShortname());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<FleetAreaInfo> infoList = this.queryFleetAreaInfo(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			FleetAreaInfo updateParam = new FleetAreaInfo();
			updateParam.setImportTime(new Date());
			updateParam.setTheFleetShortName(info.getTheFleetShortName());
			updateParam.setTheArea(info.getTheArea());
			updateParam.setTheBusinessDepartment(info.getTheBusinessDepartment());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setFleetAreaId(infoList.get(0).getFleetAreaId());
			this.updateFleetAreaInfo(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addFleetAreaInfo(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}
	@Transactional
	@Override
	public void repealAllFleetArea() {
		FleetAreaInfoMapper.repealAllFleetArea();
	}
}
