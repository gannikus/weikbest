package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderStatRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderStatRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderStatRecordQO;

/**
 * <p>
 * 订单状态变更记录表  服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderStatRecordService extends IService<OrderStatRecord> {

    /**
     * 新增数据
     *
     * @param orderStatRecordDTO orderStatRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderStatRecordDTO orderStatRecordDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param orderStatRecordDTO orderStatRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderStatRecordDTO orderStatRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderStatRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderStatRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderStatRecord> queryPage(OrderStatRecordQO orderStatRecordQO, PageReq pageReq);
}
