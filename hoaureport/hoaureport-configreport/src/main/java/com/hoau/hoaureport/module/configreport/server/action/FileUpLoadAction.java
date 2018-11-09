package com.hoau.hoaureport.module.configreport.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hoaureport.module.configreport.server.service.ITonCostManageService;
import com.hoau.hoaureport.module.configreport.server.util.ExcelUtil;

/**
 * excel上传解析
 * ClassName: ExcelImportAction  
 * @author 刘镇松
 * @date 2016年8月24日
 * @version V1.0
 */
@Controller("fileUpLoadAction")
@Scope("prototype")
public class FileUpLoadAction extends AbstractAction{
	
	private static final long serialVersionUID = -3048112043267600861L;
	@Resource
	private ITonCostManageService tonCostManageService;
	
	private File serviceXls;
	private String serviceXlsFileName;
	private String serviceXlsContentType;
	private String outFileName;
	private String fileName;
	private boolean flag=true;
	
	public String isExistFile(){
		String downloadPath = ServletActionContext.getServletContext().getRealPath("/"+fileName);
		File file = new File(downloadPath);
		if (!file.exists() && !file.isFile()){
			flag=false;
		}
		return returnSuccess();
	}

	/**
	 * 
	 * @Description: 上传文件返回文件名
	 * @param @return
	 * @param @throws IOException   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年8月24日
	 */
	public String upLoadFile() throws IOException{
		String prefix = serviceXlsFileName.substring(serviceXlsFileName.lastIndexOf(".") + 1);
		InputStream is = new FileInputStream(serviceXls);
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upLoad");
		File file = new File(uploadPath);
		if (!file.isDirectory()) {
			file.mkdir();
		}
		// 设置目标文件
		File toFile = new File(uploadPath, ExcelUtil.getNewFileName(prefix));
		// 创建一个输出流
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[1024];
		int length = 0;
		// 读取myFile文件输出到toFile文件中
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		// 关闭输出流
		os.close();
		serviceXls=null;
		outFileName="upLoad/"+toFile.getName();
		return returnSuccess();
	}

	public File getServiceXls() {
		return serviceXls;
	}

	public void setServiceXls(File serviceXls) {
		this.serviceXls = serviceXls;
	}

	public String getServiceXlsFileName() {
		return serviceXlsFileName;
	}

	public void setServiceXlsFileName(String serviceXlsFileName) {
		this.serviceXlsFileName = serviceXlsFileName;
	}

	public String getServiceXlsContentType() {
		return serviceXlsContentType;
	}

	public void setServiceXlsContentType(String serviceXlsContentType) {
		this.serviceXlsContentType = serviceXlsContentType;
	}
	public String getOutFileName() {
		return outFileName;
	}
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
