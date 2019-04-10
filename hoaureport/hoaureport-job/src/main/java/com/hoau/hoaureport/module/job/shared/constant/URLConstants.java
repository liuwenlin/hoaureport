package com.hoau.hoaureport.module.job.shared.constant;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * @author：高佳
 * @create：2015年6月4日 下午1:46:54
 * @description：
 */
public class URLConstants {
	public static  String MDM_URL = null;
	static{
		try {
			MDM_URL = (String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("mdm.url");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
