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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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

    private static final Log LOG = LogFactory.getLog(SingleVehicleWorkDurationService.class);

    @Resource
    AmapService amapService;

    @Resource
    SingleVehicleWorkDurationMapper singleVehicleWorkDurationMapper;

    /**
     * 地理编码解析服务
     */
    private static ExecutorService doGeocodingService
            = Executors.newFixedThreadPool(SystemConsts.CPU_CORES*2);

    private static CompletionService doGeocodingCompleteService
            = new ExecutorCompletionService(doGeocodingService);

    private static CompletionService doTransferGeocodingCompleteService
            = new ExecutorCompletionService(doGeocodingService);

    /**
     * 路径规划计算服务
     */
    private static ExecutorService doRoutePlanningService
            = Executors.newFixedThreadPool(SystemConsts.CPU_CORES*2);

    private static CompletionService doRoutePlanningCompleteService
            = new ExecutorCompletionService(doRoutePlanningService);

    private static CompletionService doTransferRoutePlanningCompleteService
            = new ExecutorCompletionService(doRoutePlanningService);

    /**
     * 计算上下转移线路地理编码的FutureTask
     */
    private class TransferPlanGeocodeTask implements Callable<TransferPlanLineEntity> {

        TransferPlanLineEntity transferPlanLineEntity;

        public TransferPlanGeocodeTask(TransferPlanLineEntity transferPlanLineEntity){
            this.transferPlanLineEntity = transferPlanLineEntity;
        }

        @Override
        public TransferPlanLineEntity call() throws Exception {
            return amapService.captureTransferGeocoding(transferPlanLineEntity);
        }
    }

    /**
     * 计算上下转移线路规划线路距离的FutureTask
     */
    private class TransferPlanRouteTask implements Callable<TransferPlanLineEntity> {

        TransferPlanLineEntity transferPlanLineEntity;

        public TransferPlanRouteTask(TransferPlanLineEntity transferPlanLineEntity){
            this.transferPlanLineEntity = transferPlanLineEntity;
        }

        @Override
        public TransferPlanLineEntity call() throws Exception {
            return amapService.captureTransferRoutePlanning(transferPlanLineEntity);
        }
    }

    /**
     * 计算运单地理编码的FutureTask
     */
    private class GeocodingTask implements Callable<GoodsPlanLineEntity> {

        GoodsPlanLineEntity goodsPlanLineEntity;

        public GeocodingTask(GoodsPlanLineEntity goodsPlanLineEntity){
            this.goodsPlanLineEntity = goodsPlanLineEntity;
        }

        @Override
        public GoodsPlanLineEntity call() throws Exception {
            GoodsPlanLineEntity goodsBill = amapService.captureGeocoding(goodsPlanLineEntity);
            return goodsBill;
        }
    }

    /**
     * 计算提送货路径规划的FutureTask
     */
    private class RoutePlanningTask implements Callable<GoodsPlanLineEntity> {

        private GoodsPlanLineEntity goodsPlanLineEntity;

        public RoutePlanningTask(GoodsPlanLineEntity goodsPlanLineEntity){
            this.goodsPlanLineEntity = goodsPlanLineEntity;
        }

        @Override
        public GoodsPlanLineEntity call() throws Exception {
            GoodsPlanLineEntity deliverGoodsBill = amapService.captureRoutePlanning(goodsPlanLineEntity);
            return deliverGoodsBill;
        }
    }

    /**
     * 单车工作时长计算过程方法
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public void computeSingleVehicleWorkDuration() throws InterruptedException, ExecutionException {

        //1.首先获取计算车辆的实体Map(预先加载车牌号的基础信息,待需完成字段计算完成后,然后持久化)
        Map<String, SingleVehicleWorkDurationEntity> resultMap
                = new HashMap<String, SingleVehicleWorkDurationEntity>();

        List<SingleVehicleWorkDurationEntity> entityList
                = singleVehicleWorkDurationMapper.getSingleVehicleWorkDurationList();
        //将返回的list集合中的SingleVehicleWorkDurationEntity对象
        for(SingleVehicleWorkDurationEntity entity : entityList){
            resultMap.put(entity.getCph(),entity);
        }

        //2.再获取上下转移线路串线车辆信息
        List<TransferPlanLineEntity> transferPlanLineList
                = singleVehicleWorkDurationMapper.getTransferPlanLineList();

        //3.最后获取取派件线路信息
        List<GoodsPlanLineEntity> goodsBillsList
                = singleVehicleWorkDurationMapper.getGoodsPlanLineList();

        LOG.info("开始计算单车工作时长>>>");

        Long startTime = System.currentTimeMillis();

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
            Thread.sleep(25);
        }

        //4.4 提送货线路路径规划距离计算
        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            Future<GoodsPlanLineEntity> future = doGeocodingCompleteService.take();
            doRoutePlanningCompleteService.submit(new RoutePlanningTask(future.get()));
            Thread.sleep(25);
        }

        //4.5 上下转移线路路径规划距离获取
        for(TransferPlanLineEntity transferPlan:transferPlanLineList){
            Future<TransferPlanLineEntity> future = doTransferRoutePlanningCompleteService.take();
            TransferPlanLineEntity tp = future.get();
            if(tp.getType() == TransferType.UP_TRANFER.getTypeMsg()){
                int lc = resultMap.get(tp.getCph()).getSzylc() == null?0:resultMap.get(tp.getCph()).getSzylc();
                double dw = resultMap.get(tp.getCph()).getSzydw() == null?0:resultMap.get(tp.getCph()).getSzydw();
                resultMap.get(tp.getCph()).setSzylc(lc + tp.getPlannedDistance());
                resultMap.get(tp.getCph()).setSzydw(dw + tp.getZydw());
            } else {
                int lc = resultMap.get(tp.getCph()).getXzylc() == null?0:resultMap.get(tp.getCph()).getXzylc();
                double dw = resultMap.get(tp.getCph()).getXzydw() == null?0:resultMap.get(tp.getCph()).getXzydw();
                resultMap.get(tp.getCph()).setXzylc(lc + tp.getPlannedDistance());
                resultMap.get(tp.getCph()).setXzydw(dw + tp.getZydw());
            }
        }

        //4.6 提送货线路路径规划距离获取
        for(GoodsPlanLineEntity goodsBill:goodsBillsList){
            Future<GoodsPlanLineEntity> future = doRoutePlanningCompleteService.take();
            GoodsPlanLineEntity gp = future.get();
            if(gp.getBillType() == BillType.DELIVER_GOODS.getTypeMsg()){
                int lc = resultMap.get(gp.getCph()).getPjghlc() == null?0:resultMap.get(gp.getCph()).getPjghlc();
                resultMap.get(gp.getCph()).setPjghlc(lc + gp.getGoodsDistance());
                int js = 0;
                double dw = 0;
                for(GoodsOrdersEntity order:gp.getOrderGeoCodeList()){
                    js = js + order.getCountOfOrder();
                    dw = dw + order.getTon();
                }
                resultMap.get(gp.getCph()).setPjjs(js);
                resultMap.get(gp.getCph()).setPjdw(dw);
            } else {
                int lc = resultMap.get(gp.getCph()).getQjghlc() == null?0:resultMap.get(gp.getCph()).getQjghlc();
                resultMap.get(gp.getCph()).setQjghlc(lc + gp.getGoodsDistance());
                int js = 0;
                double dw = 0;
                for(GoodsOrdersEntity order:gp.getOrderGeoCodeList()){
                    js = js + order.getCountOfOrder();
                    dw = dw + order.getTon();
                }
                resultMap.get(gp.getCph()).setQjjs(js);
                resultMap.get(gp.getCph()).setQjdw(dw);
            }
        }

        //5.将统计计算结果插入数据库中
        singleVehicleWorkDurationMapper.batchInsertSingleVehicleWorkDurationInfo(
                new LinkedList<SingleVehicleWorkDurationEntity>(resultMap.values()));

        Long endTime = System.currentTimeMillis();

        LOG.info("统计结果插入数据库完毕!总耗时为: " + (endTime - startTime)/1000 + "秒");

        //6.关闭请求任务线程池
        MapApiTool.shutdown();

        //7.关闭计算任务线程池
        this.shutdown();

    }

    private void shutdown(){
        if(doRoutePlanningService != null){
            doRoutePlanningService.shutdown();
        }
        if(doGeocodingService != null){
            doGeocodingService.shutdown();
        }
    }
}
