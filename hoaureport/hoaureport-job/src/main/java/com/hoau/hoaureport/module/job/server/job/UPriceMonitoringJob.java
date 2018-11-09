package com.hoau.hoaureport.module.job.server.job;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IUPriceMonitoringService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * 单价监控JOB
 * @author by 宋京涛
 * @Description
 * @Date 2018/1/24
 */
public class UPriceMonitoringJob extends GridJob implements StatefulJob {

    private static final Log LOG = LogFactory.getLog(UPriceMonitoringJob.class);
    @Override
    protected void doExecute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            LOG.info("the UPriceMonitoringJob start");
            //单价监控
            IUPriceMonitoringService uPriceMonitoringService = getBean("upriceMonitoringService", IUPriceMonitoringService.class);
            uPriceMonitoringService.uPriceMonitoring();
            LOG.info("the UPriceMonitoringJob end");
        } catch (Exception e) {
            LOG.error("the UPriceMonitoringJob Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
