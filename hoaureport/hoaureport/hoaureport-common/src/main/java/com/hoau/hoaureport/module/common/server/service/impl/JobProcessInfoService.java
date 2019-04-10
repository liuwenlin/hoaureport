package com.hoau.hoaureport.module.common.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.hoaureport.module.common.server.dao.JobProcessDao;
import com.hoau.hoaureport.module.common.server.service.IJobProcessInfoService;
import com.hoau.hoaureport.module.common.shared.domain.JobProcessEntity;
@Service
public class JobProcessInfoService implements IJobProcessInfoService {

	@Resource
	private JobProcessDao jobProcessDao;
	
	@Override
	@Transactional
	public void processJobInfo(JobProcessEntity jobProcess) {
		//数据库存在该job信息则更新，否则新增
		if(jobProcessDao.selectCountByCode(jobProcess) > 0) {
			jobProcessDao.updateJobRunningInfo(jobProcess);
		}else{
			jobProcessDao.addJobRunningInfo(jobProcess);
		}
	}

	@Override
	@Transactional
	public void addJobInfoBeginLog(JobProcessEntity jobProcessEntity) {
		jobProcessDao.addJobInfoBeginLog(jobProcessEntity);
	}
	
	@Override
	@Transactional
	public void addJobInfoEndLog(JobProcessEntity jobProcessEntity) {
		jobProcessDao.addJobInfoEndLog(jobProcessEntity);
	}

	@Override
	public List<JobProcessEntity> queryJobProcessList(
			JobProcessEntity jobProcessEntity, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return jobProcessDao.queryJobProcessList(jobProcessEntity, rowBounds);
	}

	@Override
	public Long queryTotalCount(JobProcessEntity jobProcessEntity) {
		return jobProcessDao.queryTotalCount(jobProcessEntity);
	}

}
