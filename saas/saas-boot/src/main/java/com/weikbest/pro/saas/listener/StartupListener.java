package com.weikbest.pro.saas.listener;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.PageUtil;
import com.weikbest.pro.saas.common.annotation.cache.LocalCache;
import com.weikbest.pro.saas.common.cache.ILocalCache;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * @author wisdomelon
 * @date 2019/6/19 0019
 * @project saas
 * @jdk 1.8
 * 系统启动时加载类
 */
@Slf4j
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("-----------------系统启动器启动,开始加载全局配置--------------------------");
        long start = System.currentTimeMillis();
        try {
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
            SpringContext.getInstance().setApplicationContext(context);
            PageUtil.setOneAsFirstPageNo();
            // 加载本地缓存
            loadLocalCache();
        } catch (Exception e) {
            log.error("-----------------系统启动器启动失败！--------------------------", e);
        }
        long end = System.currentTimeMillis();
        log.info("-----------------系统启动器启动完毕,耗时：" + (end - start) + "ms.--------------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void loadLocalCache() {
        String[] localCacheBeanNames = SpringContext.getInstance().getApplicationContext().getBeanNamesForAnnotation(LocalCache.class);
        if (ArrayUtil.isNotEmpty(localCacheBeanNames)) {
            log.info("-----------------本地缓存{}开始加载--------------------------", Arrays.toString(localCacheBeanNames));

            for (String localCacheBeanName : localCacheBeanNames) {
                ILocalCache iLocalCache = (ILocalCache) SpringContext.getInstance().getBean(localCacheBeanName);
                iLocalCache.loadCache();
            }

            log.info("-----------------本地缓存{}加载完毕--------------------------", Arrays.toString(localCacheBeanNames));
        }
    }
}
