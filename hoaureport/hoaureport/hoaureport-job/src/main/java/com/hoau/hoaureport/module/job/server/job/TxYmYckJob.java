package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.ITxYmYckService;
/**
 * 发到货转量job
 * @author 凌骏
 * @date 2016年6月12日下午2:21:34
 */
public class TxYmYckJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(TxYmYckJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the TxYmYckJob start");
			ITxYmYckService txYmYckService = getBean("txYmYckService",ITxYmYckService.class);
			txYmYckService.TxYmYckInfo();
			LOG.info("the TxYmYckJob end");
		} catch (Exception e) {
			LOG.error("the TxYmYckJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
