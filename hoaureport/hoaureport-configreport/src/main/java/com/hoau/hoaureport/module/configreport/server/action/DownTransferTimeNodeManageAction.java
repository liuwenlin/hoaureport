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
import com.hoau.hoaureport.module.configreport.server.service.IDownTransferTimeNodeManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.DownTransferTimeNode;
import com.hoau.hoaureport.module.configreport.shared.vo.DownTransferTimeNodeVo;

/**
 * 上转移时间节点管理Action
 * ClassName: DownTransferTimeNodeManageAction 
 * @author 文洁
 * @date 2016年10月31日
 * @version V1.0
 */
@Controller("downTransferTimeNodeManageAction")
@Scope("prototype")
public class DownTransferTimeNodeManageAction extends AbstractAction{

	


	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 8936105291277724413L;

	private DownTransferTimeNodeVo downTransferTimeNodeVo;
	
	@Resource
	IDownTransferTimeNodeManageService downTransferTimeNodeManageService;
	
	/**
	 * 
	 * @Description:返回 上转移时间节点管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年10月31日
	 */
	public String showDownTransferTimeNodePage() {
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
	public String queryDownTransferTimeNode(){
		try {
			downTransferTimeNodeVo.setDownTransferTimeNodeList(downTransferTimeNodeManageService.queryDownTransferTimeNode(downTransferTimeNodeVo.getDownTransferTimeNode(),start,limit));
			totalCount = downTransferTimeNodeManageService.queryDownTransferTimeNodeCount(downTransferTimeNodeVo.getDownTransferTimeNode());
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
	public String addDownTransferTimeNode(){
		DownTransferTimeNode param = downTransferTimeNodeVo.getDownTransferTimeNode();
		try {
			if(downTransferTimeNodeManageService.isExist(param)){//数据已存在不能添加
				return returnError("已存在相同的上转移时间节点数据,请重新添加！");
			}else{
				DownTransferTimeNode newInfo = new DownTransferTimeNode();
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
				
				downTransferTimeNodeManageService.addDownTransferTimeNode(newInfo);
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
	public String modifyDownTransferTimeNode(){
		try {
			DownTransferTimeNode param = downTransferTimeNodeVo.getDownTransferTimeNode();
			//根据ID查询数据库上转移时间节点信息
			DownTransferTimeNode filterParam = new DownTransferTimeNode();
			filterParam.setuId(param.getuId());
			filterParam.setActive("Y");
			List<DownTransferTimeNode> oldInfo = downTransferTimeNodeManageService.queryDownTransferTimeNode(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getDepartureDepartment(), param.getDepartureDepartment())
					  &&StringUtil.equals(oldInfo.get(0).getArrivalDepartment(), param.getArrivalDepartment())){
				downTransferTimeNodeManageService.repealAndAddDownTransferTimeNode(param);
				return returnSuccess("修改成功!");
			}else{
				DownTransferTimeNode filters = new DownTransferTimeNode();
				filters.setDepartureDepartment(param.getDepartureDepartment());
			    filters.setArrivalDepartment(param.getArrivalDepartment());
				filters.setActive("Y");
				List<DownTransferTimeNode> infoList = downTransferTimeNodeManageService.queryDownTransferTimeNode(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同上转移时间节点数据,请重新修改!");
				}else{
					downTransferTimeNodeManageService.repealAndAddDownTransferTimeNode(param);
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
	public String repealDownTransferTimeNode(){
		try {
			DownTransferTimeNode param = downTransferTimeNodeVo.getDownTransferTimeNode();
			downTransferTimeNodeManageService.repealDownTransferTimeNode(param);
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(downTransferTimeNodeVo.getFilePath());
			Map<String,Object> returnMap= downTransferTimeNodeManageService.bathImplDownTransferTimeNode(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			downTransferTimeNodeVo.setAddSize(returnMap.get("addSize").toString());
			downTransferTimeNodeVo.setCoverSize(returnMap.get("coverSize").toString());
			downTransferTimeNodeVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}

	public DownTransferTimeNodeVo getDownTransferTimeNodeVo() {
		return downTransferTimeNodeVo;
	}

	public void setDownTransferTimeNodeVo(DownTransferTimeNodeVo DownTransferTimeNodeVo) {
		this.downTransferTimeNodeVo = DownTransferTimeNodeVo;
	}
	
}
