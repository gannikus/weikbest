package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImg;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleLogisticsImgQO;

/**
 * <p>
 * 订单售后物流图片拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleLogisticsImgService extends IService<OrderAfterSaleLogisticsImg> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO
     * @return 新增结果
     */
    boolean insert(OrderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO);

    /**
     * 更新数据
     *
     * @param id                            ID
     * @param orderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSaleLogisticsImg findById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleLogisticsImgQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleLogisticsImg> queryPage(OrderAfterSaleLogisticsImgQO orderAfterSaleLogisticsImgQO, PageReq pageReq);
}
