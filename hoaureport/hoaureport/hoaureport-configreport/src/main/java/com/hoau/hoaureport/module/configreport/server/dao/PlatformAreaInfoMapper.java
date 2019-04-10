package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.PlatformAreaInfo;
/**
 * 
 * ClassName: PlatformAreaInfoMapper 
 * @author 刘镇松
 * @date 2016年8月18日
 * @version V1.0
 */
@Repository
public interface PlatformAreaInfoMapper {
    int deleteByPrimaryKey(String platformAreaId);

    int insert(PlatformAreaInfo record);

    int insertSelective(PlatformAreaInfo record);

    PlatformAreaInfo selectByPrimaryKey(String platformAreaId);

    int updateByPrimaryKeySelective(PlatformAreaInfo record);

    int updateByPrimaryKey(PlatformAreaInfo record);
    
    /**
     * 根据条件查询 
     * @param PlatformAreaVo
     * @return
     */
    List<PlatformAreaInfo> queryPlatformAreaInfoByCondition(PlatformAreaInfo param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param PlatformAreaVo
     * @return
     */
    Long queryPlatformAreaCountByCondition(PlatformAreaInfo param);
    int repealAllPlatformArea();
}