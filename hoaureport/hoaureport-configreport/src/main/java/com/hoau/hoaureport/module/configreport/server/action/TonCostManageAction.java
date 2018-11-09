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
import com.hoau.hoaureport.module.configreport.server.service.ITonCostManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.TonCostVo;

/**
 * 
 * ClassName: TonCostManageAction 
 * @author 文洁
 * @date 2016年8月24日
 * @version V1.0
 */
@Controller("tonCostManageAction")
@Scope("prototype")
public class TonCostManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7400881385702760725L;
	
	private TonCostVo tonCostVo;
	
	@Resource
	ITonCostManageService tonCostManageService;
	
	/**
	 * 
	 * @Description:返回吨成本管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	public String showTonCostInfoPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 吨成本信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月24日
	 */
	@JSON
	public String queryTonCastInfo() {
		try {
			tonCostVo.setTonCostInfos(tonCostManageService.queryTonCostInfo(tonCostVo.getTonCostInfo(), start, limit));
			totalCount = tonCostManageService.queryTonCostInfoCount(tonCostVo.getTonCostInfo());
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(tonCostVo.getFilePath());
			Map<String,Object> returnMap= tonCostManageService.bathImplTonCost(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			tonCostVo.setAddSize(returnMap.get("addSize").toString());
			tonCostVo.setCoverSize(returnMap.get("coverSize").toString());
			tonCostVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public TonCostVo getTonCostVo() {
		return tonCostVo;
	}

	public void setTonCostVo(TonCostVo tonCostVo) {
		this.tonCostVo = tonCostVo;
	}
	
}
