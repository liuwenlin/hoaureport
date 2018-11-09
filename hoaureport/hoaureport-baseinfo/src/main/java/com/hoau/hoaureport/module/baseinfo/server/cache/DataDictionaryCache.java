package com.hoau.hoaureport.module.baseinfo.server.cache;

import com.hoau.hbdp.framework.cache.DefaultStrongRedisCache;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;


/**
 * @author：高佳
 * @create：2015年6月15日 上午10:48:39
 * @description：数据字典缓存
 */
public class DataDictionaryCache extends DefaultStrongRedisCache<String, DataDictionaryEntity>{
	public final static String DATA_DICTIONARY_UUID = DataDictionaryEntity.class.getName();
	public String getUUID() {
		return DATA_DICTIONARY_UUID;
	}
}
