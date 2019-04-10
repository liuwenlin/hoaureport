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
import com.hoau.hoaureport.module.configreport.server.dao.PlatformAreaInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IPlatformAreaManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatformAreaInfo;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 
 * ClassName: PlatformAreaManageService 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
@Service
public class PlatformAreaManageService implements IPlatformAreaManageService{
	private static final Logger log = LoggerFactory.getLogger(PlatformAreaInfo.class);
	@Resource
	PlatformAreaInfoMapper platformAreaInfoMapper;
	/**
	 * 查询平台辖区信息
	 * @return
	 */
	public List<PlatformAreaInfo> queryPlatformAreaInfo(PlatformAreaInfo param,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
	return 	platformAreaInfoMapper.queryPlatformAreaInfoByCondition(param,rowBounds);
	}
	/***
	 * 查询平台辖区数量
	 */
	public Long queryPlatformAreaCount(PlatformAreaInfo param){
		return platformAreaInfoMapper.queryPlatformAreaCountByCondition(param);
	}
	
	@Transactional
	@Override
	public void addPlatformAreaInfo(PlatformAreaInfo info) {
		platformAreaInfoMapper.insertSelective(info);
	}
	@Transactional
	@Override
	public void updatePlatformAreaInfo(PlatformAreaInfo info) {
		platformAreaInfoMapper.updateByPrimaryKeySelective(info);
	}
	
	@Transactional
	@Override
	public void repealAndAddInfo(PlatformAreaInfo param, String empCode) {
		
		PlatformAreaInfo oldInfo = new PlatformAreaInfo();
		oldInfo.setPlatformAreaId(param.getPlatformAreaId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updatePlatformAreaInfo(oldInfo);
		//另外插入修改数据
		PlatformAreaInfo newInfo = new PlatformAreaInfo();
		newInfo.setAreaOperatingUnitShortName(param.getAreaOperatingUnitShortName());
		newInfo.setThePlatFormAreaShortName(param.getThePlatFormAreaShortName());
		newInfo.setTheArea(param.getTheArea());
		newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		addPlatformAreaInfo(newInfo);
	}
	
	@Override
	public Map<String, Object> bathImplPlatformAreaInfo(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<PlatformAreaInfo> platformAreaInfos = new ArrayList<PlatformAreaInfo>();

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
				  retuMap.put("error", "第"+(++i)+"行,归属平台辖区简称不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属大区不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			
		  } 
	  }
	for (Map<String, String> map : list) {
		PlatformAreaInfo bean = new PlatformAreaInfo();
		try {
			bean.setAreaOperatingUnitShortName(StringUtil.trim(map.get(0+"")));			
			bean.setThePlatFormAreaShortName(StringUtil.trim(map.get(1+"")));
			bean.setTheArea(StringUtil.trim(map.get(2+"")));
			bean.setTheBusinessDepartment(StringUtil.trim(map.get(3+"")));
		} catch (Exception e) {
			bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
			log.error("批量导入模版数据异常，业务需要仅作提示", e);
		} finally {
			platformAreaInfos.add(bean);
		}
	}
	//设置增加条数,覆盖条数
	Map<String, Long> countMap = new HashMap<String, Long>();
	countMap.put("addSize", new Long(0));// 增加条数
	countMap.put("coverSize", new Long(0));// 覆盖条数
	Long beforAddSize=null;
	Long beforCoverSize=null;
	for (int i = 0; i < platformAreaInfos.size(); i++) {
		PlatformAreaInfo info = platformAreaInfos.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdatePlatformAreaInfo(info, countMap);
			}
		} catch (Exception e) {
			log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
			countMap.put("addSize", beforAddSize);
			countMap.put("coverSize", beforCoverSize);
		}
	}
	
	retuMap.put("addSize", countMap.get("addSize"));
	retuMap.put("coverSize", countMap.get("coverSize"));
	retuMap.put("sumSize", platformAreaInfos.size());
	retuMap.put("error", "");
	return retuMap;
	}

  
	/**
	 * 
	 * @Description: 新增或覆盖平台辖区信息数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@Override
	public void addOrUpdatePlatformAreaInfo(PlatformAreaInfo info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		PlatformAreaInfo queryParam = new PlatformAreaInfo();
		queryParam.setAreaOperatingUnitShortName(info.getAreaOperatingUnitShortName());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<PlatformAreaInfo> infoList = this.queryPlatformAreaInfo(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			PlatformAreaInfo updateParam = new PlatformAreaInfo();
			updateParam.setImportTime(new Date());
			updateParam.setThePlatFormAreaShortName(info.getThePlatFormAreaShortName());
			updateParam.setTheArea(info.getTheArea());
			updateParam.setTheBusinessDepartment(info.getTheBusinessDepartment());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setPlatformAreaId(infoList.get(0).getPlatformAreaId());
			this.updatePlatformAreaInfo(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			info.setActive("Y");
			info.setEffectedTime(new Date());
			info.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
			info.setCreateTime(new Date());
			info.setCreateUserCode(user.getEmpCode().substring(2));//工号
			info.setModifyTime(new Date());
			info.setModifyUserCode(user.getEmpCode().substring(2));
			this.addPlatformAreaInfo(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}
	@Transactional
	@Override
	public void repealAllPlatformArea() {
		platformAreaInfoMapper.repealAllPlatformArea();
	}
}
