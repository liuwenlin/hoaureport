package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
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
import com.hoau.hoaureport.module.configreport.server.dao.NonCarloadTargetValueMapper;
import com.hoau.hoaureport.module.configreport.server.service.INonCarloadTVService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.NonCarloadTargetValue;
@Service
public class NonCarloadTVService implements INonCarloadTVService{

	private static final Logger log = LoggerFactory.getLogger(NonCarloadTVService.class);
	@Resource
	NonCarloadTargetValueMapper mapper;
	@Override
	public List<NonCarloadTargetValue> queryInfo(NonCarloadTargetValue param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return 	mapper.queryByCondition(param,rowBounds);
	}

	@Override
	public Long queryCount(NonCarloadTargetValue param) {
		return mapper.queryCountByCondition(param);
	}

	@Transactional
	@Override
	public void addInfo(NonCarloadTargetValue info) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		info.setModifyTime(new Date());
		info.setModifyUser(currUserCode);
		mapper.insertSelective(info);
	}

	@Override
	public void updateInfo(NonCarloadTargetValue info) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		info.setModifyTime(new Date());
		info.setModifyUser(currUserCode);
		mapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public boolean isExist(NonCarloadTargetValue param) {
		NonCarloadTargetValue exitCondition = new NonCarloadTargetValue();
		exitCondition.setBusinessUnit(param.getBusinessUnit());
		exitCondition.setRegion(param.getRegion());
		exitCondition.setTimes(param.getTimes());
		List<NonCarloadTargetValue> SalesLineInfos = queryInfo(exitCondition,0,10);
		return SalesLineInfos.size() > 0;
	}

	@Override
	public Map<String, Object> bathImplPlatformAreaInfo(String path)
			throws Exception {
		//导入结果
				Map<String,Object> retuMap =new HashMap<String, Object>();
				//解析Excel
				List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 4, 2);
				//规范时间 00:00:00
				//Pattern pTime = Pattern.compile("^(0\\d{1}|\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$");
				//规范时间 00:00
				//Pattern pTime = Pattern.compile("^(0\\d{1}|\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}$");
				//把键值对 转换成 对象集合
				List<NonCarloadTargetValue> lines = new ArrayList<NonCarloadTargetValue>();
				  if(list.size() > 0){
					  Map<String, String> map = null;
					  for(int i = 0;i<list.size();i++){
						  map = list.get(i); 
						  if(
								  !StringUtil.isNotEmpty(StringUtil.trim(map.get(1+"")))  ||
								  !StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))  ||
								  !StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))  
							){
							  retuMap.put("error", "第"+(++i)+"行,某必填项为空!");
								return retuMap;
						  }
					  } 
				  }
				for (Map<String, String> map : list) {
					NonCarloadTargetValue bean = new NonCarloadTargetValue();
					try {
						bean.setBusinessUnit(StringUtil.trim(map.get(0+"")));
						bean.setRegion(StringUtil.trim(map.get(1+"")));
						bean.setTimes(StringUtil.trim(map.get(2+"")));
						bean.setTargetValue(new BigDecimal(new Double(StringUtil.trim(map.get(3+"")))));
					} catch (Exception e) {
						bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
						log.error("批量导入模版数据异常，业务需要仅作提示", e);
					} finally {
						lines.add(bean);
					}
				}
				//设置增加条数,覆盖条数
				Map<String, Long> countMap = new HashMap<String, Long>();
				countMap.put("addSize", new Long(0));// 增加条数
				countMap.put("coverSize", new Long(0));// 覆盖条数
				Long beforAddSize=null;
				Long beforCoverSize=null;
				for (int i = 0; i < lines.size(); i++) {
					NonCarloadTargetValue info = lines.get(i);
					try {
						if (info == null) {// 有异常
							continue;
						} else {
							 beforAddSize = countMap.get("addSize");
							 beforCoverSize = countMap.get("coverSize");
							 addOrUpdateInfo(info, countMap);
						}
					} catch (Exception e) {
						log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
						countMap.put("addSize", beforAddSize);
						countMap.put("coverSize", beforCoverSize);
					}
				}
				
				retuMap.put("addSize", countMap.get("addSize"));
				retuMap.put("coverSize", countMap.get("coverSize"));
				retuMap.put("sumSize", lines.size());
				retuMap.put("error", "");
				return retuMap;
			
	}

	@Override
	public void addOrUpdateInfo(NonCarloadTargetValue info,
			Map<String, Long> countMap) throws Exception {
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		if(isExist(info)){//已存在 就直接更新
			//更新数据
			updateInfo(info);
			//覆盖+1
			coverSize++;
		}else{
			//新增+1
			addInfo(info);
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}

}
