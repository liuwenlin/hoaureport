package com.hoau.hoaureport.module.job.server.service;
/**
 * csv zip包文件处理服务接口
 * @author 273503
 *
 */
public interface ICsvZipService {
	/**
	 * 删除文件包
	 * @param days 
	 */
	void deleteCsvZip(int days);
}