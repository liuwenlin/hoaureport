package com.hoau.hoaureport.module.job.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.job.shared.domain.EczzsjEntity;

/**
 *
 * @author 凌骏
 * @date 2016年6月12日下午3:34:30
 */
@Repository
public interface YthmdsjMapper {

	List<EczzsjEntity> GetYthmdsjInfo(String beginDate, String endDate);

	List<EczzsjEntity> GetSzyfhInfo(String ydbh);

	List<EczzsjEntity> GetFhsjInfo(String ydbh,String mddssyjgs);

	EczzsjEntity GetDhsjInfo(String ydbh,String mddssyjgs);

	EczzsjEntity GetYthmdlist(String qyd,String ythmd);

	List<EczzsjEntity> GetXzysjInfo(String ydbh);

	int insertYthInfo(EczzsjEntity eczzsjEntity);

	
	
}