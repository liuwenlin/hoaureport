package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.service.ISingleVehicleWorkDurationService;
import com.hoau.hoaureport.module.job.server.util.mapUtil.MapApiTool;
import com.hoau.hoaureport.module.job.shared.constant.SystemConsts;
import com.hoau.hoaureport.module.job.shared.domain.DeliverGoodsOrders;
import com.hoau.hoaureport.module.job.shared.domain.DeliverGoodsPlanLineEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 单车工作时长数据计算业务
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/12 13:28
 */
public class SingleVehicleWorkDurationService implements ISingleVehicleWorkDurationService {

    RoutePlanningService rpService;

    /**
     * 地理编码解析服务
     */
//    private static ExecutorService doGeocodingService = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);
//
//    private static CompletionService doGeocodingCompleteService
//            = new ExecutorCompletionService(doGeocodingService);
    /**
     * 路径规划计算服务
     */
    private static ExecutorService doRoutePlanningService = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);

    private static CompletionService doRoutePlanningCompleteService
            = new ExecutorCompletionService(doRoutePlanningService);

    /**
     * 测试使用构造函数
     * @param rpService
     */
    public SingleVehicleWorkDurationService(RoutePlanningService rpService){
        this.rpService = rpService;
    }

    private class RoutePlanningTask implements Callable<DeliverGoodsPlanLineEntity>{

        private DeliverGoodsPlanLineEntity deliverGoodsPlanLineEntity;

        public RoutePlanningTask(DeliverGoodsPlanLineEntity deliverGoodsPlanLineEntity){
            this.deliverGoodsPlanLineEntity = deliverGoodsPlanLineEntity;
        }

        @Override
        public DeliverGoodsPlanLineEntity call() throws Exception {
            DeliverGoodsPlanLineEntity deliverGoodsBill = rpService.captureRoutePlanning(deliverGoodsPlanLineEntity);
            return deliverGoodsBill;
        }
    }

    @Override
    public void computeSingleVehicleWorkDuration() throws InterruptedException, ExecutionException {
        List<DeliverGoodsPlanLineEntity> deliverGoodsBillsList = getDeliverGoodsPlanLineEntity();

        for(DeliverGoodsPlanLineEntity deliverGoodsBill:deliverGoodsBillsList){
            doRoutePlanningCompleteService.submit(new RoutePlanningTask(deliverGoodsBill));
            Thread.sleep(25);
        }

        for(DeliverGoodsPlanLineEntity deliverGoodsBill:deliverGoodsBillsList){
            System.out.println("当前送货单为: " + deliverGoodsBill.getDeliverGoodsBill()
                             + " 车牌号为: " + deliverGoodsBill.getCph());
            Future<DeliverGoodsPlanLineEntity> future = doRoutePlanningCompleteService.take();
            System.out.println(future);
            DeliverGoodsPlanLineEntity deliverGoodsBillResult = future.get();
            System.out.println("当前送货单路径规划结果距离为: " + deliverGoodsBillResult);
        }

        //关闭请求任务线程池
        MapApiTool.shutdown();

        this.shutdown();

    }

//    @Override
//    public void computeSingleVehicleWorkDuration() throws InterruptedException, ExecutionException {
//        List<DeliverGoodsPlanLineEntity> deliverGoodsBillsList = getDeliverGoodsPlanLineEntity();
//
//        for(DeliverGoodsPlanLineEntity deliverGoodsBill:deliverGoodsBillsList){
//            DeliverGoodsPlanLineEntity deliverGoodsBillResult = rpService.captureRoutePlanning(deliverGoodsBill);
//            System.out.println("路径规划接口返回距离为: " + deliverGoodsBillResult.getDeliverGoodsDistance());
//            Thread.sleep(25);
//        }
//
//        //关闭请求任务线程池
//        MapApiTool.shutdown();
//
////        this.shutdown();
//
//    }

    private void shutdown(){
        if(doRoutePlanningService != null){
            doRoutePlanningService.shutdown();
        }
    }

    /**
     * 测试数据
     * 返回送货单列表
     * @return
     */
    private List<DeliverGoodsPlanLineEntity> getDeliverGoodsPlanLineEntity(){
        List<DeliverGoodsPlanLineEntity> list= new LinkedList<DeliverGoodsPlanLineEntity>();
        DeliverGoodsPlanLineEntity deliverGoodsPlanLineEntity = new DeliverGoodsPlanLineEntity();
        deliverGoodsPlanLineEntity.setStoreGeoCode("121.303183,31.20409");
        deliverGoodsPlanLineEntity.setOrderGeoCodeList(getDeliverGoodsOrders());
        deliverGoodsPlanLineEntity.setCph("沪A888888");
        deliverGoodsPlanLineEntity.setDeliverGoodsBill("0666632432");
        list.add(deliverGoodsPlanLineEntity);
        System.out.println("送货单列表已生成,列表大小为: " + list.size());
        return list;
    }

    /**
     * 测试数据
     * 返回送货单运单列表
     * @return
     */
    private List<DeliverGoodsOrders> getDeliverGoodsOrders() {
        List<DeliverGoodsOrders> geocodeList = new LinkedList<DeliverGoodsOrders>();
        DeliverGoodsOrders deliverGoodsOrders1 = new DeliverGoodsOrders();
        DeliverGoodsOrders deliverGoodsOrders2 = new DeliverGoodsOrders();
        deliverGoodsOrders1.setGeocode("121.437694,31.195071");
        deliverGoodsOrders1.setDeliverGoodsSequence(0);
        deliverGoodsOrders2.setGeocode("121.435694,31.194071");
        deliverGoodsOrders2.setDeliverGoodsSequence(1);
        geocodeList.add(deliverGoodsOrders1);
        geocodeList.add(deliverGoodsOrders2);
        return geocodeList;
    }

    public static void main(String[] args){
        SingleVehicleWorkDurationService svwds = new SingleVehicleWorkDurationService(new RoutePlanningService());
        try {
            svwds.computeSingleVehicleWorkDuration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
