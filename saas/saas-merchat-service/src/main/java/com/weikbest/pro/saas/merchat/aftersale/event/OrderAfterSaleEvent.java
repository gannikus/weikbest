package com.weikbest.pro.saas.merchat.aftersale.event;

import com.weikbest.pro.saas.common.event.GenericEvent;

/**
 * 售后监听事件
 *
 * @author 甘之萌  2023/05/12 17:48
 */
public class OrderAfterSaleEvent extends GenericEvent<Long> {

    public OrderAfterSaleEvent(Long data) {
        super(data);
    }
}
