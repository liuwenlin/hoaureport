package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.dao.SingleVehicleWorkDurationMapper;
import com.hoau.hoaureport.module.job.server.service.ISingleVehicleWorkDurationService;
import com.hoau.hoaureport.module.job.server.util.mapUtil.MapApiTool;
import com.hoau.hoaureport.module.job.shared.constant.BillType;
import com.hoau.hoaureport.module.job.shared.constant.SystemConsts;
import com.hoau.hoaureport.module.job.shared.constant.TransferType;
import com.hoau.hoaureport.module.job.shared.domain.GoodsOrdersEntity;
import com.hoau.hoaureport.module.job.shared.domain.GoodsPlanLineEntity;
import com.hoau.hoaureport.module.job.shared.domain.SingleVehicleWorkDurationEntity;
import com.hoau.hoaureport.module.job.shared.domain.TransferPlanLineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 单车工作时长数据计算业务
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/12 13:28
 */
@Service
public class SingleVehicleWorkDurationService implements ISingleVehicleWorkDurationService {

    @Autowired
    AmapService rpService;

    @Autowired
    SingleVehicleWorkDurationMapper singleVehicleWorkDurationMapper;

    /**
     * 地理编码解析服务
     */
    private static ExecutorService doGeocodingService = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);

    private static CompletionService doGeocodingCompleteService
            = new ExecutorCompletionService(doGeocodingService);

    private static CompletionService doTransferGeocodingCompleteService
            = new ExecutorCompletionService(doGeocodingService);

    /**
     * 路径规划计算服务
     */
    private static ExecutorService doRoutePlanningService = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);

    private static CompletionService doRoutePlanningCompleteService
            = new ExecutorCompletionService(doRoutePlanningService);

    private static CompletionService doTransferRoutePlanningCompleteService
            = new ExecutorCompletionService(doRoutePlanningService);

    /**
     * 测试使用构造函数
     * @param rpService
     */
    public SingleVehicleWorkDurationService(AmapService rpService){
        this.rpService = rpService;
    }

    /**
     * 计算上下转移线路地理编码的FutureTask
     */
    private class TransferPlanGeocodeTask implements Callable<TransferPlanLineEntity>{

        TransferPlanLineEntity transferPlanLineEntity;

        public TransferPlanGeocodeTask(TransferPlanLineEntity transferPlanLineEntity){
            this.transferPlanLineEntity = transferPlanLineEntity;
        }

        @Override
        public TransferPlanLineEntity call() throws Exception {
            return rpService.captureTransferGeocoding(transferPlanLineEntity);
        }
    }

    /**
     * 计算上下转移线路规划线路距离的FutureTask
     */
    private class TransferPlanRouteTask implements Callable<TransferPlanLineEntity>{

        TransferPlanLineEntity transferPlanLineEntity;

        public TransferPlanRouteTask(TransferPlanLineEntity transferPlanLineEntity){
            this.transferPlanLineEntity = transferPlanLineEntity;
        }

        @Override
        public TransferPlanLineEntity call() throws Exception {
            return rpService.captureTransferRoutePlanning(transferPlanLineEntity);
        }
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

        //1.首先获取计算车辆的实体Map(预先加载车牌号的基础信息,待需完成字段计算完成后,然后持久化)
//        Map<String, SingleVehicleWorkDurationEntity> resultMap
//                = singleVehicleWorkDurationMapper.getSingleVehicleWorkDurationMap();

        //2.再获取上下转移线路串线车辆信息
        List<TransferPlanLineEntity> transferPlanLineList = getTransferPlanLineEntity();

        //3.最后获取取派件线路信息
        List<GoodsPlanLineEntity> goodsBillsList = getGoodsPlanLineEntity();

//        computeRoutePlanning(computeOrderGeocode(goodsBillsList));


        //4.提交数据计算任务
        //4.1 上下转移线路发车到车公司地理编码检测
        for(TransferPlanLineEntity transferPlan:transferPlanLineList){
            doTransferGeocodingCompleteService.submit(new TransferPlanGeocodeTask(transferPlan));
            Thread.sleep(10);
        }

        //4.2 提送货运单地理编码计算
        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            doGeocodingCompleteService.submit(new GeocodingTask(goodsBill));
            Thread.sleep(10);
        }

        //4.3 上下转移线路发车到车公司路径规划计算
        for(TransferPlanLineEntity transferPlan:transferPlanLineList){
            Future<TransferPlanLineEntity> future = doTransferGeocodingCompleteService.take();
            doTransferRoutePlanningCompleteService.submit(new TransferPlanRouteTask(future.get()));
            System.out.println("当前转移线路经过地理编码计算后的结果: "+future.get());
            Thread.sleep(25);
        }

        //4.4 提送货线路路径规划距离计算
        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            Future<GoodsPlanLineEntity> future = doGeocodingCompleteService.take();
            GoodsPlanLineEntity geocode = future.get();
            System.out.println("地理编码返回的提送货单为: "+geocode);
            doRoutePlanningCompleteService.submit(new RoutePlanningTask(geocode));
            Thread.sleep(25);
        }

        //4.5 上下转移线路路径规划距离获取
        for(TransferPlanLineEntity transferPlan:transferPlanLineList){
            Future<TransferPlanLineEntity> future = doTransferRoutePlanningCompleteService.take();
            TransferPlanLineEntity tp = future.get();
            System.out.println("当前转移线路: " + tp.getLine()
                    + "车牌号为: " + tp.getCph()
                    + "当前转移线路路径规划结果距离为: " + tp.getPlannedDistance());
        }

        //4.6 提送货线路路径规划距离获取
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

//    private List<Future<GoodsPlanLineEntity>> computeOrderGeocode(List<GoodsPlanLineEntity> goodsBillsList)
// throws InterruptedException {
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
//    private void computeRoutePlanning(List<Future<GoodsPlanLineEntity>> goodsBillsFutureList)
// throws InterruptedException, ExecutionException {
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

    private List<TransferPlanLineEntity> getTransferPlanLineEntity(){
        List<TransferPlanLineEntity> list = new LinkedList<TransferPlanLineEntity>();
        TransferPlanLineEntity transferPlanLineEntity1 = new TransferPlanLineEntity();
        transferPlanLineEntity1.setCph("沪A88888");
        transferPlanLineEntity1.setLine("串N上海122N上海-20190415001");
        transferPlanLineEntity1.setStartCity("上海");
        transferPlanLineEntity1.setStartAddress("上海市宝山区杨行镇湄浦路2172号");
        transferPlanLineEntity1.setStartGeoCode("121.43625,31.403609");
        transferPlanLineEntity1.setEndCity("上海");
        transferPlanLineEntity1.setEndAddress("闵行区华翔路2239号");
        transferPlanLineEntity1.setEndGeoCode("");
        transferPlanLineEntity1.setZydw(1.5);
        transferPlanLineEntity1.setType(TransferType.UP_TRANFER.getTypeMsg());

        TransferPlanLineEntity transferPlanLineEntity2 = new TransferPlanLineEntity();
        transferPlanLineEntity2.setCph("沪A66666");
        transferPlanLineEntity2.setLine("串N上海N上海2-20180323009");
        transferPlanLineEntity2.setStartCity("上海");
        transferPlanLineEntity2.setStartAddress("闵行区华翔路2239号");
        transferPlanLineEntity2.setStartGeoCode("");
        transferPlanLineEntity2.setEndCity("上海");
        transferPlanLineEntity2.setEndAddress("上海市静安区彭浦镇平遥路95号");
        transferPlanLineEntity2.setEndGeoCode("121.432113,31.29617");
        transferPlanLineEntity2.setZydw(2.0);
        transferPlanLineEntity2.setType(TransferType.DOWN_TRANSFER.getTypeMsg());

        list.add(transferPlanLineEntity1);
        list.add(transferPlanLineEntity2);
        return list;
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
        goodsPlanLineEntity.setBillType(BillType.DELIVER_GOODS.getTypeMsg());

        list.add(goodsPlanLineEntity);

        GoodsPlanLineEntity goodsPlanLineEntity2 = new GoodsPlanLineEntity();
        goodsPlanLineEntity2.setStoreGeoCode("121.303183,31.20409");
        goodsPlanLineEntity2.setOrderGeoCodeList(getGoodsOrdersEntity());
        goodsPlanLineEntity2.setCph("沪A66666");
        goodsPlanLineEntity2.setGoodsBill("0666632433");
        goodsPlanLineEntity2.setBillType(BillType.DELIVER_GOODS.getTypeMsg());

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
        SingleVehicleWorkDurationService svwds = new SingleVehicleWorkDurationService(new AmapService());
        try {
            svwds.computeSingleVehicleWorkDuration();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
