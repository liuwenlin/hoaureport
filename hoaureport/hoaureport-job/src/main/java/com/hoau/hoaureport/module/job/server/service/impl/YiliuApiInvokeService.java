package com.hoau.hoaureport.module.job.server.service.impl;

import com.hoau.hoaureport.module.job.server.dao.YiliuApiMapper;
import com.hoau.hoaureport.module.job.server.service.IYiliuApiInvokeService;
import com.hoau.hoaureport.module.job.server.util.YiliuApiUtils;
import com.hoau.hoaureport.module.job.shared.domain.VehicleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 实现对易流系统API的调用
 */
@Service
public class YiliuApiInvokeService implements IYiliuApiInvokeService {

    @Autowired
    private YiliuApiMapper yiliuApiMapper;

    /**
     * 日志打印
     */
    private static final Log LOG = LogFactory.getLog(YiliuApiInvokeService.class);

    /**
     * 用于Get方式请求
     *
     * @param url
     */
    @Override
    public void doGetRequest(String url) {

    }

    /**
     * 用于Post方式请求
     *
     * @param url
     */
    @Override
    public void doPostRequest(String url) {

    }

    /**
     * 用于指定的接口的Post方式请求
     * @param url 请求地址
     * @param apiName 接口名称
     */
    @Override
    public void doPostRequest(String url,String apiName) {

        /**
         * 用存储接口请求所需的参数键值对
         */
        Map<String,String> propertiesMap = YiliuApiUtils.paramsListToMap(yiliuApiMapper.findParamsByApiName(apiName));

        /**
         * 用于获取生成签名的参数名称集合
         */
        List<String> signList = YiliuApiUtils.paramsToStrList(yiliuApiMapper.findSignParamsByApiName(apiName));

        // 创建一个HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 创建一个HttpPost对象
        HttpPost post = new HttpPost(url);

        // 创建请求内容实体
        StringEntity entity;

        // 为post请求创建响应体
        HttpResponse response;

        // 为接口请求创建一个参数集合
        List<NameValuePair> params = YiliuApiUtils.loadPostParams(propertiesMap,signList);

        try {
            //将参数包装成一个实体
            entity = new UrlEncodedFormEntity(params,"utf-8");

            // 设置请求的内容
            post.setEntity(entity);

            // 设置请求的报文头部的编码
            post.setHeader(
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));

            // 设置期望服务端返回的编码
            post.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));

            // 执行post请求
            response = client.execute(post);

            // 获取响应码
            int statusCode = response.getStatusLine().getStatusCode();

            // 创建响应Json字符串对象
            String jsonstr = "";

            System.out.println("请求响应码:"+statusCode);
            if(statusCode == 200){
                // 获取响应实体数据，转换为字符串
                jsonstr = EntityUtils.toString(response.getEntity());
                System.out.println(jsonstr);
                if(!("".equals(jsonstr))){
                    List<VehicleEntity> list = YiliuApiUtils.getJsonDataList(jsonstr, VehicleEntity.class);
                    System.out.println(list);
                    // 将获取车辆信息保存到数据库中
                    if(list != null){
                        yiliuApiMapper.batchInsertVehicleInfo(list);
                    }
                }
            } else {
                LOG.info("客户端请求失败,接口响应码:" + statusCode);
            }

            client.close();
        } catch (ClientProtocolException e) {
            LOG.error("客户端协议异常" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("客户端输入流异常" + e.getMessage());
            e.printStackTrace();
        }

    }


}
