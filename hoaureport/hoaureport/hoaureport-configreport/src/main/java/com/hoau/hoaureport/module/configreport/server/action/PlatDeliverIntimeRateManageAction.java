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
import com.hoau.hoaureport.module.configreport.server.service.IPlatDeliverIntimeRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.PlatDeliverIntimeRateVo;

/**
 * 平台送货及时率
 * ClassName: PlatDeliverIntimeRateManageAction 
 * @author 文洁
 * @date 2016年10月17日
 * @version V1.0
 */
@Controller("platDeliverIntimeRateManageAction")
@Scope("prototype")
public class PlatDeliverIntimeRateManageAction extends AbstractAction {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -4352894394911310087L;

	private PlatDeliverIntimeRateVo platDeliverIntimeRateVo;
	
	@Resource
	IPlatDeliverIntimeRateManageService platDeliverIntimeRateManageService;
	
	/**
	 * 
	 * @Description:返回平台送货及时率管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	public String showPlatDeliverIntimeRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 平台送货及时率信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	@JSON
	public String queryPlatDeliverIntimeRate() {
		try {
			platDeliverIntimeRateVo.setPlatDeliverIntimeRates(platDeliverIntimeRateManageService.queryPlatDeliverIntimeRate(platDeliverIntimeRateVo.getPlatDeliverIntimeRate(), start, limit));
			totalCount = platDeliverIntimeRateManageService.queryPlatDeliverIntimeRateCount(platDeliverIntimeRateVo.getPlatDeliverIntimeRate());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
 		}
	}
	

	/**
	 * 
	 * @Description: excel解析并插入到数据库
	 * @author 文洁
	 * @date 2016年10月17日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(platDeliverIntimeRateVo.getFilePath());
			Map<String,Object> returnMap = platDeliverIntimeRateManageService.bathImplPlatDeliverIntimeRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			platDeliverIntimeRateVo.setAddSize(returnMap.get("addSize").toString());
			platDeliverIntimeRateVo.setCoverSize(returnMap.get("coverSize").toString());
			platDeliverIntimeRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public PlatDeliverIntimeRateVo getPlatDeliverIntimeRateVo() {
		return platDeliverIntimeRateVo;
	}

	public void setPlatDeliverIntimeRateVo(PlatDeliverIntimeRateVo platDeliverIntimeRateVo) {
		this.platDeliverIntimeRateVo = platDeliverIntimeRateVo;
	}
	
}

