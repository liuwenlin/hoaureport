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
import com.hoau.hoaureport.module.configreport.server.service.ITransGoodsManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.TransGoodsIntimerateVo;

/**
 * 必走货目标值
 * ClassName: TransGoodsManageAction 
 * @author 刘镇松
 * @date 2016年11月21日
 * @version V1.0
 */
@Controller("transGoodsManageAction")
@Scope("prototype")
public class TransGoodsManageAction extends AbstractAction{
	private static final long serialVersionUID = 7083435700500472590L;

	private TransGoodsIntimerateVo transGoodsIntimerateVo;
	
	@Resource
	ITransGoodsManageService transGoodsManageService;
	
	/**
	 * 
	 * @Description:返回送货及时率管理 首界面
	 * @return index字符串 
	 * @author 刘镇松
	 * @date 2016年8月24日
	 */
	public String showTransGoodsIntimeratePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 送货及时率信息
	 * @return String 
	 * @author 刘镇松
	 * @date 2016年8月24日
	 */
	@JSON
	public String queryTransGoodsIntimerate() {
		try {
			transGoodsIntimerateVo.setTransGoodsIntimerates(transGoodsManageService.queryTransGoodsIntimerate(transGoodsIntimerateVo.getTransGoodsIntimerate(), start, limit));
			totalCount = transGoodsManageService.queryTransGoodsIntimerateCount(transGoodsIntimerateVo.getTransGoodsIntimerate());
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(transGoodsIntimerateVo.getFilePath());
			Map<String,Object> returnMap= transGoodsManageService.bathImplTransGoodsIntimerate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			transGoodsIntimerateVo.setAddSize(returnMap.get("addSize").toString());
			transGoodsIntimerateVo.setCoverSize(returnMap.get("coverSize").toString());
			transGoodsIntimerateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public TransGoodsIntimerateVo getTransGoodsIntimerateVo() {
		return transGoodsIntimerateVo;
	}

	public void setTransGoodsIntimerateVo(
			TransGoodsIntimerateVo transGoodsIntimerateVo) {
		this.transGoodsIntimerateVo = transGoodsIntimerateVo;
	}
	
}
