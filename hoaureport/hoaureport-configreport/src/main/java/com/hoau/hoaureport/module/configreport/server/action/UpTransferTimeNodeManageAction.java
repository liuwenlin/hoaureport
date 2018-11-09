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
import com.hoau.hoaureport.module.configreport.server.service.IUpTransferTimeNodeManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.UpTransferTimeNode;
import com.hoau.hoaureport.module.configreport.shared.vo.UpTransferTimeNodeVo;

/**
 * 上转移时间节点管理Action
 * ClassName: UpTransferTimeNodeManageAction 
 * @author 文洁
 * @date 2016年10月31日
 * @version V1.0
 */
@Controller("upTransferTimeNodeManageAction")
@Scope("prototype")
public class UpTransferTimeNodeManageAction extends AbstractAction{

	

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 4562717040153360454L;

	private UpTransferTimeNodeVo upTransferTimeNodeVo;
	
	@Resource
	IUpTransferTimeNodeManageService upTransferTimeNodeManageService;
	
	/**
	 * 
	 * @Description:返回 上转移时间节点管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	public String showUpTransferTimeNodePage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 上转移时间节点信息
	 * @return String
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	@JSON
	public String queryUpTransferTimeNode(){
		try {
			upTransferTimeNodeVo.setUpTransferTimeNodeList(upTransferTimeNodeManageService.queryUpTransferTimeNode(upTransferTimeNodeVo.getUpTransferTimeNode(),start,limit));
			totalCount = upTransferTimeNodeManageService.queryUpTransferTimeNodeCount(upTransferTimeNodeVo.getUpTransferTimeNode());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加上转移时间节点信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	@JSON
	public String addUpTransferTimeNode(){
		UpTransferTimeNode param = upTransferTimeNodeVo.getUpTransferTimeNode();
		try {
			if(upTransferTimeNodeManageService.isExist(param)){//数据已存在不能添加
				return returnError("已存在相同的上转移时间节点数据,请重新添加！");
			}else{
				UpTransferTimeNode newInfo = new UpTransferTimeNode();
				newInfo.setTheArea(param.getTheArea());;
				newInfo.setTheRoadArea(param.getTheRoadArea());;
				newInfo.setUpOrDownTransfer(param.getUpOrDownTransfer());
				newInfo.setIsLineCrossed(param.getIsLineCrossed());;
				newInfo.setDepartureDepartment(param.getDepartureDepartment());;
				newInfo.setTodayOrNextDay(param.getTodayOrNextDay());
				newInfo.setArrivalDepartment(param.getArrivalDepartment());
				newInfo.setDispatchTime(param.getDispatchTime());;
				newInfo.setIntransitMinutes(param.getIntransitMinutes());;
				newInfo.setArrivalTime(param.getArrivalTime());
				newInfo.setCrossedLine(param.getCrossedLine());;
				newInfo.setKilometers(param.getKilometers());;
				newInfo.setClassNum(param.getClassNum());
				newInfo.setActive("Y");
				
				upTransferTimeNodeManageService.addUpTransferTimeNode(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改上转移时间节点信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	@JSON
	public String modifyUpTransferTimeNode(){
		try {
			UpTransferTimeNode param = upTransferTimeNodeVo.getUpTransferTimeNode();
			//根据ID查询数据库上转移时间节点信息
			UpTransferTimeNode filterParam = new UpTransferTimeNode();
			filterParam.setuId(param.getuId());
			filterParam.setActive("Y");
			List<UpTransferTimeNode> oldInfo = upTransferTimeNodeManageService.queryUpTransferTimeNode(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getDepartureDepartment(), param.getDepartureDepartment())
			  &&StringUtil.equals(oldInfo.get(0).getArrivalDepartment(), param.getArrivalDepartment())){
				upTransferTimeNodeManageService.repealAndAddUpTransferTimeNode(param);
				return returnSuccess("修改成功!");
			}else{
				UpTransferTimeNode filters = new UpTransferTimeNode();
			    filters.setDepartureDepartment(param.getDepartureDepartment());
			    filters.setArrivalDepartment(param.getArrivalDepartment());
				filters.setActive("Y");
				List<UpTransferTimeNode> infoList = upTransferTimeNodeManageService.queryUpTransferTimeNode(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同上转移时间节点数据,请重新修改!");
				}else{
					upTransferTimeNodeManageService.repealAndAddUpTransferTimeNode(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:上转移时间节点作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	@JSON
	public String repealUpTransferTimeNode(){
		try {
			UpTransferTimeNode param = upTransferTimeNodeVo.getUpTransferTimeNode();
			upTransferTimeNodeManageService.repealUpTransferTimeNode(param);
			return returnSuccess("作废成功！");
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
	 * @date 2016年11月7日
	 */
	@JSON
	public String implExcel() throws Exception{
		try {
			String uploadPath = ServletActionContext.getServletContext().getRealPath(upTransferTimeNodeVo.getFilePath());
			Map<String,Object> returnMap= upTransferTimeNodeManageService.bathImplUpTransferTimeNode(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			upTransferTimeNodeVo.setAddSize(returnMap.get("addSize").toString());
			upTransferTimeNodeVo.setCoverSize(returnMap.get("coverSize").toString());
			upTransferTimeNodeVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public UpTransferTimeNodeVo getUpTransferTimeNodeVo() {
		return upTransferTimeNodeVo;
	}

	public void setUpTransferTimeNodeVo(UpTransferTimeNodeVo upTransferTimeNodeVo) {
		this.upTransferTimeNodeVo = upTransferTimeNodeVo;
	}
	
}
