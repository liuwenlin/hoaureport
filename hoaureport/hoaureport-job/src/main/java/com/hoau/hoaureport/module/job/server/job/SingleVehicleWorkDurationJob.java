package com.hoau.hoaureport.module.job.server.job;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.impl.SingleVehicleWorkDurationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/19 19:19
 */
public class SingleVehicleWorkDurationJob extends GridJob implements StatefulJob {

    private static final Log LOG = LogFactory.getLog(SingleVehicleWorkDurationJob.class);

    @Override
    protected void doExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOG.info("---------------SingleVehicleWorkDurationService Started!---------------");
            SingleVehicleWorkDurationService singleVehicleWorkDurationService = getBean("singleVehicleWorkDurationService", SingleVehicleWorkDurationService.class);
            singleVehicleWorkDurationService.computeSingleVehicleWorkDuration();
            LOG.info("---------------SingleVehicleWorkDurationService Stop!---------------");
        } catch(Exception e) {
            LOG.error("---------------SingleVehicleWorkDurationService Exception!---------------" + e.getMessage());
            e.printStackTrace();
        }
    }
}
