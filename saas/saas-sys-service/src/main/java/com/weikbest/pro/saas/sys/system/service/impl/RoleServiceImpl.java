package com.weikbest.pro.saas.sys.system.service.impl;

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
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.entity.RoleMenu;
import com.weikbest.pro.saas.sys.system.entity.UserRole;
import com.weikbest.pro.saas.sys.system.mapper.RoleMapper;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.RoleMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.RoleQO;
import com.weikbest.pro.saas.sys.system.service.RoleMenuService;
import com.weikbest.pro.saas.sys.system.service.RoleService;
import com.weikbest.pro.saas.sys.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private CodeRuleService codeRuleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(RoleDTO roleDTO) {
        Long id = GenerateIDUtil.nextId();
        return this.insert(id, roleDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(Long id, RoleDTO roleDTO) {
        Role role = RoleMapStruct.INSTANCE.converToEntity(roleDTO);
        String nextNum = codeRuleService.nextNum(CodeRuleConstant.T_SYS_ROLE);
        role.setNumber(nextNum);
        role.setId(id);
        return this.save(role);
    }

    @Override
    public boolean updateById(Long id, RoleDTO roleDTO) {
        Role role = this.findById(id);
        RoleMapStruct.INSTANCE.copyProperties(roleDTO, role);
        role.setId(id);
        return this.updateById(role);
    }

    @Override
    public Role findById(Long id) {
        Role role = this.getById(id);
        if (ObjectUtil.isNull(role)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return role;
    }

    @Override
    public IPage<Role> queryPage(RoleQO roleQO, PageReq pageReq) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(roleQO.getNumber())) {
            wrapper.eq(Role.NUMBER, roleQO.getNumber());
        }
        if (StrUtil.isNotBlank(roleQO.getName())) {
            wrapper.eq(Role.NAME, roleQO.getName());
        }
        if (StrUtil.isNotBlank(roleQO.getIsPreset())) {
            wrapper.eq(Role.IS_PRESET, roleQO.getIsPreset());
        }
        if (StrUtil.isNotBlank(roleQO.getRoleType())) {
            wrapper.eq(Role.ROLE_TYPE, roleQO.getRoleType());
        }
        if (StrUtil.isNotBlank(roleQO.getDescription())) {
            wrapper.eq(Role.DESCRIPTION, roleQO.getDescription());
        }
        if (StrUtil.isNotBlank(roleQO.getDataStatus())) {
            wrapper.eq(Role.DATA_STATUS, roleQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<String> queryFromRuleMenu(Long menuId) {
        List<RoleMenu> roleMenuList = roleMenuService.list(new QueryWrapper<RoleMenu>().eq(RoleMenu.MENU_ID, menuId));
        if (CollectionUtil.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }

        List<Long> roleIdList = roleMenuList.stream().map(RoleMenu::getRoleId).collect(Collectors.toList());
        List<Role> roleList = this.listByIds(roleIdList);
        if (CollectionUtil.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        // 返回角色名称
        return roleList.stream().map(Role::getName).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> idList) {

        // 删除角色
        this.removeBatchByIds(idList);

        // 删除角色菜单关联
        roleMenuService.remove(new QueryWrapper<RoleMenu>().in(RoleMenu.ROLE_ID, idList));

        // 删除用户角色关联
        userRoleService.remove(new QueryWrapper<UserRole>().in(UserRole.ROLE_ID, idList));
    }

    @Override
    public boolean associateUserList(Long roleId, List<Long> userIdList) {
        // 更新用户角色关联
        return userRoleService.associateUserList(roleId, userIdList);
    }
}
