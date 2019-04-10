package com.hoau.hoaureport.module.configreport.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.configreport.server.service.ICustomerInfoManageService;
import com.hoau.hoaureport.module.configreport.shared.domain.CustomerInfo;
import com.hoau.hoaureport.module.configreport.shared.vo.CustomerInfoVo;

/**
 * 大客户管理Action
 * ClassName: CustomerInfoManageAction 
 * @author 文洁
 * @date 2016年11月7日
 * @version V1.0
 */
@Controller("customerInfoManageAction")
@Scope("prototype")
public class CustomerInfoManageAction extends AbstractAction{


	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1006767525958273129L;

	private CustomerInfoVo customerInfoVo;
	
	@Resource
	ICustomerInfoManageService customerInfoManageService;
	
	/**
	 * 
	 * @Description:返回 大客户管理 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	public String showCustomerInfoPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 大客户信息
	 * @return String
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	@JSON
	public String queryCustomerInfo(){
		try {
			customerInfoVo.setCustomerInfoList(customerInfoManageService.queryCustomerInfo(customerInfoVo.getCustomerInfo(),start,limit));
			totalCount = customerInfoManageService.queryCustomerInfoCount(customerInfoVo.getCustomerInfo());
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:增加大客户信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	@JSON
	public String addCustomerInfo(){
		CustomerInfo param = customerInfoVo.getCustomerInfo();
		try {
			if(customerInfoManageService.isExist(param)){//数据已存在不能添加
				return returnError("已存在相同的大客户编码数据,请重新添加！");
			}else{
				CustomerInfo newInfo = new CustomerInfo();
				newInfo.setCustomerNum(param.getCustomerNum());;
				newInfo.setAddEfficiency(param.getAddEfficiency());;
				newInfo.setBigCustomerName(param.getBigCustomerName());
				newInfo.setActive("Y");
				
				customerInfoManageService.addCustomerInfo(newInfo);
				return returnSuccess("添加成功！");
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:修改大客户信息
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	@JSON
	public String modifyCustomerInfo(){
		try {
			//当前记录客户编号
			String currentCustomerNum = customerInfoVo.getCurrentCustomerNum();
			//提交时的客户记录
			CustomerInfo param = customerInfoVo.getCustomerInfo();
			
			//如果是修改的本记录（即没有改客户编号），则直接原纪录上修改
			if(StringUtil.equals(currentCustomerNum, param.getCustomerNum())){
				customerInfoManageService.updateCustomerInfo(param);
				return returnSuccess("修改成功!");
			}else{
				CustomerInfo filters = new CustomerInfo();
				filters.setCustomerNum(param.getCustomerNum());
				//filters.setActive("Y");
				List<CustomerInfo> infoList = customerInfoManageService.queryCustomerInfo(filters, 0, 10);
				if(infoList.size() > 0){
					return returnError("已存在相同大客户编码数据,请重新修改!");
				}else{
					customerInfoManageService.repealAndAddCustomerInfo(param);
					return returnSuccess("修改成功!");
				}
			}
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}
	
	/**
	 * 
	 * @Description:大客户作废
	 * @return String 
	 * @author 文洁
	 * @date 2016年11月7日
	 */
	@JSON
	public String repealCustomerInfo(){
		int count = 0;
		try {
			List<CustomerInfo> params = customerInfoVo.getCustomerInfoList();
			for (CustomerInfo customerInfoInfo : params) {
				customerInfoManageService.repealCustomerInfo(customerInfoInfo);
				count++;
			}
			return returnSuccess("作废成功！共作废" + count + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
			return returnError("e.toString()");
		}
	}

	
	
	public CustomerInfoVo getCustomerInfoVo() {
		return customerInfoVo;
	}

	public void setCustomerInfoVo(CustomerInfoVo customerInfoVo) {
		this.customerInfoVo = customerInfoVo;
	}
	
}
