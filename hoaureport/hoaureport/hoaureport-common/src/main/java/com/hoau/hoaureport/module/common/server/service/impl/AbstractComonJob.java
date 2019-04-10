package com.hoau.hoaureport.module.common.server.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hoaureport.module.common.server.service.IJobProcessInfoService;
import com.hoau.hoaureport.module.common.shared.define.JobElementType;
import com.hoau.hoaureport.module.common.shared.domain.JobProcessEntity;
import com.hoau.hoaureport.module.util.UUIDUtil;

/**
 * @author 288697
 * 记录job相关信息及job异常保存数据库
 */
public  abstract class AbstractComonJob extends GridJob implements StatefulJob{
    
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IJobProcessInfoService jobProcessInfoService = getBean("jobProcessInfoService", IJobProcessInfoService.class);
		//初始化需要使用的参数
		Date jobStartTime = null;
		Date jobEndTime = null;
		JobProcessEntity  jobProcess = new JobProcessEntity();
		jobProcess.setId(UUIDUtil.getUUID());
		jobProcess.setCreateDate(new Date());
		jobProcess.setCreateUser(JobElementType.USER);
		jobProcess.setModifyDate(new Date());
		jobProcess.setModifyUser(JobElementType.USER);
		JobElementType type = this.getJobType();
		jobProcess.setCode(type.getCode());
		jobProcess.setName(type.getName());
		
		try {
			//执行job业务
			jobStartTime = Calendar.getInstance().getTime();
			jobProcess.setJobStartTime(jobStartTime);
			//执行前记录日志
			jobProcessInfoService.addJobInfoBeginLog(jobProcess);
			//执行具体业务
			this.jobExecute(context);
			
			jobEndTime = Calendar.getInstance().getTime();
			jobProcess.setStatus(JobElementType.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			//记录异常日志
			jobEndTime = Calendar.getInstance().getTime();
			jobProcess.setStatus(JobElementType.FAILUTR);
			jobProcess.setErrorInfo(ExceptionUtils.getFullStackTrace(e));
		} finally {
			jobProcess.setJobEndTime(jobEndTime);
			jobProcess.setInterval(jobEndTime.getTime() - jobStartTime.getTime());
			//结束记录日志
			jobProcessInfoService.addJobInfoEndLog(jobProcess);
			//更新job信息
			jobProcessInfoService.processJobInfo(jobProcess);			
		}
		
	}

	/**
	 * 执行业务类型
	 * 
	 * @param context
	 * @throws JobExecutionException
	 */
	protected abstract void jobExecute(JobExecutionContext context) throws JobExecutionException; 

	/**
	 * 货区获取任务编码和名称
	 * 
	 * @return
	 * @throws JobExecutionException
	 */
	protected abstract JobElementType getJobType() throws JobExecutionException; 
}
