package com.hoau.hoaureport.module.job.server.util.mapUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoau.hoaureport.module.job.server.util.HttpUtil;
import com.hoau.hoaureport.module.job.shared.constant.AmapApiConstants;
import com.hoau.hoaureport.module.job.shared.constant.SystemConsts;
import com.hoau.hoaureport.module.job.shared.domain.AmapApiRoutePlanningMultiEntity;
import com.hoau.hoaureport.module.job.shared.domain.DeliverGoodsPlanLineEntity;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/10 20:23
 */
public class MapApiTool {

    //private static String routePlanningUrl_2 = "&origin=121.303183,31.20409&destination=121.303183,31.20409&waypoints=121.437694,31.195071";

    private static ExecutorService es = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);

    private static ConcurrentHashMap<String,Integer> routePlanningCache
                        = new ConcurrentHashMap<String, Integer>();

    private static ConcurrentHashMap<String,FutureTask<Integer>> processingRoutePlanningCache
                        = new ConcurrentHashMap<String, FutureTask<Integer>>();

    /**
     * 通过json解析,返回地图接口中的distance值
     * @param jsonStr
     * @return
     */
    private static Integer getRoutePlanningJsonResultToInteger(String jsonStr){
        JsonNode jsonNode;
        JsonNode objNode;
        try {
            jsonNode = new ObjectMapper().readTree(jsonStr);
            if(AmapApiConstants.STATUS_VAL.equals(jsonNode.findValue(AmapApiConstants.STATUS).textValue())&&jsonNode.findValue(AmapApiConstants.ROUTE).size()>0){
                System.out.println("find route !");
                objNode = jsonNode.findPath(AmapApiConstants.PATHS).findPath(AmapApiConstants.DISTANCE);
                int distance = Integer.parseInt(objNode.textValue());
                System.out.println("Api call back result: "+distance+ " meters");
                return distance;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 用于并发执行调用路径规划API任务的Callable实现类
     */
    private static class MapApiRoutePlanningTask implements Callable<Integer> {

        private String url;

        MapApiRoutePlanningTask(String url){
            this.url = url;
        }

        @Override
        public Integer call() throws Exception {
            try{
                Integer distance = getRoutePlanningJsonResultToInteger(HttpUtil.doGetRequest(url));
                routePlanningCache.put(url,distance);
                return distance;
            } finally {
                // 无论正常或是异常都应将正在处理请求任务从并发容器中删除
                processingRoutePlanningCache.remove(url);
            }
        }
    }

    private static FutureTask<Integer> getDistanceFuture(String url){
        FutureTask<Integer> distanceFuture = processingRoutePlanningCache.get(url);
        if(distanceFuture==null){
            FutureTask<Integer> ft = new FutureTask<Integer>(new MapApiRoutePlanningTask(url));
            distanceFuture = processingRoutePlanningCache.putIfAbsent(url,ft);
            if(distanceFuture==null){ //表示当前没有正在执行的任务
                distanceFuture = ft;
                es.execute(ft);
                System.out.println("地图路径规划请求行车距离任务已启动,请等待完成>>>");
            } else {
                System.out.println("已有地图路径规划请求行车距离任务已启动,不必重新启动");
            }
        } else {
            System.out.println("当前已有地图路径规划请求行车距离任务已启动,不必重新启动");
        }
        return distanceFuture;
    }

    public static AmapApiRoutePlanningMultiEntity getDistance(String url){
        Integer distance = routePlanningCache.get(url);
        if(distance == null){
            System.out.println("没有当前请求规划路径的缓存数据,请开始请求任务");
            return new AmapApiRoutePlanningMultiEntity(getDistanceFuture(url));
        } else {
            System.out.println("当前请求已有缓存数据,直接返回");
            return new AmapApiRoutePlanningMultiEntity(distance);
        }
    }

    public static void shutdown(){
        if(es!=null){
            es.shutdown();
        }
    }

//    public static void main(String[] args){
//        ExecutorService ec = Executors.newFixedThreadPool(SystemConsts.CPU_CORES);
//        FutureTask<Integer> ft = new FutureTask<Integer>(new MapApiRoutePlanningTask(routePlanningUrl));
//        ec.execute(ft);
//
//        try {
//            Thread.sleep(3000);
//            System.out.println("Future get: "+ft.get());
//            HttpUtil.closeClient();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        ec.shutdown();
//        ConcurrentHashMap<String, SingleVehicleWorkDurationEntity> map = new ConcurrentHashMap<String,SingleVehicleWorkDurationEntity>();
//        Lock lock = new ReentrantLock();
//    }
}
