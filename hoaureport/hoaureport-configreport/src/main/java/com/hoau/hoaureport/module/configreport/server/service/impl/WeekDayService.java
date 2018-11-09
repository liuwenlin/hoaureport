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
import com.hoau.hoaureport.module.configreport.server.dao.UnitpriceMonitorWeekdayMapper;
import com.hoau.hoaureport.module.configreport.server.service.IWeekDayService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.UnitpriceMonitorWeekday;
@Service
public class WeekDayService implements IWeekDayService{
	
	private static final Logger log = LoggerFactory.getLogger(UnitpriceMonitorWeekday.class);
	
	@Resource
	UnitpriceMonitorWeekdayMapper mapper;
	@Override
	public List<UnitpriceMonitorWeekday> queryWeekDay(UnitpriceMonitorWeekday param,int start,int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return mapper.queryUnitpriceMonitorWeekday(param, rowBounds);
	}
	@Transactional
	@Override
	public void updateWeekDay(UnitpriceMonitorWeekday param) {
		mapper.updateByPrimaryKey(param);
	}
	@Transactional
	@Override
	public void insertWeekDay(UnitpriceMonitorWeekday param) {
		mapper.insertSelective(param);
	}

	@Override
	public Long countByCondition(UnitpriceMonitorWeekday record) {
		return mapper.countByCondition(record);
	}

	@Override
	public Map<String, Object> bathImplWeekDay(String path) throws Exception {
		//导入结果
				Map<String,Object> retuMap =new HashMap<String, Object>();
				//解析Excel
				List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 2, 2);
				//把键值对 转换成 对象集合
				List<UnitpriceMonitorWeekday> tonCostList = new ArrayList<UnitpriceMonitorWeekday>();
				
				for (Map<String, String> map : list) {
					UnitpriceMonitorWeekday bean = new UnitpriceMonitorWeekday();
					try {
						bean.setWeekdayDate(StringUtil.trim(map.get(0+"")));
						bean.setWeekdayDays(StringUtil.trim(map.get(1+"")));
					} catch (Exception e) {
						bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
						log.error("批量导入模版数据异常，业务需要仅作提示", e);
					} finally {
						tonCostList.add(bean);
					}
				}
				//设置增加条数,覆盖条数
				Map<String, Long> countMap = new HashMap<String, Long>();
				countMap.put("addSize", new Long(0));// 增加条数
				countMap.put("coverSize", new Long(0));// 覆盖条数
				Long beforAddSize=null;
				Long beforCoverSize=null;
				for (int i = 0; i < tonCostList.size(); i++) {
					UnitpriceMonitorWeekday info = tonCostList.get(i);
					try {
						if (info == null) {// 有异常
							continue;
						} else {
							 beforAddSize = countMap.get("addSize");
							 beforCoverSize = countMap.get("coverSize");
							this.addOrUpdateNextdayReachRate(info, countMap);
						}
					} catch (Exception e) {
						log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
						countMap.put("addSize", beforAddSize);
						countMap.put("coverSize", beforCoverSize);
					}
				}
				
				retuMap.put("addSize", countMap.get("addSize"));
				retuMap.put("coverSize", countMap.get("coverSize"));
				retuMap.put("sumSize", tonCostList.size());
				retuMap.put("error", "");
				return retuMap;
	}
	
	public void addOrUpdateNextdayReachRate(UnitpriceMonitorWeekday info,Map<String,Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		UnitpriceMonitorWeekday queryParam = new UnitpriceMonitorWeekday();
		queryParam.setWeekdayDate(info.getWeekdayDate());
		List<UnitpriceMonitorWeekday> infoList = this.queryWeekDay(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			UnitpriceMonitorWeekday updateParam = new UnitpriceMonitorWeekday();
			updateParam.setWeekdayDate(info.getWeekdayDate());
			updateParam.setWeekdayDays(info.getWeekdayDays());
			updateParam.setModifyUser(user.getEmpCode().substring(2));
			updateParam.setModifyTime(new Date());
			this.updateWeekDay(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setModifyUser(user.getEmpCode().substring(2));
			info.setModifyTime(new Date());
			this.insertWeekDay(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
	}
}
