package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgDataAutService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserOrgDataAutEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.UserOrgDataAutVo;


/**
 * 用户部门管理界面
 * 
 * @author 涂婷婷
 * @date 2015年7月20日
 */
@Controller
@Scope("prototype")
public class UserOrgDataAutAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUserOrgDataAutService userOrgDataAutService;
	
	private UserOrgDataAutVo userOrgDataAutVo;
	
	

	public UserOrgDataAutVo getUserOrgDataAutVo() {
		return userOrgDataAutVo;
	}

	public void setUserOrgDataAutVo(UserOrgDataAutVo userOrgDataAutVo) {
		this.userOrgDataAutVo = userOrgDataAutVo;
	}

	/**
	 * 跳转到角色管理界面
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月20日
	 * @update
	 */
	public String userOrgDataAut() {
		return "index";
	}
	
	/**
	 * 用户部门数据权限信息查询
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update
	 */
	@JSON
	public String queryUserOrgDataAut()
	{
		try {
			List<UserOrgDataAutEntity> UserOrgDataAutList=userOrgDataAutService.queryUserOrgDataAut(
					userOrgDataAutVo.getUserOrgDataAutEntity(), limit, start);
			totalCount=userOrgDataAutService.queryUserOrgDataAutCount(userOrgDataAutVo.getUserOrgDataAutEntity());
			userOrgDataAutVo.setUserOrgDataAutList(UserOrgDataAutList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	/**
	 * 用户部门数据权限信息添加
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update
	 */
	@JSON
	public String addUserOrgDataAut()
	{
		try {
			userOrgDataAutService.addUserOrgDataAut(userOrgDataAutVo.getUserOrgDataAutEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	
	/**
	 * 用户部门数据权限信息删除
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update
	 */
	@JSON
	public String deleteUserOrgDataAut() {
		try {
			userOrgDataAutService.deleteUserOrgDataAut(userOrgDataAutVo.getUserOrgDataAutList());
			return returnSuccess(MessageType.DELETE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 *根据用户名查询用户部门实体
	 * UserOrgDataAutEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年7月21日
	 * @update
	 */
	@JSON
	public  String  queryUserOrgDataAutEntityByUserName()
	{
		try {
			userOrgDataAutVo.setUserOrgDataAutList(userOrgDataAutService.queryUserOrgListByUserName(
					userOrgDataAutVo.getUserOrgDataAutEntity().getUserName()));
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	
	}
}
