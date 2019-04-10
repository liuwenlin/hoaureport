package com.hoau.hoaureport.module.baseinfo.server.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IRoleService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.RoleException;
import com.hoau.hoaureport.module.baseinfo.server.cache.RoleCache;
import com.hoau.hoaureport.module.baseinfo.server.dao.RoleDao;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.util.BaseinfoConstants;
import com.hoau.hoaureport.module.util.UUIDUtil;


/**
 * @author：涂婷婷
 * @create：2015年6月9日 下午3:56:10
 * @description：
 */

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * 角色删除
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	@Override
	@Transactional
	public void deleteRole(RoleEntity roleEntity) {
		Date dt = new Date();
		roleEntity.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
		roleEntity.setModifyDate(dt);
		roleDao.deleteRole(roleEntity);	
		//失效缓存中的角色信息
		getRoleCache().invalid(roleEntity.getCode());
	}
	
	/**
	 * 多条角色删除
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	@Override
	@Transactional
	public void deleteRoleByList(List<RoleEntity> roleEntityList) {
		Date dt = new Date();
		for (RoleEntity role : roleEntityList) {			
			role.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
			role.setModifyDate(dt);
			role.setActive(BaseinfoConstants.NO);
			role.setVersionNo(UUIDUtil.getVersion());
			deleteRole(role);
			//失效缓存中的角色信息			
			getRoleCache().invalid(role.getCode());
		}
	}

	/**
	 * 角色修改
	 * @param 
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	@Override
	@Transactional
	public void updateRole(RoleEntity roleEntity) {
		
		if(StringUtil.isEmpty(roleEntity.getCode())){
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		} if(StringUtil.isEmpty(roleEntity.getName()))
		{
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		}else
		{
			Date date=new Date();
			RoleEntity entity=roleDao.queryRoleByCode(roleEntity.getCode());
			entity.setModifyDate(date);
			entity.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
			entity.setVersionNo(UUIDUtil.getVersion());
			entity.setActive(BaseinfoConstants.NO);
			roleDao.deleteRole(entity);
			
			Date da=new Date();
			roleEntity.setActive(BaseinfoConstants.ACTIVE);
			roleEntity.setId(UUIDUtil.getUUID());
			roleEntity.setVersionNo(UUIDUtil.getVersion()); 
			roleEntity.setCreateDate(da);
			roleEntity.setCreateUser(HoauReportUserContext.getCurrentUser().getUserName());
			roleEntity.setModifyDate(da);
			roleEntity.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
			roleDao.addRole(roleEntity);
			//失效缓存中的角色信息
			getRoleCache().invalid(roleEntity.getCode());
		}
		
	}

	/**
	 * 根据角色编号查询角色名称
	 * @param RoleEntity
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月17日
	 * @update 根据条件进行查询
	 */
	public String queryNameByName(String name)
	{
		return roleDao.queryNameByName(name);
	}


	
	/**
	 *  角色添加
	 * @param
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	@Override
	@Transactional
	public void addRole(RoleEntity roleEntity) {
		if(StringUtil.isEmpty(roleEntity.getCode())){
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		}else if(StringUtil.isEmpty(roleEntity.getName()))
		{
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		}
		else if(!CollectionUtils.isEmpty(this.queryRoleByCodeAndName(roleEntity))){
			throw new RoleException(RoleException.ROLE_EXISTL);
		}else
		{
			Date dt = new Date();
			roleEntity.setActive(BaseinfoConstants.ACTIVE);
			roleEntity.setId(UUIDUtil.getUUID());
			roleEntity.setVersionNo(UUIDUtil.getVersion()); 
			roleEntity.setCreateDate(dt);
			roleEntity.setCreateUser(HoauReportUserContext.getCurrentUser().getUserName());
			roleEntity.setModifyDate(dt);
			roleEntity.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
			roleDao.addRole(roleEntity);
		}
		
	}	
/*	@Override
	public List<EmployeeEntity> queryEmployeeInfoByAccount(
			EmployeeEntity employeeEntity, int limit, int start) {
		return null;
	}*/

	/**
	 * 根据角色编号和名称查询
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	
	public List<RoleEntity> queryRoleByCodeAndName(RoleEntity roleEntity)
	{		
		return roleDao.queryRoleByCodeAndName(roleEntity);
	}
	
	/**
	 * 根据条件查询角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月9日
	 * @update 
	 */
	@Override
	public List<RoleEntity> queryRole(RoleEntity roleEntity,int limit,int start) {
		
		roleEntity.setActive(BaseinfoConstants.ACTIVE);
		return roleDao.queryRoleEntity(roleEntity);
	}

	/* (non-Javadoc)
	 * @see com.hoau.Butterfly.module.bse.baseinfo.api.server.service.IRoleService#queryRoleValueByEntityCount(com.hoau.Butterfly.module.bse.baseinfo.api.shared.domain.RoleEntity)
	 */
	public Long queryRoleValueByEntityCount(RoleEntity roleEntity)
	{
		return roleDao.queryRoleValueByEntityCount(roleEntity);
	}

	/* (non-Javadoc)
	 * @see com.hoau.Butterfly.module.bse.baseinfo.api.server.service.IRoleService#queryRoleByCode(java.lang.String)
	 */
	@Override
	public RoleEntity queryRoleByCode(String code) {
		ICache<String, RoleEntity> roleCache = this.getRoleCache();
		RoleEntity role = roleCache.get(code);
		return role;		
	}
	
	/**
	 * 获取角色缓存实例
	 * @return
	 * @author 高佳
	 * @date 2015年6月13日
	 * @update 
	 */
	@SuppressWarnings("unchecked")
	private ICache<String, RoleEntity> getRoleCache(){
		ICache<String, RoleEntity> roleCache = CacheManager.getInstance().getCache(RoleCache.ROLE_CACHE_UUID);
		return roleCache;
				
	}
	
	
	/**
	 * 查询所有角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月30日
	 */

	@Override
	public List<RoleEntity> queryAllRoleInfo() {
		List<RoleEntity> roleList=roleDao.queryAllRoleInfo();
		return roleList;
	}
	
	/**
	 * 查询所有特许经营角色信息
	 * @return
	 * @author 涂婷婷
	 * @date 2015年6月30日 
	 */
	@Override
	public List<RoleEntity> queryAllFranchiseRoleInfo(){
		List<RoleEntity> roleList=roleDao.queryFranchiseAllRoleInfo();
		return roleList;
	}
	
	@Override
	public Long queryRoleCount() {
		return roleDao.queryRoleCount();
	}

	
}

