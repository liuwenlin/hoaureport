package com.hoau.hoaureport.module.configreport.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.hoaureport.module.configreport.shared.domain.CityPriceCard;

/**
 * 城市价卡Mapper
 * ClassName: CityPriceCardMapper 
 * @author 文洁
 * @date 2016年12月15日
 * @version V1.0
 */
@Repository
public interface CityPriceCardMapper {
    int deleteByPrimaryKey(Long cityPriceCardId);

    int insert(CityPriceCard record);

    int insertSelective(CityPriceCard record);

    CityPriceCard selectByPrimaryKey(Long cityPriceCardId);

    int updateByPrimaryKeySelective(CityPriceCard record);

    int updateByPrimaryKey(CityPriceCard record);
    
    /**
     * 根据条件查询 城市价卡信息
     * @param cityPriceCardVo
     * @return
     */
    List<CityPriceCard> queryCityPriceCardByCondition(CityPriceCard param,RowBounds rowBounds);
    /**
     * 查询总记录数
     * @param cityPriceCardVo
     * @return
     */
    Long queryCityPriceCardCountByCondition(CityPriceCard param);
    
    /**
     * 根据明确的条件（不支持模糊查询）查询 城市价卡信息
     * @param cityPriceCardVo
     * @return
     */
    List<CityPriceCard> queryCityPriceCardByExplicitCondition(CityPriceCard param,RowBounds rowBounds);
    int repealAllCityPriceCard();
}