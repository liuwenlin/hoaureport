package com.hoau.hoaureport.module.configreport.server.action;

import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hoaureport.module.configreport.server.service.IPlatFullRateService;
import com.hoau.hoaureport.module.configreport.shared.domain.PlatFullRate;
import com.hoau.hoaureport.module.configreport.shared.vo.PlatFullRateVo;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
@Controller("platFullRateManageAction")
@Scope("prototype")
public class PlatFullRateManageAction extends AbstractAction {


    private static final long serialVersionUID = -1570248679607622686L;
    private PlatFullRateVo platFullRateVo;
    @Resource
    private IPlatFullRateService platFullRateService;

    private List<PlatFullRate> platFullRateList ;
    /**
     * 返回到页面
     * @return
     */
    public String showPlatFullRatePage(){
        return "index";
    }

    /**
     * 查询满载率
     * @return
     */
    @JSON
    public String queryPlatFullRate(){
        try {
            platFullRateList=platFullRateService.queryPlatFullRateList(platFullRateVo.getPlatFullRate(), start, limit);
            this.totalCount = platFullRateService.queryPlatNextdayReachRateCount(platFullRateVo.getPlatFullRate());
            return returnSuccess();
        } catch (Exception e) {
            return returnSuccess(e.toString());
        }
    }

    /**
     * 导入excel表格进数据库
     * @return
     * @throws Exception
     */
    @JSON
    public String importExcel()  throws Exception{
        try {
            String uploadPath = ServletActionContext.getServletContext().getRealPath(platFullRateVo.getFilePath());
            Map<String,Object> returnMap= platFullRateService.bathImplPlatNextdayReachRate(uploadPath);
            if(StringUtil.isNotEmpty(returnMap.get("error").toString())){//导入格式有误
                return returnError(returnMap.get("error").toString());
            }
            //addSize:增加条数,coverSize:覆盖条数,sumSize:总共条数,filePath:错误的信息的文件地址
            platFullRateVo.setAddSize(returnMap.get("addSize").toString());
            platFullRateVo.setCoverSize(returnMap.get("coverSize").toString());
            platFullRateVo.setSumSize(returnMap.get("sumSize").toString());
            return returnSuccess();
        } catch (BusinessException e) {
            return returnError(e);
        }
    }

    public PlatFullRateVo getPlatFullRateVo() {
        return platFullRateVo;
    }

    public void setPlatFullRateVo(PlatFullRateVo platFullRateVo) {
        this.platFullRateVo = platFullRateVo;
    }

    public List<PlatFullRate> getPlatFullRateList() {
        return platFullRateList;
    }

    public void setPlatFullRateList(List<PlatFullRate> platFullRateList) {
        this.platFullRateList = platFullRateList;
    }


}
