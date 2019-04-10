package com.hoau.hoaureport.module.configreport.server.service;

import java.util.List;

import com.hoau.hoaureport.module.configreport.shared.domain.StationAgingInfo;

/**
 * 
  *@Description:场站辖区(时效)管理 接口
  *@author :梁令
  *@date 创建时间：2017年1月6日 下午3:33:45
 */
public interface IStationAgingManageService {
	/**
	 * @Description:查询场站辖区(时效)信息
	 * @param param
	 * @param start
	 * @param limit
	 * @return
	 */
   List<StationAgingInfo> queryStationAgingInfo(StationAgingInfo param,int start,int limit);
   /**
    * @Description:查询场站辖区(时效)总记录条数
    * @param param
    * @return
    */
   Long queryStationAgingInfoCount(StationAgingInfo param);
   /**
    * 新增场站辖区(时效)信息
    * @param param
    */
   void addStationAgingInfo(StationAgingInfo info);
   /**
    * 更新场站辖区（时效）信息
    * @param info
    */
   void updateStationAgingInfo(StationAgingInfo info);
   /**
    * 作废老数据，保存修改后的数据
    * @param param
    * @param empCode
    */
   void repealAndAddInfo(StationAgingInfo param,String empCode);
}
