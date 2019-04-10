package com.hoau.hoaureport.module.job.server.job;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IYiliuApiInvokeService;
import com.hoau.hoaureport.module.job.server.service.impl.YiliuApiInvokeService;
import com.hoau.hoaureport.module.job.shared.constant.YiliuApiParams;
import com.hoau.hoaureport.module.util.define.URLConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author linwenlin
 * @date 2018/3/9 11:39
 */
public class YiliuApiJob extends GridJob implements StatefulJob {

    private static final Log LOG = LogFactory.getLog(YiliuApiJob.class);

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        String url = URLConstants.getURL(YiliuApiParams.VEHICLE_INFO);
        try {
            LOG.info("---------------YiliuApiJob Started!---------------");
            IYiliuApiInvokeService yiliuApiInvokeService = getBean("yiliuApiInvokeService", YiliuApiInvokeService.class);
            yiliuApiInvokeService.doPostRequest(url, YiliuApiParams.VEHICLE_INFO);
            LOG.info("---------------YiliuApiJob Stop!---------------");
        } catch(Exception e) {
            LOG.error("---------------YiliuApiJob Exception!---------------" + e.getMessage());
            e.printStackTrace();
        }
    }
}
