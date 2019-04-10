package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.configreport.server.service.IWeekDayService;
import com.hoau.hoaureport.module.configreport.shared.vo.UnitpriceMonitorWeekdayVo;

@Controller("weekDayAction")
@Scope("prototype")
public class WeekDayAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5812557467439028809L;

	@Resource
	IWeekDayService service;
	
	UnitpriceMonitorWeekdayVo vo;
	
	public String showIndex(){
		return "index";
	}
	
	@JSON
	public String queryWeekDay(){
		try {
			vo.setLists(service.queryWeekDay(vo.getRdmVo(),start,limit));
			totalCount = service.countByCondition(vo.getRdmVo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(vo.getFilePath());
			Map<String,Object> returnMap= service.bathImplWeekDay(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			vo.setAddSize(returnMap.get("addSize").toString());
			vo.setCoverSize(returnMap.get("coverSize").toString());
			vo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public UnitpriceMonitorWeekdayVo getVo() {
		return vo;
	}

	public void setVo(UnitpriceMonitorWeekdayVo vo) {
		this.vo = vo;
	}
	
	
	
}
