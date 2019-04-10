package com.hoau.hoaureport.module.job.server.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.job.server.dao.FdhzlMapper;
import com.hoau.hoaureport.module.job.server.service.IFdhzlService;
import com.hoau.hoaureport.module.job.shared.domain.FdhzlEntity;
@Service
public class FdhzlService implements IFdhzlService {
	
	@Autowired
	private FdhzlMapper fdhzlMapper;
	
	@Transactional
	@Override
	public void FdhzlInfo() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");   
//		try {
//			FdhzlEntity fdhzlEntity = new FdhzlEntity();
//			Date  enddate = sdf.parse("2016/06/02");
//			fdhzlEntity.setEndDate(sdf.format(enddate));
//			Date begindate = getNextDay(enddate);
//			fdhzlEntity.setBeginDate(sdf.format(begindate));
//			fdhzlMapper.FdhzlCollected(fdhzlEntity);			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		FdhzlEntity fdhzlEntity = new FdhzlEntity();
		Date enddate = new Date();
		fdhzlEntity.setEndDate(df.format(enddate));
		Date begindate = getNextDay(enddate);
		fdhzlEntity.setBeginDate(df.format(begindate));
		fdhzlMapper.FdhzlCollected(fdhzlEntity);
	}
	public Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);//+1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

}
