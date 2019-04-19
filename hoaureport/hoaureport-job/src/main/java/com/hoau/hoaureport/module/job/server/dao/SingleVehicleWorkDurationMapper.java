package com.hoau.hoaureport.module.job.server.dao;

import com.hoau.hoaureport.module.job.shared.domain.GoodsPlanLineEntity;
import com.hoau.hoaureport.module.job.shared.domain.SingleVehicleWorkDurationEntity;
import com.hoau.hoaureport.module.job.shared.domain.TransferPlanLineEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 单车工作时长MapDao
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/16 10:50
 */
@Repository
public interface SingleVehicleWorkDurationMapper {

    /**
     * 返回单车工作时长统计基础数据Map集合
     * @return
     */
    List<SingleVehicleWorkDurationEntity> getSingleVehicleWorkDurationList();

    /**
     * 批量插入单车工作时长数据
     * @param list
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void batchInsertSingleVehicleWorkDurationInfo(List<SingleVehicleWorkDurationEntity> list);


    /**
     * 返回当前时间前一天上下转移线路数据list集合
     * @return
     */
    List<TransferPlanLineEntity> getTransferPlanLineList();

    /**
     * 返回当前时间前一天提送货线路数据list集合
     * @return
     */
    List<GoodsPlanLineEntity> getGoodsPlanLineList();
}
