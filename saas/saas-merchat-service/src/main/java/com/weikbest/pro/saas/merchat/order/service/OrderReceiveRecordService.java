package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderReceiveRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderReceiveRecordQO;

import java.util.List;

/**
 * <p>
 * 订单分账记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-06
 */
public interface OrderReceiveRecordService extends IService<OrderReceiveRecord> {

    /**
     * 新增数据
     *
     * @param orderReceiveRecordDTO orderReceiveRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderReceiveRecordDTO orderReceiveRecordDTO);

    /**
     * 更新数据
     *
     * @param id                    ID
     * @param orderReceiveRecordDTO orderReceiveRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderReceiveRecordDTO orderReceiveRecordDTO);

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
    OrderReceiveRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderReceiveRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderReceiveRecord> queryPage(OrderReceiveRecordQO orderReceiveRecordQO, PageReq pageReq);

    /**
     * 根据订单号查询
     *
     * @param number
     * @return
     */
    List<OrderReceiveRecord> queryByNumber(String number);
}
