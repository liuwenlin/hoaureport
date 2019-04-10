package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonEmployeeService;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.CommonEmployeeVo;

/**
 * @author：李旭锋
 * @create：2015年7月22日 上午11:37:35
 * @description：
 */
@Controller
@Scope("prototype")
public class CommonEmployeeSearchAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 7295824076147119817L;

	private CommonEmployeeVo commonEmployeeVo;

	public CommonEmployeeVo getCommonEmployeeVo() {
		return commonEmployeeVo;
	}

	public void setCommonEmployeeVo(CommonEmployeeVo commonEmployeeVo) {
		this.commonEmployeeVo = commonEmployeeVo;
	}

	@Autowired
	private ICommonEmployeeService commonEmployeeService;

	/**
	 * 根据条件查询员工
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月22日
	 * @update
	 */
	@JSON
	public String seacrhEmployeeByParam() {
		commonEmployeeVo.getEmployeeSearchConditionEntity().setLimit(limit);
		commonEmployeeVo.getEmployeeSearchConditionEntity().setStart(start);
		
		commonEmployeeVo.setCommonEmployeeList(commonEmployeeService
				.queryEmployeeByParam(commonEmployeeVo
						.getEmployeeSearchConditionEntity()));
		totalCount = commonEmployeeService
				.queryCountEmployeeByParam(commonEmployeeVo
						.getEmployeeSearchConditionEntity());
		
		return returnSuccess();
	}

}
