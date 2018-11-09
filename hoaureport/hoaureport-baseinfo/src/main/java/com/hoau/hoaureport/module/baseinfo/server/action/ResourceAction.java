package com.hoau.hoaureport.module.baseinfo.server.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.hoau.hbdp.framework.define.FunctionType;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IResourceService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IRoleResourceService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IRoleService;
import com.hoau.hoaureport.module.baseinfo.api.shared.define.DictionaryValueConstants;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.MessageType;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.ResourceException;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.ResourceTreeNode;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.ResourceVo;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年6月13日 上午11:13:44
 * @description：资源权限action
 */
@Controller
@Scope("prototype")
public class ResourceAction extends AbstractAction {
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IRoleResourceService roleResourceService;
	@Autowired
	private IRoleService roleService;
	
	/**
	 * 用于菜单树展开路径设置
	 */
	private String path = "";
	// 菜单树展开路径集合
	private Set<String> pathList;

	/**
	 * 资源权限vo
	 */
	private ResourceVo resourceVo;

	private String node;

	/**
	 * 
	 */
	private List<ResourceTreeNode<ResourceEntity>> nodes;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String resource() {
		return returnSuccess();
	}

	/**
	 * 根据上级权限节点查询下级权限(包含checked)
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update
	 */
	public String queryResourceByParentResChecked() {
		return this.queryResourceByParentRes(true);
	}
	
	/**
	 * 查询下级(不包含checked)
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update 
	 */
	public String queryResourceByParentRes(){
		return this.queryResourceByParentRes(false);
	}
	
	/**
	 * 查询
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月20日
	 * @update 
	 */
	@JSON
	public String queryTreePathForName() {
		try {
			ResourceEntity queryEntity = new ResourceEntity();
			queryEntity.setName(node);
			queryEntity
					.setBelongSystemType(DictionaryValueConstants.BSE_RESOURCE_BELONG_SYSTEM_TYPE_WEB);
			List<ResourceEntity> resList = resourceService
					.queryResourceByEntity(queryEntity);
			Set<String> resCodes = HoauReportUserContext.getCurrentUser().getOrgResCodes();
			pathList = new HashSet<String>();
			for (ResourceEntity res : resList) {
				if (FunctionType.BUTTON.equalsIgnoreCase(res.getResType())) {
					continue;

				}
				if (!CollectionUtils.isEmpty(resCodes)) {
					if (resCodes.contains(res.getCode())) {
						node = res.getCode();
						this.expendTreePath();
						pathList.add(new String(path));
						path = "";
					}
				}
			}
			return returnSuccess();
		} catch (ResourceException e) {
			return returnError(e);
		}
	}
	/**
	 * 菜单树的展开路径加载
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月13日
	 * @update 
	 */
	@JSON
	public String expendTreePath() {
		try {
			ResourceEntity rootRes = resourceService
					.queryResourceRoot(DictionaryValueConstants.BSE_RESOURCE_BELONG_SYSTEM_TYPE_WEB);
			ResourceEntity parentRes = resourceService
					.queryResourceByCode(node);
			String parentNode = node;
			while (!rootRes.getCode().equals(parentNode)) {
				path = "/" + parentNode + path;
				parentRes = resourceService.queryResourceByCode(parentNode)
						.getParentRes();
				if (parentRes == null) {
					path = "";
					break;
				}
				parentNode = parentRes.getCode();
			}
			path = "/" + rootRes.getCode() + path;
			return returnSuccess();
		} catch (ResourceException e) {
			return returnError(e);
		}
	}
	@JSON
	public String addResource(){
		try{
			resourceService.addResourceEntity(resourceVo.getResourceEntityDetail());
			return returnSuccess(MessageType.ADD_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	@JSON
	public String updateResource(){
		try{
			resourceService.updateResourceEntity(resourceVo.getResourceEntityDetail());
			return returnSuccess(MessageType.UPDATE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	@JSON
	public String deleteResource(){
		try{
			resourceService.deleteResourceEntity(resourceVo.getResourceEntityList());
			return returnSuccess(MessageType.DELETE_SUCCESS);
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	/**
	 * 根据条件查询
	 * 
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update
	 */
	@JSON
	public String queryResourceByExample() {
		try {
			totalCount = resourceService.queryCountByResource(resourceVo.getResourceEntityDetail());
			
			resourceVo.setResourceEntityList(resourceService
					.queryResourceByEntity(resourceVo
							.getResourceEntityDetail(),limit, start));
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}

	}

	public String queryResourceByParentRes(boolean hasChecked) {
		String parentRes = node;
		String roleCode = null;
		if (resourceVo != null) {
			if (!StringUtils.isBlank(resourceVo.getCurrRoleCode())) {
				roleCode = resourceVo.getCurrRoleCode();
			}
		}
		// 根据父节点查询资源权限
		List<ResourceEntity> resources = resourceService
				.queryResourcesByParentCode(parentRes);
		this.nodes = new ArrayList<ResourceTreeNode<ResourceEntity>>();
		// 查出角色所包含的所有权限
		Set<String> set = null;
		if (hasChecked && StringUtils.isNotBlank(roleCode)) {
			// 根据角色编码查询角色信息，包含角色权限数据
			RoleEntity role = roleService.queryRoleByCode(roleCode);
			set = role.getResIds();
		}
		// 将权限对象转换成树结点：
		for (ResourceEntity res : resources) {
			ResourceTreeNode<ResourceEntity> treeNode = new ResourceTreeNode<ResourceEntity>();
			treeNode.setId(res.getFunctionCode());
			treeNode.setText(res.getName());
			treeNode.setExpandable(!BaseinfoConstants.YES.equalsIgnoreCase(res
					.getLeafFlag()));
			treeNode.setDisplayOrder(res.getDisplayOrder());
			treeNode.setLeaf(BaseinfoConstants.YES.equalsIgnoreCase(res
					.getLeafFlag()));
			if (res.getParentRes() != null) {
				treeNode.setParentId(res.getParentRes().getFunctionCode());
			} else {
				treeNode.setParentId(null);
			}

			if (hasChecked) {
				// 如果角色包含该权限
				if (!CollectionUtils.isEmpty(set)
						&& set.contains(res.getCode())) {
					treeNode.setChecked(true);
				} else {
					treeNode.setChecked(false);
				}
			}
			treeNode.setResourceEntity(res);
			nodes.add(treeNode);
		}
		return returnSuccess();
	}

	public ResourceVo getResourceVo() {
		return resourceVo;
	}

	public void setResourceVo(ResourceVo resourceVo) {
		this.resourceVo = resourceVo;
	}

	public List<ResourceTreeNode<ResourceEntity>> getNodes() {
		return nodes;
	}

	public void setNodes(List<ResourceTreeNode<ResourceEntity>> nodes) {
		this.nodes = nodes;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<String> getPathList() {
		return pathList;
	}

	public void setPathList(Set<String> pathList) {
		this.pathList = pathList;
	}

}
