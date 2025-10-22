package com.weikbest.pro.saas.merchat.order.event;

import com.weikbest.pro.saas.common.event.GenericEvent;

/**
 * description
 *
 * @author 甘之萌  2023/05/12 17:48
 */
public class OrderPayRecordEvent extends GenericEvent<Long> {

    public OrderPayRecordEvent(Long data) {
        super(data);
    }
}
