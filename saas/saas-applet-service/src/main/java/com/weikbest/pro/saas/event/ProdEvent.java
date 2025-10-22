package com.weikbest.pro.saas.event;

import com.weikbest.pro.saas.common.event.GenericEvent;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;

/**
 * 商品监听事件
 *
 * @author 甘之萌  2023/05/04 16:31
 */
public class ProdEvent extends GenericEvent<Prod> {

    public ProdEvent(Prod data) {
        super(data);
    }
}
