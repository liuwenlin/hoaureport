package com.hoau.hoaureport.module.job.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.job.shared.domain.PdaShsmmxEntity;


/**
 *
 * @author 凌骏
 * @date 2016年6月12日下午3:34:30
 */
@Repository
public interface PdaShsmmxMapper {
	public void PdaShsmCollected(PdaShsmmxEntity pdaShsmmxEntity);
}