package com.weikbest.pro.saas.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.ext.filter.AllowDomainsServiceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author wisdomelon
 * @date 2019/6/10 0010
 * @project saas
 * @jdk 1.8
 * <p>
 * 配置请求过滤器
 */
@Slf4j
public class AccessControlAllowOriginFilter implements Filter {

    private static final String OPTIONS = "OPTIONS";

    private String[] allowDomains;

    @Override
    public void init(FilterConfig filterConfig) {
        String allowDomainsStr = filterConfig.getInitParameter("allowDomains");
        if (allowDomainsStr != null) {
            allowDomains = allowDomainsStr.split(WeikbestConstant.COMM_SPLIT);
        }

        // 从spring上下文中获取
        allowDomains = AllowDomainsServiceFactory.appendAccessControlAllowOriginURL(allowDomains);
        log.info("init allowDomains: {}", Arrays.toString(allowDomains));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取允许访问的链接(设置白名单的方法)
        /*Set<String> allowOrigins = AllowDomainsServiceFactory.allowOrigins(allowDomains);
        if (CollectionUtil.isNotEmpty(allowOrigins)) {
            String origin = request.getHeader("Origin");
            if (allowOrigins.contains(origin)) {
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Headers", "access_token, Origin, X-Requested-With, Content-Type, Accept");
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
                response.addHeader("Access-Control-Max-Age", "0");
                response.addHeader("XDomainRequestAllowed", "1");
            }

            // 直接返回OPTIONS请求，不经过后台controller。
            if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
                // 或者直接输入204、HttpStatus.SC_OK、200，等这些都可以   import org.apache.http.HttpStatus;
                return;
            }
        }*/

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "access_token, Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.addHeader("Access-Control-Max-Age", "0");
        response.addHeader("XDomainRequestAllowed", "1");

        // 直接返回OPTIONS请求，不经过后台controller。
        if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
            // 或者直接输入204、HttpStatus.SC_OK、200，等这些都可以   import org.apache.http.HttpStatus;
            return;
        }

        // 调用真正的controller
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
