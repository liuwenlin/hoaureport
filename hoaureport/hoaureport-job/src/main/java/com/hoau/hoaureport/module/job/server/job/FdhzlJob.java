package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IFdhzlService;
/**
 * 发到货转量job
 * @author 凌骏
 * @date 2016年6月12日下午2:21:34
 */
public class FdhzlJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(FdhzlJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the FdhzlJob start");
			IFdhzlService fdhzlService = getBean("fdhzlService",IFdhzlService.class);
			fdhzlService.FdhzlInfo();
			LOG.info("the FdhzlJob end");
		} catch (Exception e) {
			LOG.error("the FdhzlJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
