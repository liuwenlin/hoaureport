package com.hoau.hoaureport.module.configreport.server.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hoaureport.module.configreport.server.service.IAttendanceService;
import com.hoau.hoaureport.module.configreport.shared.vo.AttendanceVo;

/**
 * 出勤信息管理Action 
 * ClassName: AttendanceManageAction 
 * @author 文洁
 * @date 2016年9月23日
 * @version V1.0
 */
@Controller("attendanceManageAction")
@Scope("prototype")
public class AttendanceManageAction extends AbstractAction{

	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private AttendanceVo attendanceVo;
	
	@Resource
	IAttendanceService attendanceService;
	
	/**
	 * 
	 * @Description:返回出勤信息 首界面
	 * @return index字符串 
	 * @author 文洁
	 * @date 2016年9月23日
	 */
	public String showAttendanceInfoPage() {
		return "index";
	}
	
	/**
	 * 
	 * @Description:根据条件查询 每月出勤信息
	 * @return String
	 * @author 文洁
	 * @date 2016年9月23日
	 */
	@JSON
	public String queryAttendanceInfo(){
		try {
			attendanceVo.setMonthAttendanceInfoList(attendanceService.queryMonthAttendanceInfo(attendanceVo.getAttendanceInfo(),start,limit));
			totalCount = attendanceService.queryMonthAttendanceInfoCount(attendanceVo.getAttendanceInfo());
			System.out.println("==============================");
			return returnSuccess();
		} catch (Exception e) {
			return returnError(e.toString());
		}
	}

	public AttendanceVo getAttendanceVo() {
		return attendanceVo;
	}

	public void setAttendanceVo(AttendanceVo attendanceVo) {
		this.attendanceVo = attendanceVo;
	}

}
