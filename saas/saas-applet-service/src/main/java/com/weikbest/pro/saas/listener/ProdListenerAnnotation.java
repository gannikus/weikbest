package com.weikbest.pro.saas.listener;

import com.weikbest.pro.saas.common.event.GenericEvent;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 监听商品
 *
 * @author 甘之萌  2023/05/05 10:44
 */
@Component
@EnableAsync
public class ProdListenerAnnotation {

    //事件监听
    @EventListener
    //异步
    @Async
    public void prod(GenericEvent<Prod> event) {
        System.out.println("监听一个商品:"+event.getData().getId());
    }

}
