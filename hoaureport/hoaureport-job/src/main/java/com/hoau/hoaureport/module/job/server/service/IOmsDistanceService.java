package com.hoau.hoaureport.module.job.server.service;

/**
 * OMS取件距离计算服务接口
 */
public interface IOmsDistanceService {
    /**
     * 处理数据
     */
    public long orderHandle(String bizdate);

    /**
     * 更新OMS订单地址解析状态
     * @param bizdate
     * @return
     */
    public int updateOmsOrder(String bizdate);
}
