package com.hoau.hoaureport.module.job.server.dao;

import com.hoau.hoaureport.module.job.shared.domain.OmsOrderVo;
import com.hoau.hoaureport.module.job.shared.domain.OrderAddressEnity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderAddressEnityMapper {
   ;

    /**
     * This method was generated by MyBatis Generator.
     *
     * @author lx
     * @date 2018-03-09 10:46:52
     */
    int insert(OrderAddressEnity record);


    /**
     * This method was generated by MyBatis Generator.
     *
     * @author lx
     * @date 2018-03-09 10:46:52
     */
    OrderAddressEnity selectByPrimaryKey(String id);

    /**
     * 所属日待解析订单数据 分页获取
     * @param bizdate
     * @return
     */
    public List<OmsOrderVo> queryOmsOrderList(String bizdate , RowBounds rowBounds);

    /**
     * 所属日待解析订单总记录数
     * @param bizdate
     * @return
     */
    public long queryOmsOrderCount(String bizdate);

    /**
     * 新增 （已有时更新）
     * @param order
     * @return
     */
    public int insertOrderAddres(OrderAddressEnity order);

    /**
     * 解析成功状态更新
     * @param bizdate
     * @return
     */
    public int updateOmsOrderStatus(String bizdate);

}