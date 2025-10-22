package com.weikbest.pro.saas.merchat.aftersale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.*;
import com.weikbest.pro.saas.merchat.aftersale.module.excel.OrderAfterSaleDetailExcel;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleKeyGroupVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleListVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleVO;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单售后表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
public interface OrderAfterSaleService extends IService<OrderAfterSale> {

    /**
     * 新增数据
     *
     * @param orderAfterSaleDTO orderAfterSaleDTO
     * @param applySourceType   售后单新建来源
     * @return 新增结果
     */
    Long insert(OrderAfterSaleDTO orderAfterSaleDTO, String applySourceType);

    /**
     * 新增售后数据
     *
     * @param orderAfterSaleDTO
     * @param applySourceType
     * @return
     */
    OrderAfterSale insertOrderAfterSale(OrderAfterSaleDTO orderAfterSaleDTO, String applySourceType);

    /**
     * 极速退款
     *
     * @param id
     */
    void fastRefundById(Long id);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param orderAfterSaleDTO orderAfterSaleDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderAfterSaleDTO orderAfterSaleDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderAfterSale findById(Long id);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    OrderAfterSaleVO findVOById(Long id);

    /**
     * 分页查询
     *
     * @param orderAfterSaleQO
     * @param pageReq
     * @return
     */
    IPage<OrderAfterSaleListVO> queryPage(OrderAfterSaleQO orderAfterSaleQO, PageReq pageReq);

    /**
     * 获取订单售后申请列表
     *
     * @param paramMap
     * @return
     */
    List<AppOrderAfterSaleApplyDTO> queryPageAppOrderAfterApply(Map<String, Object> paramMap);

    /**
     * 获取我的售后申请列表
     *
     * @param paramMap
     * @return
     */
    List<AppOrderAfterSaleListDTO> queryPageAppOrderAfterList(Map<String, Object> paramMap);

    /**
     * 查询商户店铺订单售后状态汇总
     *
     * @param businessId
     * @param shopId
     * @return
     */
    List<OrderAfterSaleKeyGroupVO> queryGroupOrderAfterSaleKey(Long businessId, Long shopId);

    /**
     * 修改售后状态
     *
     * @param orderAfterSale
     * @param afterSaleStatus
     */
    boolean updateAfterSaleStatus(OrderAfterSale orderAfterSale, String afterSaleStatus);

    /**
     * 售后单申请后商家处理超时
     *
     * @param id 售后单ID
     * @param afterSaleStatus
     */
    void businessExecuteTimeout(Long id, String afterSaleStatus);

    /**
     * 售后单申请后商家拒绝，客户处理超时
     *
     * @param id 售后单ID
     * @param afterSaleStatus
     */
    void customerExecuteTimeout(Long id, String afterSaleStatus);

    /**
     * 售后单申请类型是退货退款、换货 后商家同意，客户发货处理超时
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    void customerDeliveryTimeout(Long id, String afterSaleStatus);

    /**
     * 售后单申请类型是退货退款、换货 客户寄回商品后，商家确认收货处理过期延时任务
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    void businessConfirmCustomerDeliveryTimeout(Long id, String afterSaleStatus);

    /**
     * 售后单客户寄回商品后商家拒绝收货，客户处理超时
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    void businessRejectDeliveryCustomerExecuteTimeout(Long id, String afterSaleStatus);

    /**
     * 商户确认收货后处理超时
     *
     * @param id              售后单ID
     * @param afterSaleStatus 售后单状态
     */
    void businessDeliveryAfterTimeout(Long id, String afterSaleStatus);

    /**
     * 查询售后单详细信息
     *
     * @param id
     * @return
     */
    OrderAfterSaleDetailVO findOrderAfterSaleDetailVOById(Long id);

    /**
     * 客户发起申请后，商家确认是否同意客户申请，并更新售后状态
     *
     * @param id
     * @param orderAfterSaleBusinessConfirmCustomerApplyDTO
     * @return
     */
    Boolean businessConfirmCustomerApply(Long id, OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO);

    @Transactional(rollbackFor = Exception.class)
    Boolean businessConfirmCustomerApplyForApi(Long id, OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO);

    /**
     * 客户寄回商品后，商家确认是否收货，并更新售后状态
     *
     * @param id
     * @param orderAfterSaleBusinessConfirmCustomerApplyDTO
     * @return
     */
    Boolean businessConfirmCustomerDelivery(Long id, OrderAfterSaleBusinessConfirmCustomerDeliveryDTO orderAfterSaleBusinessConfirmCustomerApplyDTO);

    /**
     * 商家确认收货后，发起退款
     *
     * @param id
     * @return
     */
    Boolean businessConfirmDeliveryAndRefund(Long id);

    /**
     * 商家确认收货后，重新发出物流
     *
     * @param orderAfterSaleBusinessConfirmAndReDeliveryDTO
     * @return
     */
    Boolean businessConfirmAndReDelivery(OrderAfterSaleBusinessConfirmAndReDeliveryDTO orderAfterSaleBusinessConfirmAndReDeliveryDTO);

    /**
     * 批量同意仅退款
     *
     * @param ids ID集合
     * @return
     */
    Boolean batchUpdateOnlyRefund(List<Long> ids);

    /**
     * 批量同意退货退款申请
     *
     * @param ids ID集合
     * @return
     */
    Boolean batchUpdateReturnAndRefundApply(List<Long> ids);

    /**
     * 批量同意换货申请
     *
     * @param ids ID集合
     * @return
     */
    Boolean batchUpdateExchangeApply(List<Long> ids);

    /**
     * 批量同意退货退款
     *
     * @param ids ID集合
     * @return
     */
    Boolean batchUpdateReturnAndRefund(List<Long> ids);

    /**
     * 售后单导出
     *
     * @param orderAfterSaleQO
     * @return
     */
    List<OrderAfterSaleDetailExcel> downloadDetail(OrderAfterSaleQO orderAfterSaleQO);

    /**
     * 根据订单ID查询售后历史信息
     *
     * @param orderId
     * @return
     */
    List<OrderAfterSaleVO> queryOrderAfterSaleVOHisByOrderId(Long orderId);

    /**
     * 立即退款
     * @param id
     * @return
     */
    Boolean refundRightNow(Long id);

    /**
     * 定时退款任务具体执行方法
     * @param id
     */
    void refundDelayTimeOut(Long id);

    /**
     * 根据订单Id和售后状态查询售后单
     * @param orderId
     * @param afterSaleStatus
     * @return
     */
    List<OrderAfterSale> getAfterSaleByOrderIdAndAfterSaleStatus(Long orderId, String afterSaleStatus);

    /**
     * 根据订单查询
     *
     * @param orderNumbers
     * @param busiUser
     * @return
     */
    List<OrderAfterSale> listByOrderNumbers(List<String> orderNumbers, BusiUser busiUser);
}
