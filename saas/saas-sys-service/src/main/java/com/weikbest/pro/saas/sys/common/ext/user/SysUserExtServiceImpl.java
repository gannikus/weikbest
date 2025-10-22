package com.weikbest.pro.saas.sys.common.ext.user;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.UserActivex;
import com.weikbest.pro.saas.sys.system.entity.UserLogin;
import com.weikbest.pro.saas.sys.system.entity.UserRole;
import com.weikbest.pro.saas.sys.system.service.UserActivexService;
import com.weikbest.pro.saas.sys.system.service.UserLoginService;
import com.weikbest.pro.saas.sys.system.service.UserRoleService;
import com.weikbest.pro.saas.sys.system.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 * <p>
 * 系统用户获取方法
 */
@Component
public class SysUserExtServiceImpl implements UserExtService {

    @Resource
    private UserService userService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserActivexService userActivexService;

    @Override
    public String getUserRelateType() {
        return DictConstant.UserRelateType.sys.getCode();
    }

    @Override
    public UserExt getUser(Long id) {
        UserActivex userActivex = userActivexService.getByCache(id);
        if (ObjectUtil.isNull(userActivex)) {
            return UserExt.defaultUserExt();
        }
        return new UserExt(id, userActivex.getAvatar(), userActivex.getName(), userActivex.getPhone());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(List<Long> idList) {

        // 删除系统用户
        userService.removeByIds(idList);

        // 删除用户登录表
        userLoginService.remove(new QueryWrapper<UserLogin>().in(UserLogin.USER_ID, idList));

        // 删除用户权限关联表
        userRoleService.remove(new QueryWrapper<UserRole>().in(UserRole.USER_ID, idList));

        // 更新系统用户控件表
        userActivexService.disabledByIds(idList);

        return true;
    }
}
