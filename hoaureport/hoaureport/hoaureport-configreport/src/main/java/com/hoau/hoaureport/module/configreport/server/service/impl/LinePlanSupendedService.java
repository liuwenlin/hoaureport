package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctc.wstx.util.DataUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.dao.LinePlanSuspendedMapper;
import com.hoau.hoaureport.module.configreport.server.service.ILinePlanSupendedService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;
import com.hoau.hoaureport.module.configreport.shared.domain.LinePlanSuspended;
import com.hoau.hoaureport.module.util.DateUtils;
import com.sun.tools.apt.Main;


@Service
public class LinePlanSupendedService implements ILinePlanSupendedService {
	private static final Logger log = LoggerFactory.getLogger(LinePlanSupendedService.class);
    /*** 单、对发标识*/
	private static String ONE_AND_MORE_FLAG = "单发";
	/*** 长短线标识*/
	private static final String SHORT_LONG_FLAG = "长线" ;
	/**对错标识（用于表示 星期1到星期日 勾选的标识）*/
	private static final String TRUE_FLAG  = "1"; 
	
	@Resource
    private LinePlanSuspendedMapper linePlanMapper;
	@Override
	public List<LinePlanSuspended> queryLinePlanSupendedInfo(
			LinePlanSuspended param, int start, int limit) {
	   RowBounds rowBounds = new RowBounds(start, limit);
	   return linePlanMapper.queryLinePlanByCondition(param, rowBounds);
	
	}
	/**
	 * 查询线路规划停发时间总记录条数
	 */
	@Override
	public Long queryLinePlanSuspendedCount(LinePlanSuspended param) {
		return linePlanMapper.queryLinePlanCountByCondition(param);
	}
	/**
	 * 新增线路规划停发时间信息
	 */
	@Transactional 
	@Override
	public void addLinePlanSuspendedInfo(LinePlanSuspended info) {
		//当前登录的用户
		UserEntity  currUser = (UserEntity)UserContext.getCurrentUser();
		String currUserCode = currUser.getEmpCode().substring(2);
		info.setActive("Y");
		info.setEffectedTime(new Date());
		info.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
		info.setCreateTime(new Date());
		info.setCreateUserCode(currUserCode);
		info.setModifyTime(new Date());
		info.setModifyUserCode(currUserCode);
		linePlanMapper.insertSelective(info);
	}
	/**
	 * 判断是否存在
	 */
	@Override
	public boolean isExist(LinePlanSuspended param) {
		LinePlanSuspended existCondition = new LinePlanSuspended();
		existCondition.setStartWork(param.getStartWork());//开始运作
		existCondition.setArriveWork(param.getArriveWork());//到达运作
		existCondition.setDepartureTime(param.getDepartureTime());//发车时间
		existCondition.setShifts(param.getShifts());//班次
		existCondition.setActive("Y");
		List<LinePlanSuspended>linSuspendedsinfo = queryLinePlanSupendedInfo(existCondition, 0, 10);
		return linSuspendedsinfo.size()>0;
	}
	/**
	 * 更新线路规划停发时间信息
	 */
	@Transactional
	@Override
	public void updateLinePlanSuspendedInfo(LinePlanSuspended info) {
		linePlanMapper.updateByPrimaryKeySelective(info);
		
	}
	
	/**
     * 修改即作废原数据，将新数据保存
     */
	@Transactional 
	@Override
	public void repealAndAddInfo(LinePlanSuspended param, String empCode) {
		LinePlanSuspended oldInfo = new LinePlanSuspended();
		//作废原数据
		oldInfo.setLinePlanId(param.getLinePlanId());
		oldInfo.setInvalidTime(new Date());
		oldInfo.setModifyTime(new Date());
		oldInfo.setActive("N");
		oldInfo.setModifyUserCode(empCode);
		updateLinePlanSuspendedInfo(oldInfo);
		LinePlanSuspended newInfo = new LinePlanSuspended();
		//将新数据保存
		newInfo.setStartWork(param.getStartWork());
		newInfo.setArriveWork(param.getArriveWork());
		newInfo.setStartGridLines(param.getStartGridLines());
		newInfo.setGridLines(param.getGridLines());
		newInfo.setDepartureType(param.getDepartureType());
		newInfo.setBelongLines(param.getBelongLines());
		newInfo.setLineType(param.getLineType());
		newInfo.setLineQuantity(param.getLineQuantity());
		newInfo.setDepartureFrequency(param.getDepartureFrequency());
		newInfo.setMonday(param.getMonday());
		newInfo.setThursday(param.getThursday());
		newInfo.setWednesday(param.getWednesday());
		newInfo.setTuesday(param.getTuesday());
		newInfo.setFriday(param.getFriday());
		newInfo.setSaturday(param.getSaturday());
		newInfo.setSunday(param.getSunday());
        newInfo.setLineKil(param.getLineKil());
        newInfo.setLineType(param.getLineType());
        newInfo.setShifts(param.getShifts());
        newInfo.setTotalShifts(param.getTotalShifts());
        newInfo.setDepartureTime(param.getDepartureTime());
        newInfo.setTravelTimeOne(param.getTravelTimeOne());
        newInfo.setArrivalTimeOne(param.getArrivalTimeOne());
        newInfo.setDepartureTimeTwo(param.getDepartureTimeTwo());
        newInfo.setTravelTimeTwo(param.getTravelTimeTwo());
        newInfo.setArrivalTimeTwo(param.getArrivalTimeTwo());
        newInfo.setDepartureTimeThr(param.getDepartureTimeThr());
        newInfo.setTravelTimeThr(param.getTravelTimeThr());
        newInfo.setArrivalTime(param.getArrivalTime());
        newInfo.setCarType(param.getCarType());
        newInfo.setTrainNumber(param.getTrainNumber());
        newInfo.setActualNumCars(param.getActualNumCars());
        newInfo.setDemandForCars(param.getDemandForCars());
        newInfo.setHangCarsDemand(param.getHangCarsDemand());
        newInfo.setContractDate(param.getContractDate());
        newInfo.setStopGoGoods(param.getStopGoGoods());
        newInfo.setOppeningTime(param.getOppeningTime());
        newInfo.setRemarks(param.getRemarks());
        newInfo.setActive("Y");
        newInfo.setEffectedTime(new Date());
        newInfo.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
        newInfo.setCreateTime(new Date());
        newInfo.setCreateUserCode(empCode);
        newInfo.setModifyTime(new Date());
        newInfo.setModifyUserCode(empCode);
        addLinePlanSuspendedInfo(newInfo);
	}
	/**
	 * 作废全部
	 */
	@Transactional 
	@Override
	public void repealAll() {
		linePlanMapper.repealAll();
	}
	
	@Transactional
	@Override
	public Map<String, Object> bathImplLinePlanSuspended(String path)
			throws Exception {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		Map<String, Object> retuMap =new HashMap<String, Object>();
		//解析Excel
		List<Map<String, String>> list = ExcelUtil.readToListByFile(path, 50, 2);
		//把键值对 转换成 对象集合
		List<LinePlanSuspended> linePlans = new ArrayList<LinePlanSuspended>();
		if(list.size()>0){
			Map<String, String> map = null;
			for(int i = 0;i<list.size();i++){
				map = list.get(i);
				if(StringUtil.isEmpty(StringUtil.trim(map.get(0+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，出发运作不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(1+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，到达运作不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(2+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，发车线路（原）不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(3+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，发车线路不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(4+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，所属路线不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(5+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，单/对发不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(6+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，线路货量不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(7+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，发车频率不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(15+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，线路公里数不能为空"
							+ "!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(16+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，线路类型不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(17+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，班次不能为空!");
					return retuMap;
				}
				if(StringUtil.isEmpty(StringUtil.trim(map.get(18+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，总班次不能为空!");
					return retuMap;
				}
				//发车时间
				 if(StringUtil.isEmpty(StringUtil.trim(map.get(19+"")))){
						retuMap.put("error", "第"+(++i)+"行,发车时间不能为空，请重新输入!");
						return retuMap;
				    }
				if(StringUtil.isEmpty(StringUtil.trim(map.get(20+"")))){
					retuMap.put("error", "第" + ( ++ i ) + "行，在途时间1(h)不能为空!");
					return retuMap;
				}
			}
		}
		for(Map<String,String> map :list){
			LinePlanSuspended bean = new LinePlanSuspended();
			try {
				//出发运作
				bean.setStartWork(StringUtil.trim(map.get(0+"")));
				//到达运作
				bean.setArriveWork(StringUtil.trim(map.get(1+"")));
				//发车线路(原)
				bean.setStartGridLines(StringUtil.trim(map.get(2+"")));
				//发车线路
				bean.setGridLines(StringUtil.trim(map.get(3+"")));
				//所属路线
				bean.setBelongLines(StringUtil.trim(map.get(4+"")));
				//单/对发
				if(StringUtil.isEmpty(StringUtil.trim(map.get(5+"")))){
					bean.setDepartureType("");
				}else{
			        bean.setDepartureType(StringUtil.equals(ONE_AND_MORE_FLAG, StringUtil.trim(map.get(5+"")))?"1":"0");
				}
				//线路货量
				if( StringUtil.trim(map.get(6+"")) != null){
					bean.setLineQuantity(new BigDecimal(StringUtil.trim(map.get(6+""))));
				}
				//发车频率
				bean.setDepartureFrequency(Integer.parseInt(StringUtil.trim(map.get(7+""))));
				//周一
				if(StringUtil.isEmpty(StringUtil.trim(map.get(8+"")))){
					bean.setMonday("0");
				}else{
					bean.setMonday(StringUtil.equals(TRUE_FLAG, StringUtil.trim(map.get(8+"")))?"1":"0");
				}
				
				//周二
				if(StringUtil.isEmpty(StringUtil.trim(map.get(9+"")))){
					bean.setTuesday("0");
				}else{
					bean.setTuesday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(9+"")))?"1":"0");
				}
				//周三
				if(StringUtil.isEmpty(StringUtil.trim(map.get(10+"")))){
					bean.setWednesday("0");
				}else{
					bean.setWednesday(StringUtil.equals(TRUE_FLAG, StringUtil.trim(map.get(10+"")))?"1":"0");
				}
				//周四
				if(StringUtil.isEmpty(StringUtil.trim(map.get(11+"")))){
					bean.setThursday("0");
				}else{
					bean.setThursday(StringUtil.equals(TRUE_FLAG, StringUtil.trim(map.get(11+"")))?"1":"0");
				}
				//周五
				if(StringUtil.isEmpty(StringUtil.trim(map.get(12+"")))){
					bean.setFriday("0");
				}else{
					bean.setFriday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(12+"")))?"1":"0");
				}
				
				//周六
				if(StringUtil.isEmpty(StringUtil.trim(map.get(13+"")))){
					bean.setSaturday("0");
				}else{
					bean.setSaturday(StringUtil.equals(TRUE_FLAG,StringUtil.trim(map.get(13+"")))?"1":"0");
				}
				//周日
				if(StringUtil.isEmpty(StringUtil.trim(map.get(14+"")))){
					bean.setSunday("0");
				}else{
					bean.setSunday(StringUtil.equals(TRUE_FLAG, StringUtil.trim(map.get(14+"")))?"1":"0");
				}
				//线路公里数
				bean.setLineKil(Integer.parseInt(StringUtil.trim(map.get(15+""))));
				//线路类型
				if(StringUtil.isEmpty(StringUtil.trim(map.get(16+"")))){
					bean.setLineType("");
				}else{
					bean.setLineType(StringUtil.equals(SHORT_LONG_FLAG, StringUtil.trim(map.get(16+"")))?"1":"0");
				}
				//班次
				bean.setShifts(Integer.parseInt(StringUtil.trim(map.get(17+""))));
				//总班次
				bean.setTotalShifts(Integer.parseInt(StringUtil.trim(map.get(18+""))));
			    //发车时间 格式 HH:MM:SS
				try {
					 bean.setDepartureTime(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(19+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS",e);
				}
			    //在途时间1
				if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(20+"")))){
					bean.setTravelTimeOne(null);
				}else{
					bean.setTravelTimeOne(new BigDecimal(StringUtil.trim(map.get(20+""))));
				}
				//到达时间1
				try {
					bean.setArrivalTimeOne(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(21+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS!",e);
					
				}
				//发车时间2
				try {
					bean.setDepartureTimeTwo(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(22+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS!",e);
					
				}
				//在途时间2
				if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(23+"")))){
					bean.setTravelTimeTwo(null);
				}else{
					bean.setTravelTimeTwo(new BigDecimal(StringUtil.trim(map.get(23+""))));
				}
				//到达时间2
				try {
					bean.setArrivalTimeTwo(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(24+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS!",e);
				}
				//发车时间3
				try {
					bean.setDepartureTimeThr(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(25+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS!",e);
				}
				//在途时间3
				if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(26+"")))){
					bean.setTravelTimeThr(null);
				}else{
					bean.setTravelTimeThr(new BigDecimal(StringUtil.trim(map.get(26+""))));
				}
				//到达时间
				try {
					bean.setArrivalTime(DateUtils.strToDate("2016-1-1 "+StringUtil.trim(map.get(27+""))));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("时间格式只能是 HH:MM:SS!");
				}
				//车型
				bean.setCarType(StringUtil.trim(map.get(28+"")));
				//月承诺趟数
				bean.setTrainNumber(StringUtil.trim(map.get(29+"")));
				//实际车辆数
				bean.setActualNumCars(StringUtil.trim(map.get(30+"")));
				//修去车辆数
				bean.setDemandForCars(StringUtil.trim(map.get(31+"")));
				//甩挂车辆需求
				bean.setHangCarsDemand(StringUtil.trim(map.get(32+"")));
				//合同结束日期
				 if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(33+"")))){
				    	bean.setContractDate(null);
				    }else{
				    	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
						Date date = format.parse(StringUtil.trim(map.get(33+"")));
				    	bean.setContractDate(date);
				    }
				//停发走货规定
				bean.setStopGoGoods(StringUtil.trim(map.get(34+"")));
				//开通时间
			    if(!StringUtil.isNotEmpty(StringUtil.trim(map.get(35+"")))){
			    	bean.setOppeningTime(null);
			    }else{
			    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
			    	Date date1 = format1.parse(StringUtil.trim(map.get(35+"")));
			    	bean.setOppeningTime(date1);
			    }
				//备注
				bean.setRemarks(StringUtil.trim(map.get(36+"")));
				//是否有效
				bean.setActive("Y");
				//生效时间
				bean.setEffectedTime(new Date());
				//失效时间
				bean.setInvalidTime(DateUtils.strToDate("2999-1-1 00:00:00"));
				//创建时间
				bean.setCreateTime(new Date());
				//创建人工号
				bean.setCreateUserCode(user.getEmpCode().substring(2));
				//修改时间
				bean.setModifyTime(new Date());
				//修改人工号
				bean.setModifyUserCode(user.getEmpCode().substring(2));
			} catch (Exception e) {
				bean = null;
				log.error("批量导入模版数据异常，业务需要仅作提示", e);
			}finally{
				linePlans.add(bean);
			}
		}
				
	
		/**
		 * 增加 条数和覆盖条数
		 */
		Map<String,Long> countMap = new HashMap<String, Long>();
		countMap.put("addSize", new Long(0));
		countMap.put("coverSize", new Long(0));
		Long beforAddsize = null;
		Long beforCoverSize = null;
		for(int i = 0;i<linePlans.size(); i++){
			LinePlanSuspended info = linePlans.get(i);
			try {
				if(info == null){
					continue;
				}else{
					beforAddsize = countMap.get("addSize");
					beforCoverSize = countMap.get("coverSize");
					addOrUpdateLinePlanSuspended(info, countMap);
				}
			} catch (Exception e) {
				log.error("批量导入模版数据更新操作异常，业务需要仅作提示", e);
				countMap.put("addSize", beforAddsize);
				countMap.put("coverSize", beforCoverSize);
			}
		}
		retuMap.put("addSize", countMap.get("addSize"));
		retuMap.put("coverSize",countMap.get("coverSize"));
		retuMap.put("sumSize", linePlans.size());
		retuMap.put("error","");
		return retuMap;
	}
	
	@Transactional
	@Override
	public void addOrUpdateLinePlanSuspended(LinePlanSuspended info,
			Map<String, Long> countMap) throws Exception {
		  UserEntity user = (UserEntity)(UserContext.getCurrentUser());
		  long addSize = countMap.get("addSize").longValue();
		  long coverSize = countMap.get("coverSize").longValue();
		  LinePlanSuspended queryParam =  new LinePlanSuspended();
		  queryParam.setStartWork(info.getStartWork());//开始运作
		  queryParam.setArriveWork(info.getArriveWork());//到达运作
		  queryParam.setDepartureTime(info.getDepartureTime());//发车时间
		  queryParam.setShifts(info.getShifts());//班次
		  queryParam.setActive(ItfConifgConstant.HAR_ACTIVE_YES);
		  List<LinePlanSuspended> infoList = this.queryLinePlanSupendedInfo(queryParam, 0, 1);
		  //判断是否存在，若存在则更新
		  if(infoList.size() > 0){
			 info.setLinePlanId(infoList.get(0).getLinePlanId());
			 this.repealAndAddInfo(info, user.getEmpCode().substring(2));
			
			 //覆盖+1
			 coverSize++;
		  }else{
			  this.addLinePlanSuspendedInfo(info);
			  //新增+1
			  addSize++;
		  }
		  countMap.put("addSize",addSize);
		  countMap.put("coverSize",coverSize);
	}
}
