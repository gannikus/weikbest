package com.weikbest.pro.saas.config;

import com.weikbest.pro.saas.filter.AccessControlAllowOriginFilter;
import com.weikbest.pro.saas.filter.TraceIdFilter;
import com.weikbest.pro.saas.listener.StartupListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/6/10 0010
 * @project saas
 * @jdk 1.8
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${AccessControlAllowOrigin.URL}")
    private String allowDomains;

    /***
     * 注册servlet
     */
    /*@Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return null;
    }*/

    /***
     * 注册filter
     */
    @Bean
    public FilterRegistrationBean<Filter> accessControlAllowOriginFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessControlAllowOriginFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(1);

        //设置白名单的方法
        /*Map<String, String> paramMap = new HashMap<>(10);
        paramMap.put("allowDomains", allowDomains);
        registrationBean.setInitParameters(paramMap);*/
        return registrationBean;
    }

    /***
     * 注册filter
     */
    @Bean
    public FilterRegistrationBean<Filter> traceIdFilterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceIdFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(2);
        return registrationBean;
    }

    /***
     * 注册filter
     */
//    @Bean
//    public FilterRegistrationBean<Filter> accessTokenRegistrationBean() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AccessTokenFilter());
//        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
//        registrationBean.setOrder(3);
//
//        Map<String, String> paramMap = new HashMap<>(10);
//        paramMap.put("allowDomains", allowDomains);
//        registrationBean.setInitParameters(paramMap);
//        return registrationBean;
//    }

    /***
     * 注册listener
     */
    @Bean
    public ServletListenerRegistrationBean<StartupListener> startupListenerRegistrationBean() {
        ServletListenerRegistrationBean<StartupListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new StartupListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
