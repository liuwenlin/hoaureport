package com.hoau.hoaureport.module.configreport.shared.domain;
/**
 * 
 * ClassName: ExcelExportResultEntity 
 * @author 刘镇松
 * @date 2016年8月24日
 * @version V1.0
 */
public class ExcelExportResultEntity {

    /**
     * 文件存放路径
     */
    private String filePath;

    /**
     * 导出记录条数
     */
    private long recordCount;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
}
