package com.weikbest.pro.saas.merchat.cust.util;

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.sys.system.entity.User;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
public class CustomerUtil {

    /**
     * 生成一个默认平台用户关联
     *
     * @param customer
     * @return
     */
    public static String defaultUserUnique(Customer customer) {
        // TODO 后续多渠道这里要根据类型判断
        String custThirdType = customer.getCustThirdType();

        return WeikbestConstant.TEMP + customer.getWxUnionid();
    }

    /**
     * 这些字段有一个发生了变化，则进行平台字段变更处理
     *
     * @param customer
     * @param user
     * @return
     */
    public static boolean isChange(Customer customer, User user) {
        if (!StrUtil.equals(user.getPhone(), customer.getPhone()) && StrUtil.isNotBlank(customer.getPhone())) {
            return true;
        }
        if (StrUtil.isBlank(user.getName()) && StrUtil.isNotBlank(customer.getName())) {
            return true;
        }
        if (StrUtil.isBlank(user.getEmail()) && StrUtil.isNotBlank(customer.getEmail())) {
            return true;
        }
        if (StrUtil.isBlank(user.getAvatar()) && StrUtil.isNotBlank(customer.getAvatar())) {
            return true;
        }
        return false;
    }
}
