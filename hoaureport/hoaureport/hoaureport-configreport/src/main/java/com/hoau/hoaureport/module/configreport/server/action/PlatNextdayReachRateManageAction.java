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
import com.hoau.hoaureport.module.configreport.server.service.IPlatNextdayReachRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.PlatNextdayReachRateVo;

/**
 * 平台次日送到率ManageAction
 * ClassName: PlatNextdayReachRateManageAction 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
@Controller("platNextdayReachRateManageAction")
@Scope("prototype")
public class PlatNextdayReachRateManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -7937125757115990167L;
private PlatNextdayReachRateVo platNextdayReachRateVo;
	
	@Resource
	IPlatNextdayReachRateManageService platNextdayReachRateManageService;
	
	/**
	 * 
	 * @Description:返回平台次日送达率管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	public String showPlatNextdayReachRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 平台次日送达率信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	@JSON
	public String queryPlatNextdayReachRate() {
		try {
			platNextdayReachRateVo.setPlatNextdayReachRates(platNextdayReachRateManageService.queryPlatNextdayReachRate(platNextdayReachRateVo.getPlatNextdayReachRate(), start, limit));
			totalCount = platNextdayReachRateManageService.queryPlatNextdayReachRateCount(platNextdayReachRateVo.getPlatNextdayReachRate());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
 		}
	}
	
	/**
	 * 
	 * @Description: excel解析并插入到数据库
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.reset();//清空buffer,设置页面不缓存
			//response.setContentType("text/html;charset=utf-8");
			//PrintWriter out = response.getWriter();
			String uploadPath = ServletActionContext.getServletContext().getRealPath(platNextdayReachRateVo.getFilePath());
			Map<String,Object> returnMap= platNextdayReachRateManageService.bathImplPlatNextdayReachRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			platNextdayReachRateVo.setAddSize(returnMap.get("addSize").toString());
			platNextdayReachRateVo.setCoverSize(returnMap.get("coverSize").toString());
			platNextdayReachRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public PlatNextdayReachRateVo getPlatNextdayReachRateVo() {
		return platNextdayReachRateVo;
	}

	public void setPlatNextdayReachRateVo(PlatNextdayReachRateVo platNextdayReachRateVo) {
		this.platNextdayReachRateVo = platNextdayReachRateVo;
	}
}
