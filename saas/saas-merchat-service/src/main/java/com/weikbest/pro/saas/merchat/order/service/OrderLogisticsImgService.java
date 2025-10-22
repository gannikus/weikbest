package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogisticsImg;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderLogisticsImgQO;

/**
 * <p>
 * 订单物流图片表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderLogisticsImgService extends IService<OrderLogisticsImg> {

    /**
     * 新增数据
     *
     * @param orderLogisticsImgDTO orderLogisticsImgDTO
     * @return 新增结果
     */
    boolean insert(OrderLogisticsImgDTO orderLogisticsImgDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param orderLogisticsImgDTO orderLogisticsImgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderLogisticsImgDTO orderLogisticsImgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderLogisticsImg findById(Long id);

    /**
     * 分页查询
     *
     * @param orderLogisticsImgQO
     * @param pageReq
     * @return
     */
    IPage<OrderLogisticsImg> queryPage(OrderLogisticsImgQO orderLogisticsImgQO, PageReq pageReq);

    /**
     * 保存物流图片信息
     *
     * @param orderLogisticsId
     * @param orderLogisticsDTO
     */
    void saveBatchWithOrderLogistics(Long orderLogisticsId, OrderLogisticsDTO orderLogisticsDTO);
}
