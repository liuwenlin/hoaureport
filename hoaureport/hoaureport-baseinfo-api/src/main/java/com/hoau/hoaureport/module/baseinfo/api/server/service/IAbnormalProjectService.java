package com.hoau.hoaureport.module.baseinfo.api.server.service;

import java.util.List;

import com.hoau.hoaureport.module.baseinfo.api.shared.domain.AbnormalProjectEntity;
import com.hoau.hoaureport.module.baseinfo.api.shared.vo.AbnormalProjectParams;

/**
 * @author：295073
 * @create：2016年1月11日 下午4:15:55
 * @description：异常项目维护service
 */
public interface IAbnormalProjectService {
	/**
	 * 查询
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public List<AbnormalProjectEntity> queryAbnormalProject(
			AbnormalProjectParams params);

	/**
	 * 查询总数
	 * 
	 * @return
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public Long queryCountAbnormalProject(AbnormalProjectParams params);

	/**
	 * 新增
	 * 
	 * @param entity
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public void addAbnormalProject(AbnormalProjectEntity entity);

	/**
	 * 修改
	 * 
	 * @param entity
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public void updateAbnormalProject(AbnormalProjectEntity entity);

	/**
	 * 删除
	 * 
	 * @param entity
	 * @author 295073
	 * @date 2016年1月11日
	 * @update
	 */
	public void deleteAbnormalProject(List<AbnormalProjectEntity> entitys);
}
