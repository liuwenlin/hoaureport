package com.hoau.hoaureport.module.login.server.action;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.hoau.hbdp.framework.define.FunctionType;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IResourceService;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IUserMenuService;
import com.hoau.hoaureport.module.baseinfo.api.shared.define.DictionaryValueConstants;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.TreeNode;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserMenuEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.ResourceException;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.ResourceTreeNode;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.login.shared.exception.LoginException;
import com.hoau.hoaureport.module.util.BaseinfoConstants;

/**
 * @author：高佳
 * @create：2015年6月8日 下午6:34:10
 * @description：菜单Action
 */
@Controller
@Scope("prototype")
public class MenuAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IUserMenuService userMenuService;

	/**
	 * 功能信息集合
	 */
	private List<ResourceEntity> resources;

	/**
	 * 父功能编码
	 */
	private String node;

	/**
	 * 判断是否点击了用户常用菜单
	 */
	private boolean checkUserMenu;

	/**
	 * 功能树节点集合
	 */
	@SuppressWarnings("rawtypes")
	private List<ResourceTreeNode> nodes;

	/**
	 * 常用菜单树节点集合
	 */
	@SuppressWarnings("rawtypes")
	private List<TreeNode<ResourceEntity, TreeNode>> menuNodes;

	/**
	 * 用户已经设置的常用菜单ID集合
	 */
	private List<String> userRes;

	/**
	 * 用于菜单树展开路径设置
	 */
	private String path = "";

	// 菜单树展开路径集合
	private Set<String> pathList;

	/**
	 * 加载菜单树
	 * 
	 * @return
	 * @author 高佳
	 * @date 2015年6月8日
	 * @update
	 */
	@SuppressWarnings("rawtypes")
	@JSON
	public String loadTree() {
		nodes = new ArrayList<ResourceTreeNode>();
		try {
			// 得到用户常用菜单

			if (checkUserMenu) { // 得到用户常用菜单节点列表
				try {
					nodes = findUserMenu();
				} catch (ResourceException e) {
					return returnSuccess(e.getErrorCode());
				}
				return returnSuccess();
			}

			// 得到点击的节点下的子节点菜单集合
			List<ResourceEntity> resources = findResources(node);
			for (ResourceEntity res : resources) {
				// 转换菜单对象为节点对象
				ResourceTreeNode<ResourceEntity> treeNode = changeResToTreeNode(res);
				nodes.add(treeNode);
			}
			return returnSuccess();
		} catch (BusinessException e) {
			return returnSuccess(e.getErrorCode());
		}
	}

	// 得到常用菜单的功能方法
	@SuppressWarnings("rawtypes")
	private List<ResourceTreeNode> findUserMenu() {
		Map<String, UserMenuEntity> userMenusMap = this.createUserMenus();
		List<ResourceTreeNode> userMenusTreeNode = new ArrayList<ResourceTreeNode>();
		// 通过用户常用菜单中的权限集合，得到用户常用菜单对象
		List<ResourceEntity> resList = resourceService
				.queryResourceBatchByCode(userMenusMap.keySet().toArray(
						new String[userMenusMap.size()]));
		if (resList != null && resList.size() > 0) {
			for (ResourceEntity res : resList) {
				UserMenuEntity userMenu = userMenusMap.get(res.getCode());
				res.setDisplayOrder(userMenu.getDisplayOrder().toString());
				ResourceTreeNode<ResourceEntity> treeNode = changeResToTreeNode(res);
				treeNode.setId(res.getCode() + "_usermenu");
				String cls = treeNode.getCls();
				treeNode.setCls(cls.substring(0, cls.length() - 1) + "3");
				userMenusTreeNode.add(treeNode);
			}
			// 对常用菜单进行排序
			Collections.sort(userMenusTreeNode,
					new Comparator<ResourceTreeNode>() {

						public int compare(ResourceTreeNode o1,
								ResourceTreeNode o2) {
							Integer r1 = Integer.parseInt(o1.getDisplayOrder());
							Integer r2 = Integer.parseInt(o2.getDisplayOrder());
							return r1.compareTo(r2);
						}
					});
		}
		return userMenusTreeNode;
	}

	// 用户已经设置的常用菜单与资源列表
	private Map<String, UserMenuEntity> createUserMenus() {
		// 获得当前用户
		UserEntity user = (UserEntity) UserContext.getCurrentUser();
		// 获得当前用户当前所在部门的所有权限信息集合
		Set<String> userAccessResCodes = user.getOrgResCodes();
		// 用户菜单CODE与菜单MAP
		Map<String, UserMenuEntity> userMenusMap = new HashMap<String, UserMenuEntity>();
		// 用户已经设置的常用菜单对象
		List<UserMenuEntity> userMenus = userMenuService
				.queryUserMenuByUserCode(user.getEmployee().getEmpCode());
		String[] resCodes = new String[userMenus.size()];
		for (int i = 0; i < userMenus.size(); i++) {
			if (userAccessResCodes.contains(userMenus.get(i).getResourceCode())) {
				resCodes[i] = userMenus.get(i).getResourceCode();
				userMenusMap.put(resCodes[i], userMenus.get(i));
			}
		}
		return userMenusMap;
	}

	// 查询功能方法
	private List<ResourceEntity> findResources(String parentCode) {
		// 获得当前用户
		UserEntity user = HoauReportUserContext.getCurrentUser();
		// 当前用户当前部门功能编码集合
		Set<String> resCodes = user.getOrgResCodes();
		// 当前部门未配置角色

		if (resCodes == null) {
			throw new LoginException(LoginException.CURRENT_DEPT_NO_ROLE);
		}

		// 菜单对象集合
		List<ResourceEntity> resNodes = new ArrayList<ResourceEntity>();
		List<ResourceEntity> childResources = resourceService
				.queryResourcesByParentCode(parentCode);
		for (ResourceEntity res : childResources) {
			// 判断权限是否为空
			if (res == null) {
				continue;
			}
			// 判断权限的类型是否为按钮
			if (StringUtil.equalsIgnoreCase(FunctionType.BUTTON,
					res.getResType())) {
				continue;
			}

			// 判断是否是WEB菜单
			if (StringUtil
					.equalsIgnoreCase(
							DictionaryValueConstants.BSE_RESOURCE_BELONG_SYSTEM_TYPE_APP,
							res.getBelongSystemType())) {
				continue;
			}

			// 判断权限为非检查的权限时，直接增加到权限列表中
			if (StringUtil.equalsIgnoreCase(BaseinfoConstants.NO, res.getChecked())) {
				resNodes.add(res);
				continue;
			}
			if (resCodes.contains(res.getCode())) {
				resNodes.add(res);
			}

		}
		return resNodes;
	}

	// 转换菜单对象为树节点对象
	private ResourceTreeNode<ResourceEntity> changeResToTreeNode(
			ResourceEntity res) {
		ResourceTreeNode<ResourceEntity> treeNode = new ResourceTreeNode<ResourceEntity>();
		treeNode.setId(res.getFunctionCode());
		treeNode.setText(res.getName());
		treeNode.setExpandable(!BaseinfoConstants.YES.equalsIgnoreCase(res
				.getLeafFlag()));
		treeNode.setCls(res.getCls());
		treeNode.setIconCls(res.getIconCls());
		treeNode.setDisplayOrder(res.getDisplayOrder());
		if (res.getResType().equalsIgnoreCase(FunctionType.MENU)) {
			treeNode.setUri(res.getUri());
			treeNode.setLeaf(true);
		} else {
			treeNode.setLeaf(false);
		}
		if (res.getParentRes() != null) {
			treeNode.setParentId(res.getParentRes().getFunctionCode());
		} else {
			treeNode.setParentId(null);
		}
		// treeNode.setEntity(res);
		return treeNode;
	}
	
	
	/**
	 * 功能菜单树的节点查询与展开路径加载
	 * @return
	 * @author 高佳
	 * @date 2015年7月13日
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
	 * @author 高佳
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
			path = rootRes.getCode() + path;
			return returnSuccess();
		} catch (ResourceException e) {
			return returnError(e);
		}
	}

	public List<ResourceEntity> getResources() {
		return resources;
	}

	public void setResources(List<ResourceEntity> resources) {
		this.resources = resources;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public boolean isCheckUserMenu() {
		return checkUserMenu;
	}

	public void setCheckUserMenu(boolean checkUserMenu) {
		this.checkUserMenu = checkUserMenu;
	}

	public List<ResourceTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ResourceTreeNode> nodes) {
		this.nodes = nodes;
	}

	public List<TreeNode<ResourceEntity, TreeNode>> getMenuNodes() {
		return menuNodes;
	}

	public void setMenuNodes(List<TreeNode<ResourceEntity, TreeNode>> menuNodes) {
		this.menuNodes = menuNodes;
	}

	public List<String> getUserRes() {
		return userRes;
	}

	public void setUserRes(List<String> userRes) {
		this.userRes = userRes;
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
