package com.hoau.hoaureport.module.job.server.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.hoau.hoaureport.module.configreport.shared.domain.WayBillZipEntity;
import com.hoau.hoaureport.module.job.server.service.IWaybillJobService;
import com.hoau.sso.module.api.shared.domain.UserEntity;
@Service
public class WaybillJobService implements IWaybillJobService {
	
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private MonthSqlEntityMapper monthSqlEntityMapper;
	@Autowired
	private IQueryAssembleService queryAssembleService;
	@Autowired
	private IWayBillZipService wayBillZipService;
	
	@Override
	public void queryToCsvZip(Date date) {
		 
		MonthSqlEntity sqlEntity =monthSqlEntityMapper.selectByPrimaryKey(FolderUtils.FILE_EXTENSION_CODE1001_ID);
		try {
			sqlEntity.setSqlBodyStr(new String(sqlEntity.getSqlBody(),ItfConifgConstant.SQL_BLOB_CODE) );
		} catch (Exception e) {
			log.error("blob_string:",e);
		}
		if(sqlEntity ==null || sqlEntity.getSqlBodyStr()==null ||"".equals(sqlEntity.getSqlBodyStr())){
			log.info("运单物流时间报表job数据为空 ,id：888888 ");
			return;
		}else if(sqlEntity.getSqlName()==null||"".equals(sqlEntity.getSqlName())){
			log.info("运单物流时间报表job 表头数据为空 ,id：888888 ");
			return;
		}
		
		
		Date begindate = FolderUtils.addDateByMonth(FolderUtils.addDateByday(date,-1), -1);
		begindate=FolderUtils.firstDay(begindate,16);
		StringBuffer buf = new StringBuffer();
		buf.append("BEGINDATE").append(":").append(FolderUtils.dateToStr(begindate));
		buf.append(",");
		buf.append("ENDDATE").append(":").append(FolderUtils.dateToStr(date));
		String queryParam=buf.toString();
		QuerySql querysql = queryAssembleService.querySqlById(sqlEntity.getSqlId());
		if(querysql==null){
			log.info("运单物流时间报表job对应报表sql为空 ,sqlid："+sqlEntity.getSqlId());
			return;
		}
		querysql.setSql(sqlEntity.getSqlBodyStr());//sql

		String[] head = sqlEntity.getSqlName().replaceAll("[\\t\\n\\r]","").split(",");//表头
		try {
			int limitNumber =(int)queryAssembleService.checkMaxNumber(querysql.getSql(), queryParam);
			ExcelReturn er =queryAssembleService.toZipPort(querysql, queryParam, head.length, head, 0, limitNumber, FolderUtils.FILE_USER_WAYBILLZIP, FolderUtils.FILE_EXTENSION_CODE1001,sqlEntity.getCharCode());
			WayBillZipEntity waybill = new WayBillZipEntity();
			waybill.setZipName(er.getJobFileName()+".zip");
			waybill.setZipPath(er.getZipName());
			waybill.setZipNum(FolderUtils.dateToStr(date, "yyyyMMdd"));
			UserEntity user = new UserEntity();
			user.setUserName("job");
			waybill =wayBillZipService.addWayBillZip(waybill,user);
			
			
			
		} catch (IOException e) {
			log.error("运单物流时间报表job生成zip包异常",e);
			e.printStackTrace();
		}
		
	}
	
	public static Map<String, String> controlParam(String param) {
		Map<String, String> map = new HashMap<String, String>();
		if (null == param || "".equals(param)) {
			return null;
		}
		String[] paramCount = param.split(",");
		for (String s : paramCount) {
			map.put(s.substring(0, s.indexOf(":")),
					s.substring(s.indexOf(":") + 1, s.length()));
		}
		return map;
	}
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =sf.parse("2016-07-31");
		date = new Date();
		Date begindate = FolderUtils.addDateByMonth(date, -1);
		begindate=FolderUtils.firstDay(begindate,16);
		
//		map.put("BEGINDATE", FolderUtils.dateToStr(begindate));
//		map.put("ENDDATE", FolderUtils.dateToStr(date));
		StringBuffer buf = new StringBuffer();
		buf.append("BEGINDATE").append(":").append(FolderUtils.dateToStr(begindate));
		buf.append(",");
		buf.append("ENDDATE").append(":").append(FolderUtils.dateToStr(date));
		Map<String,String> map =controlParam(buf.toString());
		System.out.println(FolderUtils.dateToStr(date));
		System.out.println(FolderUtils.dateToStr(date,"yyyy-MM-dd HH:mm:ss"));
		System.out.println(map.get("BEGINDATE")+"---"+map.get("ENDDATE"));
		System.out.println(buf.toString());
	}
	

}
