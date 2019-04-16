package com.hoau.hoaureport.module.job.server.dao;

import com.hoau.hoaureport.module.job.shared.domain.SingleVehicleWorkDurationEntity;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 单车工作时长MapDao
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/16 10:50
 */
@Repository
public interface SingleVehicleWorkDurationMapper {

    @MapKey("cph")
    Map<String, SingleVehicleWorkDurationEntity> getSingleVehicleWorkDurationMap();

}
