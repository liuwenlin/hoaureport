package com.hoau.hoaureport.module.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @author 247010 肖明明
 * @create：2015年11月3日 上午9:56:21
 * @description：http请求工具
 */
public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	private String urlStr="";
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Object getObject(Object object) throws IOException,
		BusinessException, ClassNotFoundException {
		Object obj = null;
		if (urlStr == null || urlStr.trim().length() < 1) {
			throw new BusinessException("http urlStr is null !");
		}
		try {
			URL url = new URL(urlStr);
		
			URLConnection conn = url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-type", "application/octet-stream");
			out = new ObjectOutputStream(conn.getOutputStream());
			out.writeObject(object);
			out.flush();
		
			in = new ObjectInputStream(conn.getInputStream());
			obj = in.readObject();
		} finally {
			release();
		}
		return obj;
	}
	
	/**
	 * 释放Object对像流资源
	 */
	public void release() {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				log.error("Release out object stream error! " + e.getMessage());
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				log.error("Release in object stream error! " + e.getMessage());
			}
		}
	}
	
	public HttpClientUtil(String urlStr) {
		this.urlStr=urlStr;
	}


	public String getUrlStr() {
		return urlStr;
	}


	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	
	
}
