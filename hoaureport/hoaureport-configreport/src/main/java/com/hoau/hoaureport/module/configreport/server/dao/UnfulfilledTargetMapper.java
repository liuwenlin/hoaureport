package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.UnfulfilledTarget;

/**
 * DD未兑现目标值Mapper
 * ClassName: UnfulfilledTargetMapper 
 * @author 文洁
 * @date 2016年12月20日
 * @version V1.0
 */
@Repository
public interface UnfulfilledTargetMapper {
    int deleteByPrimaryKey(Long unfulfilledTargetId);

    int insert(UnfulfilledTarget record);

    int insertSelective(UnfulfilledTarget record);

    UnfulfilledTarget selectByPrimaryKey(Long unfulfilledTargetId);

    int updateByPrimaryKeySelective(UnfulfilledTarget record);

    int updateByPrimaryKey(UnfulfilledTarget record);
    
    /**
     * 
     * @Description:根据条件查询 DD未兑现目标值信息
     * @param DD未兑现目标值实例
     * @param 行限制实例  
     * @return List<UnfulfilledTarget> 
     * @author 文洁
     * @date 2016年12月20日
     */
    List<UnfulfilledTarget> queryUnfulfilledTargetByCondition(UnfulfilledTarget param,RowBounds rowBounds);
    
    /**
     * 
     * @Description:查询总记录数
     * @param DD未兑现目标值实例
     * @return long 
     * @author 文洁
     * @date 2016年12月20日
     */
    long queryUnfulfilledTargetCountByCondition(UnfulfilledTarget param);
}