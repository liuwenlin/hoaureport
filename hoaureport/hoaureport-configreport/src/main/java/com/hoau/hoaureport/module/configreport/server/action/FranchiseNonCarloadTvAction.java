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
import com.hoau.hoaureport.module.configreport.server.service.IFranchiseNonCarloadTVService;
import com.hoau.hoaureport.module.configreport.shared.vo.FranchiseNonCarloadTvVo;
/**
 * 特许经营目标值
 * @author 刘镇松
 *
 */
@Controller("franchiseNonCarloadTvAction")
@Scope("prototype")
public class FranchiseNonCarloadTvAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704025747095238822L;

	private FranchiseNonCarloadTvVo vo;
	@Resource
	IFranchiseNonCarloadTVService service;
	public String showIndexPage(){
		return "index";
	}
	
	@JSON
	public String queryInfo(){
		try {
			vo.setList(service.queryInfo(vo.getEntity(), start, limit));
			totalCount = service.queryCount(vo.getEntity());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/** 
	 * 
	 * @Description: excel解析并插入到数据库
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(vo.getFilePath());
			Map<String,Object> returnMap= service.bathImplPlatformAreaInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			vo.setAddSize(returnMap.get("addSize").toString());
			vo.setCoverSize(returnMap.get("coverSize").toString());
			vo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public FranchiseNonCarloadTvVo getVo() {
		return vo;
	}

	public void setVo(FranchiseNonCarloadTvVo vo) {
		this.vo = vo;
	}
}
