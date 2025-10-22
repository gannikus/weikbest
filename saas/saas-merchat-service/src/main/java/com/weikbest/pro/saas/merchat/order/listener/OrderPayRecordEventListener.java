package com.weikbest.pro.saas.merchat.order.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.event.GenericEvent;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderPayRecord;
import com.weikbest.pro.saas.merchat.order.entity.OrderProdInfo;
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.event.OrderPayRecordEvent;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderPayRecordService;
import com.weikbest.pro.saas.merchat.order.service.OrderReceiveRecordService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceDetailService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.service.CapitalRecordService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;

/**
 * 支付事件监听类
 *
 * @author 甘之萌  2023/05/15 9:42
 */

@Slf4j
@Component
public class OrderPayRecordEventListener {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderReceiveRecordService orderReceiveRecordService;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopFinanceDetailService shopFinanceDetailService;

    @Resource
    private CapitalRecordService capitalRecordService;

    @Resource
    private RedisContext redisContext;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private OrderPayRecordService orderPayRecordService;


    /**
     * 分账回退监听
     * @param event
     */
    @EventListener
    @Transactional(rollbackFor = Exception.class)
    public void ledgerRollback(OrderPayRecordEvent event){
        Long orderId = event.getData();
        log.info("分账回退事件监听参数：{}",orderId);

        OrderInfo orderInfo = orderInfoService.findById(orderId);
        WxPayService wxPayService = shopThirdService.findWxPayServiceByOrderNumber(orderInfo.getNumber());
//        OrderPayRecord orderPayRecord = orderPayRecordService.findByOrderId(orderId);
//
//        Object unfreezeTradeNo = redisContext.get("UNFREEZE_TRADE_NO:"+orderId);
//        String state = "";
//        if(ObjectUtil.isNotNull(unfreezeTradeNo) && StrUtil.isNotEmpty((String)unfreezeTradeNo)){
//            try {
//                ProfitSharingResult response  = wxPayService.getProfitSharingV3Service().getProfitSharingResult((String)unfreezeTradeNo, orderPayRecord.getTradingFlow());
//                state = response.getState();
//            }catch(Exception e){
//                throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getMessage(), e);
//            }
//        }else{
//            //解冻分账资金
//            state = orderInfoService.orderFundReleaseHourTimeout(orderId);
//        }
//        if(!"FINISHED".equals(state)){
//            throw new WeikbestException(String.format("微信资金解冻正在排队！请1分钟后再试。", orderInfo.getNumber()));
//        }
//        orderPayRecord.setUnfreezeTradeNo((String) unfreezeTradeNo);


        // 查询分账记录
        List<OrderReceiveRecord> orderReceiveRecordList = orderReceiveRecordService.list(new QueryWrapper<OrderReceiveRecord>().eq(OrderReceiveRecord.NUMBER, orderInfo.getNumber()).eq(OrderReceiveRecord.RECEIVE_TYPE,"1"));
        for (OrderReceiveRecord orderReceiveRecord : orderReceiveRecordList) {
            String outReturnNo = OrderUtil.getOutrefundno();

            //查询是否存在分账类型为 2-平台分账回退 的数据，如果存在说明已进行清算，这时需比较 1-平台分账扣款 是否和 2-平台分账回退 比例相同
            //如果比例相同，则不需退技术服务费，如果不同，则需退技术服务费。如果分账类型 2 的数据不存在，则需要退 3-平台售后分账回退 数据

            BigDecimal receiveScale ;
            Integer amountFraction ;
            BigDecimal amountYuan ;
            String receiveType ;
            QueryWrapper<OrderReceiveRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(OrderReceiveRecord.NUMBER, orderInfo.getNumber());
            queryWrapper.eq(OrderReceiveRecord.RECEIVE_TYPE,"2");
            List<OrderReceiveRecord> records = orderReceiveRecordService.list(queryWrapper);
            if(records != null && records.size() > 0){
                OrderReceiveRecord record = records.get(0);
                if(record.getReceiveScale().compareTo(orderReceiveRecord.getReceiveScale()) == WeikbestConstant.ZERO_INT){
                    //如果平台分账扣款 和 平台分账回退 比例相同，则不需要退账处理。
                    break;
                }
                //退技术服务费
                receiveScale = orderReceiveRecord.getReceiveScale().subtract(record.getReceiveScale());
                receiveType = "4";

                amountFraction = orderReceiveRecord.getAmount() - record.getAmount() ;

            }else{
                //平台售后回退
                receiveScale = orderReceiveRecord.getReceiveScale();
                receiveType = "3";

                amountFraction = orderReceiveRecord.getAmount();
            }

            try {

                BigDecimal valuebig = new BigDecimal(100);

                amountYuan = new BigDecimal(amountFraction).divide(valuebig);


                // 调用微信请求分账回退接口
                ProfitSharingReturnRequest request = new ProfitSharingReturnRequest();
                request.setOrderId(orderReceiveRecord.getWxOrderId());
                request.setOutReturnNo(outReturnNo);
                request.setReturnMchid(orderReceiveRecord.getMchId());
                request.setAmount(amountFraction.longValue());
                request.setDescription("客户退款");
                log.info("微信请求分账回退参数："+request);
                ProfitSharingReturnResult response = wxPayService.getProfitSharingV3Service().profitSharingReturn(request);
//                ProfitSharingReturnResult response = wxPayService.getProfitSharingV3Service().getProfitSharingReturnResult(orderReceiveRecord.getOutTradeNo(), "202305131242592330080824");

                String receiveRecordStatus = StrUtil.isNotBlank(response.getFailReason()) ? DictConstant.ReceiveRecordStatus.code_5.getCode() : DictConstant.ReceiveRecordStatus.code_3.getCode();

                OrderReceiveRecord record =  new OrderReceiveRecord();
                Long recordId = GenerateIDUtil.nextId();
                record.setId(recordId);
                record.setNumber(orderInfo.getNumber());
                record.setAmount(amountFraction);
                record.setReceiveTime(DateUtil.date());
                record.setTransactionId(orderReceiveRecord.getTransactionId());
                record.setBusinessType(orderReceiveRecord.getBusinessType());
                record.setReceiveType(receiveType);
                record.setOutTradeNo(outReturnNo);
                record.setDescription("退款退账处理");
                record.setReceiveScale(receiveScale);
                //如果状态为PROCESSING-处理中，查询分账回退结果
                if ("PROCESSING".equals(response.getResult())) {
                    log.info("微信分账回退结果查询参数：outOrderNo={}，outReturnNo={}",orderReceiveRecord.getOutTradeNo(),outReturnNo);
                    ProfitSharingReturnResult profitSharingReturnResult = wxPayService.getProfitSharingV3Service().getProfitSharingReturnResult(orderReceiveRecord.getOutTradeNo(), outReturnNo);
                    String receiveRecordStatus1 = StrUtil.isNotBlank(profitSharingReturnResult.getFailReason()) ? DictConstant.ReceiveRecordStatus.code_5.getCode() : DictConstant.ReceiveRecordStatus.code_3.getCode();
                    record.setWxOrderId(profitSharingReturnResult.getReturnId());
                    record.setMchId(profitSharingReturnResult.getReturnMchid());
                    record.setReceiveResult(profitSharingReturnResult.getResult());
                    record.setReceiveFailReason(profitSharingReturnResult.getFailReason());
                    record.setReceiveRecordStatus(receiveRecordStatus1);
                }else {
                    record.setWxOrderId(response.getReturnId());
                    record.setReceiveResult(response.getResult());
                    record.setReceiveFailReason(response.getFailReason());
                    record.setMchId(response.getReturnMchid());
                    record.setReceiveRecordStatus(receiveRecordStatus);
                }
                // 新增分账回退记录
                orderReceiveRecordService.save(record);

                QueryWrapper<ShopFinanceAccount> accountQueryWrapper = new QueryWrapper<>();
                accountQueryWrapper.eq(ShopFinanceAccount.SHOP_ID,orderInfo.getShopId());
                ShopFinanceAccount account = shopFinanceAccountService.getOne(accountQueryWrapper);
                //新增回退明细
                ShopFinanceDetail detail = new ShopFinanceDetail();
                Long sfdId = GenerateIDUtil.nextId();
                detail.setId(sfdId);
                detail.setShopId(orderInfo.getShopId());
                detail.setFinanceAccountId(account.getId());
                if("3".equals(receiveType)){
                    detail.setFinanceType(DictConstant.FinanceType.financetype_40.getCode());
                }
                if("4".equals(receiveType)){
                    detail.setFinanceType(DictConstant.FinanceType.financetype_60.getCode());
                }
                detail.setCapitalFlowType("2");
                detail.setEnterTime(DateUtil.date());
                detail.setWxOrderId(response.getReturnId());
                detail.setAmountDetail(amountYuan);
                detail.setNumber(orderInfo.getNumber());
                shopFinanceDetailService.save(detail);

                //更新未结算金额，存在退款则从未结算金额里面减去
                if(!orderReceiveRecord.getReceiveRecordStatus().equals(DictConstant.ReceiveRecordStatus.code_8.getCode())){
                    BigDecimal amount = new BigDecimal(orderReceiveRecord.getAmount());
                    account.setSettleAmount(account.getSettleAmount().subtract(amount.divide(valuebig)));
                    shopFinanceAccountService.updateById(account);
                }

                if("4".equals(receiveType)) {
                    //保存平台技术服务费流向信息
                    CapitalRecord capitalRecord = new CapitalRecord();
                    Long capitalId = GenerateIDUtil.nextId();
                    capitalRecord.setId(capitalId).setBillAmount("-"+amountYuan.toString())
                            .setBillTime(DateUtil.date()).setShopId(orderInfo.getShopId().toString())
                            .setNumber(orderInfo.getNumber());
                    capitalRecordService.save(capitalRecord);
                }


            } catch (WxPayException e) {
                log.error("调用分账回退接口失败! " + e.getCustomErrorMsg(), e);
                throw new WeikbestException(ResultConstant.THIRD_SERVICE_FAIL.getCode(), e.getCustomErrorMsg(), e);
            }
        }

    }

}
