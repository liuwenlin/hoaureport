package com.hoau.hoaureport.module.util;

import com.alibaba.fastjson.JSONObject;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class LatitudeUtils {
	private static Logger log = LoggerFactory.getLogger(LatitudeUtils.class);
	private static Properties pro = loadConfig();
	//服务器sn
	public static final String KEY_1 = pro.getProperty("baidumap.service.snkey");
	//浏览器sn
	public static final String KEY_2 = pro.getProperty("baidumap.browser.snkey");
	//测试用
   ////	//public static final String KEY_2 ="uA0at0G2vcVh9ImavSD1mzwbVKQGklHe";
	
	private static final double PI = 3.14159265;  
    private static final double EARTH_RADIUS = 6378137;  
    private static final double RAD = Math.PI / 180.0;  
    
    private static Properties loadConfig() {
		Properties pro;
		try {
			pro = ConfigFileLoadUtil
					.getPropertiesForClasspath("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			pro = new Properties();
		} catch (IOException e) {
			e.printStackTrace();
			pro = new Properties();
		}
		return pro;
	}
    
	/**
	 * 获取指定IP对应的经纬度（为空返回当前机器经纬度）
	 * 
	 * @param ip
	 * @return
	 */
	public static String[] getIPXY(String ip) {

		if (null == ip) {
			ip = "";
		}

		try {

			URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + KEY_2
					+ "&ip=" + ip + "&coor=bd09ll");
			InputStream inputStream = url.openStream();
			InputStreamReader inputReader = new InputStreamReader(inputStream);
			BufferedReader reader = new BufferedReader(inputReader);
			StringBuffer sb = new StringBuffer();
			String str;
			do {
				str = reader.readLine();
				sb.append(str);
			} while (null != str);

			str = sb.toString();
			if (null == str || str.isEmpty()) {
				return null;
			}

			// 获取坐标位子
			int index = str.indexOf("point");
			int end = str.indexOf("}}", index);

			if (index == -1 || end == -1) {
				return null;
			}

			str = str.substring(index - 1, end + 1);
			if (null == str || str.isEmpty()) {
				return null;
			}

			String[] ss = str.split(":");
			if (ss.length != 4) {
				return null;
			}

			String x = ss[2].split(",")[0];
			String y = ss[3];

			x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
			y = y.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));

			System.out.print("x:"+x);
			System.out.print("y:"+y);
			return new String[] { x, y };

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
	 */
	public static Map<String, String> getLatitude(String address,String city) {
		try {
			// 将地址转换成utf-8的16进制
			address = URLEncoder.encode(address, "UTF-8");
			// 如果有代理，要设置代理，没代理可注释
			// System.setProperty("http.proxyHost","192.168.172.23");
			// System.setProperty("http.proxyPort","3209");
			String urlstr="";
			//URL resjson = null;
			if(city!=null && !"".equals(city)) {
				urlstr="http://api.map.baidu.com/geocoder/v2/?address=" + address + "&city=" + city + "&output=json&ak=" + KEY_2;
			}else {
				urlstr="http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + KEY_2 ;
			}
			log.info("http>>>>> request url:"+urlstr);
			URL resjson = new URL(urlstr);
			BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
			log.info("http>>>>> return json:" + str);
			Map<String, String> map = null;
			if (str != null) {
				JSONObject jsonObject = JSONObject.parseObject(str);

				//0表示正常
			   if(null != jsonObject.get("status") && jsonObject.get("status").toString().equals("0")){
				   	map = new HashMap<String, String>();

		        	String lng=jsonObject.getJSONObject("result").getJSONObject("location").getString("lng");
		        	String lat=jsonObject.getJSONObject("result").getJSONObject("location").getString("lat");
		        	//是否精确查找 1为精确查找，0为不精确
		        	String precise = jsonObject.getJSONObject("result").get("precise").toString();
		        	//可信度
		        	String confidence = jsonObject.getJSONObject("result").get("confidence").toString();
		        	map.put("lng", lng);
		        	map.put("lat", lat);
		        	map.put("precise", precise);
		        	map.put("confidence", confidence);
		        	return map;
		        }

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("http_url_err:",e);
			return null;
		}
		return new HashMap();
	}
	
	/** 
     * 生成以中心点为中心的四方形经纬度 
     *  
     * @param lat 纬度 
     * @param lon 精度 
     * @param raidus 半径（以米为单位） 
     * @return 
     */  
    public static double[] getAround(double lat, double lon, int raidus) {  
  
        Double latitude = lat;  
        Double longitude = lon;  
        Double degree = (24901 * 1609) / 360.0;  
        double raidusMile = raidus;  
  
        Double dpmLat = 1 / degree;  
        Double radiusLat = dpmLat * raidusMile;  
        Double minLat = latitude - radiusLat;  
        Double maxLat = latitude + radiusLat;  
  
        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));  
        Double dpmLng = 1 / mpdLng;               
        Double radiusLng = dpmLng * raidusMile;   
        Double minLng = longitude - radiusLng;    
        Double maxLng = longitude + radiusLng;    
        return new double[] { minLat, minLng, maxLat, maxLng };  
    }
    
    /** 
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米 
     * @param lng1 
     * @param lat1 
     * @param lng2 
     * @param lat2 
     * @return 
     */  
    public static double getDistance(double lng1, double lat1, double lng2, double lat2)  
    {  
       double radLat1 = lat1*RAD;  
       double radLat2 = lat2*RAD;  
       double a = radLat1 - radLat2;  
       double b = (lng1 - lng2)*RAD;  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +  
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }  

	public static void main(String args[]) {
		Map<String, String> map = LatitudeUtils.getLatitude("平硕华中路465弄85号","上海市");
		Map<String, String> map2 = LatitudeUtils.getLatitude("中国上海市闵行区合川路3136","");
		if (null != map) {
			System.out.println(map.get("lng"));
			System.out.println(map.get("lat"));
		}
		if (null != map) {
			System.out.println(map2.get("lng"));
			System.out.println(map2.get("lat"));
		}
		System.out.println(getDistance(Double.valueOf(map.get("lng")),Double.valueOf(map.get("lat")),Double.valueOf(map2.get("lng")),Double.valueOf(map2.get("lat"))));

	}
}