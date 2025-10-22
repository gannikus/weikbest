package com.weikbest.pro.saas.common.ext.user;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.util.context.SpringContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 * <p>
 * 用户额外的服务
 */
@Component
public class UserExtServiceFactory {

    /**
     * 获取用户表额外服务
     *
     * @return
     */
    public List<UserExtService> getAllUserExtService() {
        List<UserExtService> resultLit = new ArrayList<>();

        // 从Spring中获取平台用户额外服务服务类
        String[] beanNames = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(UserExtService.class);
        if (ArrayUtil.isNotEmpty(beanNames)) {
            for (String beanName : beanNames) {
                UserExtService userExtService = (UserExtService) SpringContext.getInstance().getBean(beanName);
                resultLit.add(userExtService);
            }
        }
        return resultLit;
    }

    /**
     * 获取用户表额外服务
     *
     * @param userRelateType 用户类型
     * @return
     */
    public UserExtService getUserExtService(String userRelateType) {
        // 从Spring中获取平台用户额外服务服务类
        String[] beanNames = SpringContext.getInstance().getApplicationContext().getBeanNamesForType(UserExtService.class);
        if (ArrayUtil.isNotEmpty(beanNames)) {
            for (String beanName : beanNames) {
                UserExtService userExtService = (UserExtService) SpringContext.getInstance().getBean(beanName);
                if (StrUtil.equals(userExtService.getUserRelateType(), userRelateType)) {
                    return userExtService;
                }
            }
        }
        throw new WeikbestException("无法匹配到用户额外服务类，请检查用户类型是否正确：" + userRelateType);
    }

}
