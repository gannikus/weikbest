package com.weikbest.pro.saas.merchat.aftersale.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.Deal;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/15
 */
public class OrderAfterSaleUtil {

    /**
     * 极速退款金额上限
     */
    private static final BigDecimal MAX_FAST_REFUND_AMOUNT = new BigDecimal(300);

    /**
     * 下单时间上限
     */
    private static final Integer MAX_HOUR = 2;

    /**
     * 是否符合极速退款的要求
     * 1. 订单金额＜300元
     * 2. 下单后2小时内申请【仅退款】
     * 3. 申请【退货退款】且7天内商家未发货
     *
     * @param deal        系统交易规则实体
     * @param payAmount   支付金额
     * @param applyType   售后申请类型
     * @param orderTime   下单时间
     * @param orderStatus 订单状态
     * @return 是否符合
     */
    public static boolean isFastRefund(Deal deal, BigDecimal payAmount, String applyType, Date orderTime, String orderStatus) {
        // 1. 订单金额＜300元
        if (payAmount.compareTo(new BigDecimal(deal.getFastRefundCondition1())) < WeikbestConstant.ZERO_INT) {
            // 2.申请【仅退款】并且订单状态是：待发货
            if (StrUtil.equals(applyType, DictConstant.AfterSaleApplyType.only_refund.getCode()) && StrUtil.equals(orderStatus, DictConstant.OrderStatus.orderStatus_10.getCode())) {

                boolean flag = false;
                // 下单后2小时内 或 7天内商家未发货
                if(StrUtil.equals(deal.getIsOpenCondition2(), DictConstant.Whether.yes.getCode())) {
                    flag = DateUtil.between(orderTime, DateUtil.date(), DateUnit.HOUR) <= deal.getFastRefundCondition2();
                }
                if(StrUtil.equals(deal.getIsOpenCondition2(), DictConstant.Whether.yes.getCode())) {
                    flag = flag || DateUtil.between(orderTime, DateUtil.date(), DateUnit.DAY) <= deal.getFastRefundCondition3();
                }
                return flag;
            }
        }

        // 默认不符合
        return false;
    }

    /**
     * 显示退款金额
     *
     * @param amount
     * @return
     */
    public static String showAmount(BigDecimal amount) {
        return "￥" + amount.toString();
    }


}
