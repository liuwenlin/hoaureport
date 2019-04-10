package com.hoau.hoaureport.module.job.server.job;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.job.server.service.IOmsDistanceService;
import com.hoau.hoaureport.module.job.server.service.IOrgSyncService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * OMS订单取件距离计算job
 * @author wmz
 * @date 2018年3月8日下午3:21:34
 */
public class OmsOrderDistanceJob extends GridJob implements StatefulJob {
    private static final Log LOG = LogFactory.getLog(OmsOrderDistanceJob.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOG.info("the OmsDistanceJob start");
            //当前时间减一天为当前所属日
            String nowdate = getNowDate(new Date(),-1);
            LOG.info("the OmsDistanceJob nowDate:"+nowdate);
            IOmsDistanceService distanceService = getBean("omsDistanceService",IOmsDistanceService.class);
            long row =distanceService.orderHandle(nowdate);
            if(row >0){
                LOG.info("the OmsDistanceJob updateOmsOrder_total:"+row);
                distanceService.updateOmsOrder(nowdate);
            }

            LOG.info("the OmsDistanceJob end");
        } catch (Exception e) {
            LOG.error("the OmsDistanceJob Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getNowDate(Date date,int a){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date );
        calendar.add(Calendar.DATE, a);
        return dateFormat.format(calendar.getTime());
    }
}
