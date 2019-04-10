package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;

/**
 * @author：高佳
 * @create：2015年6月8日 下午6:34:58
 * @description：
 */
public interface IResourceService {

	/**
	 * 根据上级查询权限
	 * @param parentCode
	 * @return
	 * @author 高佳
	 * @date 2015年6月8日
	 * @update 
	 */
	List<ResourceEntity> queryResourcesByParentCode(String parentCode);

	/**
	 * 根据编码批量查询权限
	 * @param array
	 * @return
	 * @author 高佳
	 * @date 2015年6月8日
	 * @update 
	 */
	List<ResourceEntity> queryResourceBatchByCode(String[] array);

	/**
	 * 根据条件查询权限
	 * @param queryEntity
	 * @return
	 * @author 高佳
	 * @date 2015年7月13日
	 * @update 
	 */
	List<ResourceEntity> queryResourceByEntity(ResourceEntity queryEntity);
	
	
	/**
	 * 分页查询
	 * @param queryEntity
	 * @param limit
	 * @param start
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update 
	 */
	List<ResourceEntity> queryResourceByEntity(ResourceEntity queryEntity, int limit, int start);

	/**
	 * 精确查询 查询权限的根结点 根据权限名称查询权限的所有上级 ，上下级通过CODE,PARENT_RES来关联
	 * @param bseResourceBelongSystemTypeWeb
	 * @return
	 * @author 高佳
	 * @date 2015年7月13日
	 * @update 
	 */
	ResourceEntity queryResourceRoot(String bseResourceBelongSystemTypeWeb);

	/**
	 * 根据code查询权限
	 * @param node
	 * @return
	 * @author 高佳
	 * @date 2015年7月13日
	 * @update 
	 */
	ResourceEntity queryResourceByCode(String node);
	
	/**
	 * 添加
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	void addResourceEntity(ResourceEntity resourceEntity);

	/**
	 * 修改
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	void updateResourceEntity(ResourceEntity resourceEntity);
	
	/**
	 * 删除
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	void deleteResourceEntity(List<ResourceEntity> resourceEntity);
	
	/**
	 * 条件查询总数
	 * @param resourceEntity
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	Long queryCountByResource(ResourceEntity resourceEntity);
}
