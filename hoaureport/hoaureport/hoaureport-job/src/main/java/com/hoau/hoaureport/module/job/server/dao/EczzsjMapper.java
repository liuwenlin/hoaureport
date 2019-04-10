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
public interface EczzsjMapper {
	public List<EczzsjEntity> getEczzsjInfo(String beginDate,String endDate);
	
	public int insertEczzData(EczzsjEntity eczzsjEntity);
	
	public String getSftjd(String fdbgsbh, String fcbh); 

	public String getTjdScsj(String fcbh,String scgsbh);

	public String getTjdfc(String scgsbh, String fcbh);

	public String getTjddc(String fdbgsbh, String fcbh);

	public EczzsjEntity getycjxzsj(String ydbh); 
}