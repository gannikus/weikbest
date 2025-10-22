package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.system.entity.UserRole;
import com.weikbest.pro.saas.sys.system.mapper.UserRoleMapper;
import com.weikbest.pro.saas.sys.system.module.dto.UserRoleDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.UserRoleMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.UserRoleQO;
import com.weikbest.pro.saas.sys.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户角色关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public boolean insert(UserRoleDTO userRoleDTO) {
        UserRole userRole = UserRoleMapStruct.INSTANCE.converToEntity(userRoleDTO);
        return this.save(userRole);
    }

    @Override
    public boolean updateById(Long id, UserRoleDTO userRoleDTO) {
        UserRole userRole = this.findById(id);
        UserRoleMapStruct.INSTANCE.copyProperties(userRoleDTO, userRole);
        userRole.setId(id);
        return this.updateById(userRole);
    }

    @Override
    public UserRole findById(Long id) {
        UserRole userRole = this.getById(id);
        if (ObjectUtil.isNull(userRole)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return userRole;
    }

    @Override
    public IPage<UserRole> queryPage(UserRoleQO userRoleQO, PageReq pageReq) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean associateUserList(Long roleId, List<Long> userIdList) {
        // 删除用户角色关联
        this.remove(new QueryWrapper<UserRole>().eq(UserRole.ROLE_ID, roleId));

        // 2. 添加菜单关联权限数据
        List<UserRole> userRoleList = userIdList.stream().map(userId -> new UserRole().setUserId(userId).setRoleId(roleId)).collect(Collectors.toList());
        return this.saveBatch(userRoleList);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean associateRoleList(Long userId, List<Long> roleIdList) {
        // 删除用户角色关联
        this.remove(new QueryWrapper<UserRole>().eq(UserRole.USER_ID, userId));

        // 2. 添加菜单关联权限数据
        List<UserRole> userRoleList = roleIdList.stream().map(roleId -> new UserRole().setUserId(userId).setRoleId(roleId)).collect(Collectors.toList());
        return this.saveBatch(userRoleList);
    }
}
