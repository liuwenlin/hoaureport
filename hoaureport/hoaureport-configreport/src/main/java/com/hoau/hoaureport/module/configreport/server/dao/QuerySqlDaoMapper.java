package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.RoleEntity;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.domain.SqlRoleEntity;

@Repository
public interface QuerySqlDaoMapper {
	public void insertQuerySql(QuerySql querySql);

	public void updateQuerySql(QuerySql querySql);

	public int deleteQuerySql(List<String> querySqlNos);

	public List<QuerySql> selectPageQuerySql(QuerySql querySql, RowBounds rb);

	public Long totalQuerySqlCount(QuerySql querySql);

	public List<RoleEntity> querySqlRoleList(String sqlCode);

	public int deleteSqlRoles(Map<String, Object> map);

	public void addSqlRoles(SqlRoleEntity sqlRoles);

	public int deleteSqlRoleById(Map<String, String> map);

	public QuerySql querySqlInfo(QuerySql querySql);
}
