package com.weikbest.pro.saas.merchat.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliver;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverListVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单批量发货记录表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-26
 */
public interface OrderBatchDeliverMapper extends BaseMapper<OrderBatchDeliver> {

    /**
     * 订单批量发货记录分页查询
     *
     * @param page
     * @param orderBatchDeliverQO
     * @return
     */
    IPage<OrderBatchDeliverListVO> queryPage(Page<OrderBatchDeliverListVO> page, @Param("orderBatchDeliver") OrderBatchDeliverQO orderBatchDeliverQO);
}
