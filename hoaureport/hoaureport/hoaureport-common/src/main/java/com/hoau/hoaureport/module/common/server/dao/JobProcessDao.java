package com.hoau.hoaureport.module.common.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.common.shared.domain.JobProcessEntity;
@Repository
public interface JobProcessDao {
	
	/**
	 * 修改jobProcessEntity
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	void updateJobRunningInfo(JobProcessEntity jobProcessEntity);
	
	/**
	 * 添加jobProcessEntity
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	void addJobRunningInfo(JobProcessEntity jobProcessEntity);
	
	/**
	 * 根据任务编号查询是否已存在
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	Long selectCountByCode(JobProcessEntity jobProcessEntity);
	
	/**
	 * 记录job运行日志(执行结束)
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	void addJobInfoEndLog(JobProcessEntity jobProcessEntity);
	
	/**
	 * 记录job运行日志(开始执行)
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	void addJobInfoBeginLog(JobProcessEntity jobProcessEntity);

	/**
	 * 分页查询明细
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	List<JobProcessEntity> queryJobProcessList(
			JobProcessEntity jobProcessEntity, RowBounds rowBounds);

	/**
	 * 分页查询总条数
	 * @param jobProcessEntity
	 * @author 张贞献
	 * @date 2015年7月14日
	 * @update 
	 */
	Long queryTotalCount(JobProcessEntity jobProcessEntity);
}
