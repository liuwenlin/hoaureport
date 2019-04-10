package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.MonthSqlEntity;

/**
 * 整月报表生成SQL管理接口类
 * @author 273503
 *
 */
public interface IMonthSqlService {
	/**
	 * 总记录数
	 * @param sqlEntity
	 * @return
	 */
	Long queryTotalCount(MonthSqlEntity sqlEntity);
	/**
	 * 分页查询
	 * @param sqlEntity
	 * @param start
	 * @param limit
	 * @return
	 */
	List<MonthSqlEntity> queryMonthSql(MonthSqlEntity sqlEntity, int start,
			int limit);
	/**
	 * 新增
	 * @param sqlEntity
	 */
	void addMonthSql(MonthSqlEntity sqlEntity);
	/**
	 * 修改
	 * @param sqlEntity
	 */
	void updateMonthSql(MonthSqlEntity sqlEntity);
	/**
	 * 删除
	 * @param ids
	 */
	void removeMonthSql(List<String> ids);

}
