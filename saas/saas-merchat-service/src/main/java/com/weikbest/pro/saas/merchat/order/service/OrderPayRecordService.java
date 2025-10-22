package com.weikbest.pro.saas.merchat.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyV3Result;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderPayRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderPayRecordQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderPayRecordVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单支付记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface OrderPayRecordService extends IService<OrderPayRecord> {

    /**
     * 新增数据
     *
     * @param orderPayRecordDTO orderPayRecordDTO
     * @return 新增结果
     */
    boolean insert(OrderPayRecordDTO orderPayRecordDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param orderPayRecordDTO orderPayRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, OrderPayRecordDTO orderPayRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    OrderPayRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param orderPayRecordQO
     * @param pageReq
     * @return
     */
    IPage<OrderPayRecord> queryPage(OrderPayRecordQO orderPayRecordQO, PageReq pageReq);

    /**
     * 订单退款
     *
     * @param orderId 订单ID
     * @param reason  退款原因
     * @param refund  退款金额
     */
    void refund(Long orderId, String reason, BigDecimal refund);

    /**
     * 根据订单ID查询
     *
     * @param orderId
     * @return
     */
    OrderPayRecord findByOrderId(Long orderId);

    /**
     * 订单退款微信回调
     *
     * @param result
     */
    void refundNotify(WxPayRefundNotifyV3Result result);

    /**
     * 根据订单ID查询
     *
     * @param orderId
     * @return
     */
    OrderPayRecordVO findVOByOrderId(Long orderId);

    /**
     * 根据支付订单号查询
     *
     * @param outTradeNoList
     * @return
     */
    List<OrderPayRecord> queryByOutTradeNoList(List<String> outTradeNoList);

    /**
     * 根据支付订单号查询
     *
     * @param outTradeNo
     * @return
     */
    OrderPayRecord findByOutTradeNo(String outTradeNo);
}
