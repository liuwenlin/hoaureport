package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.service.IRoutePlanningService;
import com.hoau.hoaureport.module.job.server.util.mapUtil.MapApiTool;
import com.hoau.hoaureport.module.job.shared.constant.AmapApiConstants;
import com.hoau.hoaureport.module.job.shared.domain.AmapApiRoutePlanningMultiEntity;
import com.hoau.hoaureport.module.job.shared.domain.DeliverGoodsOrders;
import com.hoau.hoaureport.module.job.shared.domain.DeliverGoodsPlanLineEntity;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 23:17
 */
public class RoutePlanningService implements IRoutePlanningService {

    /**
     * 获取路径规划服务
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public DeliverGoodsPlanLineEntity captureRoutePlanning(DeliverGoodsPlanLineEntity deliverGoodsPlanLine)
            throws InterruptedException, ExecutionException {
//        Map<String, AmapApiRoutePlanningMultiEntity> multiResultMap = new HashMap<String, AmapApiRoutePlanningMultiEntity>();
        DeliverGoodsPlanLineEntity deliverGoodsPlanLineEntity = deliverGoodsPlanLine;
        int deliverGoodsListSize = deliverGoodsPlanLineEntity.getOrderGeoCodeList().size();
        if(deliverGoodsListSize <= AmapApiConstants.WAYPOINTS_MAX_NUM){
//            multiResultMap.put(deliverGoodsPlanLineEntity.getDeliverGoodsBill(),MapApiTool.getDistance(getRoutePlanningUrl(deliverGoodsPlanLineEntity)));
            AmapApiRoutePlanningMultiEntity apme = MapApiTool.getDistance(getRoutePlanningUrl(deliverGoodsPlanLineEntity));
            if(apme.getAmapResultDistance() == null){
                deliverGoodsPlanLineEntity.setDeliverGoodsDistance(apme.getFutureDistance().get());
                System.out.println("当前请求路径规划接口通过future返回的距离为: " + apme.getFutureDistance().get());
            } else {
                deliverGoodsPlanLineEntity.setDeliverGoodsDistance(apme.getAmapResultDistance());
                System.out.println("当前请求路径规划接口通过缓存返回的距离为: " + apme.getAmapResultDistance());
            }

        } else {
            System.out.println("送货单列表大小16时的情况,待完成..."); //TODO
        }

        return deliverGoodsPlanLineEntity;
    }

    /**
     * 根据送货单明细实体生成路径规划请求url
     * @param deliverGoodsPlanLineEntity
     * @return
     */
    private String getRoutePlanningUrl(DeliverGoodsPlanLineEntity deliverGoodsPlanLineEntity){
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(AmapApiConstants.ROUTE_PLANNING_URL);
        strBuff.append("&origin=").append(deliverGoodsPlanLineEntity.getStoreGeoCode());
        strBuff.append("&destination=").append(deliverGoodsPlanLineEntity.getStoreGeoCode());
        strBuff.append("&waypoints=");
        for(DeliverGoodsOrders orders:deliverGoodsPlanLineEntity.getOrderGeoCodeList()){
            strBuff.append(orders.getGeocode()).append(";");
        }
        strBuff.substring(0,strBuff.length()-1);
        System.out.println("请求路径为:"+strBuff.toString());
        return strBuff.toString();
    }

//    public static void main(String[] args){
//        RoutePlanningService rpService = new RoutePlanningService();
//        try {
//            rpService.captureRoutePlanning();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

}
