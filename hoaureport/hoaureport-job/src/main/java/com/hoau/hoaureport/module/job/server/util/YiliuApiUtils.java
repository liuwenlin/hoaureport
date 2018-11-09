package com.hoau.hoaureport.module.job.server.util;

import com.google.gson.*;
import com.hoau.hoaureport.module.job.shared.constant.YiliuApiParams;
import com.hoau.hoaureport.module.job.shared.domain.YiliuApiEntity;
import com.hoau.hoaureport.module.util.DateUtils;
import com.hoau.hoaureport.module.util.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author linwenlin
 * @date 2018/3/5
 * @time 21:32
 */
public class YiliuApiUtils {

    /**
     * 用于记录每次请求时间的字符串
     */
    private static String timestamp;

    /**
     * 根据接口参数和签名所需参数生成请求参数列表
     * @param propertiesMap
     * @param signList
     * @return
     */
    public static List<NameValuePair> loadPostParams(Map<String,String> propertiesMap,List<String> signList) {

        // 创建参数名-参数值对集合
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();

        for(String str : signList){
            if(YiliuApiParams.TIMESTAMP.equals(str)){
                timestamp = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT).format(new Date(System.currentTimeMillis()));
                paramsList.add(new BasicNameValuePair(str,timestamp));
            } else {
                paramsList.add(new BasicNameValuePair(str,propertiesMap.get(str) == null ? "" : propertiesMap.get(str)));
            }

        }

        // 添加md5加密后的签名字符串（sign签名生成规则详情参照易流API接口说明）
        String signature = StringUtils.md5(
                propertiesMap.get(YiliuApiParams.APPSECREAT)
                + StringUtils.toStringSeq(filterEffectivePropertiesValue(propertiesMap,signList.toArray(new String[0])))
                + propertiesMap.get(YiliuApiParams.APPSECREAT));
        paramsList.add(new BasicNameValuePair(YiliuApiParams.SIGN, signature));

        return paramsList;
    }

    /**
     * 将用于生成有效签名的接口参数名和参数值组成字符串数组返回
     * @param map 传入的接口参数键值对
     * @param effectiveParams 生成签名所需的其余参数
     * @return 处理后字符串数组
     */
    private static String[] filterEffectivePropertiesValue(Map<String,String> map , String... effectiveParams) {

        List<String> paramsList = new ArrayList<String>();
        for(String str : effectiveParams){
            String temp = map.get(str);
            if(temp == null){ //如果Map中的value值为null，设置为空串
                temp = "";
            }
            if(str.equals(YiliuApiParams.TIMESTAMP)){ //如果Map中的value有易流接口请求的时间戳，将其设置为每次请求时的系统时间(参照易流API接口文档关于timestamp的说明)
                temp = timestamp;
            }
            paramsList.add(str + temp);
        }
        return paramsList.toArray(new String[0]);
    }


    /**
     * 用于处理接口返回的JSON数据
     * @param jsonstr 传入的json字符串
     * @param type 传入的class类型
     * @return 返回给定参数类型的List集合
     */
    public static <T> List<T> getJsonDataList(String jsonstr, Class<T> type){
        List<T> list = new ArrayList<T>();

        // 创建接口返回的JSON
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Integer.class,new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class,new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class,new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class,new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class,new LongDefault0Adapter())
                .registerTypeAdapter(long.class,new LongDefault0Adapter())
                .create();
        JsonObject resultData;
        JsonArray jsonArray;

        resultData = new JsonParser().parse(jsonstr).getAsJsonObject();
        int code = resultData.getAsJsonObject("result").get("code").getAsInt();
        System.out.println(code);
        if(code == 1){
            jsonArray = resultData.getAsJsonObject("result").getAsJsonArray("data");
            for(JsonElement jsonElement : jsonArray){
                list.add(gson.fromJson(jsonElement,type));
            }
            return list;
        } else {
            return null;
        }

    }

    /**
     * 用于将易流接口的参数集合转化为Map
     * @param list 查询易流接口返回的实例集合
     * @return 参数值对的Map
     */
    public static Map<String,String> paramsListToMap(List<YiliuApiEntity> list){
        Map<String,String> map = new HashMap<String, String>();
        for(YiliuApiEntity entity : list){
            if(entity != null){
                map.put(entity.getParams_name(),entity.getParams_value());
            }
        }
        return map;
    }

    /**
     * 用于将易流接口参数实例集合转化为字符集合
     * @param list
     * @return
     */
    public static List<String> paramsToStrList(List<YiliuApiEntity> list){
        List<String> ls = new ArrayList<String>();
        for(YiliuApiEntity entity : list){
            if(entity != null){
                ls.add(entity.getParams_name());
            }
        }
        return ls;
    }
}
