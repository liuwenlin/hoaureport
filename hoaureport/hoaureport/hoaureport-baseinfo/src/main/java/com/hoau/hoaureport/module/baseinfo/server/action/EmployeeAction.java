package com.hoau.hoaureport.module.baseinfo.server.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IEmployeeService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.EmployeeEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.EmployeeVo;
/**
 *员工信息action
 * @author 涂婷婷
 * @date 2015年7月24日
 */
@Controller
@Scope("prototype")
public class EmployeeAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IEmployeeService employeeService;
	
	private EmployeeVo employeeVo;
	
	
	/**
	 * 跳转到员工信息界面
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月24日
	 * @update
	 */
	public String indexEmployee() {
		return "index";
	}
	
	
	/**
	 * 查询员工信息
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月24日
	 * @update
	 */
	@JSON
	public String queryEmployeeList(){
		try {
			List<EmployeeEntity> employeeList=employeeService.queryEmployeeList(employeeVo.getEmployeeEntity(), limit, start);
			totalCount=employeeService.queryEmployeeCount(employeeVo.getEmployeeEntity());
			employeeVo.setEmployeeList(employeeList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	
	
	public EmployeeVo getEmployeeVo() {
		return employeeVo;
	}

	public void setEmployeeVo(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
	}

	
	
}
