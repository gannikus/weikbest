package com.weikbest.pro.saas.merchat.aftersale.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingReturnResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.redis.RedisContext;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.common.util.OrderUtil;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.event.OrderAfterSaleEvent;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
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
public class OrderAfterSaleEventListener {

    @Resource
    private OrderAfterSaleService orderAfterSaleService;



    /**
     * 立即退款失败监听
     * @param event
     */
    @EventListener
    @Transactional(rollbackFor = Exception.class)
    public void refundFailed(OrderAfterSaleEvent event){
        Long afterSaleId = event.getData();
        log.info("立即退款失败监听事件参数：{}",afterSaleId);
        OrderAfterSale orderAfterSale = orderAfterSaleService.findById(afterSaleId);
        if (ObjectUtil.isNotNull(orderAfterSale)){
            //更新售后单状态为退款中
            orderAfterSaleService.updateAfterSaleStatus(orderAfterSale,DictConstant.AfterSaleStatus.afterSaleStatus_12.getCode());

        }
    }

}
