package com.hoau.hoaureport.module.job.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IWaybillJobService;
/**
 * 运单物流时间报表每天生成包job
 * @author 273503
 * @date 2016年7月12日下午3:21:34
 */
public class WayBillZipJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(WayBillZipJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the WaybillJob start");
			
			IWaybillJobService waybillJobService = getBean("waybillJobService",IWaybillJobService.class);
			waybillJobService.queryToCsvZip(new Date());
			LOG.info("the WaybillJob end");
		} catch (Exception e) {
			LOG.error("the WaybillJob Exception : " + e.getMessage());
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
