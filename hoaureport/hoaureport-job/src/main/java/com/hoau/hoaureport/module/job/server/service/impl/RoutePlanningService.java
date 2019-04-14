package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.service.IRoutePlanningService;
import com.hoau.hoaureport.module.job.server.util.mapUtil.MapApiTool;
import com.hoau.hoaureport.module.job.shared.constant.AmapApiConstants;
import com.hoau.hoaureport.module.job.shared.domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 23:17
 */
public class RoutePlanningService implements IRoutePlanningService {

    /**
     * 获取体送货单中每个运单明细的地理编码
     * @param goodsPlanLine
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public GoodsPlanLineEntity captureGeocoding(GoodsPlanLineEntity goodsPlanLine) throws InterruptedException, ExecutionException {
        GoodsPlanLineEntity goodsPlanLineEntity = goodsPlanLine;
        Map<GoodsOrdersEntity,AmapApiGeocodeMultiEntity> multiMap = new HashMap<GoodsOrdersEntity,AmapApiGeocodeMultiEntity>();
        for(GoodsOrdersEntity order:goodsPlanLineEntity.getOrderGeoCodeList()){
            multiMap.put(order,MapApiTool.getGeocode(getGeocodeUrl(order)));
            Thread.sleep(25);
        }

        for(GoodsOrdersEntity order:goodsPlanLineEntity.getOrderGeoCodeList()){
            AmapApiGeocodeMultiEntity geocodeMultiResult = multiMap.get(order);
            if(geocodeMultiResult.getGeocode() == null){
                System.out.println("当前地理编码通过geocodeFuture返回");
                order.setGeocode(geocodeMultiResult.getFutureGeocode().get());
            } else {
                System.out.println("当前地理编码通过geocode返回");
                order.setGeocode(geocodeMultiResult.getGeocode());
            }
        }

        return goodsPlanLineEntity;
    }

    private String getGeocodeUrl(GoodsOrdersEntity order) {
        return AmapApiConstants.GEOCODE_URL + order.getAddress();
    }

    /**
     * 获取路径规划服务
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public GoodsPlanLineEntity captureRoutePlanning(GoodsPlanLineEntity goodsPlanLine)
            throws InterruptedException, ExecutionException {
//        Map<String, AmapApiRoutePlanningMultiEntity> multiResultMap = new HashMap<String, AmapApiRoutePlanningMultiEntity>();
        GoodsPlanLineEntity goodsPlanLineEntity = goodsPlanLine;
        int deliverGoodsListSize = goodsPlanLineEntity.getOrderGeoCodeList().size();
        if(deliverGoodsListSize <= AmapApiConstants.WAYPOINTS_MAX_NUM){
//            multiResultMap.put(deliverGoodsPlanLineEntity.getDeliverGoodsBill(),MapApiTool.getDistance(getRoutePlanningUrl(deliverGoodsPlanLineEntity)));
            AmapApiRoutePlanningMultiEntity apme = MapApiTool.getDistance(getRoutePlanningUrl(goodsPlanLineEntity));
            if(apme.getAmapResultDistance() == null){
                goodsPlanLineEntity.setGoodsDistance(apme.getFutureDistance().get());
                System.out.println("当前请求路径规划接口通过future返回的距离为: " + apme.getFutureDistance().get());
            } else {
                goodsPlanLineEntity.setGoodsDistance(apme.getAmapResultDistance());
                System.out.println("当前请求路径规划接口通过缓存返回的距离为: " + apme.getAmapResultDistance());
            }

        } else {
            System.out.println("送货单列表大小16时的情况,待完成..."); //TODO
        }

        return goodsPlanLineEntity;
    }

    /**
     * 根据送货单明细实体生成路径规划请求url
     * @param goodsPlanLineEntity
     * @return
     */
    private String getRoutePlanningUrl(GoodsPlanLineEntity goodsPlanLineEntity){
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(AmapApiConstants.ROUTE_PLANNING_URL);
        strBuff.append("&origin=").append(goodsPlanLineEntity.getStoreGeoCode());
        strBuff.append("&destination=").append(goodsPlanLineEntity.getStoreGeoCode());
        strBuff.append("&waypoints=");
        for(GoodsOrdersEntity orders:goodsPlanLineEntity.getOrderGeoCodeList()){
            strBuff.append(orders.getGeocode()).append(";");
        }
        strBuff.substring(0,strBuff.length()-1);
        System.out.println("路径规划请求url为:"+strBuff.toString());
        return strBuff.toString();
    }

}
