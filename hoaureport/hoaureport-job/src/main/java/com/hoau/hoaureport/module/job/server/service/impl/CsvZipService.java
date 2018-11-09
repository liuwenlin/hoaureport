package com.hoau.hoaureport.module.job.server.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.common.server.constants.ItfConifgConstant;
import com.hoau.hoaureport.module.configreport.server.service.IWayBillZipService;
import com.hoau.hoaureport.module.job.server.service.ICsvZipService;
/**
 * csv zip包文件处理服务接口实现类
 * @author 273503
 *
 */
@Service
public class CsvZipService implements ICsvZipService {

	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private IWayBillZipService wayBillZipService;
	
	@Override
	public void deleteCsvZip(int days) {
		String dateName = getDateName(days);
		
		deleteWayBillZip(dateName);
		
		String path = ItfConifgConstant.HAR_FILE_DIR+dateName;
		File file = new File(path);
		//目录是否存在
		if(file.exists() && file.isDirectory()){
			deleteFile(file);
		}
	}
	/**
	 * 删除目录及子目录文件
	 * @param file
	 * @return
	 */
	private boolean deleteFile(File file){
		String[] children =file.list();
		if(children.length<1){
			return file.delete();
		}
		for(int i=0;i<children.length;i++){
			File cf = new File(file, children[i]);
			if(cf.isDirectory()){
				deleteFile(cf);
			}else{
				cf.delete();
			}
		}
		file.delete();
		return true;
	}
	/**
	 * 清除运单物流时间16号报表包数据
	 * @param date
	 */
	private void deleteWayBillZip(String date){
		try {
			int r =wayBillZipService.deleteByCreatedate(date);
			log.info("运单物流时间每月16号报表zip包数据清除完成：<= "+date+" row:"+r);
		} catch (Exception e) {
			log.error("运单物流时间每月16号报表zip包数据清除异常：",e);
		}
	}
	
	
	private  String getDateName(int days){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(Calendar.DAY_OF_YEAR, (0 - days));
		Date dt = rightNow.getTime();
		String reStr = sdf.format(dt);
		log.info(" job delfile days:"+reStr);
		return reStr;
	}
	

}
