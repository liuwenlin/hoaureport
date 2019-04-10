package com.hoau.hoaureport.module.baseinfo.server.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：高佳
 * @create：2015年6月30日 下午3:45:55
 * @description：公共选择器
 */
@Controller
@Scope("prototype")
public class CommonSelectorDemoAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return returnSuccess();
	}
}
