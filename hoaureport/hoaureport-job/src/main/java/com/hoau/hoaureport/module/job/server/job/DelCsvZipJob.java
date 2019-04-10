package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.ICsvZipService;
/**
 * csv zip包清除job
 * @author 273503
 * @date 2016年7月5日下午2:21:34
 */
public class DelCsvZipJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(DelCsvZipJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the CsvZipJob start");
			
			ICsvZipService csvZipService = getBean("csvZipService",ICsvZipService.class);
			csvZipService.deleteCsvZip(getDays(context));
			LOG.info("the CsvZipJob end");
		} catch (Exception e) {
			LOG.error("the CsvZipJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private int getDays(JobExecutionContext context){
		int days =6*30;//默认180天
		try {
			 days =Integer.valueOf((String)getProperty(context, "days"));
		} catch (Exception e) {
			LOG.error("the CsvZipJob_days Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return days;
	}
}
