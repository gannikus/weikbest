package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderRecAddr;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderRecAddrDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderRecAddrQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderRecAddrVO;

/**
 * <p>
 * 订单收货地址拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderRecAddrService extends IService<OrderRecAddr> {

    /**
     * 新增数据
     *
     * @param orderRecAddrDTO orderRecAddrDTO
     * @return 新增结果
     */
    boolean insert(OrderRecAddrDTO orderRecAddrDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param orderRecAddrDTO orderRecAddrDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderRecAddrDTO orderRecAddrDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderRecAddr findById(Long id);

    /**
     * 分页查询
     *
     * @param orderRecAddrQO
     * @param pageReq
     * @return
     */
    IPage<OrderRecAddr> queryPage(OrderRecAddrQO orderRecAddrQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    OrderRecAddrVO findVOById(Long id);
}
