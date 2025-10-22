package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.entity.ShopRole;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopUserRoleMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopUserRoleMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserRoleQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopRoleService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserRoleService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 店铺用户角色关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Service
public class ShopUserRoleServiceImpl extends ServiceImpl<ShopUserRoleMapper, ShopUserRole> implements ShopUserRoleService {

    @Resource
    private RoleService roleService;

    @Resource
    private ShopRoleService shopRoleService;

    @Override
    public boolean insert(ShopUserRoleDTO shopUserRoleDTO) {
        ShopUserRole shopUserRole = ShopUserRoleMapStruct.INSTANCE.converToEntity(shopUserRoleDTO);
        return this.save(shopUserRole);
    }

    @Override
    public boolean updateById(Long id, ShopUserRoleDTO shopUserRoleDTO) {
        ShopUserRole shopUserRole = this.findById(id);
        ShopUserRoleMapStruct.INSTANCE.copyProperties(shopUserRoleDTO, shopUserRole);
        shopUserRole.setId(id);
        return this.updateById(shopUserRole);
    }

    @Override
    public ShopUserRole findById(Long id) {
        ShopUserRole shopUserRole = this.getById(id);
        if (ObjectUtil.isNull(shopUserRole)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopUserRole;
    }

    @Override
    public IPage<ShopUserRole> queryPage(ShopUserRoleQO shopUserRoleQO, PageReq pageReq) {
        QueryWrapper<ShopUserRole> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean associateRoleList(Long shopUserId, Long businessUserId, List<Long> roleIdList) {
        // 删除用户角色关联
        this.remove(new QueryWrapper<ShopUserRole>().eq(ShopUserRole.SHOP_USER_ID, shopUserId));

        // 2. 添加菜单关联权限数据
        List<ShopUserRole> shopUserRoleList = roleIdList.stream().map(roleId -> new ShopUserRole().setShopUserId(shopUserId).setBusinessUserId(businessUserId).setRoleId(roleId)).collect(Collectors.toList());
        return this.saveBatch(shopUserRoleList);
    }

    @Override
    public List<Role> queryRule(Long businessId, Long shopId) {
        List<ShopRole> shopRoleList = shopRoleService.list(new QueryWrapper<ShopRole>().eq(ShopRole.BUSINESS_ID, businessId).eq(ShopRole.SHOP_ID, shopId));
        if (CollectionUtil.isEmpty(shopRoleList)) {
            return new ArrayList<>();
        }
        List<Long> roleIdList = shopRoleList.stream().map(ShopRole::getId).collect(Collectors.toList());
        return roleService.list(new QueryWrapper<Role>().in(Role.ID, roleIdList).eq(Role.DATA_STATUS, DictConstant.Status.enable.getCode()));
    }

    @Override
    public Map<Long, List<String>> queryRoleNameGroupByShopUseIdList(List<Long> shopUserIdList) {
        List<ShopUserRole> shopUserRoleList = this.list(new QueryWrapper<ShopUserRole>().in(ShopUserRole.SHOP_USER_ID, shopUserIdList));

        Map<Long, List<ShopUserRole>> shopUserRoleListMap = shopUserRoleList.stream().collect(Collectors.groupingBy(ShopUserRole::getShopUserId));
        Map<Long, List<String>> resultMap = new HashMap<>(shopUserRoleListMap.size());
        shopUserRoleListMap.forEach((key, value) -> {
            List<Long> roleIdList = value.stream().map(ShopUserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleService.listByIds(roleIdList);
            resultMap.put(key, roles.stream().map(Role::getName).collect(Collectors.toList()));
        });
        return resultMap;
    }

    @Override
    public List<Long> queryAuthRule(Long busiUserId, Long shopUserId) {
        List<ShopUserRole> shopUserRoleList = this.list(new QueryWrapper<ShopUserRole>().eq(ShopUserRole.SHOP_USER_ID, shopUserId).eq(ShopUserRole.BUSINESS_USER_ID, busiUserId));
        return shopUserRoleList.stream().map(ShopUserRole::getRoleId).collect(Collectors.toList());
    }
}
