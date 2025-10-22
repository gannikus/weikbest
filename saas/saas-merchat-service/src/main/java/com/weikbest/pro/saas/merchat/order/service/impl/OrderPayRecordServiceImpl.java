package com.weikbest.pro.saas.merchat.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCouponsReturnRequest;
import com.github.binarywang.wxpay.bean.marketing.BusiFavorCouponsReturnResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyV3Result;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.redis.RedisKey;
import com.weikbest.pro.saas.common.redis.RedisLock;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeExpiredDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.delaytaskprocess.AppCouponRestrictTypeNotUsedDelayTaskProcess;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.service.CustCouponRestrictService;
import com.weikbest.pro.saas.merchat.order.delaytaskprocess.OrderSettlementByDepositDayDelayTaskProcess;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.event.OrderPayRecordEvent;
import com.weikbest.pro.saas.merchat.order.mapper.OrderPayRecordMapper;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderPayRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.mapstruct.OrderPayRecordMapStruct;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderPayRecordQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderPayRecordVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.merchat.order.service.OrderProdInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderReceiveRecordService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceDetailService;
import com.weikbest.pro.saas.merchat.shop.service.ShopStatementDetailService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.service.CapitalRecordService;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单支付记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Service
public class OrderPayRecordServiceImpl extends ServiceImpl<OrderPayRecordMapper, OrderPayRecord> implements OrderPayRecordService, ApplicationEventPublisherAware {

    @Resource
    private MemoryService memoryService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private CustCouponRestrictService custCouponRestrictService;

    @Resource
    private CouponService couponService;

    @Resource
    private OrderProdInfoService orderProdInfoService;

    @Resource
    private OrderReceiveRecordService orderReceiveRecordService;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopFinanceDetailService shopFinanceDetailService;

    @Resource
    private ShopStatementDetailService shopStatementDetailService;

    @Resource
    private AppCouponRestrictTypeExpiredDelayTaskProcess appCouponRestrictTypeExpiredDelayTaskProcess;

    @Resource
    private AppCouponRestrictTypeNotUsedDelayTaskProcess appCouponRestrictTypeNotUsedDelayTaskProcess;

    @Resource
    private RedisLock redisLock;

    @Resource
    private OrderSettlementByDepositDayDelayTaskProcess orderSettlementByDepositDayDelayTaskProcess;

    @Resource
    private CapitalRecordService capitalRecordService;

    @Resource
    private RedisContext redisContext;

    private ApplicationEventPublisher applicationEventPublisher;



    @Override
    public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }



    @Override
    public boolean insert(OrderPayRecordDTO orderPayRecordDTO) {
        OrderPayRecord orderPayRecord = OrderPayRecordMapStruct.INSTANCE.converToEntity(orderPayRecordDTO);
        return this.save(orderPayRecord);
    }

    @Override
    public boolean updateById(Long id, OrderPayRecordDTO orderPayRecordDTO) {
        OrderPayRecord orderPayRecord = this.findById(id);
        OrderPayRecordMapStruct.INSTANCE.copyProperties(orderPayRecordDTO, orderPayRecord);
        orderPayRecord.setId(id);
        return this.updateById(orderPayRecord);
    }

    @Override
    public OrderPayRecord findById(Long id) {
        OrderPayRecord orderPayRecord = this.getById(id);
        if (ObjectUtil.isNull(orderPayRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderPayRecord;
    }

    @Override
    public OrderPayRecord findByOrderId(Long orderId) {
        OrderPayRecord orderPayRecord = this.getOne(new QueryWrapper<OrderPayRecord>().eq(OrderPayRecord.ORDER_ID, orderId));
        if (ObjectUtil.isNull(orderPayRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return orderPayRecord;
    }

    @Override
    public OrderPayRecordVO findVOByOrderId(Long orderId) {
        OrderPayRecord orderPayRecord = this.findByOrderId(orderId);
        return OrderPayRecordMapStruct.INSTANCE.converToVO(orderPayRecord);
    }

    @Override
    public OrderPayRecord findByOutTradeNo(String outTradeNo) {
        OrderPayRecord orderPayRecord = this.getOne(new QueryWrapper<OrderPayRecord>().eq(OrderPayRecord.OUT_TRADE_NO, outTradeNo));
//        if (ObjectUtil.isNull(orderPayRecord)) {
//            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
//        }
        return orderPayRecord;
    }

    @Override
    public List<OrderPayRecord> queryByOutTradeNoList(List<String> outTradeNoList) {
        return this.list(new QueryWrapper<OrderPayRecord>().in(OrderPayRecord.OUT_TRADE_NO, outTradeNoList));
    }

    @Override
    public IPage<OrderPayRecord> queryPage(OrderPayRecordQO orderPayRecordQO, PageReq pageReq) {
        QueryWrapper<OrderPayRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(orderPayRecordQO.getPayMode())) {
            wrapper.eq(OrderPayRecord.PAY_MODE, orderPayRecordQO.getPayMode());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getPayStatus())) {
            wrapper.eq(OrderPayRecord.PAY_STATUS, orderPayRecordQO.getPayStatus());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getPayContent())) {
            wrapper.eq(OrderPayRecord.PAY_CONTENT, orderPayRecordQO.getPayContent());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getTradingFlow())) {
            wrapper.eq(OrderPayRecord.TRADING_FLOW, orderPayRecordQO.getTradingFlow());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getOutTradeNo())) {
            wrapper.eq(OrderPayRecord.OUT_TRADE_NO, orderPayRecordQO.getOutTradeNo());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getPayRespContent())) {
            wrapper.eq(OrderPayRecord.PAY_RESP_CONTENT, orderPayRecordQO.getPayRespContent());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getCallbackContent())) {
            wrapper.eq(OrderPayRecord.CALLBACK_CONTENT, orderPayRecordQO.getCallbackContent());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getRefundTradeNo())) {
            wrapper.eq(OrderPayRecord.REFUND_TRADE_NO, orderPayRecordQO.getRefundTradeNo());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getRefundStatus())) {
            wrapper.eq(OrderPayRecord.REFUND_STATUS, orderPayRecordQO.getRefundStatus());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getRefundRespContent())) {
            wrapper.eq(OrderPayRecord.REFUND_RESP_CONTENT, orderPayRecordQO.getRefundRespContent());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getRefundCallbackContent())) {
            wrapper.eq(OrderPayRecord.REFUND_CALLBACK_CONTENT, orderPayRecordQO.getRefundCallbackContent());
        }
        if (StrUtil.isNotBlank(orderPayRecordQO.getDescription())) {
            wrapper.eq(OrderPayRecord.DESCRIPTION, orderPayRecordQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refund(Long orderId, String reason, BigDecimal refund) {
        OrderInfo orderInfo = orderInfoService.findById(orderId);
        WxPayService wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfo.getNumber());
        OrderPayRecord orderPayRecord = this.findByOrderId(orderId);
        OrderProdInfo orderProdInfo = orderProdInfoService.findById(orderId);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Long> orderIds = new ArrayList<>();
        //合单支付总金额
        if (StrUtil.isNotBlank(orderInfo.getMainNumber())){
            orderIds = orderInfoService.findByOrderMainNumber(orderInfo.getMainNumber()).stream().map(OrderInfo::getId).collect(Collectors.toList());
            List<OrderPayRecord> list = this.list(new QueryWrapper<OrderPayRecord>().in(OrderPayRecord.ORDER_ID, orderIds));
            totalAmount = ObjectUtil.isNull(list.get(0).getTotalAmount()) ? list.get(0).getPayAmount() : list.get(0).getTotalAmount();
        }else {
            totalAmount = ObjectUtil.isNull(orderPayRecord.getTotalAmount()) ? orderPayRecord.getPayAmount() : orderPayRecord.getTotalAmount() ;
        }

        //如果是分账订单 先完结分账，解冻资金，再执行退款
        if(StrUtil.equals(orderInfo.getIsReceiveOrder(), DictConstant.Whether.yes.getCode())) {

            Object unfreezeTradeNo;

             unfreezeTradeNo = redisContext.get("UNFREEZE_TRADE_NO:"+orderId);

             if (ObjectUtil.isNull(unfreezeTradeNo)){
                 unfreezeTradeNo = orderPayRecord.getUnfreezeTradeNo();
             }
            String state = "";
            if(ObjectUtil.isNotNull(unfreezeTradeNo) && StrUtil.isNotEmpty((String)unfreezeTradeNo)){
                try {
                    ProfitSharingResult response  = wxPayService.getProfitSharingV3Service().getProfitSharingResult((String)unfreezeTradeNo, orderPayRecord.getTradingFlow());
                    state = response.getState();
                }catch(Exception e){
                    throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getMessage(), e);
                }
            }else{
                //解冻分账资金
                state = orderInfoService.orderFundReleaseHourTimeout(orderId);
            }
            if(!"FINISHED".equals(state)){
                throw new WeikbestException(String.format("微信资金解冻正在排队！请1分钟后再试。", orderInfo.getNumber()));
            }
//            orderPayRecord.setUnfreezeTradeNo((String) unfreezeTradeNo);

            // 如果该笔订单是分账订单，则需要做退账处理
            //执行退款之前先删除押金回退清算任务

            if (StrUtil.isNotBlank(orderInfo.getMainNumber())){
                orderIds.forEach(id-> orderSettlementByDepositDayDelayTaskProcess.removeTask(id));
            }else {
                orderSettlementByDepositDayDelayTaskProcess.removeTask(orderId);

            }


            List<OrderReceiveRecord> orderReceiveRecordList = orderReceiveRecordService.list(
                    new QueryWrapper<OrderReceiveRecord>()
                    .eq(OrderReceiveRecord.NUMBER, orderInfo.getNumber())
                    .in(OrderReceiveRecord.RECEIVE_TYPE, Arrays.asList("3","4")
                    ));
            //先判断是否存在分账回退记录
            if (CollectionUtil.isEmpty(orderReceiveRecordList)){
                //发布事件-分账回退
                applicationEventPublisher.publishEvent(new OrderPayRecordEvent(orderId));
//                throw new WeikbestException(String.format("正在进行分账回退！请1分钟后再试。", orderInfo.getNumber()));
            }
        }


        // 微信退款
        String outrefundno = OrderUtil.getOutrefundno();
        String wxRefundId = "";
        try {
            WxPayRefundV3Request request = new WxPayRefundV3Request();
            request.setTransactionId(orderPayRecord.getTradingFlow())
                    .setOutRefundNo(outrefundno)
                    .setReason(reason)
                    .setAmount(new WxPayRefundV3Request.Amount().setCurrency("CNY").setTotal(WxPayAmountConvertUtil.multiplyConvert(totalAmount))
                    .setRefund(WxPayAmountConvertUtil.multiplyConvert(refund)))
                    .setNotifyUrl(memoryService.queryConfig(ConfigConstant.WX_REFUND_NOTIFY_URL));
            log.info("微信退款参数：订单号：{}，退款单号：{}，退款请求参数：{}",orderInfo.getNumber(),outrefundno, JSON.toJSON(request));
            WxPayRefundV3Result wxPayRefundV3Result = wxPayService.refundV3(request);
            String createTime = wxPayRefundV3Result.getCreateTime();

            wxRefundId = wxPayRefundV3Result.getRefundId();

            // 更新订单支付记录
            orderPayRecord.setRefundId(wxRefundId).setRefundTradeNo(outrefundno).setRefundAmount(refund).setRefundReason(reason).setRefundStatus(DictConstant.RefundStatus.refundStatus_2.getCode())
                    .setRefundTime(DateUtil.parse(createTime)).setRefundRespContent(JsonUtils.bean2Json(wxPayRefundV3Result));
            this.updateById(orderPayRecord);
        } catch (WxPayException e) {
            log.error("调用微信退款接口失败! " + e.getCustomErrorMsg(), e);
            throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getCustomErrorMsg(), e);
        }

        // 如果该笔订单使用了优惠券（平台劵，立减劵），则还需要做退券处理
        if (StrUtil.isNotBlank(orderInfo.getCouponType()) && !StrUtil.equals(orderInfo.getCouponType(), DictConstant.CouponType.TYPE_2.getCode())) {
            // 查询客户领券信息
            Long custCouponRestrictId = orderInfo.getCustCouponRestrictId();
            CustCouponRestrict custCouponRestrict = custCouponRestrictService.findById(custCouponRestrictId);

            WxPayService wxPayCouponService = null;

            if(custCouponRestrict.getCouponType().equals(DictConstant.CouponType.TYPE_3.getCode())){
                //如果是平台优惠券，则取平台数据
                wxPayCouponService = shopThirdService.findCouponWxPayServicePayConfig();
            }else{
                wxPayCouponService = wxPayService;
            }

            // 查询优惠券信息
            Coupon coupon = couponService.queryCouponById(orderInfo.getCouponId());
            String returnRequestNo = OrderUtil.getNoncestr();
            try {
                // 调用微信退券接口
                MarketingBusiFavorService marketingBusiFavorService = wxPayCouponService.getMarketingBusiFavorService();
                BusiFavorCouponsReturnRequest request = new BusiFavorCouponsReturnRequest();
                request.setCouponCode(custCouponRestrict.getCouponCode());
                request.setStockId(coupon.getStockId());
                request.setReturnRequestNo(returnRequestNo);
                BusiFavorCouponsReturnResult busiFavorCouponsReturnResult = marketingBusiFavorService.returnBusiFavorCoupons(request);

                // 更新客户领券信息
                custCouponRestrict.setIsCouponsReturn(DictConstant.Whether.yes.getCode()).setReturnRequestNo(returnRequestNo).setCouponsReturnTime(DateUtil.parse(busiFavorCouponsReturnResult.getWechatpayReturnTime()));
                custCouponRestrictService.updateById(custCouponRestrict);
                custCouponRestrictService.saveCustCouponRestrictType(custCouponRestrictId);
                // 重新设置延时队列
                // 设置未生效到未使用延时队列
                appCouponRestrictTypeNotUsedDelayTaskProcess.putTask(custCouponRestrictId, coupon.getUseStartTime().getTime());
                // 设置未使用到已过期延时队列
                appCouponRestrictTypeExpiredDelayTaskProcess.putTask(custCouponRestrictId, coupon.getUseEndTime().getTime());
            } catch (WxPayException e) {
                log.error("调用微信退券接口失败! " + e.getCustomErrorMsg(), e);
                throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getCustomErrorMsg(), e);
            }
        }

        // 写入平台及商户资金账户及账单明细表
        // 店铺资金账户
        ShopFinanceAccount shopFinanceAccount = shopFinanceAccountService.findByShopId(orderInfo.getShopId());

        // 添加店铺资金明细表
        ShopFinanceDetail shopFinanceDetail = new ShopFinanceDetail();
        shopFinanceDetail.setShopId(orderInfo.getShopId())
                .setFinanceAccountId(shopFinanceAccount.getId())
                .setNumber(orderInfo.getNumber())
                .setFinanceType(DictConstant.FinanceType.financetype_30.getCode())
                .setCapitalFlowType(DictConstant.CapitalFlowType.capitalFlowType_2.getCode())
                .setWxOrderId(wxRefundId)
                .setAmountDetail(orderPayRecord.getRefundAmount())
                .setEnterTime(DateUtil.date());
        shopFinanceDetailService.save(shopFinanceDetail);

        // 添加店铺对账单明细表
        ShopStatementDetail shopStatementDetail = new ShopStatementDetail();
        shopStatementDetail.setStatementType(DictConstant.StatementType.statementType_2.getCode())
                .setNumber(orderInfo.getNumber())
                .setOrderAfterSaleId(orderInfo.getOrderAfterSaleId())
                .setShopId(orderInfo.getShopId())
                .setCustomerId(orderInfo.getCustomerId())
                .setProdName(orderProdInfo.getProdName())
                .setOrderTime(orderInfo.getOrderTime())
                .setPayTraceId(orderPayRecord.getRefundId())
                .setSettleType(DictConstant.SettleType.settleType_0.getCode())
                .setPayType(orderPayRecord.getPayMode())
                .setPayTime(orderPayRecord.getPayTime())
                .setPayAmount(orderPayRecord.getPayAmount())
                .setRefundAmount(orderPayRecord.getRefundAmount())
                .setRefundTime(orderPayRecord.getRefundTime())
                .setDescription("售后退款");
        shopStatementDetailService.save(shopStatementDetail);



    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refundNotify(WxPayRefundNotifyV3Result result) {
        WxPayRefundNotifyV3Result.DecryptNotifyResult decryptNotifyResult = result.getResult();
        String orderNumber = decryptNotifyResult.getOutTradeNo();
        String outRefundNo = decryptNotifyResult.getOutRefundNo();
        String refundStatus = decryptNotifyResult.getRefundStatus();
        OrderInfo orderInfo;
        if (orderNumber.startsWith(BasicConstant.OrderNumber.MAIN_NUMBER_START_WITH)){
            OrderPayRecord one = this.getOne(new QueryWrapper<OrderPayRecord>().eq(OrderPayRecord.REFUND_TRADE_NO, outRefundNo));
             orderInfo = orderInfoService.findByOrderNumber(one.getOutTradeNo());
        }else {
             orderInfo = orderInfoService.findByOrderNumber(orderNumber);
        }
        OrderAfterSale orderAfterSale = orderAfterSaleService.getById(orderInfo.getOrderAfterSaleId());
        OrderPayRecord orderPayRecord = this.findByOrderId(orderInfo.getId());

        if (DictConstant.OrderStatus.isFinish(orderInfo.getOrderStatus())
                && (ObjectUtil.isNotNull(orderAfterSale) && DictConstant.AfterSaleStatus.isFinish(orderAfterSale.getAfterSaleStatus()))
                && StrUtil.equals(orderPayRecord.getRefundStatus(), refundStatus)) {
            log.info("订单号：{},退款单号：{} 退款回调已处理完成。无需重复处理", orderNumber, outRefundNo);
            return;
        }

        String lockKey = RedisKey.generalKey(RedisKey.Lock.LOCK_REFUNDNOTIFY_KEY, orderNumber, outRefundNo);
        boolean tryLock = redisLock.tryLock(lockKey);
        if (!tryLock) {
            log.info("订单号：{},退款单号：{} 退款正在进行中，无需重复处理", orderNumber, outRefundNo);
            return;
        }

        try {
            // 订单状态默认修改为关闭
            String orderStatus = DictConstant.OrderStatus.orderStatus_99.getCode();
            String description = "系统退款成功";

            // 判断是否存在售后
            // 售后状态修改为售后完成状态
            if(ObjectUtil.isNotNull(orderAfterSale)) {
                if (StrUtil.equals(orderAfterSale.getIsFastSale(), DictConstant.Whether.yes.getCode())) {
                    // 极速退款单
                    orderAfterSaleService.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_9.getCode());
                    // 订单状态改为已关闭
                    orderStatus = DictConstant.OrderStatus.orderStatus_99.getCode();
                    description = "系统极速退款成功";
                } else if (StrUtil.equals(orderAfterSale.getAfterSaleKey(), DictConstant.AfterSaleKey.afterSaleKey_9.getCode())) {
                    // 非极速退款单，平台处理中
                    orderAfterSaleService.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_0.getCode());
                } else {
                    // 商家受理的正常退款
                    orderAfterSaleService.updateAfterSaleStatus(orderAfterSale, DictConstant.AfterSaleStatus.afterSaleStatus_1.getCode());
                }
            }

            // 订单状态更新
            orderInfo.setOrderFinishTime(DateUtil.date());
            orderInfo.setOrderAfterFlag(DictConstant.Whether.no.getCode());
            orderInfoService.updateOrderStatus(orderInfo, orderStatus, description);

            // 更新订单支付记录
            orderPayRecord.setRefundStatus(DictConstant.RefundStatus.refundStatus_1.getCode()).setRefundCallbackContent(JsonUtils.bean2Json(result)).setRefundCallbackTime(DateUtil.parse(decryptNotifyResult.getSuccessTime()));
            this.updateById(orderPayRecord);

            // 店铺资金账户
            ShopFinanceAccount shopFinanceAccount = shopFinanceAccountService.findByShopId(orderInfo.getShopId());

            //退款成功,对账单明细表修改结算状态为: 已结算

            List<ShopStatementDetail> details = shopStatementDetailService.list(new QueryWrapper<ShopStatementDetail>()
                    .eq(ShopStatementDetail.NUMBER, orderInfo.getNumber())
                    .eq(ShopStatementDetail.SETTLE_TYPE, DictConstant.SettleType.settleType_0.getCode()));
            if (CollectionUtil.isNotEmpty(details)) {
                details.forEach(d -> {
                    d.setSettleType(DictConstant.SettleType.settleType_1.getCode());
                });
                shopStatementDetailService.updateBatchById(details);
            }


            // 添加店铺对账单明细表
         /*   ShopStatementDetail shopStatementDetail = shopStatementDetailService.getOne(new QueryWrapper<ShopStatementDetail>().eq(ShopStatementDetail.NUMBER, orderInfo.getNumber())
                    .eq(ShopStatementDetail.STATEMENT_TYPE, DictConstant.StatementType.statementType_2.getCode())
                    .eq(ShopStatementDetail.SETTLE_TYPE, DictConstant.SettleType.settleType_0.getCode())
                    .eq(ShopStatementDetail.PAY_TRACE_ID, orderPayRecord.getRefundId()));
            if(ObjectUtil.isNotNull(shopStatementDetail)) {
                shopStatementDetail.setSettleType(DictConstant.SettleType.settleType_1.getCode());
                shopStatementDetailService.updateById(shopStatementDetail);

                // 店铺资金账户
                // 2023.1.17 lipeng 待结算业务只有普通商户进行分账时才进行结算，该业务已在退款方法内实现，取消这块代码
                *//**
                 BigDecimal settleAmount = shopFinanceAccount.getSettleAmount();
                 settleAmount = settleAmount.add(orderPayRecord.getRefundAmount());
                 shopFinanceAccount.setSettleAmount(settleAmount);
                 shopFinanceAccountService.updateById(shopFinanceAccount);
                 **//*
            }*/

        } finally {
            // 解锁
            redisLock.unlock(lockKey);
        }
    }
}
