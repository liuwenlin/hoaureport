package com.hoau.hoaureport.module.job.server.service;

import com.hoau.hoaureport.module.job.shared.domain.GoodsPlanLineEntity;

import java.util.concurrent.ExecutionException;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 15:29
 */
public interface IRoutePlanningService {

    GoodsPlanLineEntity captureGeocoding(GoodsPlanLineEntity goodsPlanLine) throws InterruptedException, ExecutionException;

    GoodsPlanLineEntity captureRoutePlanning(GoodsPlanLineEntity goodsPlanLine) throws InterruptedException, ExecutionException;

}
