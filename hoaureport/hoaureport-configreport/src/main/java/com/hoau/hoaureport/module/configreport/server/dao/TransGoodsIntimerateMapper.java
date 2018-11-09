package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.hoaureport.module.configreport.shared.domain.TransGoodsIntimerate;
/**
 * 
 * ClassName: TransGoodsIntimerateMapper 
 * @author 刘镇松
 * @date 2016年11月21日
 * @version V1.0
 */
public interface TransGoodsIntimerateMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(TransGoodsIntimerate record);

    int insertSelective(TransGoodsIntimerate record);

    TransGoodsIntimerate selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(TransGoodsIntimerate record);

    int updateByPrimaryKey(TransGoodsIntimerate record);
    

    /**
     * 
     * @Description: 查询必走货目标值
     * @param @param param
     * @param @param rowBounds
     * @param @return   
     * @return List<TransGoodsIntimerate> 
     * @throws
     * @author 刘镇松
     * @date 2016年11月21日
     */
    List<TransGoodsIntimerate> queryTransGoodsIntimerateByCondition(TransGoodsIntimerate param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param 次日送达率信息实例
     * @return long 
     * @author 文洁
     * @date 2016年8月23日
     */
    long queryTransGoodsIntimerateCountByCondition(TransGoodsIntimerate param);
}