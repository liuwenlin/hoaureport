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
import com.hoau.hoaureport.module.configreport.server.service.INextdayReachRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.NextdayReachRateVo;

/**
 * 
 * ClassName: NextdayReachRateManageAction 
 * @author 文洁
 * @date 2016年8月24日
 * @version V1.0
 */
@Controller("nextdayReachRateManageAction")
@Scope("prototype")
public class NextdayReachRateManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7400881385702760725L;
	
	private NextdayReachRateVo nextdayReachRateVo;
	
	@Resource
	INextdayReachRateManageService nextdayReachRateManageService;
	
	/**
	 * 
	 * @Description:返回次日送达率管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	public String showNextdayReachRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 次日送达率信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	@JSON
	public String queryNextdayReachRate() {
		try {
			nextdayReachRateVo.setNextdayReachRates(nextdayReachRateManageService.queryNextdayReachRate(nextdayReachRateVo.getNextdayReachRate(), start, limit));
			totalCount = nextdayReachRateManageService.queryNextdayReachRateCount(nextdayReachRateVo.getNextdayReachRate());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
 		}
	}
	
	/**
	 * 
	 * @Description: excel解析并插入到数据库
	 * @param @return
	 * @param @throws Exception   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月24日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.reset();//清空buffer,设置页面不缓存
			//response.setContentType("text/html;charset=utf-8");
			//PrintWriter out = response.getWriter();
			String uploadPath = ServletActionContext.getServletContext().getRealPath(nextdayReachRateVo.getFilePath());
			Map<String,Object> returnMap= nextdayReachRateManageService.bathImplNextdayReachRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			nextdayReachRateVo.setAddSize(returnMap.get("addSize").toString());
			nextdayReachRateVo.setCoverSize(returnMap.get("coverSize").toString());
			nextdayReachRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public NextdayReachRateVo getNextdayReachRateVo() {
		return nextdayReachRateVo;
	}

	public void setNextdayReachRateVo(NextdayReachRateVo nextdayReachRateVo) {
		this.nextdayReachRateVo = nextdayReachRateVo;
	}
	
}
