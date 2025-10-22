package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderProdInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderProdInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderProdInfoVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单商品参数详细表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderProdInfoService extends IService<OrderProdInfo> {

    /**
     * 新增数据
     *
     * @param orderProdInfoDTO orderProdInfoDTO
     * @return 新增结果
     */
    boolean insert(OrderProdInfoDTO orderProdInfoDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param orderProdInfoDTO orderProdInfoDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderProdInfoDTO orderProdInfoDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderProdInfo findById(Long id);

    /**
     * 分页查询
     *
     * @param orderProdInfoQO
     * @param pageReq
     * @return
     */
    IPage<OrderProdInfo> queryPage(OrderProdInfoQO orderProdInfoQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     */
    OrderProdInfoVO findVOById(Long id);

    /**
     * 根据ID集合查询
     *
     * @param idList
     * @return
     */
    Map<Long, OrderProdInfo> queryMapByIdList(List<Long> idList);
}
