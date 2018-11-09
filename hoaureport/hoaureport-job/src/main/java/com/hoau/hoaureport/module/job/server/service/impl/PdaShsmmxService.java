package com.hoau.hoaureport.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoau.hoaureport.module.job.server.dao.PdaShsmmxMapper;
import com.hoau.hoaureport.module.job.server.service.IPdaShsmmxService;
import com.hoau.hoaureport.module.job.shared.domain.PdaShsmmxEntity;


@Service
public class PdaShsmmxService implements IPdaShsmmxService {

	@Autowired
	private PdaShsmmxMapper pdaShsmmxMapper;
	
	@Override
	public void PdaShsmmxInfo() {
		PdaShsmmxEntity pdaShsmmxEntity = new PdaShsmmxEntity();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		//获取上个月第一天
		Calendar begin_calendar = Calendar.getInstance();
		begin_calendar.setTime(new Date()); 
		begin_calendar.add(Calendar.MONTH, -1);
		begin_calendar.set(Calendar.DAY_OF_MONTH, 1);
		pdaShsmmxEntity.setBeginDate(df.format(begin_calendar.getTime()));
		//获取上个月最后一天
		Calendar end_calendar = Calendar.getInstance();
		end_calendar.setTime(new Date()); 
		end_calendar.set(Calendar.DAY_OF_MONTH, 1);
		end_calendar.add(Calendar.DATE, -1);
		pdaShsmmxEntity.setEndDate(df.format(end_calendar.getTime()));
		pdaShsmmxMapper.PdaShsmCollected(pdaShsmmxEntity);
	}
	


}
