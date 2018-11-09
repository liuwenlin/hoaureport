package com.hoau.hoaureport.module.configreport.server.action;

import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *@Author 任龙
 *@Date 2016/4/1 16:42
 *@Parameters
 */
@Controller
@Scope("prototype")
public class queryInventoryAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    /**库存查询*/
    public String queryInventory(){
        return "queryInventory";
    }
    /**入库确认*/
    public String putInStorage(){
        return "putInStorage";
    }
    /**出库确认*/
    public String outbound(){
        return "goodsInformation";
    }
    /**运单查询*/
    public String waybill(){
        return "waybill";
    }
    /**运单查询*/
    public String butterfly(){
        return "butterfly";
    }

    /**如风达查询*/
    public String rufengda(){
        return "rufengda";
    }
    /**件信息查询*/
    public String goodsInformation(){
        return "goodsInformation";
    }
    /**装卸记录模糊查询*/
    public String unloadingOfRecord(){
        return "unloadingOfRecord";
    }
    /**扫描记录查询*/
    public String scanning(){
        return "scanning";
    }

}
