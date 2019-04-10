package com.hoau.hoaureport.module.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

/**
 * @author：高佳
 * @create：2015年6月12日 上午8:56:15
 * @description：文件操作工具类
 */
public class FileUtils extends FileCopyUtils{

	/**
	 * 根据文件路径获取byte数组
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @author 高佳
	 * @date 2015年6月12日
	 * @update
	 */
	public static byte[] getBytes(String filePath) {
		byte[] bytes = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			bytes = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 将byte数组写入文件
	 * @param bfile 数组
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @author 高佳
	 * @date 2015年6月12日
	 * @update 
	 */
	public static void saveByteArrayToFile(byte[] bfile, String filePath,
			String fileName) {
		File out = createFile(filePath, fileName);
		try {
			FileCopyUtils.copy(bfile,out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写文件
	 * @param in 写入文件流
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(InputStream in,String filePath,String fileName){
		File file = createFile(filePath,fileName);
		try {
			FileOutputStream out = new FileOutputStream(file);
			FileCopyUtils.copy(in, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件复制
	 * @param in
	 * @param out
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(File in,File out){
		try {
			FileCopyUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件复制
	 * @param in
	 * @param filePath
	 * @param fileName
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static void copyFile(File in,String filePath,String fileName){
		File out = createFile(filePath,fileName);
		copyFile(in,out);
	}
	/**
	 * 创建文件路径
	 * @param filePath 文件目录
	 * @param fileName 文件名
	 * @return
	 * @author 高佳
	 * @date 2015年8月7日
	 * @update 
	 */
	public static File createFile(String filePath,String fileName){
		File dir = new File(filePath);
		// 判断文件目录是否存在
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if(!filePath.endsWith(File.separator)){
			filePath = filePath + File.separator;
		}
		File file = new File(filePath + fileName);
		return file;
	}
}
