package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.job.server.dao.OrderAddressEnityMapper;
import com.hoau.hoaureport.module.job.server.service.IOmsDistanceService;
import com.hoau.hoaureport.module.job.shared.domain.OmsOrderVo;
import com.hoau.hoaureport.module.job.shared.domain.OrderAddressEnity;
import com.hoau.hoaureport.module.util.LatitudeUtils;
import com.hoau.hoaureport.module.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OMS取件距离计算服务接口实现类
 */
@Service
public class OmsDistanceService implements IOmsDistanceService {
    private static Logger log = LoggerFactory.getLogger(OmsDistanceService.class);

    @Autowired
    private OrderAddressEnityMapper orderAddressEnityMapper;

    private final static int ORDER_LIMIT=1000;

    @Override
    public long orderHandle(String bizdate) {

        if(StringUtil.isEmpty(bizdate)){
            return 0;
        }
        long total =orderAddressEnityMapper.queryOmsOrderCount(bizdate);
        if(total<1){
            return 0;
        }
        int listsize = (int)total/ORDER_LIMIT;
        if(total%ORDER_LIMIT > 0){
            listsize++;
        }
        log.info("queryOmsOrderCount_total:"+total);
        for(int i=0;i<listsize;i++){
            queryOrderList(bizdate,i*ORDER_LIMIT,ORDER_LIMIT);
        }

        return total;
    }

    @Override
    public int updateOmsOrder(String bizdate) {

        if(!StringUtil.isEmpty(bizdate)){
            return orderAddressEnityMapper.updateOmsOrderStatus(bizdate);
        }
        return 0;

    }


    private void queryOrderList(String bizdate,int start,int limit){
        try{
            log.info("queryOrderList_start:"+start);
            List<OmsOrderVo> listOrder= orderAddressEnityMapper.queryOmsOrderList(bizdate,new RowBounds(start,limit));
            for(OmsOrderVo order :listOrder){
                orderAddress(order);
            }

        }catch(Exception e){
            log.error("queryOrderList_err:",e);
            e.printStackTrace();
        }
    }

    private void orderAddress(OmsOrderVo order){
        try{
            if(order ==null || StringUtil.isEmpty(order.getShipperAddress()) || StringUtil.isEmpty(order.getPickupAddress())){
                return;
            }
            log.info("orderAddress_orderno:"+order.getOrderNo());
            OrderAddressEnity addressEnity = new OrderAddressEnity();
            addressEnity.setId(UUIDUtil.getUUID().toString());
            addressEnity.setOrderno(order.getOrderNo());
            addressEnity.setShipperAddres(order.getShipperAddress());
            addressEnity.setShipperCity(order.getShipperCity());
            addressEnity.setPickupAddres(order.getPickupAddress());
            addressEnity.setCreateUser("job");
            addressEnity.setModifyUser("job");
            addressEnity.setCreateDate(new Date());
            addressEnity.setModifyDate(new Date());
            //发货地理位置
            Map<String,String> shipperMap = LatitudeUtils.getLatitude(order.getShipperAddress(),order.getShipperCity());
            //发货地解析失败
            if(shipperMap==null || StringUtil.isEmpty(shipperMap.get("lng")) || StringUtil.isEmpty(shipperMap.get("lat"))){
                addressEnity.setApistatus(2);
                orderAddressEnityMapper.insertOrderAddres(addressEnity);
                return;
            }
            addressEnity.setShipperLng(shipperMap.get("lng"));
            addressEnity.setShipperLat(shipperMap.get("lat"));
            //取件地理位置
            Map<String,String> pickupMap = LatitudeUtils.getLatitude(order.getPickupAddress(),"");
            //取件地解析失败
            if(pickupMap==null || StringUtil.isEmpty(pickupMap.get("lng")) || StringUtil.isEmpty(pickupMap.get("lat"))){
                addressEnity.setApistatus(2);
                //addressEnity.setCreateDate(new Date());
                orderAddressEnityMapper.insertOrderAddres(addressEnity);
                return;
            }
            addressEnity.setPickupLng(pickupMap.get("lng"));
            addressEnity.setPickupLat(pickupMap.get("lat"));

            //距离
            BigDecimal dis = distance(shipperMap.get("lng"),shipperMap.get("lat"),pickupMap.get("lng"),pickupMap.get("lat"));
            if(dis ==null){
                addressEnity.setApistatus(2);
                //addressEnity.setCreateDate(new Date());
                orderAddressEnityMapper.insertOrderAddres(addressEnity);
                return;
            }
            addressEnity.setOrderDistance(dis);
            addressEnity.setApistatus(1);
            //addressEnity.setCreateDate(new Date());
            orderAddressEnityMapper.insertOrderAddres(addressEnity);
        }catch (Exception e){
            log.error("orderAddress_err:",e);
            e.printStackTrace();
        }


    }


    private BigDecimal distance(String slng, String slat, String plng, String plat){

        try{
            Double d =LatitudeUtils.getDistance(Double.valueOf(slng),Double.valueOf(slat),Double.valueOf(plng),Double.valueOf(plat));
            return new BigDecimal(d);
        }catch (Exception e){
            log.error("distance_err:",e);
            e.printStackTrace();
        }
        return null;
    }









    public static void main(String[] args) {
        long total=11000;
        int listsize = (int)total/ORDER_LIMIT;

        if(total%ORDER_LIMIT > 0){
            listsize++;
        }
        System.out.println(listsize);


    }


}
