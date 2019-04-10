package com.hoau.hoaureport.module.util;/**
 * 经纬度转换工具
 * @author 高佳
 * @date 2015年5月26日
 */
public class LatLngConvertUtil {
	
	/**
	 * 谷歌纬度转换成百度纬度
	 * @param lat
	 * @return
	 * @author 高佳
	 * @date 2015年5月26日
	 * @update 
	 */
	public static double latG2B(double lat){
		double i = (Math.round((lat+0.0060) * 10000000));
		double j = 10000000d;
		double res = i/ j;
		return res;
	}
	/**
	 * 谷歌地图经度转换为百度地图经度
	 * @param lng
	 * @return
	 * @author 高佳
	 * @date 2015年5月26日
	 * @update 
	 */
	public static double lngG2B(double lng){
		double i = (Math.round((lng+0.0065) * 10000000));
		double j = 10000000d;
		double res = i/ j;
		return res;
	}
	
}
