package com.hoau.hoaureport.module.job.server.dao;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author by 宋京涛
 * @Description
 * @Date 2018/1/31
 */
@Repository
public interface UpriceMonitoringMapper {

    public void priceBasicProc(Date inDate);

    public void priceCommonBizProc(Date inDate);

    public void priceCommonProc(Date inDate);

    public void priceCommonProductProc(Date inDate);

    public void priceCustomerTypeProc(Date inDate);

    public void priceDirectlyNoncarloadProc(Date inDate);

    public void priceNonCarloadProc(Date inDate);

    public void priceSpecialPermissionProc(Date inDate);

    public void priceStrategicCustomerProc(Date inDate);

    public void priceSummaryProc(Date inDate);

}
