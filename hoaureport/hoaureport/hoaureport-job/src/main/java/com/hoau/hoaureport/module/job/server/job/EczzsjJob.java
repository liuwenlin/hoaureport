package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IEczzsjService;
/**
 * 二次中转数据job
 * @author 凌骏
 * @date 2016年6月12日下午2:21:34
 */
public class EczzsjJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(EczzsjJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the EczzsjJob start");
			IEczzsjService eczzsjService = getBean("eczzsjService",IEczzsjService.class);
			eczzsjService.PutEczzsjInfo();
			LOG.info("the EczzsjJob end");
		} catch (Exception e) {
			LOG.error("the EczzsjJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
