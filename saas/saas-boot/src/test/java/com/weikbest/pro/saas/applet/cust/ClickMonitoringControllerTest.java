package com.weikbest.pro.saas.applet.cust;

import com.weikbest.pro.saas.SaasBootApplication;
import com.weikbest.pro.saas.common.event.GenericEvent;
import com.weikbest.pro.saas.event.ProdEvent;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.event.OrderPayRecordEvent;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 监听测试类
 *
 * @author 甘之萌  2023/05/05 10:33
 */
@SpringBootTest(classes ={SaasBootApplication.class})
@RunWith(SpringRunner.class)
public class ClickMonitoringControllerTest implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NotNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Test
    public void testListen() {
//        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setNumber("2313131");
//        GenericEvent<OrderInfo> orderInfoGenericEvent = new GenericEvent<OrderInfo>(orderInfo);
        System.out.println("启动事件监听--");
//        Prod prod = new Prod();
//        prod.setId(2313131L);
//        ProdEvent prodEvent = new ProdEvent(prod);
        OrderPayRecordEvent orderPayRecordEvent = new OrderPayRecordEvent(1655757130273263616L);
//        applicationEventPublisher.publishEvent(orderInfoGenericEvent);
        applicationEventPublisher.publishEvent(orderPayRecordEvent);
    }

//    @EventListener
//    public void order(GenericEvent<OrderInfo> event) {
//        System.out.println("监听一个订单:" + event.getData().getNumber());
//
//    }


}
