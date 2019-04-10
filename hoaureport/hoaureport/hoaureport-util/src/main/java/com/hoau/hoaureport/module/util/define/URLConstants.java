package com.hoau.hoaureport.module.util.define;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * @author：高佳
 * @create：2015年6月5日 下午1:20:05
 * @description：URL相关常量类
 */
public class URLConstants {
	private static Properties property = null;
	static{
		try {
			property = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MDM url配置
	 */
	public  final static  String MDM_URL = property.getProperty("mdm.url");
	
	/**
	 * 微信企业号 url配置
	 */
	public  final static  String EWECHAT_URL = property.getProperty("ewechat.url");

	/**
	 * 根据config文件中配置的属性名称获取对应的URL
	 * @param propertyName 传入的属性名称
	 * @return 返回URL
	 */
	public static String getURL(String propertyName){
		return property.getProperty(propertyName);
	}
}
