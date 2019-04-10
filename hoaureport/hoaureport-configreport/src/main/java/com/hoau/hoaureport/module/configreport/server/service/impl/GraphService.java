package com.hoau.hoaureport.module.configreport.server.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.configreport.server.dao.CostInfoDaoMapper;
import com.hoau.hoaureport.module.configreport.server.dao.SmsDataEntityMapper;
import com.hoau.hoaureport.module.configreport.server.dao.TimelinessDaoMapper;
import com.hoau.hoaureport.module.configreport.server.service.IGraphService;
import com.hoau.hoaureport.module.configreport.shared.domain.GraphBaseInfo;
import com.hoau.hoaureport.module.configreport.shared.domain.SmsDataEntity;
import com.hoau.hoaureport.module.configreport.shared.vo.GraphInfoVo;
import com.hoau.hoaureport.module.configreport.shared.vo.PieDataVo;
import com.hoau.hoaureport.module.configreport.shared.vo.SmsDataVo;
/**
 * 图形化界面数据服务接口实现类
 * @author 273503
 *
 */
@Service
public class GraphService implements IGraphService {

	@Autowired
	private CostInfoDaoMapper costInfoDaoMapper;
	@Autowired
	private TimelinessDaoMapper timelinessDaoMapper;
	@Autowired
	private SmsDataEntityMapper smsDataEntityMapper;
	
	@Override
	public GraphInfoVo queryCost(Date costDay) {
		
		if(costDay ==null){
			costDay = new Date();
		}
		costDay = FolderUtils.addDateByday(costDay, -1);
		GraphInfoVo graph = new GraphInfoVo();
		graph.setDepts(new ArrayList<String>());
		graph.setOutvalues(new ArrayList<Double>());
		graph.setTargets(new ArrayList<Double>());
		
		List<GraphBaseInfo> list =costInfoDaoMapper.queryCostList(FolderUtils.dateToStr(costDay, "yyyy-MM-dd"));
		for(int i=0;i<list.size();i++){
			graph.getDepts().add(list.get(i).getDeptName());
			graph.getOutvalues().add(list.get(i).getOutputValue());
			graph.getTargets().add(list.get(i).getTarget());
		}
		GraphBaseInfo info = costInfoDaoMapper.queryCostMaxValue(FolderUtils.dateToStr(costDay, "yyyy-MM-dd"));
		if(info!=null){
			graph.setCmax(info.getCmax());
			graph.setCmin(info.getCmin());
		}
		return graph;
	}
	@Override
	public GraphInfoVo queryNowDay(Date day) {

		if(day ==null){
			day = new Date();
		}
		day = FolderUtils.addDateByday(day, -1);
		GraphInfoVo graph = new GraphInfoVo();
		graph.setDepts(new ArrayList<String>());
		graph.setOutvalues(new ArrayList<Double>());
		graph.setTargets(new ArrayList<Double>());
		
		List<GraphBaseInfo> list =timelinessDaoMapper.queryList(FolderUtils.dateToStr(day, "yyyy-MM-dd"));
		for(int i=0;i<list.size();i++){
			graph.getDepts().add(list.get(i).getDeptName());
			graph.getOutvalues().add(list.get(i).getOutputValue());
			graph.getTargets().add(list.get(i).getTarget());
		}

		return graph;
	}
	@Override
	public GraphInfoVo queryNextDay(Date day) {
		if(day ==null){
			day = new Date();
		}
		day = FolderUtils.addDateByday(day, -1);
		GraphInfoVo graph = new GraphInfoVo();
		graph.setDepts(new ArrayList<String>());
		graph.setOutvalues(new ArrayList<Double>());
		graph.setTargets(new ArrayList<Double>());
		
		List<GraphBaseInfo> list =timelinessDaoMapper.queryNextDayList(FolderUtils.dateToStr(day, "yyyy-MM-dd"));
		for(int i=0;i<list.size();i++){
			graph.getDepts().add(list.get(i).getDeptName());
			graph.getOutvalues().add(list.get(i).getOutputValue());
			graph.getTargets().add(list.get(i).getTarget());
		}

		return graph;
	}
	@Override
	public List<SmsDataEntity> querySmsDataList(SmsDataEntity smsdata,
			Date day) {
		if(smsdata ==null){
			smsdata =new SmsDataEntity();
		}
		if(day ==null){
			day = new Date();
		}
		day = FolderUtils.addDateByday(day,-1);
		smsdata.setSsr(FolderUtils.dateToStr(day, "yyyy-MM-dd"));
		return smsDataEntityMapper.queryList(smsdata);
		
	}
	@Override
	public SmsDataVo querySmsData(SmsDataEntity smsdata, Date day) {
		List<SmsDataEntity> smslist = querySmsDataList(smsdata,day);
		if(smslist.size()<1){
			return null;
		}
		
		return initSmsData(smslist.get(0));
	}
	
	private SmsDataVo initSmsData(SmsDataEntity sms){
		if(sms ==null){
			return null;
		}
		
		SmsDataVo smsDataVo = new SmsDataVo();
		//当日最大值
		double maxv = findMaxValue(new double[]{sms.getDrmb(),sms.getDrwc()});
		//当日目标
		smsDataVo.setDayTarget(new PieDataVo(sms.getDrmb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDrmb())));
		//当日完成
		smsDataVo.setDayComplete(new PieDataVo(sms.getDrwc(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDrwc())));
		
		//当月最大值
		maxv = findMaxValue(new double[]{sms.getDymb(),sms.getDywc()});
		//当月目标
		smsDataVo.setMonthTarget(new PieDataVo(sms.getDymb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDymb())));
		//当月完成
		smsDataVo.setMonthComplete(new PieDataVo(sms.getDywc(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDywc())));
		
		//当月最大比值
		maxv = findMaxValue(new double[]{sms.getDywcb(),sms.getDyyjwcb(),sms.getDytb(),sms.getDyhb()});
		if(maxv<100){
			maxv=100;
		}
		//当月完成比
		smsDataVo.setMonthRatio(new PieDataVo(sms.getDywcb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDywcb())));
		//当月预计完成比
		smsDataVo.setMonthPlanRatio(new PieDataVo(sms.getDyyjwcb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDyyjwcb())));
		//当月同比
		smsDataVo.setMonthYOY(new PieDataVo(sms.getDytb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDytb())));
		//当月环比
		smsDataVo.setMonthMOM(new PieDataVo(sms.getDyhb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getDyhb())));

		//本年最大值
		maxv = findMaxValue(new double[]{sms.getBnmb(),sms.getBnwc()});
		//本年目标
		smsDataVo.setYearTarget(new PieDataVo(sms.getBnmb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getBnmb())));
		//本年完成
		smsDataVo.setYearComplete(new PieDataVo(sms.getBnwc(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getBnwc())));
		
		//本年最大比值
		maxv = sms.getBnwcb();
		if(maxv<100){
			maxv=100;
		}
		//本年完成比
		smsDataVo.setYearhRatio(new PieDataVo(sms.getBnwcb(),getHideValByTarget(ItfConifgConstant.MAP_PIE_RATIO,maxv,sms.getBnwcb())));
		//公司名称
		smsDataVo.setCompName(sms.getCompname());
		
		return smsDataVo;
	}
	
	/**
	 * 获取最大值对应的隐藏域值
	 * @param maxv 最大值
	 * @param coefficient 比例系数
	 * @return
	 */
	private double getMaxHideValByMax(double maxv,double coefficient ){
		BigDecimal  maxval = new BigDecimal(maxv); 
		return maxval.multiply(new BigDecimal(coefficient)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		 
	}
	/**
	 * 目标值对应隐藏域值
	 * @param coefficient 系数
	 * @param maxval 最大值
	 * @param target 目标值
	 * @return
	 */
	private double getHideValByTarget(double coefficient,double maxval,double target){
		double maxhideval = getMaxHideValByMax(maxval,coefficient);
		BigDecimal  maxdec = new BigDecimal(maxval);
		return maxdec.subtract(new BigDecimal(target)).add(new BigDecimal(maxhideval)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 数组中最大值
	 * @param arr
	 * @return
	 */
	private double findMaxValue(double[]arr){
		if(arr==null || arr.length<1){
			return 0;
		}
		double max = arr[0]; 
		for(int i=0;i<arr.length;i++) { 
			if(max<arr[i]) { 
				max = arr[i]; 
			} 
		}
		return max;
	}
	

}
