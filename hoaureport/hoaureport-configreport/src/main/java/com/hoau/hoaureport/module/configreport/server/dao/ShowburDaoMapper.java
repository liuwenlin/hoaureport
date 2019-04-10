package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.ShowburEntity;


@Repository
public interface ShowburDaoMapper {
	public List<ShowburEntity> getShowburInfo(String id);

	public String getSumByMonth(String axisName, String day);
}
