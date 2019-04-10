package com.hoau.hoaureport.module.configreport.shared.vo;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatFullRate;

import java.io.Serializable;
import java.util.List;

/**满载率VO
 * @author by 宋京涛
 * @Description
 * @Date 2018/3/12
 */
public class PlatFullRateVo implements Serializable {


    private static final long serialVersionUID = -8143517728868564028L;
    //满载率实体
    private PlatFullRate platFullRate;
    //满载率集合
    private List<PlatFullRate> platFullRateList;

    private String isConfirm;
    private String order;
    private String selects;
    //文件路径
    private String filePath;
    private String addSize;//新增条数
    private String coverSize;//覆盖条数
    private String sumSize; //导入总数量

    public PlatFullRate getPlatFullRate() {
        return platFullRate;
    }

    public void setPlatFullRate(PlatFullRate platFullRate) {
        this.platFullRate = platFullRate;
    }

    public List<PlatFullRate> getPlatFullRateList() {
        return platFullRateList;
    }

    public void setPlatFullRateList(List<PlatFullRate> platFullRateList) {
        this.platFullRateList = platFullRateList;
    }

    public String getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSelects() {
        return selects;
    }

    public void setSelects(String selects) {
        this.selects = selects;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAddSize() {
        return addSize;
    }

    public void setAddSize(String addSize) {
        this.addSize = addSize;
    }

    public String getCoverSize() {
        return coverSize;
    }

    public void setCoverSize(String coverSize) {
        this.coverSize = coverSize;
    }

    public String getSumSize() {
        return sumSize;
    }

    public void setSumSize(String sumSize) {
        this.sumSize = sumSize;
    }



}
