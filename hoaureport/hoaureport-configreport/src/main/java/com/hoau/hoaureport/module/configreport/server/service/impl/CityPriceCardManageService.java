package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.hoau.hoaureport.module.configreport.server.dao.CityPriceCardMapper;
import com.hoau.hoaureport.module.configreport.server.service.ICityPriceCardManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.CityPriceCard;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 城市价卡管理服务实现类
 * ClassName: CityPriceCardManageService 
 * @author 文洁
 * @date 2016年12月15日
 * @version V1.0
 */
@Service
public class CityPriceCardManageService implements ICityPriceCardManageService{
	private static final Logger log = LoggerFactory.getLogger(CityPriceCard.class);
	/**是否标识（用于表示 是否提供定日达等 确定提供的标识）*/
	private static final String CONFRIM_FLAG  = "是";
	/**对错标识（用于表示 星期1到星期日 勾选的标识）*/
	private static final String TRUE_FLAG  = "1"; 
	@Resource
	CityPriceCardMapper cityPriceCardMapper;
	
	/**
	 * 查询城市价卡
	 */
	@Override
	public List<CityPriceCard> queryCityPriceCard(CityPriceCard param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return cityPriceCardMapper.queryCityPriceCardByCondition(param, rowBounds);
	}

	/**
	 * 查询明确的城市价卡
	 */
	@Override
	public List<CityPriceCard> queryCityPriceCardByExplicitCondition(CityPriceCard param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return cityPriceCardMapper.queryCityPriceCardByExplicitCondition(param, rowBounds);
	}
	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryCityPriceCardCount(CityPriceCard param) {
		return cityPriceCardMapper.queryCityPriceCardCountByCondition(param);
	}

	/**
	 * 新增城市价卡
	 */
	@Transactional
	@Override
	public void addCityPriceCard(CityPriceCard record) {
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
		cityPriceCardMapper.insertSelective(record);
	}

	/**
	 * 在原纪录上更新城市价卡
	 */
	@Transactional
	@Override
	public void updateCityPriceCard(CityPriceCard record) {
		cityPriceCardMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddCityPriceCard(CityPriceCard param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealCityPriceCard(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addCityPriceCard(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealCityPriceCard(CityPriceCard param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		CityPriceCard oldInfo = new CityPriceCard();
		oldInfo.setCityPriceCardId(param.getCityPriceCardId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateCityPriceCard(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(CityPriceCard param) {
		//设置判断为已存在的条件
		CityPriceCard exitCondition = new CityPriceCard();
		exitCondition.setDispatchCity(param.getDispatchCity());
		exitCondition.setArrivalCity(param.getArrivalCity());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		
		List<CityPriceCard> CityPriceCards = this.queryCityPriceCardByExplicitCondition(exitCondition,0,10);
		return CityPriceCards.size() > 0;
	}

	@Override
	public Map<String, Object> bathImplCityPriceCard(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<CityPriceCard> cityPriceCards = new ArrayList<CityPriceCard>();
	//匹配城市编码 最长10位数字
	  Pattern pCcode = Pattern.compile("^\\d{0,10}");
	//匹配通知承诺时间 和 通知送货时间 非负整数 最长10位
	  Pattern pTime = Pattern.compile("0|([1-9][0-9]{0,9})");
	//匹配开通月份  201601
	  Pattern pMonth = Pattern.compile("^\\d{6}");
	  if(list.size() > 0){
		  Map<String, String> map = null;
		  for(int i = 0;i<list.size();i++){
			  map = list.get(i); 
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(0+"")))){
				  retuMap.put("error", "第"+(++i)+"行,线路不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(1+"")))){
				  retuMap.put("error", "第"+(++i)+"行,起运地不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(2+"")))){
				  retuMap.put("error", "第"+(++i)+"行,目的地不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(3+"")))){
				  retuMap.put("error", "第"+(++i)+"行,城市线路不能为空，为空项请用‘—’填充!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(4+"")))){
				  retuMap.put("error", "第"+(++i)+"行,发货城市不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(5+"")))){
				  retuMap.put("error", "第"+(++i)+"行,发货城市编码不能为空!");
				  return retuMap;
			  }
			  if(!pCcode.matcher(StringUtil.trim(map.get(5+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,发货城市编码只能是不超过10位的数字,请修改后重新导入!");
					return retuMap;
			    }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(6+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到货城市不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(7+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到货城市编码不能为空!");
					return retuMap;
			  }
			  if(!pCcode.matcher(StringUtil.trim(map.get(7+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,到货城市编码只能是不超过10位的数字,请修改后重新导入!");
					return retuMap;
			    }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(8+"")))){
				  retuMap.put("error", "第"+(++i)+"行,发货是否提供定日达不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(9+"")))){
				  retuMap.put("error", "第"+(++i)+"行,发货是否偏远网点不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(10+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到货是否提供定日达不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(11+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到货是否偏远网点不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(12+"")))){
				  retuMap.put("error", "第"+(++i)+"行,通知承诺时间不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(13+"")))){
				  retuMap.put("error", "第"+(++i)+"行,送货承诺时间不能为空!");
					return retuMap;
			  }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(12+"")))){
				  retuMap.put("error", "第"+(++i)+"行,通知承诺时间不能为空!");
					return retuMap;
			  }
			  if(!pTime.matcher(StringUtil.trim(map.get(12+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,通知承诺时间只能为不超过10位的非负整数,请修改后重新导入!");
					return retuMap;
			    }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(13+"")))){
				  retuMap.put("error", "第"+(++i)+"行,送货承诺时间不能为空!");
					return retuMap;
			  }
			  if(!pTime.matcher(StringUtil.trim(map.get(13+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,送货承诺时间只能为不超过10位的非负整数,请修改后重新导入!");
					return retuMap;
			    }
			  if(StringUtil.isEmpty(StringUtil.trim(map.get(14+"")))){
				  retuMap.put("error", "第"+(++i)+"行,开通月份不能为空!");
					return retuMap;
			  }
			  if(!pMonth.matcher(StringUtil.trim(map.get(14+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,开通月份格式须为“yyyymm”，如“201601”,请修改后重新导入!");
					return retuMap;
			    }
		  } 
	  }
	for (Map<String, String> map : list) {
		CityPriceCard bean = new CityPriceCard();
		try {		
			//线路
			bean.setLineName(StringUtil.trim(map.get(0+"")));
			//起运地
			bean.setLoadingPort(StringUtil.trim(map.get(1+"")));
			//目的地
			bean.setTermini(StringUtil.trim(map.get(2+"")));
			//城市线路
			bean.setCityLine(StringUtil.trim(map.get(3+"")));
			//发货城市
			bean.setDispatchCity(StringUtil.trim(map.get(4+"")));
			//发货城市编码
			bean.setDispatchCityId(StringUtil.trim(map.get(5+"")));
			//到货城市
			bean.setArrivalCity(StringUtil.trim(map.get(6+"")));
			//到货城市编码
			bean.setArrivalCityId(StringUtil.trim(map.get(7+"")));
			//发货是否提供定日达
			if(StringUtil.isEmpty(StringUtil.trim(map.get(8+"")))){
				bean.setServeLoadIntime("");
			}else {
			bean.setServeLoadIntime(StringUtil.equals(CONFRIM_FLAG,StringUtil.trim(map.get(8+"")))?"1":"0");
			}
			//发货是否偏远网点
			if(StringUtil.isEmpty(StringUtil.trim(map.get(9+"")))){
				bean.setFromRemoteBranch("");
			}else {
			bean.setFromRemoteBranch(StringUtil.equals(CONFRIM_FLAG,StringUtil.trim(map.get(9+"")))?"1":"0");
			}
			//到货是否提供定日达
			if(StringUtil.isEmpty(StringUtil.trim(map.get(10+"")))){
				bean.setServeReachIntime("");
			}else {
			bean.setServeReachIntime(StringUtil.equals(CONFRIM_FLAG,StringUtil.trim(map.get(10+"")))?"1":"0");
			}
			//到货是否偏远网点
			if(StringUtil.isEmpty(StringUtil.trim(map.get(11+"")))){
				bean.setToRemoteBranch("");
			}else {
			bean.setToRemoteBranch(StringUtil.equals(CONFRIM_FLAG,StringUtil.trim(map.get(11+"")))?"1":"0");
			}
			//通知承诺时间
			try {
				bean.setPromiseNoteTime(new BigDecimal(StringUtil.trim(map.get(12+""))));
			} catch (NumberFormatException e) {
				log.error("承诺时间必须为数字", e);
			}catch (Exception e) {
				log.error("承诺时间转换出错", e);
			}
			//送货承诺时间
			try {
				bean.setPromiseDeliverTime(new BigDecimal(StringUtil.trim(map.get(13+""))));
			} catch (NumberFormatException e) {
				log.error("送货承诺时间必须为数字", e);
			}catch (Exception e) {
				log.error("送货承诺时间转换出错", e);
			}
			//开通月份;
			bean.setOpenMonth(StringUtil.trim(map.get(14+"")));
			//星期一
			if(StringUtil.isEmpty(StringUtil.trim(map.get(15+"")))){
				bean.setMonday("0");
			}else {
			bean.setMonday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(15+"")))?"1":"0");
			}
			//星期二
			if(StringUtil.isEmpty(StringUtil.trim(map.get(16+"")))){
				bean.setTuesday("0");
			}else {
			bean.setTuesday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(16+"")))?"1":"0");
			}
			//星期三
			if(StringUtil.isEmpty(StringUtil.trim(map.get(17+"")))){
				bean.setWednesday("0");
			}else {
			bean.setWednesday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(17+"")))?"1":"0");
			}
			//星期四
			if(StringUtil.isEmpty(StringUtil.trim(map.get(18+"")))){
				bean.setThursday("0");
			}else {
			bean.setThursday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(18+"")))?"1":"0");
			}
			//星期五
			if(StringUtil.isEmpty(StringUtil.trim(map.get(19+"")))){
				bean.setFriday("0");
			}else {
			bean.setFriday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(19+"")))?"1":"0");
			}
			//星期六
			if(StringUtil.isEmpty(StringUtil.trim(map.get(20+"")))){
				bean.setSaturday("0");
			}else {
			bean.setSaturday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(20+"")))?"1":"0");
			}
			//星期日
			if(StringUtil.isEmpty(StringUtil.trim(map.get(21+"")))){
				bean.setSunday("0");
			}else {
			bean.setSunday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(21+"")))?"1":"0");
			}
			//备注
			bean.setNote(StringUtil.trim(map.get(22+"")));
		} catch (Exception e) {
			bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
			log.error("批量导入模版数据异常，业务需要仅作提示", e);
		} finally {
			cityPriceCards.add(bean);
		}
	}
	//设置增加条数,覆盖条数
	Map<String, Long> countMap = new HashMap<String, Long>();
	countMap.put("addSize", new Long(0));// 增加条数
	countMap.put("coverSize", new Long(0));// 覆盖条数
	Long beforAddSize=null;
	Long beforCoverSize=null;
	for (int i = 0; i < cityPriceCards.size(); i++) {
		CityPriceCard info = cityPriceCards.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdateCityPriceCard(info, countMap);
			}
		} catch (Exception e) {
			log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
			countMap.put("addSize", beforAddSize);
			countMap.put("coverSize", beforCoverSize);
		}
	}
	
	retuMap.put("addSize", countMap.get("addSize"));
	retuMap.put("coverSize", countMap.get("coverSize"));
	retuMap.put("sumSize", cityPriceCards.size());
	retuMap.put("error", "");
	return retuMap;
	}

  
	/**
	 * 
	 * @Description: 新增或覆盖城市价卡数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年12月15日
	 */
	@Override
	public void addOrUpdateCityPriceCard(CityPriceCard info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		CityPriceCard queryParam = new CityPriceCard();
		queryParam.setDispatchCity(info.getDispatchCity());
		queryParam.setArrivalCity(info.getArrivalCity());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<CityPriceCard> infoList = this.queryCityPriceCardByExplicitCondition(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			CityPriceCard updateParam = new CityPriceCard();
			updateParam.setImportTime(new Date());
			updateParam.setModifyTime(new Date());
			updateParam.setModifyUserCode(user.getEmpCode().substring(2));
			updateParam.setCityPriceCardId(infoList.get(0).getCityPriceCardId());
			

			updateParam.setLineName(info.getLineName());
			updateParam.setLoadingPort(info.getLoadingPort());
			updateParam.setTermini(info.getTermini());
			updateParam.setCityLine(info.getCityLine());
			
			//发货城市和到货城市没有改变时，对应编号也不更改
			//updateParam.setDispatchCityId(info.getDispatchCityId());
			//updateParam.setArrivalCityId(info.getArrivalCityId());
			
			updateParam.setServeLoadIntime(info.getServeLoadIntime());
			updateParam.setFromRemoteBranch(info.getFromRemoteBranch());
			updateParam.setServeReachIntime(info.getServeReachIntime());
			updateParam.setToRemoteBranch(info.getToRemoteBranch());
			updateParam.setPromiseNoteTime(info.getPromiseNoteTime());
			updateParam.setPromiseDeliverTime(info.getPromiseDeliverTime());
			updateParam.setOpenMonth(info.getOpenMonth());
			updateParam.setMonday(info.getMonday());
			updateParam.setTuesday(info.getTuesday());
			updateParam.setWednesday(info.getWednesday());
			updateParam.setThursday(info.getThursday());
			updateParam.setFriday(info.getFriday());
			updateParam.setSaturday(info.getSaturday());
			updateParam.setSunday(info.getSunday());
			updateParam.setNote(info.getNote());
			
			this.updateCityPriceCard(updateParam);
			//覆盖+1
			coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addCityPriceCard(info);
			//新增+1
			addSize++;
		}
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}

	@Override
	public void repealAllCityPriceCard() {
		cityPriceCardMapper.repealAllCityPriceCard();
	}
}
