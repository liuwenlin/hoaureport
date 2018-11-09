package com.hoau.hoaureport.module.job.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IAllMonthSqlJobService;
/**
 * 整月报表job
 * @author 273503
 * @date 2016年7月19日上午10:21:34
 */
public class AllMonthSqlJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(AllMonthSqlJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("整月报表Job 开始");
			
			IAllMonthSqlJobService allMonthSqlJobService = getBean("allMonthSqlJobService",IAllMonthSqlJobService.class);
			allMonthSqlJobService.allMonthToZip(new Date());
			LOG.info("整月报表Job 结束");
		} catch (Exception e) {
			LOG.error("整月报表Job 异常 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
//	private int getDays(JobExecutionContext context){
//		int days =6*30;//默认180天
//		try {
//			 days =Integer.valueOf((String)getProperty(context, "days"));
//		} catch (Exception e) {
//			LOG.error("the CsvZipJob_days Exception : " + e.getMessage());
//			e.printStackTrace();
//		}
//		return days;
//	}
}
