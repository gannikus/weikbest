package com.weikbest.pro.saas.merchat.common.ext.user;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.service.CustomerService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 * <p>
 * 小程序用户
 */
@Component
public class CustomerUserExtServiceImpl implements UserExtService {

    @Resource
    private CustomerService customerService;

    @Override
    public String getUserRelateType() {
        return DictConstant.UserRelateType.applet.getCode();
    }

    @Override
    public UserExt getUser(Long id) {
        Customer customer = customerService.getById(id);
        if (ObjectUtil.isNull(customer)) {
            return UserExt.defaultUserExt();
        }
        return new UserExt(id, customer.getAvatar(), customer.getName(), customer.getPhone());
    }

    @Override
    public boolean removeByIds(List<Long> idList) {
        // 客户表数据不删除.
        return true;
    }

    /**
     * 客户的业务字段是：openid
     *
     * @param queryMap
     * @return
     */
    @Override
    public UserExt getUser(Map<String, Object> queryMap) {
        Customer customer = customerService.findByOpenid(MapUtil.getStr(queryMap, "openid"));
        if (ObjectUtil.isNull(customer)) {
            return UserExt.defaultUserExt();
        }
        return new UserExt(customer.getId(), customer.getName(), customer.getAvatar(), customer.getPhone());
    }
}
