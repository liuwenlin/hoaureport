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
import com.hoau.hoaureport.module.configreport.server.service.IDeliverIntimeRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.DeliverIntimeRateVo;

/**
 * 送货及时率
 * ClassName: DeliverIntimeRateManageAction 
 * @author 刘镇松
 * @date 2016年8月25日
 * @version V1.0
 */
@Controller("deliverIntimeRateManageAction")
@Scope("prototype")
public class DeliverIntimeRateManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7400881385702760725L;
	
	private DeliverIntimeRateVo deliverIntimeRateVo;
	
	@Resource
	IDeliverIntimeRateManageService deliverIntimeRateManageService;
	
	/**
	 * 
	 * @Description:返回送货及时率管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	public String showDeliverIntimeRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 送货及时率信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	@JSON
	public String queryDeliverIntimeRate() {
		try {
			deliverIntimeRateVo.setDeliverIntimeRates(deliverIntimeRateManageService.queryDeliverIntimeRate(deliverIntimeRateVo.getDeliverIntimeRate(), start, limit));
			totalCount = deliverIntimeRateManageService.queryDeliverIntimeRateCount(deliverIntimeRateVo.getDeliverIntimeRate());
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(deliverIntimeRateVo.getFilePath());
			Map<String,Object> returnMap= deliverIntimeRateManageService.bathImplDeliverIntimeRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			deliverIntimeRateVo.setAddSize(returnMap.get("addSize").toString());
			deliverIntimeRateVo.setCoverSize(returnMap.get("coverSize").toString());
			deliverIntimeRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public DeliverIntimeRateVo getDeliverIntimeRateVo() {
		return deliverIntimeRateVo;
	}

	public void setDeliverIntimeRateVo(DeliverIntimeRateVo deliverIntimeRateVo) {
		this.deliverIntimeRateVo = deliverIntimeRateVo;
	}
	
}
