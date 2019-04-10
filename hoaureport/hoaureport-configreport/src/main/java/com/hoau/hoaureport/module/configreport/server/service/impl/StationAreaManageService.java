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
import com.hoau.hoaureport.module.configreport.server.dao.StationAreaInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IStationAreaManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.StationAreaInfo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 场站辖区管理服务实现类
 * ClassName: StationAreaManageService 
 * @author 文洁
 * @date 2016年8月17日
 * @version V1.0
 */
@Service
public class StationAreaManageService implements IStationAreaManageService{

	private static final Logger log = LoggerFactory.getLogger(StationAreaInfo.class);
	@Resource
	StationAreaInfoMapper stationAreaInfoMapper;
	
	/**
	 * 查询场站辖区信息
	 */
	@Override
	public List<StationAreaInfo> queryStationAreaInfo(StationAreaInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return stationAreaInfoMapper.queryStationAreaInfoByCondition(param, rowBounds);
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryStationAreaCount(StationAreaInfo param) {
		return stationAreaInfoMapper.queryStationAreaCountByCondition(param);
	}

	/**
	 * 增加场站辖区
	 */
	@Transactional
	@Override
	public void addStationAreaInfo(StationAreaInfo record) {
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
		stationAreaInfoMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新场站辖区信息
	 */
	@Transactional
	@Override
	public void updateStationAreaInfo(StationAreaInfo record) {
		stationAreaInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddStationAreaInfo(StationAreaInfo param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealStationAreaInfo(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addStationAreaInfo(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealStationAreaInfo(StationAreaInfo param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		StationAreaInfo oldInfo = new StationAreaInfo();
		oldInfo.setStationAreaId(param.getStationAreaId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateStationAreaInfo(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(StationAreaInfo param) {
		//设置判断为已存在的条件
		StationAreaInfo exitCondition = new StationAreaInfo();
		exitCondition.setOperationUnitCode(param.getOperationUnitCode());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				
		List<StationAreaInfo> stationAreaInfos = this.queryStationAreaInfo(exitCondition,0,10);
		return stationAreaInfos.size() > 0;
	}

	@Override
	public Map<String, Object> bathImplStationAreaInfo(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<StationAreaInfo> stationAreaInfos = new ArrayList<StationAreaInfo>();
	  if(list.size() > 0){
		  Map<String, String> map = null;
		  for(int i = 0;i<list.size();i++){
			  map = list.get(i); 
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(0+"")))){
				  retuMap.put("error", "第"+(++i)+"行,作业单位代码不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属事业部不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(1+"")))){
				  retuMap.put("error", "第"+(++i)+"行,归属场站简称不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属大区不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			
		  } 
	  }
	for (Map<String, String> map : list) {
		StationAreaInfo bean = new StationAreaInfo();
		try {
			bean.setOperationUnitCode(StringUtil.trim(map.get(0+"")));			
			bean.setTheStationShortName(StringUtil.trim(map.get(1+"")));
			bean.setTheArea(StringUtil.trim(map.get(2+"")));
			bean.setTheBusinessDepartment(StringUtil.trim(map.get(3+"")));
		} catch (Exception e) {
			bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
			log.error("批量导入模版数据异常，业务需要仅作提示", e);
		} finally {
			stationAreaInfos.add(bean);
		}
	}
	//设置增加条数,覆盖条数
	Map<String, Long> countMap = new HashMap<String, Long>();
	countMap.put("addSize", new Long(0));// 增加条数
	countMap.put("coverSize", new Long(0));// 覆盖条数
	Long beforAddSize=null;
	Long beforCoverSize=null;
	for (int i = 0; i < stationAreaInfos.size(); i++) {
		StationAreaInfo info = stationAreaInfos.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdateStationAreaInfo(info, countMap);
			}
		} catch (Exception e) {
			log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
			countMap.put("addSize", beforAddSize);
			countMap.put("coverSize", beforCoverSize);
		}
	}
	
	retuMap.put("addSize", countMap.get("addSize"));
	retuMap.put("coverSize", countMap.get("coverSize"));
	retuMap.put("sumSize", stationAreaInfos.size());
	retuMap.put("error", "");
	return retuMap;
	}

  
	/**
	 * 
	 * @Description: 新增或覆盖场站辖区信息数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@Override
	public void addOrUpdateStationAreaInfo(StationAreaInfo info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		StationAreaInfo queryParam = new StationAreaInfo();
		queryParam.setOperationUnitCode(info.getOperationUnitCode());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<StationAreaInfo> infoList = this.queryStationAreaInfo(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			StationAreaInfo updateParam = new StationAreaInfo();
			updateParam.setImportTime(new Date());
			updateParam.setTheStationShortName(info.getTheStationShortName());
			updateParam.setTheArea(info.getTheArea());
			updateParam.setTheBusinessDepartment(info.getTheBusinessDepartment());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setStationAreaId(infoList.get(0).getStationAreaId());
			this.updateStationAreaInfo(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addStationAreaInfo(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}

	@Transactional
	@Override
	public void repealAllStationArea() {
		stationAreaInfoMapper.repealAllStationArea();
	}
}
