package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonOrgService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.CommonOrgEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.CommonOrgVo;

/**
 * @author：高佳
 * @create：2015年6月30日 下午1:47:09
 * @description：公共选择器组织相关Action
 */
@Controller
@Scope("prototype")
public class CommonOrgSearchAction extends AbstractAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private CommonOrgVo commonOrgVo;
	
	@Autowired
	private ICommonOrgService commonOrgService;
	
	/**
	 * 根据条件查询组织信息
	 * @return
	 * @author 高佳
	 * @date 2015年6月30日
	 * @update 
	 */
	@JSON
	@SecurityNonCheckRequired
	public String seacrhOrgByParam(){
		commonOrgVo.getOrgSearchConditionEntity().setLimit(limit);
		commonOrgVo.getOrgSearchConditionEntity().setStart(start);
		List<CommonOrgEntity> commonOrgEntityList = commonOrgService.queryOrgByParam(commonOrgVo.getOrgSearchConditionEntity());
		setTotalCount(commonOrgService.countOrgByParam(commonOrgVo.getOrgSearchConditionEntity()));
		commonOrgVo.setCommonOrgEntityList(commonOrgEntityList);
		return returnSuccess();
	}
	public CommonOrgVo getCommonOrgVo() {
		return commonOrgVo;
	}
	public void setCommonOrgVo(CommonOrgVo commonOrgVo) {
		this.commonOrgVo = commonOrgVo;
	}

	
}
