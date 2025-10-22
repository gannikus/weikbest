package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImgHis;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleLogisticsImgHisQO;

import java.util.List;

/**
 * <p>
 * 订单售后物流图片拆分历史表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleLogisticsImgHisService extends IService<OrderAfterSaleLogisticsImgHis> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO
     * @return 新增结果
     */
    boolean insert(OrderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO);

    /**
     * 更新数据
     *
     * @param id                               ID
     * @param orderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSaleLogisticsImgHis findById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleLogisticsImgHisQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleLogisticsImgHis> queryPage(OrderAfterSaleLogisticsImgHisQO orderAfterSaleLogisticsImgHisQO, PageReq pageReq);

    /**
     * 查询图片信息
     *
     * @param historyId
     * @param courierImgType
     * @return
     */
    List<String> queryImgByHistoryIdAndCourierImgType(Long historyId, String courierImgType);
}
