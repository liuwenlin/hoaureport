package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IOrgAdministrativeInfoService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserOrgRoleService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.OrgRoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.UserVo;

/**
 * 
 *
 * @author 涂婷婷
 * @date 2015年9月14日
 */
@Controller
@Scope("prototype")
public class UserAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrgAdministrativeInfoService orgAdministrativeInfoService;

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserOrgRoleService userOrgRoleService;
	
	private UserVo userVo;


	
	
	/**
	 * 跳转用户管理界面
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月11日
	 * @update
	 */
	public String indexUser() {
		return "index";
	}
	
	
	/**
	 * 查询用户集合
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月11日
	 * @update
	 */	
	@JSON
	public String queryUserList() {
		try {
			userVo.setUserEntityList(userService.queryUserByEntity(userVo.getUserEntity(),limit, start));
			totalCount = userService.countUserByEntity(userVo.getUserEntity());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	
	/**
	 * 添加用户
	 * 
	 * @return
	 * @author涂婷婷
	 * @date 2015年6月18日
	 */
	
	@JSON
	public String addUserEntity() {
		try {
			userService.addUser(userVo.getUserEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	
	/**
	 * 添加特许经营用户
	 * 
	 * @return
	 * @author涂婷婷
	 * @date 2015年9月9日
	 */
	
	@JSON
	public String addEmployeeEntity() {
		try {
			userService.addEmployee(userVo.getUserEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	
	/**
	 * 删除用户信息
	 * @return
	 * @author 高佳
	 * @date 2015年7月23日
	 * @update 
	 */
	@JSON
	public String deleteUser(){
		try {
			userService.deleteUserByUserName(userVo.getUserNameList());
			return returnSuccess(MessageType.DELETE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	/**
	 * 添加用户组织角色信息
	 * 
	 * @return
	 * @author涂婷婷
	 * @date 2015年7月9日
	 */
	
	@JSON
	public String updateUserOrgRole() {
		try {			
			userOrgRoleService.updateUserOrgRole( userVo.getUserName(),userVo.getOrgCode(),userVo.getRoleCodes());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	/**
	 * 根据id修改特许经营用户信息
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年9月10日
	 * @update
	 */
	@JSON
	public String updateEmployeeById(){
		try{
			userService.updateEmployeeById(userVo.getUserEntity());
			return returnSuccess(MessageType.UPDATE_SUCCESS);
		}catch(BusinessException e){
			return returnError(e);
		}
	}
	
	@JSON
	public String queryEmployeeInfoById(){
		try{
			userVo.setUserEntity(
					userService.queryEmployeeInfoById(
							userVo.getUserEntity().getId()));
			return returnSuccess();
		}catch(BusinessException e){
			return returnError(e);
			
		}
	}
	
	/**
	 * 根据用户查询部门角色关系
	 * @return
	 * @author 高佳
	 * @date 2015年7月22日
	 * @update 
	 */
	@JSON
	public String queryOrgRoleByUserName(){
		try {			
			List<OrgRoleEntity> orgRoles = userService.queryOrgRolesByUserName(userVo.getUserName());
			//无相关角色信息返回空列表
			if(CollectionUtils.isEmpty(orgRoles)){
				userVo.setRoleEntityList(new ArrayList<RoleEntity>());
				return returnSuccess();
			}
			//遍历用户组织角色信息，获得所选组织中的角色信息返回到界面
			for (OrgRoleEntity orgRoleEntity : orgRoles) {
				if(orgRoleEntity.getCode().equals(userVo.getOrgCode())){
					userVo.setRoleEntityList(orgRoleEntity.getRoleList());
					break;
				}
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}


	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
}
