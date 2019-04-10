package com.hoau.hoaureport.module.configreport.shared.domain;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class ExcelReturn {

	private String fileName;

	private InputStream inputStream;
	
	private String zipName;
	
	private String jobFileName;

	public String getFileName() {
		return fileName;
	}

	
	public String getZipName() {
		return zipName;
	}


	public void setZipName(String zipName) {
		this.zipName = zipName;
	}


	public String getJobFileName() {
		return jobFileName;
	}


	public void setJobFileName(String jobFileName) {
		this.jobFileName = jobFileName;
	}


	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String agent = request.getHeader("User-Agent");
		if (null != agent) {
			agent = agent.toLowerCase();
			if (agent.contains("firefox")) {
				this.fileName = new String(URLDecoder.decode(fileName, "UTF-8")
						.getBytes(), "ISO8859-1");
			} else if (agent.contains("chrome")) {
				this.fileName = new String(URLDecoder.decode(fileName, "UTF-8")
						.getBytes(), "ISO8859-1");
			}
		}
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
