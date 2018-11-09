package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.ExportQuery;
import com.hoau.hoaureport.module.configreport.shared.domain.NameValue;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;

@Repository
public interface QueryAssembleDaoMapper {
	public Long totalshowQuerySql(QuerySql queryAssemble);

	public List<QuerySql> showQuerySql(QuerySql queryAssemble, RowBounds rb);

	public List<ExportQuery> execSqlAll(String sql, Map<String,String> queryParam,int length,boolean isMaxCheck,int start, int limit);

	public List<NameValue> execSqlQuery(String sql);

	public long checkMaxNumber(String countSql, Map<String,String> queryParam);

	public List<NameValue> queryCombo(Map<String, String> controlParam, String sql);

	public QuerySql querySqlById(String id); 

}
