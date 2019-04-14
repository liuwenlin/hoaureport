package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.service.ISingleVehicleWorkDurationService;
import com.hoau.hoaureport.module.job.server.util.mapUtil.MapApiTool;
import com.hoau.hoaureport.module.job.shared.constant.BillType;
import com.hoau.hoaureport.module.job.shared.constant.SystemConsts;
import com.hoau.hoaureport.module.job.shared.domain.GoodsOrdersEntity;
import com.hoau.hoaureport.module.job.shared.domain.GoodsPlanLineEntity;

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
    private static ExecutorService doGeocodingService = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);

    private static CompletionService doGeocodingCompleteService
            = new ExecutorCompletionService(doGeocodingService);

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

    /**
     * 计算运单地理编码的FutureTask
     */
    private class GeocodingTask implements Callable<GoodsPlanLineEntity>{

        GoodsPlanLineEntity goodsPlanLineEntity;

        public GeocodingTask(GoodsPlanLineEntity goodsPlanLineEntity){
            this.goodsPlanLineEntity = goodsPlanLineEntity;
        }

        @Override
        public GoodsPlanLineEntity call() throws Exception {
            GoodsPlanLineEntity goodsBill = rpService.captureGeocoding(goodsPlanLineEntity);
            return goodsBill;
        }
    }

    /**
     * 计算提送货路径规划的FutureTask
     */
    private class RoutePlanningTask implements Callable<GoodsPlanLineEntity>{

        private GoodsPlanLineEntity goodsPlanLineEntity;

        public RoutePlanningTask(GoodsPlanLineEntity goodsPlanLineEntity){
            this.goodsPlanLineEntity = goodsPlanLineEntity;
        }

        @Override
        public GoodsPlanLineEntity call() throws Exception {
            GoodsPlanLineEntity deliverGoodsBill = rpService.captureRoutePlanning(goodsPlanLineEntity);
            return deliverGoodsBill;
        }
    }

    @Override
    public void computeSingleVehicleWorkDuration() throws InterruptedException, ExecutionException {

        List<GoodsPlanLineEntity> goodsBillsList = getGoodsPlanLineEntity();

//        computeRoutePlanning(computeOrderGeocode(goodsBillsList));


        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            doGeocodingCompleteService.submit(new GeocodingTask(goodsBill));
            Thread.sleep(25);
        }

        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            Future<GoodsPlanLineEntity> future = doGeocodingCompleteService.take();
            GoodsPlanLineEntity geocode = future.get();
            System.out.println("地理编码返回的提送货单为: "+geocode);
            doRoutePlanningCompleteService.submit(new RoutePlanningTask(geocode));
        }

        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            Future<GoodsPlanLineEntity> future = doRoutePlanningCompleteService.take();
            System.out.println("当前送货单为: " + future.get().getGoodsBill()
                    + " 车牌号为: " + future.get().getCph()
                    + " 编号类型: " + future.get().getBillType());
            System.out.println("当前送货单路径规划结果距离为: " + future.get());
        }

        //关闭请求任务线程池
        MapApiTool.shutdown();

        //关闭计算任务线程池
        this.shutdown();

    }

//    private List<Future<GoodsPlanLineEntity>> computeOrderGeocode(List<GoodsPlanLineEntity> goodsBillsList) throws InterruptedException {
//
//        List<Future<GoodsPlanLineEntity>> goodsPlanLineList = new LinkedList<Future<GoodsPlanLineEntity>>();
//
//        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
//            doRoutePlanningCompleteService.submit(new GeocodingTask(goodsBill));
//            Thread.sleep(25);
//        }
//
//        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
//            Future<GoodsPlanLineEntity> future = doGeocodingCompleteService.take();
//            goodsPlanLineList.add(future);
//        }
//
//        return goodsPlanLineList;
//    }
//
//
//    private void computeRoutePlanning(List<Future<GoodsPlanLineEntity>> goodsBillsFutureList) throws InterruptedException, ExecutionException {
//        for(Future<GoodsPlanLineEntity> goodsBillFuture:goodsBillsFutureList){
//            doRoutePlanningCompleteService.submit(new RoutePlanningTask(goodsBillFuture.get()));
//            Thread.sleep(25);
//        }
//
//        for(Future<GoodsPlanLineEntity> goodsBillFuture:goodsBillsFutureList){
//            System.out.println("当前送货单为: " + goodsBillFuture.get().getGoodsBill()
//                             + " 车牌号为: " + goodsBillFuture.get().getCph()
//                             + " 编号类型: " + goodsBillFuture.get().getBillType());
//            Future<GoodsPlanLineEntity> future = doRoutePlanningCompleteService.take();
//            System.out.println(future);
//            GoodsPlanLineEntity goodsBillResult = future.get();
//            System.out.println("当前送货单路径规划结果距离为: " + goodsBillResult);
//        }
//    }

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
        if(doGeocodingService != null){
            doGeocodingService.shutdown();
        }
    }

    /**
     * 测试数据
     * 返回送货单列表
     * @return
     */
    private List<GoodsPlanLineEntity> getGoodsPlanLineEntity(){
        List<GoodsPlanLineEntity> list= new LinkedList<GoodsPlanLineEntity>();
        GoodsPlanLineEntity goodsPlanLineEntity = new GoodsPlanLineEntity();
        goodsPlanLineEntity.setStoreGeoCode("121.303183,31.20409");
        goodsPlanLineEntity.setOrderGeoCodeList(getGoodsOrdersEntity());
        goodsPlanLineEntity.setCph("沪A88888");
        goodsPlanLineEntity.setGoodsBill("0666632432");
        goodsPlanLineEntity.setBillType(BillType.DELIVER_GOODS);

        list.add(goodsPlanLineEntity);

        GoodsPlanLineEntity goodsPlanLineEntity2 = new GoodsPlanLineEntity();
        goodsPlanLineEntity2.setStoreGeoCode("121.303183,31.20409");
        goodsPlanLineEntity2.setOrderGeoCodeList(getGoodsOrdersEntity());
        goodsPlanLineEntity2.setCph("沪A66666");
        goodsPlanLineEntity2.setGoodsBill("0666632433");
        goodsPlanLineEntity2.setBillType(BillType.DELIVER_GOODS);

        list.add(goodsPlanLineEntity2);

        System.out.println("送货单列表已生成,列表大小为: " + list.size());
        return list;
    }

    /**
     * 测试数据
     * 返回送货单运单列表
     * @return
     */
    private List<GoodsOrdersEntity> getGoodsOrdersEntity() {
        List<GoodsOrdersEntity> geocodeList = new LinkedList<GoodsOrdersEntity>();
        GoodsOrdersEntity goodsOrdersEntity1 = new GoodsOrdersEntity();
        GoodsOrdersEntity goodsOrdersEntity2 = new GoodsOrdersEntity();
//        goodsOrdersEntity1.setGeocode("121.437694,31.195071");
        goodsOrdersEntity1.setGoodsSequence(0);
        goodsOrdersEntity1.setYdbh("F8888888");
        goodsOrdersEntity1.setBillType(BillType.DELIVER_GOODS.getTypeMsg());
        goodsOrdersEntity1.setAddress("上海市长宁区古北路上海纺织西北1门");
//        goodsOrdersEntity2.setGeocode("121.435694,31.194071");
        goodsOrdersEntity2.setGoodsSequence(1);
        goodsOrdersEntity2.setYdbh("F6666666");
        goodsOrdersEntity2.setBillType(BillType.DELIVER_GOODS.getTypeMsg());
        goodsOrdersEntity2.setAddress("上海市浦东区陆家嘴东方明珠塔");
        geocodeList.add(goodsOrdersEntity1);
        geocodeList.add(goodsOrdersEntity2);
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
