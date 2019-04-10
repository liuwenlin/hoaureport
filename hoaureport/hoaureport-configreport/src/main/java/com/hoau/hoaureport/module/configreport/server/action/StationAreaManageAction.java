package com.hoau.hoaureport.module.configreport.server.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.configreport.server.service.IStationAreaManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.StationAreaInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.StationAreaVo;

/**
 * 场站辖区管理Action
 * ClassName: StationAreaManageAction 
 * @author 文洁
 * @date 2016年8月17日
 * @version V1.0
 */
@Controller("stationAreaManageAction")
@Scope("prototype")
public class StationAreaManageAction extends AbstractAction{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1585173611894570249L;

	private StationAreaVo stationAreaVo;
	
	@Resource
	IStationAreaManageService stationAreaManageService;
	
	/**
	 * 
	 * @Description:返回 场站辖区管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	public String showStationAreaPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 场站辖区信息
	 * @return String
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	@JSON
	public String queryStationAreaInfo(){
		try {
			stationAreaVo.setStationAreaInfoList(stationAreaManageService.queryStationAreaInfo(stationAreaVo.getStationAreaInfo(),start,limit));
			totalCount = stationAreaManageService.queryStationAreaCount(stationAreaVo.getStationAreaInfo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加场站辖区信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	@JSON
	public String addStationAreaInfo(){
		StationAreaInfo param = stationAreaVo.getStationAreaInfo();
		try {
			if(stationAreaManageService.isExist(param)){//数据已存在不能添加
				return returnError("已存在相同的作业单位代码数据,请重新添加！");
			}else{
				StationAreaInfo newInfo = new StationAreaInfo();
				newInfo.setOperationUnitCode(param.getOperationUnitCode());;
				newInfo.setTheStationShortName(param.getTheStationShortName());;
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				
				stationAreaManageService.addStationAreaInfo(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改场站辖区信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	@JSON
	public String modifyStationAreaInfo(){
		try {
			StationAreaInfo param = stationAreaVo.getStationAreaInfo();
			//根据ID查询数据库场站辖区信息
			StationAreaInfo filterParam = new StationAreaInfo();
			filterParam.setStationAreaId(param.getStationAreaId());
			filterParam.setActive("Y");
			List<StationAreaInfo> oldInfo = stationAreaManageService.queryStationAreaInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getOperationUnitCode(), param.getOperationUnitCode())){
				stationAreaManageService.repealAndAddStationAreaInfo(param);
				return returnSuccess("修改成功!");
			}else{
				StationAreaInfo filters = new StationAreaInfo();
				filters.setOperationUnitCode(param.getOperationUnitCode());
				filters.setActive("Y");
				List<StationAreaInfo> infoList = stationAreaManageService.queryStationAreaInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同作业单位代码简称数据,请重新修改!");
				}else{
					stationAreaManageService.repealAndAddStationAreaInfo(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:场站辖区作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月17日
	 */
	@JSON
	public String repealStationAreaInfo(){
		int count = 0;
		try {
			List<StationAreaInfo> params = stationAreaVo.getStationAreaInfoList();
			for (StationAreaInfo stationAreaInfo : params) {
				stationAreaManageService.repealStationAreaInfo(stationAreaInfo);
				count++;
			}
			return returnSuccess("作废成功！共作废" + count + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
			return returnError("e.toString()");
		}
	}

	/** 
	 * 
	 * @Description: excel解析并插入到数据库
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月02日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(stationAreaVo.getFilePath());
			Map<String,Object> returnMap= stationAreaManageService.bathImplStationAreaInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			stationAreaVo.setAddSize(returnMap.get("addSize").toString());
			stationAreaVo.setCoverSize(returnMap.get("coverSize").toString());
			stationAreaVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	/**
	 * 
	 * @Description: 作废所有的数据
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月11日
	 */
	@JSON
	public String repealAll(){
		try {
			stationAreaManageService.repealAllStationArea();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	public StationAreaVo getStationAreaVo() {
		return stationAreaVo;
	}

	public void setStationAreaVo(StationAreaVo stationAreaVo) {
		this.stationAreaVo = stationAreaVo;
	}
	
}
