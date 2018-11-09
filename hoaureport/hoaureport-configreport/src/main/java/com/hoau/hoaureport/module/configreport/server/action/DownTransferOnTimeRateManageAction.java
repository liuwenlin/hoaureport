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
import com.hoau.hoaureport.module.configreport.server.service.IDownTransferOnTimeRateManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.DownTransferOnTimeRateVo;

/**
 * 下转准点率目标值ManageAction
 * ClassName: DownTransferOnTimeRateManageAction 
 * @author 文洁
 * @date 2016年11月9日
 * @version V1.0
 */
@Controller("downTransferOnTimeRateManageAction")
@Scope("prototype")
public class DownTransferOnTimeRateManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -6713092118067806935L;

	private DownTransferOnTimeRateVo downTransferOnTimeRateVo;
	
	@Resource
	IDownTransferOnTimeRateManageService downTransferOnTimeRateManageService;
	
	/**
	 * 
	 * @Description:返回下转准点率目标值管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	public String showDownTransferOnTimeRatePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 下转准点率目标值信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月9日
	 */
	@JSON
	public String queryDownTransferOnTimeRate() {
		try {
			downTransferOnTimeRateVo.setDownTransferOnTimeRates(downTransferOnTimeRateManageService.queryDownTransferOnTimeRate(downTransferOnTimeRateVo.getDownTransferOnTimeRate(), start, limit));
			totalCount = downTransferOnTimeRateManageService.queryDownTransferOnTimeRateCount(downTransferOnTimeRateVo.getDownTransferOnTimeRate());
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(downTransferOnTimeRateVo.getFilePath());
			Map<String,Object> returnMap= downTransferOnTimeRateManageService.bathImplDownTransferOnTimeRate(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			downTransferOnTimeRateVo.setAddSize(returnMap.get("addSize").toString());
			downTransferOnTimeRateVo.setCoverSize(returnMap.get("coverSize").toString());
			downTransferOnTimeRateVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public DownTransferOnTimeRateVo getDownTransferOnTimeRateVo() {
		return downTransferOnTimeRateVo;
	}

	public void setDownTransferOnTimeRateVo(DownTransferOnTimeRateVo downTransferOnTimeRateVo) {
		this.downTransferOnTimeRateVo = downTransferOnTimeRateVo;
	}
	
}
