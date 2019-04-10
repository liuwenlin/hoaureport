package com.hoau.hoaureport.module.baseinfo.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.server.service.IDataDictionaryService;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.DataDictionaryEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.exception.DataDictionaryException;
import com.hoau.hoaureport.module.baseinfo.server.cache.DataDictionaryCache;
import com.hoau.hoaureport.module.baseinfo.server.dao.DataDictionaryDao;
import com.hoau.hoaureport.module.frameworkimpl.server.context.HoauReportUserContext;
import com.hoau.hoaureport.module.util.BaseinfoConstants;
import com.hoau.hoaureport.module.util.UUIDUtil;

/**
 * @author：李旭锋
 * @create：2015年6月5日 下午5:20:58
 * @description：
 */
@Service
public class DataDictionaryService implements IDataDictionaryService {

	@Autowired
	private DataDictionaryDao dataDictionaryDao;

	@Override
	@Transactional
	public void deleteDictionary(DataDictionaryEntity dictionEntity) {
			dictionEntity.setActive(BaseinfoConstants.NO);
			dictionEntity.setVersionNo(UUIDUtil.getVersion());
			dictionEntity.setModifyDate(new Date());
			dictionEntity.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
			
			dataDictionaryDao.deleteDictionaryEntity(dictionEntity);
		

	}


	@Override
	@Transactional
	public void addDictionary(DataDictionaryEntity dictionEntity) {
		
		dictionEntity = addIdAndTimeToObject(dictionEntity);
		//编码不能为空
		if(StringUtil.isBlank(dictionEntity.getTermsCode())){
			throw new DataDictionaryException(DataDictionaryException.DATADICTION_TERMSCODE_ISNULL);
		}
		//名称不能为空
		if(StringUtil.isBlank(dictionEntity.getTermsCode())){
			throw new DataDictionaryException(DataDictionaryException.DATADICTION_TERMSNAME_ISNULL);
		}
		dataDictionaryDao.saveDictionaryEntity(dictionEntity);

	}

	@Override
	public List<DataDictionaryEntity> queryDictionaryByExample(
			DataDictionaryEntity dictionaryEntity,int limit, int start) {	
		RowBounds rowBounds = new RowBounds(start,limit);
		dictionaryEntity.setActive(BaseinfoConstants.YES);
		return dataDictionaryDao
				.queryDictionaryEntityByExample(dictionaryEntity,rowBounds);
	}
	@Override
	public Long queryCountDictionaryByExaple(DataDictionaryEntity dictionaryEntityint){
		dictionaryEntityint.setActive(BaseinfoConstants.YES);
		return dataDictionaryDao.queryCountTotalByExample(dictionaryEntityint);
	}
	

	@Override
	public List<DataDictionaryEntity> queryParmasByPrantsCode(String code) {
		return this.dataDictionaryDao.queryParmasByParentCode(code);
	}
 

	/**
	 * 将新增词条增加详细信息
	 * 
	 * @param object
	 * @return
	 * @author 李旭锋
	 * @date 2015年6月10日
	 * @update
	 */
	public DataDictionaryEntity addIdAndTimeToObject(DataDictionaryEntity object) {
		Date dt = new Date();
		object.setId(UUIDUtil.getUUID());
		object.setActive(BaseinfoConstants.YES);
		object.setCreateDate(dt);
		object.setVersionNo(UUIDUtil.getVersion());
		object.setCreateUser(HoauReportUserContext.getCurrentUser().getUserName());
		object.setModifyDate(dt);
		object.setModifyUser(HoauReportUserContext.getCurrentUser().getUserName());
		return object;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DataDictionaryEntity> queryAllDataDictionary() {
		List<DataDictionaryEntity> dicList = new ArrayList<DataDictionaryEntity>();
		ICache<String, DataDictionaryEntity> dicCache = CacheManager
				.getInstance().getCache(
						DataDictionaryCache.DATA_DICTIONARY_UUID);
		Map<String, DataDictionaryEntity> dicMap = dicCache.get();
		if (dicMap != null && dicMap.values() != null) {
			 dicList.addAll(dicMap.values());
		}
		return dicList;
	}

}
