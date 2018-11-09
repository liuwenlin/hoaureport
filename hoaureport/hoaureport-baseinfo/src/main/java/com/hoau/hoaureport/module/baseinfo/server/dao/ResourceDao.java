package com.hoau.hoaureport.module.baseinfo.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ResourceEntity;


/**
 * @author：高佳
 * @create：2015年6月8日 下午7:18:28
 * @description：
 */
@Repository
public interface ResourceDao {
	
	/**
	 * 添加
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update 
	 */
	void addResourceEntity(ResourceEntity resourceEntity);
	
	/**
	 * 修改
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update 
	 */
	void updateResourceEntity(ResourceEntity resourceEntity);
	
	/**
	 * 删除
	 * @param resourceEntity
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update 
	 */
	void deleteResourceEntity(ResourceEntity resourceEntity);

	/**
	 * 根据编码查权限
	 * @param key
	 * @return
	 * @author 高佳
	 * @date 2015年6月8日
	 * @update 
	 */
	ResourceEntity queryResourceByCode(Map<String,Object> map);

	/**
	 * 根据code查询下级菜单
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月8日
	 * @update 
	 */
	List<ResourceEntity> queryDirectChildResourceByCode(Map<String, Object> map);

	/**
	 * 查询系统根权限
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月10日
	 * @update 
	 */
	ResourceEntity queryResourceRoot(Map<String, Object> map);

	/**
	 * 
	 * @param map
	 * @return
	 * @author 高佳
	 * @date 2015年6月10日
	 * @update 
	 */
	List<ResourceEntity> queryResourceBatchByUri(Map<String, Object> map);

	/**
	 * 根据条件查询权限
	 * @param entity
	 * @return
	 * @author 高佳
	 * @date 2015年7月13日
	 * @update 
	 */
	List<ResourceEntity> queryResourceByEntity(ResourceEntity entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @param rowBounds
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月15日
	 * @update 
	 */
	List<ResourceEntity> queryResourceByEntity(ResourceEntity entity,RowBounds rowBounds);

	/**
	 * 条件查询
	 * @param entity
	 * @param rowBounds
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	List<ResourceEntity> queryResourceByResourceEntity(ResourceEntity entity,RowBounds rowBounds);
	
	/**
	 * 条件查询总数
	 * @param entity
	 * @return
	 * @author 李旭锋
	 * @date 2015年7月16日
	 * @update 
	 */
	Long queryCountByRserouce(ResourceEntity entity);
}
