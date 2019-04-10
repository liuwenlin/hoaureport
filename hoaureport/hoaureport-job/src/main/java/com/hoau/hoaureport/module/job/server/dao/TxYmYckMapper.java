package com.hoau.hoaureport.module.job.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.job.shared.domain.TxYmYckEntity;



/**
 *
 * @author 凌骏
 * @date 2016年6月12日下午3:34:30
 */
@Repository
public interface TxYmYckMapper {
	public List<TxYmYckEntity> YckGsCollected();

	public List<TxYmYckEntity> GetYckQmje(String gsbh, String temp_ssy);
	
	public List<TxYmYckEntity> GetBeforeYckQmje(String gsbh, String temp_ssy);

	public List<TxYmYckEntity> GetBeforeYckQcje(String gsbh, String temp_ssy);

	public int InsertYmYck(TxYmYckEntity txYmYckEntity);

	public TxYmYckEntity GetBeforeDjje(String gsbh, String temp_ssy);
	
	public TxYmYckEntity GetBeforeQcDjje(String gsbh);

	public List<TxYmYckEntity> GetPastYck(String gsbh, String temp_ssy);

	public List<TxYmYckEntity> GetMaxShsj(String gsbh, String temp_ssy);

}