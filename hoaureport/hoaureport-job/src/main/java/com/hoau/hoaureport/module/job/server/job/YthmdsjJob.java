package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IYthmdsjService;
/**
 * 二次中转数据job
 * @author 凌骏
 * @date 2016年6月12日下午2:21:34
 */
public class YthmdsjJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(YthmdsjJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the YthmdsjJob start");
			IYthmdsjService ythmdsjService = getBean("ythmdsjService",IYthmdsjService.class);
			ythmdsjService.PutYthmdsjInfo();
			LOG.info("the YthmdsjJob end");
		} catch (Exception e) {
			LOG.error("the YthmdsjJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
