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
import com.hoau.hoaureport.module.configreport.server.dao.StoreTheInfoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IStoreTheInfoManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.StoreTheInfo;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 门店信息管理服务实现类
 * ClassName: StoreTheInfoManageService 
 * @author 文洁
 * @date 2016年9月12日
 * @version V1.0
 */
@Service
public class StoreTheInfoManageService implements IStoreTheInfoManageService{
	private static final Logger log = LoggerFactory.getLogger(StoreTheInfo.class);
	@Resource
	StoreTheInfoMapper storeTheInfoMapper;
	
	/**
	 * 查询门店信息
	 */
	@Override
	public List<StoreTheInfo> queryStoreTheInfo(StoreTheInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return storeTheInfoMapper.queryStoreTheInfoByCondition(param, rowBounds);
	}

	/**
	 * 查询明确的门店信息
	 */
	@Override
	public List<StoreTheInfo> queryStoreTheInfoByExplicitCondition(StoreTheInfo param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return storeTheInfoMapper.queryStoreTheInfoByExplicitCondition(param, rowBounds);
	}
	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryStoreTheInfoCount(StoreTheInfo param) {
		return storeTheInfoMapper.queryStoreTheInfoCountByCondition(param);
	}

	/**
	 * 增加门店
	 */
	@Transactional
	@Override
	public void addStoreTheInfo(StoreTheInfo record) {
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
		storeTheInfoMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新门店信息
	 */
	@Transactional
	@Override
	public void updateStoreTheInfo(StoreTheInfo record) {
		storeTheInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddStoreTheInfo(StoreTheInfo param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealStoreTheInfo(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addStoreTheInfo(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealStoreTheInfo(StoreTheInfo param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		StoreTheInfo oldInfo = new StoreTheInfo();
		oldInfo.setStoreId(param.getStoreId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateStoreTheInfo(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(StoreTheInfo param) {
		//设置判断为已存在的条件
		StoreTheInfo exitCondition = new StoreTheInfo();
		exitCondition.setStoreCode(param.getStoreCode());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		
		List<StoreTheInfo> StoreTheInfos = this.queryStoreTheInfoByExplicitCondition(exitCondition,0,10);
		return StoreTheInfos.size() > 0;
	}

	@Override
	public Map<String, Object> bathImplStoreTheInfo(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<StoreTheInfo> storeTheInfos = new ArrayList<StoreTheInfo>();

	  if(list.size() > 0){
		  Map<String, String> map = null;
		  for(int i = 0;i<list.size();i++){
			  map = list.get(i); 
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(0+"")))){
				  retuMap.put("error", "第"+(++i)+"行,门店代码不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(1+"")))){
				  retuMap.put("error", "第"+(++i)+"行,门店名称不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属路区不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属大区不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(4+"")))){
				  retuMap.put("error", "第"+(++i)+"行,所属事业部不能为空!");
					return retuMap;
			  }
			  
		  } 
	  }
	for (Map<String, String> map : list) {
		StoreTheInfo bean = new StoreTheInfo();
		try {
			bean.setStoreCode(StringUtil.trim(map.get(0+"")));			
			bean.setStoreName(StringUtil.trim(map.get(1+"")));
			bean.setTheRoadArea(StringUtil.trim(map.get(2+"")));
			bean.setTheArea(StringUtil.trim(map.get(3+"")));
			bean.setTheBusinessDepartment(StringUtil.trim(map.get(4+"")));
		} catch (Exception e) {
			bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
			log.error("批量导入模版数据异常，业务需要仅作提示", e);
		} finally {
			storeTheInfos.add(bean);
		}
	}
	//设置增加条数,覆盖条数
	Map<String, Long> countMap = new HashMap<String, Long>();
	countMap.put("addSize", new Long(0));// 增加条数
	countMap.put("coverSize", new Long(0));// 覆盖条数
	Long beforAddSize=null;
	Long beforCoverSize=null;
	for (int i = 0; i < storeTheInfos.size(); i++) {
		StoreTheInfo info = storeTheInfos.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdateStoreTheInfo(info, countMap);
			}
		} catch (Exception e) {
			log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
			countMap.put("addSize", beforAddSize);
			countMap.put("coverSize", beforCoverSize);
		}
	}
	
	retuMap.put("addSize", countMap.get("addSize"));
	retuMap.put("coverSize", countMap.get("coverSize"));
	retuMap.put("sumSize", storeTheInfos.size());
	retuMap.put("error", "");
	return retuMap;
	}

  
	/**
	 * 
	 * @Description: 新增或覆盖门店所属信息数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年10月24日
	 */
	@Override
	public void addOrUpdateStoreTheInfo(StoreTheInfo info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		StoreTheInfo queryParam = new StoreTheInfo();
		queryParam.setStoreCode(info.getStoreCode());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<StoreTheInfo> infoList = this.queryStoreTheInfoByExplicitCondition(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			StoreTheInfo updateParam = new StoreTheInfo();
			updateParam.setImportTime(new Date());
			updateParam.setStoreName(info.getStoreName());
			updateParam.setTheRoadArea(info.getTheRoadArea());
			updateParam.setTheArea(info.getTheArea());
			updateParam.setTheBusinessDepartment(info.getTheBusinessDepartment());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setStoreId(infoList.get(0).getStoreId());
			this.updateStoreTheInfo(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addStoreTheInfo(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}

	@Override
	public void repealAllStoreTheInfo() {
		storeTheInfoMapper.repealAllStoreTheInfo();
	}
}
