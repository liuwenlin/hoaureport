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
import com.hoau.hoaureport.module.configreport.server.service.IDepartmentManagerService;
import com.hoau.hoaureport.module.configreport.shared.vo.DepartmentManagerVo;
/**
 * 
 * @author 刘镇松
 *	基础资料 - 部门管理者
 */
@Controller("departmentManagerAction")
@Scope("prototype")
public class DepartmentManagerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6370194972824536728L;
	
	@Resource
	IDepartmentManagerService service;
	
	DepartmentManagerVo vo;
	
	public String showIndex(){
		return "index";
	}
	
	@JSON
	public String queryDepartmentManager(){
		try {
			vo.setLists(service.queryDepartmentManager(vo.getRdmVo(),start,limit));
			totalCount = service.countByCondition(vo.getRdmVo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	
	@JSON
	public String implExcel() throws Exception{
		try {
			//HttpServletResponse response = ServletActionContext.getResponse();
			//response.reset();//清空buffer,设置页面不缓存
			//response.setContentType("text/html;charset=utf-8");
			//PrintWriter out = response.getWriter();
			String uploadPath = ServletActionContext.getServletContext().getRealPath(vo.getFilePath());
			Map<String,Object> returnMap= service.bathImplDepartmentManager(uploadPath);
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

	public DepartmentManagerVo getVo() {
		return vo;
	}

	public void setVo(DepartmentManagerVo vo) {
		this.vo = vo;
	}
	
}
