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
import com.hoau.hoaureport.module.configreport.server.service.IFleetAreaManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.FleetAreaInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.FleetAreaVo;

/**
 * 车队辖区管理Action
 * ClassName: FleetAreaManageAction 
 * @author 文洁
 * @date 2016年8月18日
 * @version V1.0
 */
@Controller("fleetAreaManageAction")
@Scope("prototype")
public class FleetAreaManageAction extends AbstractAction{


	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3577312676065417801L;

	private FleetAreaVo fleetAreaVo;
	
	@Resource
	IFleetAreaManageService fleetAreaManageService;
	
	/**
	 * 
	 * @Description:返回 车队辖区管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	public String showFleetAreaPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 车队辖区信息
	 * @return String
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	@JSON
	public String queryFleetAreaInfo(){
		try {
			fleetAreaVo.setFleetAreaInfoList(fleetAreaManageService.queryFleetAreaInfo(fleetAreaVo.getFleetAreaInfo(),start,limit));
			totalCount = fleetAreaManageService.queryFleetAreaCount(fleetAreaVo.getFleetAreaInfo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加车队辖区信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	@JSON
	public String addFleetAreaInfo(){
		FleetAreaInfo param = fleetAreaVo.getFleetAreaInfo();
		try {
			if(fleetAreaManageService.isExist(param)){//数据已存在无须添加
				return returnError("已存在相同的辖区作业单位简称数据,请重新添加！");
			}else{
				FleetAreaInfo newInfo = new FleetAreaInfo();
				newInfo.setAreaOperationUnitShortname(param.getAreaOperationUnitShortname());;
				newInfo.setTheFleetShortName(param.getTheFleetShortName());;
				newInfo.setTheArea(param.getTheArea());
				newInfo.setTheBusinessDepartment(param.getTheBusinessDepartment());
				newInfo.setActive("Y");
				
				fleetAreaManageService.addFleetAreaInfo(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改车队辖区信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	@JSON
	public String modifyFleetAreaInfo(){
		try {
			FleetAreaInfo param = fleetAreaVo.getFleetAreaInfo();
			//根据ID查询数据库车队辖区信息
			FleetAreaInfo filterParam = new FleetAreaInfo();
			filterParam.setFleetAreaId(param.getFleetAreaId());
			filterParam.setActive("Y");
			List<FleetAreaInfo> oldInfo = fleetAreaManageService.queryFleetAreaInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getAreaOperationUnitShortname(), param.getAreaOperationUnitShortname())){
				fleetAreaManageService.repealAndAddFleetAreaInfo(param);
				return returnSuccess("修改成功!");
			}else{
				FleetAreaInfo filters = new FleetAreaInfo();
				filters.setAreaOperationUnitShortname(param.getAreaOperationUnitShortname());
				filters.setActive("Y");
				List<FleetAreaInfo> infoList = fleetAreaManageService.queryFleetAreaInfo(filterParam, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同的辖区作业简称数据,请重新修改!");
				}else{
					fleetAreaManageService.repealAndAddFleetAreaInfo(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:车队辖区作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年8月18日
	 */
	@JSON
	public String repealFleetAreaInfo(){
		int count = 0;
		try {
			List<FleetAreaInfo> params = fleetAreaVo.getFleetAreaInfoList();
			for (FleetAreaInfo param : params) {
				fleetAreaManageService.repealFleetAreaInfo(param);
				count++;
			}
			return returnSuccess("作废成功！共作废" + count + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
			return returnError(e.toString());
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(fleetAreaVo.getFilePath());
			Map<String,Object> returnMap= fleetAreaManageService.bathImplFleetAreaInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			fleetAreaVo.setAddSize(returnMap.get("addSize").toString());
			fleetAreaVo.setCoverSize(returnMap.get("coverSize").toString());
			fleetAreaVo.setSumSize(returnMap.get("sumSize").toString());
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
			fleetAreaManageService.repealAllFleetArea();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	
	public FleetAreaVo getFleetAreaVo() {
		return fleetAreaVo;
	}

	public void setFleetAreaVo(FleetAreaVo FleetAreaVo) {
		this.fleetAreaVo = FleetAreaVo;
	}
	
}
