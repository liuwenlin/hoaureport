package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.SpecialStationInfo;

/**
 * 
 *
 * @Descripation:场站特许服务接口
 * @author:liangling 
 * @date:2016年11月22日 上午11:08:27
 *
 */
public interface ISpecialStationManageService {
   /**
    * 
    * @Description:查询场站特许信息
    * @param  @param param
    * @param  @param start
    * @param  @param limit
    * @param  @return
    * @return List<SpecialStationInfo>
    * @exception:
    * @author: liangling
    * @time:2016年11月22日  上午11:01:17
    *
    */
	List<SpecialStationInfo> querySpecialStationInfo(SpecialStationInfo param,int start,int limit);
	/**
	 * 
	 * @Description:查询场站总的记录数
	 * @param  @param param
	 * @param  @return
	 * @return Long
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:00:47
	 *
	 */
	Long querySpecialStationCount(SpecialStationInfo param);
	/**
	 * 
	 * @Description:新增场站信息
	 * @param  @param info
	 * @return void
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:00:07
	 *
	 */
	void addSpecialStationInfo(SpecialStationInfo info);
	/**
	 *
	 * @Description: 更新场站信息
	 * @param  @param info
	 * @return void
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:01:58
	 *
	 */
	void updateSpecialStationInfo(SpecialStationInfo info);
	/**
	 * 
	 * @Description:修改即作废原数据，将新数据保存
	 * @param  @param param
	 * @param  @param empCode
	 * @return void
	 * @exception:
	 * @author: liangling
	 * @time:2016年11月22日  上午11:02:25
	 *
	 */
	void repealAndAddInfo(SpecialStationInfo param,String empCode);

}
