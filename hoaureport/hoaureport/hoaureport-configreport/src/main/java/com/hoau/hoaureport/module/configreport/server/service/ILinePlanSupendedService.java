package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.hoaureport.module.configreport.shared.domain.LinePlanSuspended;


public interface ILinePlanSupendedService {
	 /**
	    * @Description:查询线路规划停发时间信息
	    * @param  @param param
	    * @param  @param start
	    * @param  @param limit
	    * @param  @return
	    * @return List<LinePlanSuspended>
	    * @exception:
	    * @author: liangling
	    */
		List<LinePlanSuspended> queryLinePlanSupendedInfo(LinePlanSuspended param,int start,int limit);
		/**
		 * 
		 * @Description:查询线路规划时间停发总记录数
		 * @param  @param param
		 * @param  @return
		 * @return Long
		 * @exception:
		 * @author: liangling
		 */
		 Long queryLinePlanSuspendedCount(LinePlanSuspended param);
		/**
		 * 
		 * @Description:新增线路规划停发时间信息
		 * @param  @param info
		 * @return void
		 * @exception:
		 * @author: liangling
		 */
		 void addLinePlanSuspendedInfo(LinePlanSuspended info);
		/**
		 *
		 * @Description: 修改线路规划停发时间
		 * @param  @param info
		 * @return void
		 * @exception:
		 * @author: liangling
		 *
		 */
		void updateLinePlanSuspendedInfo(LinePlanSuspended info);
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
	    void repealAndAddInfo(LinePlanSuspended param,String empCode);
		/**
		 * 判断是否存在
		 * @param param
		 * @return
		 */
	    boolean isExist(LinePlanSuspended param);
	    /**
		 *  该方法会将错误的信息收集起来，返回给调用者
		 * @Description:处理从Excel里批量导入数据的操作
		 * @param  path
		 * @return Map<String,Object> Map<String,Object> addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
		 * @author 梁令
		 */
		public  Map<String,Object>  bathImplLinePlanSuspended(String path) throws Exception;
		/**
		 * 
		 * @Description: 添加或者更新线路规划停发时间所属信息
		 * @param info
		 * @param countMap
		 * @author 梁令
		 * @date 2016年12月23日
		 */
		public void addOrUpdateLinePlanSuspended(LinePlanSuspended info,Map<String,Long> countMap) throws Exception;
		/**
         * 作废所有数据
         */
	    public void repealAll();
	}
