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
import com.hoau.hoaureport.module.configreport.server.service.IUnfulfilledTargetManageService;
import com.hoau.hoaureport.module.configreport.shared.vo.UnfulfilledTargetVo;

/**
 * DD未兑现目标值Action
 * ClassName: UnfulfilledTargetManageAction 
 * @author 文洁
 * @date 2016年12月21日
 * @version V1.0
 */
@Controller("unfulfilledTargetManageAction")
@Scope("prototype")
public class UnfulfilledTargetManageAction extends AbstractAction{

	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -5133313066241833537L;

	private UnfulfilledTargetVo unfulfilledTargetVo;
	
	@Resource
	IUnfulfilledTargetManageService unfulfilledTargetManageService;
	
	/**
	 * 
	 * @Description:返回DD未兑现目标值管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年12月21日
	 */
	public String showUnfulfilledTargetPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 DD未兑现目标值信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年12月21日
	 */
	@JSON
	public String queryUnfulfilledTarget() {
		try {
			unfulfilledTargetVo.setUnfulfilledTargets(unfulfilledTargetManageService.queryUnfulfilledTarget(unfulfilledTargetVo.getUnfulfilledTarget(), start, limit));
			totalCount = unfulfilledTargetManageService.queryUnfulfilledTargetCount(unfulfilledTargetVo.getUnfulfilledTarget());
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
	 * @date 2016年12月21日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(unfulfilledTargetVo.getFilePath());
			Map<String,Object> returnMap= unfulfilledTargetManageService.bathImplUnfulfilledTarget(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			unfulfilledTargetVo.setAddSize(returnMap.get("addSize").toString());
			unfulfilledTargetVo.setCoverSize(returnMap.get("coverSize").toString());
			unfulfilledTargetVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public UnfulfilledTargetVo getUnfulfilledTargetVo() {
		return unfulfilledTargetVo;
	}

	public void setUnfulfilledTargetVo(UnfulfilledTargetVo unfulfilledTargetVo) {
		this.unfulfilledTargetVo = unfulfilledTargetVo;
	}
	
}
