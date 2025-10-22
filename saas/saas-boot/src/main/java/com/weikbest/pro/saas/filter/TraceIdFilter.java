package com.weikbest.pro.saas.filter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 西瓜瓜
 * @project weikbest
 * @jdk 1.8
 * @since :2022/9/3
 * <p>
 * 请求生成统一流水号
 */
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 从 http 请求头中取出 traceid
        String traceid = request.getHeader(WeikbestConstant.TRACEID);

        // 检查是否存在 traceid
        if (StrUtil.isBlank(traceid)) {
            // 从 http 请求域中取出 access_token
            traceid = request.getParameter(WeikbestConstant.TRACEID);
            if (StrUtil.isBlank(traceid)) {
                // 生成一个traceid
                // 放入request请求中
                request.setAttribute(WeikbestConstant.TRACEID, IdUtil.fastSimpleUUID());
            }
        }

        // 调用真正的controller
        filterChain.doFilter(request, servletResponse);
    }
}
