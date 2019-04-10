package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.dao.UpriceMonitoringMapper;
import com.hoau.hoaureport.module.job.server.service.IUPriceMonitoringService;
import com.hoau.hoaureport.module.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单价监控Service
 * @author by 宋京涛
 * @Description
 * @Date 2018/1/30
 */
@Service
public class UpriceMonitoringService implements IUPriceMonitoringService {

    private static final Log LOG = LogFactory.getLog(UpriceMonitoringService.class);

    @Autowired
    UpriceMonitoringMapper upriceMonitoringMapper;

    /**
     * 单价监控
     */
    @Override
    public void uPriceMonitoring() {
        Date inDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            inDate = sdf.parse(DateUtils.addDay(new Date(), -1));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LOG.info("生成运单基础数据 PRICE_BASIC_PROC 开始");
        upriceMonitoringMapper.priceBasicProc(inDate);
        LOG.info("生成运单基础数据 PRICE_BASIC_PROC 结束");

        LOG.info("常规业务单价监控 PRICE_COMMON_BIZ_PROC 开始");
        upriceMonitoringMapper.priceCommonBizProc(inDate);
        LOG.info("常规业务单价监控 PRICE_COMMON_BIZ_PROC 结束");

        LOG.info("PRICE_COMMON_PROC 开始");
        upriceMonitoringMapper.priceCommonProc(inDate);
        LOG.info("PRICE_COMMON_PROC 结束");

        LOG.info("常规产品单价监控 PRICE_COMMON_PRODUCT_PROC 开始");
        upriceMonitoringMapper.priceCommonProductProc(inDate);
        LOG.info("常规产品单价监控 PRICE_COMMON_PRODUCT_PROC 结束");

        LOG.info("客户类型单价监控 PRICE_CUSTOMER_TYPE_PROC 开始");
        upriceMonitoringMapper.priceCustomerTypeProc(inDate);
        LOG.info("客户类型单价监控 PRICE_CUSTOMER_TYPE_PROC 结束");

        LOG.info("直营非整车单价监控 PRICE_DIRECTLY_NONCARLOAD_PROC 开始");
        upriceMonitoringMapper.priceDirectlyNoncarloadProc(inDate);
        LOG.info("直营非整车单价监控 PRICE_DIRECTLY_NONCARLOAD_PROC 结束");

        LOG.info("非整车业务单价监控 PRICE_NON_CARLOAD_PROC 开始");
        upriceMonitoringMapper.priceNonCarloadProc(inDate);
        LOG.info("非整车业务单价监控 PRICE_NON_CARLOAD_PROC 结束");

        LOG.info("特许单价监控 PRICE_SPECIAL_PERMISSION_PROC 开始");
        upriceMonitoringMapper.priceSpecialPermissionProc(inDate);
        LOG.info("特许单价监控 PRICE_SPECIAL_PERMISSION_PROC 结束");

        LOG.info("战略客户单价监控 PRICE_STRATEGIC_CUSTOMER_PROC 开始");
        upriceMonitoringMapper.priceStrategicCustomerProc(inDate);
        LOG.info("战略客户单价监控 PRICE_STRATEGIC_CUSTOMER_PROC 结束");

        LOG.info("单价监控汇总 PRICE_SUMMARY_PROC 开始");
        upriceMonitoringMapper.priceSummaryProc(inDate);
        LOG.info("单价监控汇总 PRICE_SUMMARY_PROC 结束");
    }

}
