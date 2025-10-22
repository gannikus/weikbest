package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliverRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverRecordQO;

import java.util.List;

/**
 * <p>
 * 订单批量发货记录拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
public interface OrderBatchDeliverRecordService extends IService<OrderBatchDeliverRecord> {

    /**
     * 新增数据
     *
     * @param orderBatchDeliverRecordDTO orderBatchDeliverRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderBatchDeliverRecordDTO orderBatchDeliverRecordDTO);

    /**
     * 更新数据
     *
     * @param id                         ID
     * @param orderBatchDeliverRecordDTO orderBatchDeliverRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderBatchDeliverRecordDTO orderBatchDeliverRecordDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderBatchDeliverRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderBatchDeliverRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderBatchDeliverRecord> queryPage(OrderBatchDeliverRecordQO orderBatchDeliverRecordQO, PageReq pageReq);
}
