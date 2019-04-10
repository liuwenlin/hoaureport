package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.ICommonUserService;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.CommonUserVo;

/**
 * @author：李旭锋
 * @create：2015年7月20日 下午5:17:45
 * @description：
 */
@Controller
@Scope("prototype")
public class CommonUserSearchAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -871092932836990699L;

	private CommonUserVo commonUserVo;

	@Autowired
	private ICommonUserService commonUserService;

	/**
	 * 根据条件查询用户信息
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月21日
	 * @update
	 */
	@JSON
	public String seacrhUserByParam() {
		commonUserVo.getUserSearchConditionEntity().setLimit(limit);
		commonUserVo.getUserSearchConditionEntity().setStart(start);
		commonUserVo.setCommonUserEntityList(commonUserService
				.queryUserByParam(commonUserVo.getUserSearchConditionEntity()));
		setTotalCount(commonUserService.queryCountUserByParam(commonUserVo
				.getUserSearchConditionEntity()));
		return returnSuccess();
	}

	public CommonUserVo getCommonUserVo() {
		return commonUserVo;
	}

	public void setCommonUserVo(CommonUserVo commonUserVo) {
		this.commonUserVo = commonUserVo;
	}

}
