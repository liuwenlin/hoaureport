package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonOmsHRService;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.CommonOmsHRVo;
/**
 * @author 肖明明
 * @date 2016年1月23日下午5:38:58
 */
@Controller
@Scope("prototype")
public class CommonOmsHRSearchAction extends AbstractAction {
	private static final long serialVersionUID = 4955297792772859742L;
	private CommonOmsHRVo commonOmsHRVo;
	@Autowired
	ICommonOmsHRService commonOmsHRService;
	public CommonOmsHRVo getCommonOmsHRVo() {
		return commonOmsHRVo;
	}
	public void setCommonOmsHRVo(CommonOmsHRVo commonOmsHRVo) {
		this.commonOmsHRVo = commonOmsHRVo;
	}
	
	@JSON
	public String seacrhOmsHRByParam() {
		commonOmsHRVo.getOmsHRSearchConditionEntity().setLimit(limit);
		commonOmsHRVo.getOmsHRSearchConditionEntity().setStart(start);
		
		commonOmsHRVo.setCommonOmsHRList(commonOmsHRService
				.queryEmployeeByParam(commonOmsHRVo
						.getOmsHRSearchConditionEntity()));
		totalCount = commonOmsHRService
				.queryCountEmployeeByParam(commonOmsHRVo
						.getOmsHRSearchConditionEntity());
		return returnSuccess();
	}
}
