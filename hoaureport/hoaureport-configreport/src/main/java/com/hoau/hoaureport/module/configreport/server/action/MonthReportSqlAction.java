package com.hoau.hoaureport.module.configreport.server.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.configreport.server.service.IMonthSqlService;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthSqlEntity;

/**
 * 整月报表生成sql管理
 * @author 273503
 *
 */
@Controller
@Scope("prototype")
public class MonthReportSqlAction extends AbstractAction{
	private static final long serialVersionUID = -7139141634266450683L;
	private Logger log = Logger.getLogger(this.getClass());
	private MonthSqlEntity sqlEntity;
	private List<MonthSqlEntity> sqlList;
	private List<String> ids;
	@Autowired
	private IMonthSqlService monthSqlService;
	/**
	 * 数据查询
	 * @return
	 */
	public String queryMonthSql(){
		try {
			totalCount =monthSqlService.queryTotalCount(sqlEntity);
			sqlList =monthSqlService.queryMonthSql(sqlEntity,start,limit);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("queryMonthSql_err:",e);
		}
		return this.returnSuccess();
	}
	/**
	 * 新增
	 * @return
	 */
	public String addMonthSql(){
		
		try {
			monthSqlService.addMonthSql(sqlEntity);
			
		} catch (BusinessException e) {
			log.error("queryMonthSql_err:",e);
			return returnError(e);
		}
		return this.returnSuccess();
	}
	
	/**
	 * 数据更新
	 * @return
	 */
	public String updateMonthSql(){
		try {
			monthSqlService.updateMonthSql(sqlEntity);
			
		} catch (BusinessException e) {
			log.error("updateMonthSql_err:",e);
			return returnError(e);
		}
		return this.returnSuccess();
	}
	/**
	 * 删除
	 * @return
	 */
	public String removeMonthSql(){
		try {
			monthSqlService.removeMonthSql(ids);
			
		} catch (BusinessException e) {
			log.error("updateMonthSql_err:",e);
			return returnError(e);
		}
		return this.returnSuccess();
	}
	
	public MonthSqlEntity getSqlEntity() {
		return sqlEntity;
	}
	public void setSqlEntity(MonthSqlEntity sqlEntity) {
		this.sqlEntity = sqlEntity;
	}
	public List<MonthSqlEntity> getSqlList() {
		return sqlList;
	}
	public void setSqlList(List<MonthSqlEntity> sqlList) {
		this.sqlList = sqlList;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	
	
	
	
}
