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
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiUserMapStruct;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUserRole;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopUserMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserSaveDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopUserMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopUserRoleMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserRoleVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserRoleService;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
import com.weikbest.pro.saas.sys.system.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 商户店铺账号表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ShopUserServiceImpl extends ServiceImpl<ShopUserMapper, ShopUser> implements ShopUserService {

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private ShopUserRoleService shopUserRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ShopUserSaveDTO shopUserSaveDTO) {
        // 1. 根据商户ID+手机号查询商户表中是否存在该商户
        Long businessUserId;
        BusiUser busiUser = busiUserService.getByBusinessIdAndPhone(shopUserSaveDTO.getBusinessId(), shopUserSaveDTO.getPhone());
        if (ObjectUtil.isEmpty(busiUser)) {
            // 将商户账户与新的商户ID关联
            BusiUserDTO busiUserDTO = ShopUserMapStruct.INSTANCE.converToBusiUserDTO(shopUserSaveDTO);
            businessUserId = busiUserService.insertSubReturnId(busiUserDTO, shopUserSaveDTO.getBusinessId());
        } else {
            businessUserId = busiUser.getId();
        }
        // 2.创建店铺账号
        ShopUser shopUser = ShopUserMapStruct.INSTANCE.converToEntity(shopUserSaveDTO, businessUserId);
        boolean save = this.save(shopUser);

        // 3. 关联店铺账号角色
        shopUserRoleService.associateRoleList(shopUser.getId(), shopUser.getBusinessUserId(), shopUserSaveDTO.getRoleIdList());
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ShopUserSaveDTO shopUserSaveDTO) {
        // 1.更新店铺账号表
        ShopUser shopUser = this.findById(id);
        ShopUserMapStruct.INSTANCE.copyProperties(shopUserSaveDTO, shopUser);
        shopUser.setId(id);
        boolean update = this.updateById(shopUser);

        // 2.更新商户账号表
        BusiUser busiUser = busiUserService.findById(shopUser.getBusinessUserId());
        busiUser.setName(shopUserSaveDTO.getName());
        busiUser.setPhone(shopUserSaveDTO.getPhone());
        BusiUserDTO busiUserDTO = BusiUserMapStruct.INSTANCE.converToDTO(busiUser);
        busiUserService.updateById(busiUser.getId(), busiUserDTO);

        // 3.关联店铺账号角色
        shopUserRoleService.associateRoleList(shopUser.getId(), shopUser.getBusinessUserId(), shopUserSaveDTO.getRoleIdList());

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDataStatusById(Long id, String dataStatus) {
        // 1.更新店铺账号表
        ShopUser shopUser = this.findById(id);
        shopUser.setDataStatus(dataStatus);
        return this.updateById(shopUser);
    }

    @Override
    public boolean associateRoleList(ShopUserDTO shopUserDTO, List<Long> roleIdList) {
        // 查询商户店铺账号ID
        ShopUser shopUser = findShopUser(shopUserDTO.getBusinessId(), shopUserDTO.getBusinessUserId(), shopUserDTO.getShopId());

        // 商户店铺账号关联角色
        return shopUserRoleService.associateRoleList(shopUser.getId(), shopUser.getBusinessUserId(), roleIdList);
    }

    private ShopUser findShopUser(Long businessId, Long businessUserId, Long shopId) {
        ShopUser shopUser = this.getOne(new QueryWrapper<ShopUser>().eq(ShopUser.BUSINESS_ID, businessId)
                .eq(ShopUser.BUSINESS_USER_ID, businessUserId).eq(ShopUser.SHOP_ID, shopId));
        if (ObjectUtil.isEmpty(shopUser)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopUser;
    }

    @Override
    public List<ShopUserRoleVO> queryShopUserRole(ShopUserDTO shopUserDTO) {
        // 查询商户店铺账号ID
        ShopUser shopUser = findShopUser(shopUserDTO.getBusinessId(), shopUserDTO.getBusinessUserId(), shopUserDTO.getShopId());

        // 查询全部商户角色
        List<Role> roleList = shopUserRoleService.queryRule(shopUserDTO.getBusinessId(), shopUserDTO.getShopId());

        // 查询商户店铺账号关联的角色
        List<ShopUserRole> shopUserRoleList = shopUserRoleService.list(new QueryWrapper<ShopUserRole>().eq(ShopUserRole.SHOP_USER_ID, shopUser.getId()));
        Set<Long> userRoleIdSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(shopUserRoleList)) {
            userRoleIdSet.addAll(shopUserRoleList.stream().map(ShopUserRole::getRoleId).collect(Collectors.toSet()));
        }

        // 返回用户关联角色
        return roleList.stream().map(role -> {
            ShopUserRoleVO shopUserRoleVO = ShopUserRoleMapStruct.INSTANCE.converRoleToShopUserRoleVO(role, shopUser.getId(), shopUser.getBusinessUserId());
            shopUserRoleVO.setChecked(userRoleIdSet.contains(role.getId()));
            return shopUserRoleVO;
        }).collect(Collectors.toList());
    }

    @Override
    public ShopUser findById(Long id) {
        ShopUser shopUser = this.getById(id);
        if (ObjectUtil.isNull(shopUser)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopUser;
    }

    @Override
    public ShopUserDetailVO findDetailById(Long id) {
        ShopUser shopUser = this.findById(id);
        ShopUserDetailVO shopUserDetailVO = ShopUserMapStruct.INSTANCE.converToDetailVO(shopUser);

        // 商户店铺账号关联的商户账号
        BusiUser busiUser = busiUserService.findById(shopUserDetailVO.getBusinessUserId());
        shopUserDetailVO.setName(busiUser.getName());
        shopUserDetailVO.setPhone(busiUser.getPhone());

        // 查询商户店铺账号关联的角色
        List<ShopUserRole> shopUserRoleList = shopUserRoleService.list(new QueryWrapper<ShopUserRole>().eq(ShopUserRole.SHOP_USER_ID, shopUser.getId()));
        if (CollectionUtil.isNotEmpty(shopUserRoleList)) {
            shopUserDetailVO.setRoleIdList(shopUserRoleList.stream().map(shopUserRole -> String.valueOf(shopUserRole.getRoleId())).collect(Collectors.toList()));
        }

        return shopUserDetailVO;
    }

    @Override
    public List<ShopUser> queryByBusinessUserId(Long businessUserId) {
        return this.list(new QueryWrapper<ShopUser>().eq(ShopUser.BUSINESS_USER_ID, businessUserId));
    }

    @Override
    public Long findId(Long businessId, Long shopId, Long busiUserId) {
        ShopUser shopUser = this.getOne(new QueryWrapper<ShopUser>().eq(ShopUser.BUSINESS_USER_ID, busiUserId).eq(ShopUser.BUSINESS_ID, businessId).eq(ShopUser.SHOP_ID, shopId));
        if (ObjectUtil.isNull(shopUser)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shopUser.getId();
    }

    @Override
    public IPage<ShopUserListVO> queryPage(ShopUserQO shopUserQO, PageReq pageReq) {
        IPage<ShopUserListVO> shopUserListVOPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), shopUserQO);
        if (CollectionUtil.isNotEmpty(shopUserListVOPage.getRecords())) {
            List<ShopUserListVO> shopUserListVOList = shopUserListVOPage.getRecords();
            // 查询关联的角色
            List<Long> shopUserIdList = shopUserListVOList.stream().map(ShopUserListVO::getId).collect(Collectors.toList());
            Map<Long, List<String>> shopUserRoleNameListMap = shopUserRoleService.queryRoleNameGroupByShopUseIdList(shopUserIdList);
            for (ShopUserListVO shopUserListVO : shopUserListVOList) {
                Long shopUserId = shopUserListVO.getId();
                List<String> roleNameList = shopUserRoleNameListMap.get(shopUserId);
                shopUserListVO.setRoleNameList(roleNameList);
            }
        }
        return shopUserListVOPage;
    }
}
