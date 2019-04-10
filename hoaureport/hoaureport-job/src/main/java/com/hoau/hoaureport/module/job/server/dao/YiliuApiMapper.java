package com.hoau.hoaureport.module.job.server.dao;

import com.hoau.hoaureport.module.job.shared.domain.VehicleEntity;
import com.hoau.hoaureport.module.job.shared.domain.YiliuApiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linwenlin
 * @date 2018/3/5
 * @time 20:35
 */
@Repository
public interface YiliuApiMapper {
    /**
     * 批量插入易流接口返回的车辆信息
     * @param list
     */
    public void batchInsertVehicleInfo(List<VehicleEntity> list);

    /**
     * 批量更新易流接口返回的车辆信息
     * @param list
     * @return
     */
    public int batchUpdateVehicleInfo(List<VehicleEntity> list);

    /**
     *  查询当前表中所有的车牌号
     * @return
     */
    public List<String> findAllVehicles();

    /**
     * 根据接口名称返回接口的参数Map
     * @param apiName 接口名称
     * @return
     */
    public List<YiliuApiEntity> findParamsByApiName(String apiName);

    /**
     * 根据接口名称返回签名所需的参数名称集合
     * @param apiName 接口名称
     * @return
     */
    public List<YiliuApiEntity> findSignParamsByApiName(String apiName);
}
