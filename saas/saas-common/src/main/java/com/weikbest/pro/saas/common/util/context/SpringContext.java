package com.weikbest.pro.saas.common.util.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wisdomelon
 * @date 2019/6/11 0011
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SpringContext() {
    }

    public static SpringContext getInstance() {
        return SpringContextHolder.INSTANCE;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        log.info("spring context: {} prepare is ready....", ctx);
        SpringContext.applicationContext = ctx;
    }

    public <T> T getBean(Class<T> c) {
        return applicationContext.getBean(c);
    }

    public Object getBean(String s) {
        return applicationContext.getBean(s);
    }

    private static class SpringContextHolder {
        private static final SpringContext INSTANCE = new SpringContext();
    }
}
