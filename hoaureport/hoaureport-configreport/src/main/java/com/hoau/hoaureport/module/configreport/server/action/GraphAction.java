package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.configreport.server.service.IGraphService;
import com.hoau.hoaureport.module.configreport.shared.domain.SmsDataEntity;
import com.hoau.hoaureport.module.configreport.shared.vo.GraphInfoVo;
import com.hoau.hoaureport.module.configreport.shared.vo.SmsDataVo;
/**
 * 图像化界面数据Action 
 * @author 273503
 *
 */
@Controller
@Scope("prototype")
public class GraphAction extends AbstractAction {
	Logger log = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 8538060338324724219L;
	@Autowired
	private IGraphService graphService;
    
	private GraphInfoVo  graphInfoVo ;
	private Date costDay;
	private SmsDataEntity smsdata;
	
	private SmsDataVo smsDataVo;
	
	
	/**
	 * 吨成本
	 * @return
	 */
	public String queryCost(){
		try {
			graphInfoVo=graphService.queryCost(costDay);
			if(graphInfoVo==null || graphInfoVo.getDepts()==null ||graphInfoVo.getDepts().size()<1){
				return this.returnError("吨成本当日数据为空");
			}
			return this.returnSuccess();
		} catch (Exception e) {
			log.error("吨成本查询异常",e);
			return this.returnError("数据查询异常");
		}
	}

	/**
	 * 时效送货及时率
	 * @return
	 */
	public String queryNowDay(){
		try {
			graphInfoVo=graphService.queryNowDay(costDay);
			if(graphInfoVo==null || graphInfoVo.getDepts()==null ||graphInfoVo.getDepts().size()<1){
				return this.returnError("时效送货及时率当日数据为空");
			}
			return this.returnSuccess();
		} catch (Exception e) {
			log.error("时效送货及时率查询异常",e);
			return this.returnError("时效送货及时率查询异常");
		}
	}
	
	/**
	 * 次日送达率
	 * @return
	 */
	public String queryNextDay(){
		try {
			graphInfoVo=graphService.queryNextDay(costDay);
			
			if(graphInfoVo==null || graphInfoVo.getDepts()==null ||graphInfoVo.getDepts().size()<1){
				return this.returnError("时效次日送达率当日数据为空");
			}
			return this.returnSuccess();
		} catch (Exception e) {
			log.error("时效次日送达率查询异常",e);
			return this.returnError("时效次日送达率查询异常");
		}
	}
	
	

	/**
	 * 产值早报界面请求
	 * @return
	 */
	public String loginMapPage(){
		try {
			smsdata = new SmsDataEntity();
			List<SmsDataEntity> smslist =graphService.querySmsDataList(smsdata,costDay);
			if(smslist.size()>0){
				smsdata = smslist.get(0);
			}
			if(smsdata ==null){
				smsdata= new SmsDataEntity();
			}
			
		} catch (Exception e) {
			log.error("产值早报界面请求查询异常",e);
		}
		return this.returnSuccess();
	}
	
	/**
	 * 产值早报数据查询
	 * @return
	 */
	public String querySmsData(){
		try {
			if(smsdata ==null ||smsdata.getCompname()==null ||"".equals(smsdata.getCompname())){
				return this.returnError("当前部门查询参数为空！");
			}
			SmsDataVo smsv =graphService.querySmsData(smsdata,costDay);
			if(smsv ==null){
				return this.returnError("当前部门早报数据为空！");
			}
			setSmsDataVo(smsv);
			return this.returnSuccess();
		} catch (Exception e) {
			log.error("当前部门早报数据查询异常",e);
			return this.returnError("当前部门早报数据查询异常");
		}
	}
	
	
	
	
	public GraphInfoVo getGraphInfoVo() {
		return graphInfoVo;
	}

	public void setGraphInfoVo(GraphInfoVo graphInfoVo) {
		this.graphInfoVo = graphInfoVo;
	}

	public Date getCostDay() {
		return costDay;
	}

	public void setCostDay(Date costDay) {
		this.costDay = costDay;
	}

	public SmsDataEntity getSmsdata() {
		return smsdata;
	}

	public void setSmsdata(SmsDataEntity smsdata) {
		this.smsdata = smsdata;
	}

	public SmsDataVo getSmsDataVo() {
		return smsDataVo;
	}

	public void setSmsDataVo(SmsDataVo smsDataVo) {
		this.smsDataVo = smsDataVo;
	}
	
	
	
	
	
}
