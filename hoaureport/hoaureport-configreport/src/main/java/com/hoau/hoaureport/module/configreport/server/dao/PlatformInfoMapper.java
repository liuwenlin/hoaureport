package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformInfo;
/**
 * 
 * ClassName: PlatformInfoMapper 
 * @author 刘镇松
 * @date 2016年8月17日
 * @version V1.0
 */
@Repository
public interface PlatformInfoMapper {
    int deleteByPrimaryKey(String platformId);

    int insert(PlatformInfo record);

    int insertSelective(PlatformInfo record);

    PlatformInfo selectByPrimaryKey(String platformId);

    int updateByPrimaryKeySelective(PlatformInfo record);

    int updateByPrimaryKey(PlatformInfo record);
    
    /**
     * 根据条件查询 
     * @param PlatformVo
     * @return
     */
    List<PlatformInfo> queryPlatformInfoByCondition(PlatformInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param PlatformVo
     * @return
     */
    Long queryPlatformCountByCondition(PlatformInfo param);
}