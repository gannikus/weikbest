package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleConsultRecord;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleConsultRecordDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleConsultRecordQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordVO;

import java.util.List;

/**
 * <p>
 * 订单售后协商历史记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleConsultRecordService extends IService<OrderAfterSaleConsultRecord> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO);

    /**
     * 新增数据
     *
     * @param orderAfterSale
     * @param changeType
     * @return
     */
    Long insertByOrderAfterSale(OrderAfterSale orderAfterSale, String changeType);

    /**
     * 小程序端新增数据
     *
     * @param orderAfterSale
     * @param changeType
     * @return
     */
    Long appinsertByOrderAfterSale(OrderAfterSale orderAfterSale, String changeType);

    /**
     * 更新数据
     *
     * @param id                             ID
     * @param orderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSaleConsultRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleConsultRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleConsultRecord> queryPage(OrderAfterSaleConsultRecordQO orderAfterSaleConsultRecordQO, PageReq pageReq);

    /**
     * 根据售后单ID查询协商历史
     *
     * @param orderAfterSaleId
     * @return
     */
    List<OrderAfterSaleConsultRecordVO> queryVOByOrderAfterSaleId(Long orderAfterSaleId);

    /**
     * 根据售后单ID查询协商历史
     *
     * @param orderAfterSaleId
     * @return
     */
    List<OrderAfterSaleConsultRecordDetailVO> queryDetailVOByOrderAfterSaleId(Long orderAfterSaleId);
}
