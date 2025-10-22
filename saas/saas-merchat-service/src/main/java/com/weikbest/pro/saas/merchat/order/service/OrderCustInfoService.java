package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderCustInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderCustInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderCustInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderCustInfoVO;

/**
 * <p>
 * 客户订单与客户关联拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderCustInfoService extends IService<OrderCustInfo> {

    /**
     * 新增数据
     *
     * @param orderCustInfoDTO orderCustInfoDTO
     * @return 新增结果
     */
    boolean insert(OrderCustInfoDTO orderCustInfoDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param orderCustInfoDTO orderCustInfoDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderCustInfoDTO orderCustInfoDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderCustInfo findById(Long id);

    /**
     * 分页查询
     *
     * @param orderCustInfoQO
     * @param pageReq
     * @return
     */
    IPage<OrderCustInfo> queryPage(OrderCustInfoQO orderCustInfoQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    OrderCustInfoVO findVOById(Long id);
}
