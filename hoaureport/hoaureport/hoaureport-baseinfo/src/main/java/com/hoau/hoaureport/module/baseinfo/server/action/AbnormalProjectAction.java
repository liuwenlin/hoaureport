package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IAbnormalProjectService;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.AbnormalProjectVo;

/**
 * @author：295073
 * @create：2016年1月12日 下午4:35:34
 * @description：异常项目维护
 */
@Controller
@Scope("prototype")
public class AbnormalProjectAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private AbnormalProjectVo abnormalProjectVo;
	
	@Autowired
	private IAbnormalProjectService abnormalProjectService;
	
	public String index(){
		return "index";
	}
	/**
	 * 查询异常项目
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String queryAbnormalProject() {

		try {
			abnormalProjectVo.getAbnormalProjectParams().setLimit(limit);
			abnormalProjectVo.getAbnormalProjectParams().setOffset(start);
			abnormalProjectVo.setAbnormalProjectList(abnormalProjectService
					.queryAbnormalProject(abnormalProjectVo
							.getAbnormalProjectParams()));
			totalCount = abnormalProjectService
					.queryCountAbnormalProject(abnormalProjectVo
							.getAbnormalProjectParams());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增异常项目
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String addAbnormalProject() {
		try {
			abnormalProjectService.addAbnormalProject(abnormalProjectVo
					.getAbnormalProjectEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.ADD_SUCCESS);
	}

	/**
	 * 删除异常项目
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String deleteAbnormalProject() {
		try {
			abnormalProjectService.deleteAbnormalProject(abnormalProjectVo
					.getAbnormalProjectList());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.DELETE_SUCCESS);
	}

	/**
	 * 更新异常项目
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String updateAbnormalProject() {
		try {
			abnormalProjectService.updateAbnormalProject(abnormalProjectVo
					.getAbnormalProjectEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.UPDATE_SUCCESS);
	}
	public AbnormalProjectVo getAbnormalProjectVo() {
		return abnormalProjectVo;
	}
	public void setAbnormalProjectVo(AbnormalProjectVo abnormalProjectVo) {
		this.abnormalProjectVo = abnormalProjectVo;
	}
}
