package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImgHis;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleImgHisQO;

import java.util.List;

/**
 * <p>
 * 订单售后图片拆分历史表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleImgHisService extends IService<OrderAfterSaleImgHis> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleImgHisDTO orderAfterSaleImgHisDTO
     * @return 新增结果
     */
    boolean insert(OrderAfterSaleImgHisDTO orderAfterSaleImgHisDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param orderAfterSaleImgHisDTO orderAfterSaleImgHisDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleImgHisDTO orderAfterSaleImgHisDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSaleImgHis findById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleImgHisQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleImgHis> queryPage(OrderAfterSaleImgHisQO orderAfterSaleImgHisQO, PageReq pageReq);

    /**
     * 根据历史记录ID查询
     *
     * @param historyId
     * @return
     */
    List<String> queryImgByHistoryId(Long historyId);
}
