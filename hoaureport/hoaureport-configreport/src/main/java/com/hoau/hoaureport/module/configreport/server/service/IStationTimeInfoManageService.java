package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationTimeInfo;

/** 
 *@Description:场站（时效） 接口
 *@author : 梁令
 *@date 创建时间：2017年1月11日 下午2:15:33 
 */
public interface IStationTimeInfoManageService {
	/**
	 * @Description:查询场站(时效)信息
	 * @param param
	 * @param start
	 * @param limit
	 * @return
	 */
   List<StationTimeInfo> queryStationTimeInfo(StationTimeInfo param,int start,int limit);
   /**
    * @Description:查询场站(时效)总记录条数
    * @param param
    * @return
    */
   Long queryStationTimeInfoCount(StationTimeInfo param);
   /**
    * 新增场站(时效)信息
    * @param param
    */
   void addStationTimeInfo(StationTimeInfo info);
   /**
    * 更新场站信息
    * @param info
    */
   void updateStationTimeInfo(StationTimeInfo info);
   /**
    * 作废老数据，保存修改后的数据
    * @param param
    * @param empCode
    */
   void repealAndAddInfo(StationTimeInfo param,String empCode);
}

