package com.hoau.hoaureport.module.configreport.server.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.baseinfo.api.shared.domain.UserEntity;
import com.hoau.hoaureport.module.configreport.server.service.ISalesLineService;
import com.hoau.hoaureport.module.configreport.shared.domain.SalesLine;
import com.hoau.hoaureport.module.configreport.shared.vo.SalesLineVo;

/**
 * 销售线路节点
 * ClassName: SalesLineAction 
 * @author 刘镇松
 * @date 2016年10月31日
 * @version V1.0
 */
@Controller("salesLineAction")
@Scope("prototype")
public class SalesLineAction extends AbstractAction {
	private static final long serialVersionUID = 2029236674994501375L;
	private SalesLineVo salesLineVo;
	@Resource
	ISalesLineService iSalesLineService;
	/**
	 * 
	 * @Description: 首页
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年10月31日
	 */
	public String showSalesLinePage(){
		return "index";
	}
	/**
	 * 
	 * @Description: 查询销售线路信息
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月1日
	 */
	@JSON
	public String querySalesLineInfo(){
		try {
			salesLineVo.setSalesLineList(
					iSalesLineService.querySalesLineInfo(salesLineVo.getSalesLine(), start, limit));
			totalCount = iSalesLineService.querySalesLineCount(salesLineVo.getSalesLine());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 作废
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月1日
	 */
	@JSON
	public String repealSalesLineInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			List<SalesLine> list = salesLineVo.getSalesLineList();
			for(SalesLine item : list){
				SalesLine oldInfo = new SalesLine();
				oldInfo.setSlId(item.getSlId());
				oldInfo.setActive("N");
				oldInfo.setInvalidTime(new Date());
				oldInfo.setModifyTime(new Date());
				oldInfo.setModifyUserCode(user.getEmpCode().substring(2));
				iSalesLineService.updateSalesLineInfo(oldInfo);
			}
			return returnSuccess("作废成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 添加
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月1日
	 */
	@JSON
	public String addSalesLineInfo(){
		SalesLine param = salesLineVo.getSalesLine();
		try {
			if(iSalesLineService.isExist(param)){//数据已存在无须添加
				return returnError("已存在相同的销售路线数据,请重新添加!");
			}else{
				iSalesLineService.addSalesLine(param);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	/**
	 * 
	 * @Description: 修改
	 * @param @return   
	 * @return String 
	 * @throws
	 * @author 刘镇松
	 * @date 2016年11月1日
	 */
	@JSON
	public String modifySalesLineInfo(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		try {
			SalesLine param = salesLineVo.getSalesLine();
			//根据ID查询数据库场站信息
			SalesLine filterParam = new SalesLine();
			filterParam.setSlId(param.getSlId());
			filterParam.setActive("Y");
			List<SalesLine> oldInfo = iSalesLineService.querySalesLineInfo(filterParam, 0, 10);
			//是否修改成本条数据
			if(StringUtil.equals(oldInfo.get(0).getSalesLineName(), param.getSalesLineName())){
				iSalesLineService.repealAndAddInfo(param,user.getEmpCode().substring(2));
				return returnSuccess("修改成功!");
			}else{
				SalesLine filters = new SalesLine();
				filters.setSalesLineName(param.getSalesLineName());
				filters.setActive("Y");
				if(iSalesLineService.isExist(filters)){
					return returnError("已存在相同销售线路节点数据,请重新修改!");
				}else{
					iSalesLineService.repealAndAddInfo(param,user.getEmpCode().substring(2));
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
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
			String uploadPath = ServletActionContext.getServletContext().getRealPath(salesLineVo.getFilePath());
			Map<String,Object> returnMap= iSalesLineService.bathImplPlatformAreaInfo(uploadPath);
			if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
				return returnError(returnMap.get("error").toString());
			}
			//JSONObject json=new JSONObject();
			//addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
			salesLineVo.setAddSize(returnMap.get("addSize").toString());
			salesLineVo.setCoverSize(returnMap.get("coverSize").toString());
			salesLineVo.setSumSize(returnMap.get("sumSize").toString());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	public SalesLineVo getSalesLineVo() {
		return salesLineVo;
	}
	public void setSalesLineVo(SalesLineVo salesLineVo) {
		this.salesLineVo = salesLineVo;
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
			iSalesLineService.repealAllSalesLine();
			return returnSuccess("全部作废成功");
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
}
