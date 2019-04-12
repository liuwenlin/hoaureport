package com.hoau.hoaureport.module.job.server.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoau.hoaureport.module.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author liuwenlin
 * @version v1.0
 * @date 2019/4/11 15:16
 */
public class HttpUtil {

    private static CloseableHttpClient client = HttpClients.createDefault();

    /**
     * Using http client to do get url request
     * @param url
     * @return
     */
    public static String doGetRequest(String url){
        HttpGet get = new HttpGet(url);
        HttpResponse response;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            System.out.println("Status code: "+response.getStatusLine().getStatusCode());
            String result = EntityUtils.toString(entity, "UTF-8");
            return result;
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Using http client to do post url request
     * @param url
     * @return
     */
    public static String doPostRequest(String url){
        return "";
    }

    public static void closeClient(){
        try {
            if(client != null){
                client.close();
                System.out.println("HttpClient连接已关闭");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
