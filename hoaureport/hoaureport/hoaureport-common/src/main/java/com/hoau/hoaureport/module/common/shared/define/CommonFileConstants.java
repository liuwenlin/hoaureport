package com.hoau.hoaureport.module.common.shared.define;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * @author：高佳
 * @create：2015年8月5日 上午10:23:47
 * @description：公共文件常量类
 */
public class CommonFileConstants {
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
	 * 资源文件根路径
	 */
	public static final String REAOURCE_ROOT_URL = property.getProperty("resource.root.url");
	
	/**
	 * 品质管理资源根路径
	 */
	public static final String REAOURCE_QLT_URL = REAOURCE_ROOT_URL + "qlt/";
}
