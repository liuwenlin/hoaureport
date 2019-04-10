package com.hoau.hoaureport.module.job.server.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IOrgSyncService;
/**
 * 部门同步job
 * @author 莫涛
 * @date 2016年1月16日下午2:21:34
 */
public class OrgSyncJob extends GridJob implements StatefulJob{
	
	private static final Log LOG = LogFactory.getLog(OrgSyncJob.class);
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOG.info("the OrgSyncJob start");
			IOrgSyncService orgSyncService = getBean("orgSyncService",IOrgSyncService.class);
			orgSyncService.orgSync();
			LOG.info("the OrgSyncJob end");
		} catch (Exception e) {
			LOG.error("the OrgSyncJob Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
