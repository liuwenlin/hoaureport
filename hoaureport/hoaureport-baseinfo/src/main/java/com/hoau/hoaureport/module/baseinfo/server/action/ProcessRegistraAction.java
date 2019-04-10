package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IProcessRegistraService;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.ProcessRegistraVo;

/**
 * 处理登记维护
 * 
 * @author 295073
 * @date 2016年1月8日
 */
@Controller
@Scope("prototype")
public class ProcessRegistraAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ProcessRegistraVo processRegistraVo;
	@Autowired
	private IProcessRegistraService processRegistraService;

	/**
	 * 初始化进入页面
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public String index() {
		return "index";
	}

	/**
	 * 查询处理登记
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String queryProcessRegistra() {
		try {
			processRegistraVo.getProcessRegistraParams().setLimit(limit);
			processRegistraVo.getProcessRegistraParams().setOffset(start);
			processRegistraVo.setProcessRegistraList(processRegistraService
					.queryProcessRegistra(processRegistraVo
							.getProcessRegistraParams()));
			totalCount = processRegistraService
					.queryCountProcessRegistra(processRegistraVo
							.getProcessRegistraParams());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增处理登记
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String addProcessRegistra() {
		try {
			processRegistraService.addProcessRegistra(processRegistraVo
					.getProcessRegistraEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.ADD_SUCCESS);
	}

	/**
	 * 删除处理登记
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String deleteProcessRegistra() {
		try {
			processRegistraService.deleteProcessRegistra(processRegistraVo
					.getProcessRegistraList());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.DELETE_SUCCESS);
	}

	/**
	 * 更新处理登记
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	@JSON
	public String updateProcessRegistra() {
		try {
			processRegistraService.updateProcessRegistra(processRegistraVo
					.getProcessRegistraEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.UPDATE_SUCCESS);
	}

	public ProcessRegistraVo getProcessRegistraVo() {
		return processRegistraVo;
	}

	public void setProcessRegistraVo(ProcessRegistraVo processRegistraVo) {
		this.processRegistraVo = processRegistraVo;
	}
}
