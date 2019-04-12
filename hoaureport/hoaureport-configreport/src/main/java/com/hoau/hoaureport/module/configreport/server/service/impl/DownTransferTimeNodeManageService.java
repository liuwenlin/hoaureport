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
import com.hoau.hoaureport.module.configreport.server.dao.DownTransferTimeNodeMapper;
import com.hoau.hoaureport.module.configreport.server.service.IDownTransferTimeNodeManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferTimeNode;
import com.hoau.hoaureport.module.util.DateUtils;

/**
 * 上转移时间节点管理服务实现类
 * ClassName: DownTransferTimeNodeManageService 
 * @author 文洁
 * @date 2016年10月31
 * @version V1.0
 */
@Service
public class DownTransferTimeNodeManageService implements IDownTransferTimeNodeManageService{

	private static final Logger log = LoggerFactory.getLogger(DownTransferTimeNode.class);
	@Resource
	DownTransferTimeNodeMapper downTransferTimeNodeMapper;
	
	/**
	 * 查询上转移时间节点信息
	 */
	@Override
	public List<DownTransferTimeNode> queryDownTransferTimeNode(DownTransferTimeNode param,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return downTransferTimeNodeMapper.queryDownTransferTimeNodeByCondition(param, rowBounds);
	}

	/**
	 * 查询记录总数
	 */
	@Override
	public Long queryDownTransferTimeNodeCount(DownTransferTimeNode param) {
		return downTransferTimeNodeMapper.queryDownTransferTimeNodeCountByCondition(param);
	}

	/**
	 * 增加上转移时间节点
	 */
	@Transactional
	@Override
	public void addDownTransferTimeNode(DownTransferTimeNode record) {
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
		downTransferTimeNodeMapper.insertSelective(record);
	}

	/**
	 * 在原纪录下更新上转移时间节点信息
	 */
	@Transactional
	@Override
	public void updateDownTransferTimeNode(DownTransferTimeNode record) {
		downTransferTimeNodeMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 原纪录作废，更新后的记录作为新数据插入
	 */
	@Transactional
	@Override
	public void repealAndAddDownTransferTimeNode(DownTransferTimeNode param) {
		//原纪录作废，并取得修改者编号
		String modifier = repealDownTransferTimeNode(param);
		
		//另外插入修改数据
		param.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		param.setEffectedTime(new Date());
		param.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		param.setCreateTime(new Date());
		param.setCreateUserCode(modifier);
		param.setModifyTime(new Date());
		param.setModifyUserCode(modifier);
		
		addDownTransferTimeNode(param);
		
	}

	/**
	 * 原记录作废,返回操作者编号
	 */
	@Override
	public String repealDownTransferTimeNode(DownTransferTimeNode param) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		DownTransferTimeNode oldInfo = new DownTransferTimeNode();
		oldInfo.setuId(param.getuId());
		oldInfo.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(currUserCode);
		
		updateDownTransferTimeNode(oldInfo);
		return currUserCode;
	}

	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(DownTransferTimeNode param) {
		//设置判断为已存在的条件
		DownTransferTimeNode exitCondition = new DownTransferTimeNode();
		exitCondition.setDepartureDepartment(param.getDepartureDepartment());
		exitCondition.setArrivalDepartment(param.getArrivalDepartment());
		//不考虑已作废记录
		exitCondition.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
				
		List<DownTransferTimeNode> downTransferTimeNodes = this.queryDownTransferTimeNode(exitCondition,0,10);
		return downTransferTimeNodes.size() > 0;
	}
	
	@Override
	public Map<String, Object> bathImplDownTransferTimeNode(String path)throws Exception {
	//导入结果
	Map<String,Object> retuMap =new HashMap<String, Object>();
	//解析Excel
	List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 32, 2);
	//把键值对 转换成 对象集合
	List<DownTransferTimeNode> stationAreaInfos = new ArrayList<DownTransferTimeNode>();

	//匹配发车和到达时间
	  Pattern pTime = Pattern.compile("^(0\\d{1}|\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$");
	  
	  if(list.size() > 0){
		  Map<String, String> map = null;
		  for(int i = 0;i<list.size();i++){
			  map = list.get(i); 
			 
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(4+"")))){
				  retuMap.put("error", "第"+(++i)+"行,出发部门不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(5+"")))){
				  retuMap.put("error", "第"+(++i)+"行,次日（1）or当日（0）不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(6+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到达部门不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(7+"")))){
				  retuMap.put("error", "第"+(++i)+"行,发车时间不能为空!");
					return retuMap;
			  }
			  if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(9+"")))){
				  retuMap.put("error", "第"+(++i)+"行,到达时间不能为空!");
					return retuMap;
			  }
			  if(!pTime.matcher(StringUtil.trim(map.get(7+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,发车时间格式有错误,格式只能是'HH:mm:ss',请修改后重新导入!");
					return retuMap;
				}
			  if(!pTime.matcher(StringUtil.trim(map.get(9+""))).matches()){
					retuMap.put("error", "第"+(++i)+"行,到达时间格式有错误,格式只能是'HH:mm:ss',请修改后重新导入!");
					return retuMap;
				}
			  
		  } 
	  }
	for (Map<String, String> map : list) {
		DownTransferTimeNode bean = new DownTransferTimeNode();
		try {
			//大区
			bean.setTheArea(StringUtil.trim(map.get(0+"")));
			//所属路区
			bean.setTheRoadArea(StringUtil.trim(map.get(1+"")));
			//上转/下转
			if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))){
				bean.setUpOrDownTransfer("1");
			}else {
				bean.setUpOrDownTransfer(StringUtil.equals("上转", StringUtil.trim(map.get(2+"")))?"0":"1");
			}
			//是否串线
			if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))){
				bean.setIsLineCrossed("");
			}else {
			bean.setIsLineCrossed(StringUtil.equals("是",StringUtil.trim(map.get(3+"")))?"Y":"N");
			}
			//出发部门
			bean.setDepartureDepartment(StringUtil.trim(map.get(4+"")));
			//次日（1）or当日（0）
			bean.setTodayOrNextDay(StringUtil.equals("1",StringUtil.trim(map.get(5+"")))?"1":"0");
			//到达部门
			bean.setArrivalDepartment(StringUtil.trim(map.get(6+"")));
			//发车时间 格式只能是"HH:mm:ss"
			try {
				bean.setDispatchTime(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(7+""))));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("时间 格式只能是'HH:mm:ss'", e);
			}
			//在途时长（分钟）
			if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(8+"")))){
				bean.setIntransitMinutes("0");
			}else {
			bean.setIntransitMinutes(StringUtil.trim(map.get(8+"")));
			}
			//到达时间
			try {
				bean.setArrivalTime(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(9+""))));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("时间 格式只能是'HH:mm:ss'", e);
			}
			//串线线路
			bean.setCrossedLine(StringUtil.trim(map.get(10+"")));
			//公里数
			if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(11+"")))){
				bean.setKilometers("0");
			}else {
				bean.setKilometers(StringUtil.trim(map.get(11+"")));
			}
			//班次
			bean.setClassNum(StringUtil.trim(map.get(12+"")));
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
		DownTransferTimeNode info = stationAreaInfos.get(i);
		try {
			if (info == null) {// 有异常
				continue;
			} else {
				 beforAddSize = countMap.get("addSize");
				 beforCoverSize = countMap.get("coverSize");
				this.addOrUpdateDownTransferTimeNode(info, countMap);
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
	 * @Description: 新增或覆盖上转移时间节点信息数据
	 * @param pcbean
	 * @param countMap
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@Override
	public void addOrUpdateDownTransferTimeNode(DownTransferTimeNode info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		DownTransferTimeNode queryParam = new DownTransferTimeNode();
		queryParam.setDepartureDepartment(info.getDepartureDepartment());
		queryParam.setArrivalDepartment(info.getArrivalDepartment());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<DownTransferTimeNode> infoList = this.queryDownTransferTimeNode(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			info.setuId(infoList.get(0).getuId());
			this.repealAndAddDownTransferTimeNode(info);
			//覆盖+1
			//coverSize++;
		}else{
			//插入数据
			info.setImportTime(new Date());
			this.addDownTransferTimeNode(info);
			//新增+1
		}
		addSize++;
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}
	
}
