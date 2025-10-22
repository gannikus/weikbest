package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.api.module.dto.OrderLogisticsDto;
import com.weikbest.pro.saas.merchat.api.module.vo.OrderDeliveryErrorVo;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogistics;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderBatchDeliverRecordExcelDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderLogisticsQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单物流记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderLogisticsService extends IService<OrderLogistics> {

    /**
     * 新增数据
     *
     * @param orderLogisticsDTO orderLogisticsDTO
     * @return 新增结果
     */
    boolean insert(OrderLogisticsDTO orderLogisticsDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param orderLogisticsDTO orderLogisticsDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderLogisticsDTO orderLogisticsDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderLogistics findById(Long id);

    /**
     * 分页查询
     *
     * @param orderLogisticsQO
     * @param pageReq
     * @return
     */
    IPage<OrderLogistics> queryPage(OrderLogisticsQO orderLogisticsQO, PageReq pageReq);

    /**
     * 查询物流记录
     *
     * @param id
     * @return
     */
    OrderLogisticsVO findVOAndResetContentById(Long id);

    /**
     * 去发货，并修改订单状态
     *
     * @param orderLogisticsDTO
     * @return
     */
    boolean orderDeliverAndUpdateOrderStatus(OrderLogisticsDTO orderLogisticsDTO);

    /**
     * 批量发货，修改订单状态为已发货
     *
     * @param orderBatchDeliverRecordExcelDTOList
     */
    void batchOrderDeliverAndUpdateOrderStatus(List<OrderBatchDeliverRecordExcelDTO> orderBatchDeliverRecordExcelDTOList);

    /**
     * 查询物流记录
     *
     * @param orderId
     * @return
     */
    OrderLogisticsVO findVOAndResetContentByOrderId(Long orderId);

    /**
     * 查询物流记录
     *
     * @param orderId
     * @return
     */
    OrderLogisticsVO getVOAndResetContentByOrderId(Long orderId);

    /**
     * 查询发货信息
     *
     * @param orderId
     * @return
     */
    OrderLogistics getByOrderId(Long orderId);

    /**
     * 发货
     *
     * @param orderLogisticsDtoList
     * @param businessId
     * @return
     */
    List<OrderDeliveryErrorVo> delivery(List<OrderLogisticsDto> orderLogisticsDtoList, Long businessId);
}
