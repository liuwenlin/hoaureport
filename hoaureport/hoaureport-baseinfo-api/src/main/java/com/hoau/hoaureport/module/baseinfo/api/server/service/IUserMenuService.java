package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserMenuEntity;

/**
 * @author：高佳
 * @create：2015年6月8日 下午6:54:50
 * @description：用户常用菜单service
 */
public interface IUserMenuService {

	List<UserMenuEntity> queryUserMenuByUserCode(String empCode);

}
