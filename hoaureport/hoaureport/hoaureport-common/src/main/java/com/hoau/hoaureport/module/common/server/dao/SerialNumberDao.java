package com.hoau.hoaureport.module.common.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.common.shared.domain.SerialNumberRuleEntity;

/**
 * @author：高佳
 * @create：2015年8月19日 上午11:05:48
 * @description：
 */
@Repository
public interface SerialNumberDao {

	/**
	 * 根据code查询序号实体并添加悲观锁
	 * @param code
	 * @return
	 * @author 高佳
	 * @date 2015年8月19日
	 * @update 
	 */
	SerialNumberRuleEntity querySerialNumberRuleForUpdate(String code);

	/**
	 * 新增序号信息
	 * @param serialNumberRuleEntity
	 * @author 高佳
	 * @date 2015年8月19日
	 * @update 
	 */
	void addSerialNumberRule(SerialNumberRuleEntity serialNumberRuleEntity);

	/**
	 * 更新序号信息
	 * @param serialNumberRuleEntity
	 * @author 高佳
	 * @date 2015年8月19日
	 * @update 
	 */
	void updateSerialNumberRule(SerialNumberRuleEntity serialNumberRuleEntity);

}
