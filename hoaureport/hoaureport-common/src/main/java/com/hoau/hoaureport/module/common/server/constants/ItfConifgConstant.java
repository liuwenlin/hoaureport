package com.hoau.hoaureport.module.common.server.constants;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 
 * @author 莫涛
 * @date 2016年1月13日下午2:21:39
 */
public class ItfConifgConstant {
	public static  String MDM_URL_ORG_ALL = null;
	public static  String MDM_URL_ORG_INCREMENT = null;
	public static  String HAR_FILE_DIR = null;
	
	public static double MAP_PIE_RATIO =0;
	
	public static  String MAP_HOMEPAGE_URI = null;
	public static String MAP_HOMEPAGE_ID=null;
	
	/**是否有效  有效**/
	public static final String HAR_ACTIVE_YES = "Y";
	/**是否有效  无效**/
	public static final String HAR_ACTIVE_NO = "N";
	
	/**Blob 转 String 编码**/
	public static String SQL_BLOB_CODE="GBK";
	
	
	static{
		try {
			MDM_URL_ORG_ALL = (String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("mdm.org_all");
			MDM_URL_ORG_INCREMENT = (String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("mdm.org_increment");
			HAR_FILE_DIR = (String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("har.file.dir");
			MAP_PIE_RATIO =Double.parseDouble( ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("map.pie.ratio").toString());
			
			MAP_HOMEPAGE_URI =(String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("map.homepage.uri");
			MAP_HOMEPAGE_ID =(String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("map.homepage.id");
		
			SQL_BLOB_CODE = (String)ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").get("sql.blobtostring.code");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
