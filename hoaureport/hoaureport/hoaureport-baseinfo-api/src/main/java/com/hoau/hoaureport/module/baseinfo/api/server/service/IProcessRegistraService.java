package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.ProcessRegistraEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.ProcessRegistraParams;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:56:51
 * @description：处理记录 service
 */
public interface IProcessRegistraService {
	/**
	 * 查询处理记录
	 * @param params
	 * @param rowBounds
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update 
	 */
	public List<ProcessRegistraEntity> queryProcessRegistra(ProcessRegistraParams params);

	/**
	 * 查询处理记录总数
	 * @param params
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update 
	 */
	public Long queryCountProcessRegistra(ProcessRegistraParams params);
	
	/**
	 * 新增处理记录
	 * @param entity
	 * @author 295073
	 * @date 2016年1月11日
	 * @update 
	 */
	public void addProcessRegistra(ProcessRegistraEntity entity);
	
	/**
	 * 修改处理记录
	 * @param entity
	 * @author 295073
	 * @date 2016年1月11日
	 * @update 
	 */
	public void updateProcessRegistra(ProcessRegistraEntity entity);
	
	/**
	 * 删除处理记录
	 * @param entitys
	 * @author 295073
	 * @date 2016年1月11日
	 * @update 
	 */
	public void deleteProcessRegistra(List<ProcessRegistraEntity> entity);
}
