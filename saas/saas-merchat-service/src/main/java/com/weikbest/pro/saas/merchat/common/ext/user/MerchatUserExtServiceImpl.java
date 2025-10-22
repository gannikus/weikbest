package com.weikbest.pro.saas.merchat.common.ext.user;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.ext.user.UserExtService;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserRoleService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 * <p>
 * 商户用户
 */
@Component
public class MerchatUserExtServiceImpl implements UserExtService {

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private ShopUserService shopUserService;

    @Resource
    private ShopUserRoleService shopUserRoleService;

    @Override
    public String getUserRelateType() {
        return DictConstant.UserRelateType.merchat.getCode();
    }

    @Override
    public UserExt getUser(Long id) {
        BusiUser busiUser = busiUserService.getById(id);
        if (ObjectUtil.isNull(busiUser)) {
            return UserExt.defaultUserExt();
        }
        return new UserExt(id, busiUser.getAvatar(), busiUser.getName(), busiUser.getPhone());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(List<Long> idList) {

        // 删除商户用户
        busiUserService.removeByIds(idList);

        // 查询店铺用户
        List<ShopUser> shopUserList = shopUserService.list(new QueryWrapper<ShopUser>().in(ShopUser.BUSINESS_USER_ID, idList));
        if (CollectionUtil.isNotEmpty(shopUserList)) {
            List<Long> shopUserIdList = shopUserList.stream().map(ShopUser::getId).collect(Collectors.toList());
            // 删除店铺用户
            shopUserService.removeByIds(shopUserIdList);

            // 删除店铺用户角色关联
            shopUserRoleService.remove(new QueryWrapper<ShopUserRole>().in(ShopUserRole.SHOP_USER_ID, shopUserIdList));
        }

        return true;
    }
}
