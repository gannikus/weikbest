package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderSourceScale;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderSourceScaleDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderSourceScaleQO;

import java.util.List;

/**
 * <p>
 * 订单来源分账比例表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
public interface OrderSourceScaleService extends IService<OrderSourceScale> {

    /**
     * 新增数据
     *
     * @param orderSourceScaleDTO orderSourceScaleDTO
     * @return 新增结果
     */
    boolean insert(OrderSourceScaleDTO orderSourceScaleDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param orderSourceScaleDTO orderSourceScaleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderSourceScaleDTO orderSourceScaleDTO);

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
    OrderSourceScale findById(Long id);

    /**
     * 分页查询
     *
     * @param orderSourceScaleQO
     * @param pageReq
     * @return
     */
    IPage<OrderSourceScale> queryPage(OrderSourceScaleQO orderSourceScaleQO, PageReq pageReq);
}
