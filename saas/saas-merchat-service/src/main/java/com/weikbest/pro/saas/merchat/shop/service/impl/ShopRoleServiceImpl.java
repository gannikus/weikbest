package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopRole;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopRoleMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopRoleMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopRoleQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopRoleVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopRoleService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserRoleService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
import com.weikbest.pro.saas.sys.system.service.MenuService;
import com.weikbest.pro.saas.sys.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 店铺角色表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Service
public class ShopRoleServiceImpl extends ServiceImpl<ShopRoleMapper, ShopRole> implements ShopRoleService {

    @Resource
    private RoleService roleService;

    @Resource
    private BusiUserRelateService busiUserRelateService;

    @Resource
    private ShopUserRoleService shopUserRoleService;

    @Resource
    private ShopUserService shopUserService;

    @Resource
    private MenuService menuService;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ShopRoleDTO shopRoleDTO) {
        ShopRole shopRole = ShopRoleMapStruct.INSTANCE.converToEntity(shopRoleDTO);
        boolean save = this.save(shopRole);

        // 添加平台角色
        RoleDTO roleDTO = ShopRoleMapStruct.INSTANCE.converToRoleDTO(shopRoleDTO);
        roleDTO.setRoleType(DictConstant.RoleType.merchat.getCode());
        roleDTO.setDataStatus(DictConstant.Status.enable.getCode());
        roleDTO.setIsPreset(DictConstant.Whether.no.getCode());
        roleService.insert(shopRole.getId(), roleDTO);

        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ShopRoleDTO shopRoleDTO) {
        ShopRole shopRole = this.findById(id);
        ShopRoleMapStruct.INSTANCE.copyProperties(shopRoleDTO, shopRole);
        shopRole.setId(id);
        boolean update = this.updateById(shopRole);

        // 更新平台角色
        RoleDTO roleDTO = ShopRoleMapStruct.INSTANCE.converToRoleDTO(shopRoleDTO);
        roleDTO.setRoleType(DictConstant.RoleType.merchat.getCode());
        roleDTO.setDataStatus(DictConstant.Status.enable.getCode());
        roleDTO.setIsPreset(DictConstant.Whether.no.getCode());
        roleService.updateById(id, roleDTO);
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        boolean delete = this.removeBatchByIds(ids);

        // 删除角色表
        roleService.deleteBatchByIds(ids);

        // 删除用户角色关联表
        shopUserRoleService.remove(new QueryWrapper<ShopUserRole>().in(ShopUserRole.ROLE_ID, ids));

        return delete;
    }

    @Override
    public ShopRole findById(Long id) {
        ShopRole shopRole = this.getById(id);
        if (ObjectUtil.isNull(shopRole)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopRole;
    }

    @Override
    public ShopRoleVO findVOById(Long id) {
        ShopRole shopRole = this.findById(id);
        Role role = roleService.findById(id);
        return ShopRoleMapStruct.INSTANCE.converToVO(shopRole, role);
    }

    @Override
    public IPage<ShopRoleVO> queryPage(ShopRoleQO shopRoleQO, PageReq pageReq) {
        return this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), shopRoleQO);
    }

    @Override
    public List<ShopRoleVO> queryAllRole(Long businessId, Long shopId) {
        List<ShopRoleVO> shopRoleVOList = this.baseMapper.queryAllRole(businessId, shopId);
        return shopRoleVOList;
    }

    @Override
    public List<MenuVO> queryAuth(Long businessId, Long shopId, Long busiUserId) {

        BusiUserRelate busiUserRelate = busiUserRelateService.getByBusinessIdAndBusinessUserId(businessId, busiUserId);
        if(StrUtil.equals(busiUserRelate.getIsMainUser(), DictConstant.Whether.yes.getCode())) {
            // 主账户拥有所有菜单权限
            return menuService.queryAuthMenuByType(DictConstant.MenuType.merchat.getCode());
        }


        // 非主账户查询菜单权限
        Long shopUserId = shopUserService.findId(businessId, shopId, busiUserId);
        List<Long> roleIdList = shopUserRoleService.queryAuthRule(busiUserId, shopUserId);
        if(CollectionUtil.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        return menuService.queryAuthMenuByRoleIdList(roleIdList);

    }
}
