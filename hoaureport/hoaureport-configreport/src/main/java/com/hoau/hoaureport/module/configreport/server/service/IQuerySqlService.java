package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hbdp.framework.service.IService;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.exception.BamSysException;
import com.hoau.hoaureport.module.configreport.shared.vo.RoleThreeVo;

public interface IQuerySqlService extends IService {

	public void saveQuerySql(QuerySql querySql) throws BamSysException;

	public void modifyQuerySql(QuerySql querySql) throws BamSysException;

	public int toVoidQuerySql(List<String> querySqlNos);

	public List<QuerySql> showPageQuerySql(QuerySql querySql, int start,
			int limit);

	public Long totalQuerySqlCount(QuerySql querySql);

	public List<RoleThreeVo> querySqlRoleList(String sqlCode);

	public QuerySql querySqlInfo(QuerySql querySql);
}
