package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IRoleResourceService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IRoleService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.RoleVo;
import com.hoau.hoaureport.module.util.define.SymbolConstants;

/**
 * @author：高佳
 * @create：2015年6月9日 上午9:00:24
 * @description：角色管理Action
 */
@Controller
@Scope("prototype")
public class RoleAction extends AbstractAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private RoleVo roleVo;

	public RoleVo getRoleVo() {
		return roleVo;
	}

	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRoleResourceService roleResourceService;

	/**
	 * 跳转到角色管理界面
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */
	public String role() {
		return returnSuccess();
	}

	/**
	 * 角色查询
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */

	@JSON
	public String queryRole() {
		try {
			List<RoleEntity> roleEntityList = roleService.queryRole(
					roleVo.getRoleEntity(), limit, start);
			totalCount = roleService.queryRoleValueByEntityCount(roleVo
					.getRoleEntity());
			roleVo.setRoleEntityList(roleEntityList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}

	}

	/**
	 * 根据角色编号查询
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */

	@JSON
	public String queryRoleByCode() {
		try {
			/*
			 * roleVo.setRoleEntityList(roleService.queryRoleByCode(
			 * roleVo.getRoleEntity()));
			 */
			roleVo.setRoleEntity(roleService.queryRoleByCode(roleVo
					.getRoleEntity().getCode()));
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}

	}

	/**
	 * 查询所有角色信息
	 * 
	 * @return
	 * @author涂婷婷
	 * @date 2015年6月30日
	 */
	@JSON
	public String queryAllRoleInfo() {
		try {
			List<RoleEntity> roleList = roleService.queryAllRoleInfo();
			if (roleVo == null) {
				roleVo = new RoleVo();
			}
			roleVo.setRoleEntityList(roleList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	/**
	 * 查询所有特许经营角色信息
	 * 
	 * @return
	 * @author涂婷婷
	 * @date 2015年9月14日
	 */
	@JSON
	public String queryAllFranchiseRoleInfo() {
		try {
			List<RoleEntity> roleList = roleService.queryAllFranchiseRoleInfo();
			if (roleVo == null) {
				roleVo = new RoleVo();
			}
			roleVo.setRoleEntityList(roleList);
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 角色添加
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */

	@JSON
	public String addRole() {
		try {
			roleService.addRole(roleVo.getRoleEntity());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 角色删除
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */
	@JSON
	public String deleteRole() {

		try {
			List<RoleEntity> list = roleVo.getRoleEntityList();
			roleService.deleteRoleByList(list);
			return returnSuccess(MessageType.DELETE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	/**
	 * 角色修改
	 * 
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update
	 */

	public String updateRole() {
		try {
			roleService.updateRole(roleVo.getRoleEntity());
			return returnSuccess(MessageType.UPDATE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	@JSON
	public String updateRoleResource() {
		// 如果角色的编码为空，则直接返回
		if (roleVo == null || StringUtil.isEmpty(roleVo.getCurrRoleCode())) {
			return returnSuccess();
		}
		String roleCode = roleVo.getCurrRoleCode();
		String addResourceCodesString = roleVo.getResourceCodes();
		String deleteResourceCodesString = roleVo.getDeleteResourceCodes();
		addResourceCodesString = addResourceCodesString == null ? ""
				: addResourceCodesString;
		deleteResourceCodesString = deleteResourceCodesString == null ? ""
				: deleteResourceCodesString;
		// 前台传过来的角色的新增的权限编码，多个，用英文逗号","分隔
		String[] addResCodes = addResourceCodesString
				.split(SymbolConstants.EN_COMMA);
		// 前台传过来的角色的作废的权限编码，多个，用英文逗号","分隔
		String[] deleteResCodes = deleteResourceCodesString
				.split(SymbolConstants.EN_COMMA);
		// 将resourceCode转成set，便于修改
		Set<String> addResourceCodes = new HashSet<String>();
		addResourceCodes.addAll(Arrays.asList(addResCodes));
		Set<String> deleteResourceCodes = new HashSet<String>();
		deleteResourceCodes.addAll(Arrays.asList(deleteResCodes));
		addResourceCodes.remove(null);
		addResourceCodes.remove("");
		deleteResourceCodes.remove(null);
		deleteResourceCodes.remove("");
		roleResourceService.updateRoleResource(roleCode, addResourceCodes,
				deleteResourceCodes);
		return returnSuccess(MessageType.UPDATE_SUCCESS);
	}
}
