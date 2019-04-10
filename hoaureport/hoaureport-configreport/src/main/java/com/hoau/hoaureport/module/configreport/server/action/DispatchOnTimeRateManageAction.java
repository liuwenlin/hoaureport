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
import com.hoau.hoaureport.module.configreport.server.service.IDispatchOnTimeRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.DispatchOnTimeRateVo;

/**
 * 发货准点率目标值ManageAction
 * ClassName: DispatchOnTimeRateManageAction 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
@Controller("dispatchOnTimeRateManageAction")
@Scope("prototype")
public class DispatchOnTimeRateManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6082220090875341040L;

	private DispatchOnTimeRateVo dispatchOnTimeRateVo;
	
	@Resource
	IDispatchOnTimeRateManageService dispatchOnTimeRateManageService;
	
	/**
	 * 
	 * @Description:返回发货准点率目标值管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	public String showDispatchOnTimeRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 发货准点率目标值信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	@JSON
	public String queryDispatchOnTimeRate() {
		try {
			dispatchOnTimeRateVo.setDispatchOnTimeRates(dispatchOnTimeRateManageService.queryDispatchOnTimeRate(dispatchOnTimeRateVo.getDispatchOnTimeRate(), start, limit));
			totalCount = dispatchOnTimeRateManageService.queryDispatchOnTimeRateCount(dispatchOnTimeRateVo.getDispatchOnTimeRate());
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
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.reset();//清空buffer,设置页面不缓存
			//response.setContentType("text/html;charset=utf-8");
			//PrintWriter out = response.getWriter();
			String uploadPath = ServletActionContext.getServletContext().getRealPath(dispatchOnTimeRateVo.getFilePath());
			Map<String,Object> returnMap= dispatchOnTimeRateManageService.bathImplDispatchOnTimeRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			dispatchOnTimeRateVo.setAddSize(returnMap.get("addSize").toString());
			dispatchOnTimeRateVo.setCoverSize(returnMap.get("coverSize").toString());
			dispatchOnTimeRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public DispatchOnTimeRateVo getDispatchOnTimeRateVo() {
		return dispatchOnTimeRateVo;
	}

	public void setDispatchOnTimeRateVo(DispatchOnTimeRateVo dispatchOnTimeRateVo) {
		this.dispatchOnTimeRateVo = dispatchOnTimeRateVo;
	}
	
}
