package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImg;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleImgQO;

/**
 * <p>
 * 订单售后图片拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleImgService extends IService<OrderAfterSaleImg> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleImgDTO orderAfterSaleImgDTO
     * @return 新增结果
     */
    boolean insert(OrderAfterSaleImgDTO orderAfterSaleImgDTO);

    /**
     * 更新数据
     *
     * @param id                   ID
     * @param orderAfterSaleImgDTO orderAfterSaleImgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleImgDTO orderAfterSaleImgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSaleImg findById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleImgQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleImg> queryPage(OrderAfterSaleImgQO orderAfterSaleImgQO, PageReq pageReq);
}
