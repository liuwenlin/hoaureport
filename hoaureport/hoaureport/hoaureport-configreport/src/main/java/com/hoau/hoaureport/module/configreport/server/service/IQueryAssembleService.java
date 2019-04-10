package com.hoau.hoaureport.module.configreport.server.service;

import java.io.IOException;
import java.util.List;

import com.hoau.hbdp.framework.service.IService;
import com.hoau.hoaureport.module.configreport.shared.domain.ExcelReturn;
import com.hoau.hoaureport.module.configreport.shared.domain.ExportQuery;
import com.hoau.hoaureport.module.configreport.shared.domain.Field;
import com.hoau.hoaureport.module.configreport.shared.domain.NameValue;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.configreport.shared.domain.ShowburEntity;
import com.hoau.hoaureport.module.configreport.shared.domain.TableHead;
import com.hoau.hoaureport.module.configreport.shared.exception.BamSysException;

public interface IQueryAssembleService extends IService {

	public Long totalshowQuerySql(QuerySql queryAssemble);

	public List<QuerySql> showQuerySql(QuerySql queryAssemble, int start,
			int limit) throws BamSysException;

	public List<ExportQuery> execSqlAll(String sql, String queryParam,
			int length, int start, int limit);

	public List<TableHead> queryTableHeads(String[] head);

	public List<Field> queryField(int length);

	public ExcelReturn toExcelPort(String sql,String remark, String queryParam, int length,
			String[] head, int startNumber, int limitNumber);

	public long checkMaxNumber(String sql, String queryParam);

	public List<NameValue> queryCombo(String queryParam, String sql);

	public QuerySql querySqlById(String id);

//	public ExcelReturn toZipPort(String sql, String remark, String queryParam,
//			int length, String[] head, int startNumber, int limitNumber,String username) throws IOException;

//	public ExcelReturn toZipPort(QuerySql querysqlInfo, int length,
//			String[] head, int startNumber, int limitNumber, String username,
//			String downCode);

	public ExcelReturn toZipPort(QuerySql querysqlInfo, String queryParam,
			int length, String[] head, int startNumber, int limitNumber,
			String username, String downCode,String charCode)throws IOException;

	public List<ShowburEntity> getShowburInfo(String id);
	
	public String sqlAuthority(String tmp_sql,String userParentOrgName);

	public ExcelReturn toZipPortParentOrg(QuerySql querysqlInfo, String queryParam,
								 int length, String[] head, int startNumber, int limitNumber,
								 String username, String downCode,String charCode,String userParentOrgName)throws IOException;
}
