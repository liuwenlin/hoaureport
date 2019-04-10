package com.hoau.hoaureport.module.util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author：高佳
 * @create：2015年8月20日 下午3:04:04
 * @description：图片工具类
 */
public class ImageUtil {
	
	
	/**
	 * 判断是否是图片
	 * @param imageFile
	 * @return
	 * @author 高佳
	 * @date 2015年8月20日
	 * @update 
	 */
	public static boolean isImage(File imageFile){
		try {
			BufferedImage image = ImageIO.read(imageFile);
			return image != null;
		} catch (IOException e) {
			return false;
		}
	}

}
