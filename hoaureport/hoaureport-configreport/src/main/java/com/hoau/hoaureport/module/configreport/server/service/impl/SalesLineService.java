package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.hoau.hoaureport.module.configreport.server.dao.SalesLineMapper;
import com.hoau.hoaureport.module.configreport.server.service.ISalesLineService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.SalesLine;
import com.hoau.hoaureport.module.util.DateUtils;
/**
 * 销售线路
 * ClassName: SalesLineService 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
@Service
public class SalesLineService implements ISalesLineService{
	private static final Logger log = LoggerFactory.getLogger(SalesLineService.class);
	@Resource
	SalesLineMapper salesLineMapper;
	/**
	 * 查询销售线路
	 * @return
	 */
	public List<SalesLine> querySalesLineInfo(SalesLine param,int start,int limit){
		RowBounds rowBounds = new RowBounds(start,limit);
	return 	salesLineMapper.querySalesLineByCondition(param,rowBounds);
	}
	/***
	 * 查询销售线路数量
	 */
	public Long querySalesLineCount(SalesLine param){
		return salesLineMapper.querySalesLineCountByCondition(param);
	}
	
	@Transactional
	@Override
	public void addSalesLine(SalesLine info) {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//工号
		String currUserCode = currUser.getEmpCode().substring(2);
		info.setActive("Y");
		info.setEffectedTime(new Date());
		info.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		info.setCreateTime(new Date());
		info.setCreateUserCode(currUserCode);
		info.setModifyTime(new Date());
		info.setModifyUserCode(currUserCode);
		salesLineMapper.insertSelective(info);
	}
	@Transactional
	@Override
	public void updateSalesLineInfo(SalesLine info) {
		salesLineMapper.updateByPrimaryKeySelective(info);
	}
	
	@Transactional
	@Override
	public void repealAndAddInfo(SalesLine param, String empCode) {
		SalesLine oldInfo = new SalesLine();
		oldInfo.setSlId(param.getSlId());
		oldInfo.setActive("N");
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setModifyUserCode(empCode);
		updateSalesLineInfo(oldInfo);
		//另外插入修改数据
		SalesLine newInfo = new SalesLine();
		newInfo.setLineNum(param.getLineNum());
		newInfo.setNote(param.getNote());
		newInfo.setSalesLineName(param.getSalesLineName());
		newInfo.setLoadingPort(param.getLoadingPort());
		newInfo.setLoadingPortId(param.getLoadingPortId());
		newInfo.setTransitDepot1(param.getTransitDepot1());
		newInfo.setTransitDepot2(param.getTransitDepot2());
		newInfo.setTermint(param.getTermint());
		newInfo.setTermintId(param.getTermintId());
		newInfo.setRely1Class(param.getRely1Class());
		newInfo.setRely2Class(param.getRely2Class());
		newInfo.setRely3Class(param.getRely3Class());
		newInfo.setLine1(param.getLine1());
		newInfo.setLine2(param.getLine2());
		newInfo.setLine3(param.getLine3());
		newInfo.setNoTransferArrival(param.getNoTransferArrival());
		newInfo.setOneTransferArrival(param.getOneTransferArrival());
		newInfo.setTwoTransferArrival(param.getTwoTransferArrival());
		newInfo.setBreakOneDayTimes(param.getBreakOneDayTimes());
		newInfo.setBreakTwoDaysTimes(param.getBreakTwoDaysTimes());
		newInfo.setRelyLine1(param.getRelyLine1());
		newInfo.setRelyLine2(param.getRelyLine2());
		newInfo.setRelyLine3(param.getRelyLine3());
		newInfo.setDispatchDayAndNextDayNum(param.getDispatchDayAndNextDayNum());
		newInfo.setLoadingPortFirstLevelDispa(param.getLoadingPortFirstLevelDispa());
		newInfo.setTransitDepot1DispatchTime(param.getTransitDepot1DispatchTime());
		newInfo.setTransitDepot2DispatchTime(param.getTransitDepot2DispatchTime());
		newInfo.setTransitDepot1ArraivalTime(param.getTransitDepot1ArraivalTime());
		newInfo.setTransitDepot2ArraivalTime(param.getTransitDepot2ArraivalTime());
		newInfo.setTermitFirstArrivalTime(param.getTermitFirstArrivalTime());
		newInfo.setTransitHours1(param.getTransitHours1());
		newInfo.setTransitHours2(param.getTransitHours2());
		newInfo.setTransitHours3(param.getTransitHours3());
		newInfo.setBreakHours1(param.getBreakHours1());
		newInfo.setBreakHours2(param.getBreakHours2());
		newInfo.setHoursInTransit(param.getHoursInTransit());
		newInfo.setUnloadHours(param.getUnloadHours());
		newInfo.setTerminalShortLongLines(param.getTerminalShortLongLines());
		newInfo.setArrival0And1(param.getArrival0And1());
		newInfo.setOpenMonth(param.getOpenMonth());
		newInfo.setLineStatus(param.getLineStatus());
		newInfo.setPickupDays(param.getPickupDays());
		newInfo.setDeliverDays(param.getDeliverDays());
		newInfo.setMonday(param.getMonday());
		newInfo.setTuesday(param.getTuesday());
		newInfo.setWednesday(param.getWednesday());
		newInfo.setThusday(param.getThusday());
		newInfo.setFriday(param.getFriday());
		newInfo.setSaturday(param.getSaturday());
		newInfo.setSunday(param.getSunday());
		newInfo.setTimelyNote(param.getTimelyNote());
		newInfo.setOpenLineYear(param.getOpenLineYear());
		newInfo.setIsOpenGolden100Lines(param.getIsOpenGolden100Lines());
		newInfo.setStandardNote(param.getStandardNote());
		newInfo.setLinePartner(param.getLinePartner());
		newInfo.setActive("Y");
		newInfo.setEffectedTime(new Date());
		newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		newInfo.setCreateTime(new Date());
		newInfo.setCreateUserCode(empCode);//工号
		newInfo.setModifyTime(new Date());
		newInfo.setModifyUserCode(empCode);
		addSalesLine(newInfo);
	}
	/**
	 * 判断是否已存在
	 */
	@Override
	public boolean isExist(SalesLine param) {
		//设置判断为已存在的条件
		SalesLine exitCondition = new SalesLine();
		exitCondition.setSalesLineName(param.getSalesLineName());
		exitCondition.setLoadingPort(param.getLoadingPort());
		exitCondition.setTermint(param.getTermint());
		//不考虑已作废记录
		exitCondition.setActive("Y");
		List<SalesLine> SalesLineInfos = querySalesLineInfo(exitCondition,0,10);
		return SalesLineInfos.size() > 0;
	}
	
	@Transactional
	@Override
	public Map<String, Object> bathImplPlatformAreaInfo(String path)
			throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		//导入结果
		Map<String,Object> retuMap =new HashMap<String, Object>();
		//解析Excel
		List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 70, 2);
		//规范时间 00:00:00
		Pattern pTime = Pattern.compile("^(0\\d{1}|\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$");
		//规范时间 00:00
		//Pattern pTime = Pattern.compile("^(0\\d{1}|\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}$");
		//把键值对 转换成 对象集合
		List<SalesLine> SalesLines = new ArrayList<SalesLine>();
		  if(list.size() > 0){
			  Map<String, String> map = null;
			  for(int i = 0;i<list.size();i++){
				  map = list.get(i); 
				  if(
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(2+"")))  ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(3+"")))  ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(4+"")))  ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(7+"")))  ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(8+"")))  ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(23+""))) ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(24+""))) ||
						  !StringUtil.isNotEmpty(StringUtil.trim(map.get(38+""))) 
					){
					  retuMap.put("error", "第"+(++i)+"行,某必填项为空!");
						return retuMap;
				  }
			  } 
		  }
			Calendar calendar = Calendar.getInstance();
			String[] arr = null;
		for (Map<String, String> map : list) {
			SalesLine bean = new SalesLine();
			try {
				bean.setLineNum(StringUtil.trim(map.get(0+"")));			
				bean.setNote(StringUtil.trim(map.get(1+"")));
				bean.setSalesLineName(StringUtil.trim(map.get(2+"")));
				bean.setLoadingPort(StringUtil.trim(map.get(3+"")));
				bean.setLoadingPortId(StringUtil.trim(map.get(4+"")));			
				bean.setTransitDepot1(StringUtil.trim(map.get(5+"")));
				bean.setTransitDepot2(StringUtil.trim(map.get(6+"")));
				bean.setTermint(StringUtil.trim(map.get(7+"")));
				bean.setTermintId(StringUtil.trim(map.get(8+"")));			
				bean.setRely1Class(StringUtil.trim(map.get(9+"")));
				bean.setRely2Class(StringUtil.trim(map.get(10+"")));
				bean.setRely3Class(StringUtil.trim(map.get(11+"")));
				bean.setLine1(StringUtil.trim(map.get(12+"")));			
				bean.setLine2(StringUtil.trim(map.get(13+"")));
				bean.setLine3(StringUtil.trim(map.get(14+"")));
				String s15 = StringUtil.trim(map.get(15+""));
				if(s15!=null && pTime.matcher(s15).matches()){
					arr = s15.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setNoTransferArrival(calendar.getTime());
				}
				String s16 = StringUtil.trim(map.get(16+""));
				if(s16!=null && pTime.matcher(s16).matches()){
					arr = s16.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setOneTransferArrival(calendar.getTime());
				}
				String s17 = StringUtil.trim(map.get(17+""));
				if(s17!=null && pTime.matcher(s17).matches()){
					arr = s17.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTwoTransferArrival(calendar.getTime());
				}
				bean.setBreakOneDayTimes(StringUtil.trim(map.get(18+"")));
				bean.setBreakTwoDaysTimes(StringUtil.trim(map.get(19+"")));
				bean.setRelyLine1(StringUtil.trim(map.get(20+"")));			
				bean.setRelyLine2(StringUtil.trim(map.get(21+"")));
				bean.setRelyLine3(StringUtil.trim(map.get(22+"")));
				bean.setDispatchDayAndNextDayNum(StringUtil.trim(map.get(23+"")));
				String s24 = StringUtil.trim(map.get(24+""));
				if(s24!=null && pTime.matcher(s24).matches()){
					arr = s24.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setLoadingPortFirstLevelDispa(calendar.getTime());	
				}
				String s25 = StringUtil.trim(map.get(25+""));
				if(s25!=null && pTime.matcher(s25).matches()){
					arr = s25.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTransitDepot1DispatchTime(calendar.getTime());
				}
				String s26 = StringUtil.trim(map.get(26+""));
				if(s26!=null && pTime.matcher(s26).matches()){
					arr = s26.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTransitDepot2DispatchTime(calendar.getTime());
				}
				String s27 = StringUtil.trim(map.get(27+""));
				if(s27!=null && pTime.matcher(s27).matches()){
					arr = s27.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTransitDepot1ArraivalTime(calendar.getTime());
				}
				String s28 = StringUtil.trim(map.get(28+""));
				if(s28!=null && pTime.matcher(s28).matches()){
					arr = s28.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTransitDepot2ArraivalTime(calendar.getTime());
				}
				String s29 = StringUtil.trim(map.get(29+""));
				if(s29!=null && pTime.matcher(s29).matches()){
					arr = s29.split(":");
					calendar.set(1970, 1,0, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),0);
					bean.setTermitFirstArrivalTime(calendar.getTime());
				}
				if(StringUtil.trim(map.get(30+""))!=null){
					bean.setTransitHours1(new BigDecimal(StringUtil.trim(map.get(30+""))));
				}
				if(StringUtil.trim(map.get(31+""))!=null){
					bean.setTransitHours2(new BigDecimal(StringUtil.trim(map.get(31+""))));
				}
				if(StringUtil.trim(map.get(32+""))!=null){
					bean.setTransitHours3(new BigDecimal(StringUtil.trim(map.get(32+""))));
				}
				if(StringUtil.trim(map.get(33+""))!=null){
					bean.setBreakHours1(new BigDecimal(StringUtil.trim(map.get(33+""))));
				}
				if(StringUtil.trim(map.get(34+""))!=null){
					bean.setBreakHours2(new BigDecimal(StringUtil.trim(map.get(34+""))));
				}
				if(StringUtil.trim(map.get(35+""))!=null){
					bean.setHoursInTransit(new BigDecimal(StringUtil.trim(map.get(35+""))));
				}
				if(StringUtil.trim(map.get(36+""))!=null){
					bean.setUnloadHours(new BigDecimal(StringUtil.trim(map.get(36+""))));
				}			
				bean.setTerminalShortLongLines(StringUtil.trim(map.get(37+"")));
				bean.setArrival0And1(StringUtil.trim(map.get(38+"")));
				bean.setOpenMonth(StringUtil.trim(map.get(39+"")));
				bean.setLineStatus(StringUtil.trim(map.get(40+"")));			
				bean.setPickupDays(StringUtil.trim(map.get(41+"")));
				bean.setDeliverDays(StringUtil.trim(map.get(42+"")));
				bean.setMonday(StringUtil.trim(map.get(43+"")));
				bean.setTuesday(StringUtil.trim(map.get(44+"")));
				bean.setWednesday(StringUtil.trim(map.get(45+"")));
				bean.setThusday(StringUtil.trim(map.get(46+"")));			
				bean.setFriday(StringUtil.trim(map.get(47+"")));
				bean.setSaturday(StringUtil.trim(map.get(48+"")));
				bean.setSunday(StringUtil.trim(map.get(49+"")));
				bean.setTimelyNote(StringUtil.trim(map.get(50+"")));			
				bean.setOpenLineYear(StringUtil.trim(map.get(51+"")));
				bean.setIsOpenGolden100Lines(StringUtil.trim(map.get(52+"")));
				bean.setStandardNote(StringUtil.trim(map.get(53+"")));
				bean.setLinePartner(StringUtil.trim(map.get(54+"")));
				bean.setActive("Y");
				bean.setEffectedTime(new Date());
				bean.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				bean.setCreateTime(new Date());
				bean.setCreateUserCode(user.getEmpCode().substring(2));
				bean.setModifyTime(new Date());
				bean.setModifyUserCode(user.getEmpCode().substring(2));
			} catch (Exception e) {
				bean = null;// 如果有异常就把pcbean设为null,这样每条信息都加进去了
				log.error("批量导入模版数据异常，业务需要仅作提示", e);
			} finally {
				SalesLines.add(bean);
			}
		}
		//设置增加条数,覆盖条数
		Map<String, Long> countMap = new HashMap<String, Long>();
		countMap.put("addSize", new Long(0));// 增加条数
		countMap.put("coverSize", new Long(0));// 覆盖条数
		Long beforAddSize=null;
		Long beforCoverSize=null;
		for (int i = 0; i < SalesLines.size(); i++) {
			SalesLine info = SalesLines.get(i);
			try {
				if (info == null) {// 有异常
					continue;
				} else {
					 beforAddSize = countMap.get("addSize");
					 beforCoverSize = countMap.get("coverSize");
					 addOrUpdateSalesLineInfo(info, countMap);
				}
			} catch (Exception e) {
				log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
				countMap.put("addSize", beforAddSize);
				countMap.put("coverSize", beforCoverSize);
			}
		}
		
		retuMap.put("addSize", countMap.get("addSize"));
		retuMap.put("coverSize", countMap.get("coverSize"));
		retuMap.put("sumSize", SalesLines.size());
		retuMap.put("error", "");
		return retuMap;
	}
	@Transactional
	@Override
	public void addOrUpdateSalesLineInfo(SalesLine info,
			Map<String, Long> countMap) throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		long addSize= countMap.get("addSize").longValue();
		long coverSize= countMap.get("coverSize").longValue();
		//查询历史有效信息
		SalesLine queryParam = new SalesLine();
		queryParam.setSalesLineName(info.getSalesLineName());
		queryParam.setLoadingPort(info.getLoadingPort());
		queryParam.setTermint(info.getTermint());
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);//只判断有效信息
		List<SalesLine> infoList = this.querySalesLineInfo(queryParam, 0, 1);
		if(infoList.size() > 0){//已存在 就直接更新
			//更新数据
			info.setSlId(infoList.get(0).getSlId());
			this.repealAndAddInfo(info,user.getEmpCode().substring(2));
			//覆盖+1
			//coverSize++;
		}else{
			//新增+1
			this.addSalesLine(info);
		}
		//判断无效信息  确保作废信息作废时间 与 导入相同数据行时间衔接
		queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_NO);
		List<SalesLine> invalidInfoList = this.querySalesLineInfo(queryParam, 0, 1);
		SalesLine lastSalesLine = invalidInfoList.get(0);
		if(invalidInfoList.size() > 0){
			for(SalesLine temp : invalidInfoList){
				if(lastSalesLine.getInvalidTime().before(temp.getInvalidTime())){
					lastSalesLine = temp;
				}
			}
			lastSalesLine.setInvalidTime(new Date());
			salesLineMapper.updateByPrimaryKeySelective(lastSalesLine);
		}
		addSize++;
		countMap.put("addSize", addSize);
		countMap.put("coverSize", coverSize);
		
	}
	@Transactional
	@Override
	public void repealAllSalesLine() {
		salesLineMapper.repealAllSalesLine();
	}
}
