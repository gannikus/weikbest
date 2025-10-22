package com.weikbest.pro.saas.common.ext.filter;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.util.context.SpringContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 */
public class AllowDomainsServiceFactory {

    /**
     * 获取放开跨域访问的url
     *
     * @return
     */
    public static String[] appendAccessControlAllowOriginURL(String[] allowDomains) {
        if (allowDomains == null) {
            allowDomains = new String[WeikbestConstant.ZERO_INT];
        }
        if (ObjectUtil.isNotEmpty(SpringContext.getInstance().getApplicationContext())) {
            String[] beanNames = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(AllowDomainsService.class);
            if (ArrayUtil.isNotEmpty(beanNames)) {
                for (String beanName : beanNames) {
                    AllowDomainsService allowDomainsService = (AllowDomainsService) SpringContext.getInstance().getBean(beanName);
                    String accessControlAllowOriginURL = allowDomainsService.accessControlAllowOriginURL();
                    if (StrUtil.isNotBlank(accessControlAllowOriginURL)) {
                        allowDomains = ArrayUtil.append(allowDomains, accessControlAllowOriginURL.split(WeikbestConstant.COMM_SPLIT));
                    }
                }
            }
        }
        return allowDomains;
    }

    /**
     * 获取放开跨域访问的url
     *
     * @return
     */
    public static Set<String> allowOrigins(String[] allowDomains) {
        Set<String> allowOrigins = new HashSet<>();
        String[] beanNames = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(AllowDomainsService.class);
        if (ArrayUtil.isNotEmpty(beanNames)) {
            for (String beanName : beanNames) {
                AllowDomainsService allowDomainsService = (AllowDomainsService) SpringContext.getInstance().getBean(beanName);
                String accessControlAllowOriginURL = allowDomainsService.accessControlAllowOriginURL();
                if (StrUtil.isNotBlank(accessControlAllowOriginURL)) {
                    allowOrigins.addAll(Arrays.asList(accessControlAllowOriginURL.split(WeikbestConstant.COMM_SPLIT)));
                }
            }
        }
        if (ArrayUtil.isNotEmpty(allowDomains)) {
            allowOrigins.addAll(Arrays.asList(allowDomains));
        }
        return allowOrigins;
    }
}
