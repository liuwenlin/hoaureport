package com.hoau.hoaureport.module.job.server.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.common.shared.util.FolderUtils;
import com.hoau.hoaureport.module.configreport.server.dao.MonthSqlEntityMapper;
import com.hoau.hoaureport.module.configreport.server.service.IQueryAssembleService;
import com.hoau.hoaureport.module.configreport.server.service.IWayBillZipService;
import com.hoau.hoaureport.module.configreport.shared.domain.ExcelReturn;
import com.hoau.hoaureport.module.configreport.shared.domain.MonthSqlEntity;
import com.hoau.hoaureport.module.configreport.shared.domain.QuerySql;
import com.hoau.hoaureport.module.job.server.service.IAllMonthSqlJobService;
/**
 * 整月报表生成zip服务类
 * @author 273503
 *
 */
@Service
public class AllMonthSqlJobService implements IAllMonthSqlJobService {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private MonthSqlEntityMapper monthSqlEntityMapper;
	@Autowired
	private IQueryAssembleService queryAssembleService;
	@Autowired
	private IWayBillZipService wayBillZipService;
	
	@Override
	public void allMonthToZip(Date date) {
		MonthSqlEntity  monthsql = new MonthSqlEntity();
		monthsql.setSqlType(FolderUtils.FILE_EXTENSION_CODE1002);
		
		List<MonthSqlEntity> sqllist =monthSqlEntityMapper.selectListBysqlType(monthsql);
		if(sqllist ==null || sqllist.size()<1){
			log.info("整月报表sql查询:数据为空："+FolderUtils.dateToStr(date, "yyyy-MM-dd"));
			return;
		}
		Date begindate = FolderUtils.addDateByMonth(date, -1);//上月
		begindate=FolderUtils.firstDay(begindate,1);//上月第一天
		Date endDate = FolderUtils.firstDay(date,1);//本月第一天
		StringBuffer buf = new StringBuffer();
		buf.append("BEGINDATE").append(":").append(FolderUtils.dateToStr(begindate));
		buf.append(",");
		buf.append("ENDDATE").append(":").append(FolderUtils.dateToStr(endDate));
		String queryParam=buf.toString();
		
		for(MonthSqlEntity ms:sqllist){
			try {
				ms.setSqlBodyStr(new String(ms.getSqlBody(),ItfConifgConstant.SQL_BLOB_CODE));
				queryToZip(ms,queryParam);
			} catch (Exception e) {
				log.error("blob_string2:",e);
			}
			
		}
		
	}
	
	private void queryToZip(MonthSqlEntity sqlEntity,String queryParam){
		try {
			QuerySql querysql = queryAssembleService.querySqlById(sqlEntity.getSqlId());
			if(querysql==null){
				log.info("整月报表job对应报表sql为空 ,sqlid："+sqlEntity.getSqlId());
				return;
			}
			querysql.setSql(sqlEntity.getSqlBodyStr());//sql
			String[] head = sqlEntity.getSqlName().replaceAll("[\\t\\n\\r]","").split(",");//表头
			
			int limitNumber =(int)queryAssembleService.checkMaxNumber(querysql.getSql(), queryParam);
			log.info("整月报表（"+querysql.getRemark()+")开始:"+limitNumber);
			ExcelReturn er =queryAssembleService.toZipPort(querysql, queryParam, head.length, head, 0, limitNumber, FolderUtils.FILE_USER_ADMIN, FolderUtils.FILE_EXTENSION_CODE1002,sqlEntity.getCharCode());
			log.info("整月报表（"+querysql.getRemark()+")结束："+er.getZipName());
			
		} catch (Exception e) {
			log.error("整月报表生成zip异常:",e);
		}
	}

}
