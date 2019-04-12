package com.hoau.hoaureport.module.job.server.service;

import java.util.concurrent.ExecutionException;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/12 13:16
 */
public interface ISingleVehicleWorkDurationService {

    void computeSingleVehicleWorkDuration() throws InterruptedException, ExecutionException;

}
