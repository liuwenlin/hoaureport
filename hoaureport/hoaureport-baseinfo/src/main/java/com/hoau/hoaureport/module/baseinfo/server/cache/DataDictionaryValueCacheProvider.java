package com.hoau.hoaureport.module.baseinfo.server.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.provider.ITTLCacheProvider;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.server.dao.DataDictionaryValueDao;

/**
 * @author 龙海仁
 * @create：2015年11月10日 上午9:14:10
 * @description：
 */
@Component
public class DataDictionaryValueCacheProvider implements ITTLCacheProvider<String> {
	@Autowired
	private DataDictionaryValueDao dataDictionaryValueDao;

	@Override
	public String get(String key) {
		if(StringUtil.isEmpty(key)){
			return null;
		}
		String[] params = key.split("#");
		if(params.length != 2){
			return null;
		}
		return dataDictionaryValueDao.queryDataDictionaryValue(params[0], params[1]);
	}
}
