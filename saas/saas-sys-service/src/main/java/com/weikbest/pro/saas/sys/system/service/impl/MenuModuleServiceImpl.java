package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.*;
import com.weikbest.pro.saas.sys.system.mapper.MenuModuleMapper;
import com.weikbest.pro.saas.sys.system.module.dto.MenuModuleDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.MenuModuleMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.MenuModuleQO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuModuleVO;
import com.weikbest.pro.saas.sys.system.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统模块表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class MenuModuleServiceImpl extends ServiceImpl<MenuModuleMapper, MenuModule> implements MenuModuleService {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public boolean insert(MenuModuleDTO menuModuleDTO) {
        MenuModule menuModule = MenuModuleMapStruct.INSTANCE.converToEntity(menuModuleDTO);
        return this.save(menuModule);
    }

    @Override
    public boolean updateById(Long id, MenuModuleDTO menuModuleDTO) {
        MenuModule menuModule = this.findById(id);
        MenuModuleMapStruct.INSTANCE.copyProperties(menuModuleDTO, menuModule);
        menuModule.setId(id);
        return this.updateById(menuModule);
    }

    @Override
    public MenuModule findById(Long id) {
        MenuModule menuModule = this.getById(id);
        if (ObjectUtil.isNull(menuModule)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return menuModule;
    }

    @Override
    public IPage<MenuModule> queryPage(MenuModuleQO menuModuleQO, PageReq pageReq) {
        QueryWrapper<MenuModule> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(menuModuleQO.getNumber())) {
            wrapper.eq(MenuModule.NUMBER, menuModuleQO.getNumber());
        }
        if (StrUtil.isNotBlank(menuModuleQO.getName())) {
            wrapper.eq(MenuModule.NAME, menuModuleQO.getName());
        }
        if (StrUtil.isNotBlank(menuModuleQO.getModuleType())) {
            wrapper.eq(MenuModule.MODULE_TYPE, menuModuleQO.getModuleType());
        }
        if (StrUtil.isNotBlank(menuModuleQO.getIconClass())) {
            wrapper.eq(MenuModule.ICON_CLASS, menuModuleQO.getIconClass());
        }
        if (StrUtil.isNotBlank(menuModuleQO.getDescription())) {
            wrapper.eq(MenuModule.DESCRIPTION, menuModuleQO.getDescription());
        }
        if (StrUtil.isNotBlank(menuModuleQO.getDataStatus())) {
            wrapper.eq(MenuModule.DATA_STATUS, menuModuleQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<DictEntry> queryDictByType(String type) {
        QueryWrapper<MenuModule> wrapper = new QueryWrapper<MenuModule>().eq(MenuModule.DATA_STATUS, DictConstant.Status.enable.getCode());
        if (StrUtil.isNotBlank(type)) {
            wrapper.eq(MenuModule.MODULE_TYPE, type);
        }

        List<MenuModule> menuModuleList = this.list(wrapper);
        if (CollectionUtil.isEmpty(menuModuleList)) {
            return new ArrayList<>();
        }

        // 返回数据字典
        return menuModuleList.stream().map(MenuModuleMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }

    public List<MenuModuleVO> queryByType(String type) {
        QueryWrapper<MenuModule> wrapper = new QueryWrapper<MenuModule>().eq(MenuModule.DATA_STATUS, DictConstant.Status.enable.getCode());
        if (StrUtil.isNotBlank(type)) {
            wrapper.eq(MenuModule.MODULE_TYPE, type);
        }

        List<MenuModule> menuModuleList = this.list(wrapper);
        if (CollectionUtil.isEmpty(menuModuleList)) {
            return new ArrayList<>();
        }

        // 返回数据字典
        return menuModuleList.stream().map(MenuModuleMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> idList) {

        // 查询模块对应的全部菜单ID
        List<Menu> list = menuService.list(new QueryWrapper<Menu>().eq(Menu.MENU_MODULE_ID, idList).select(Menu.ID, Menu.MENU_MODULE_ID));

        // 删除模块
        this.removeBatchByIds(idList);

        // 删除菜单
        if (CollectionUtil.isNotEmpty(list)) {
            menuService.deleteBatchByIds(list.stream().map(Menu::getId).collect(Collectors.toList()));
        }
    }

    @Override
    public List<MenuModuleVO> queryAuthModuleByTokenUser(TokenUser tokenUser, String moduleType) {
        Long id = tokenUser.getUserId();
        User user = userService.findById(id);

        if (ObjectUtil.equal(WeikbestConstant.SUPERID, user.getId()) || StrUtil.equals(user.getIsSuper(), DictConstant.Whether.yes.getCode())) {
            // 超级管理员或者是有管理员标记的人查看全部模块
            return queryByType(moduleType);
        }

        // 普通用户查询有权限的模块
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq(UserRole.USER_ID, user.getId()));
        if (CollectionUtil.isEmpty(userRoleList)) {
            return new ArrayList<>();
        }
        List<RoleMenu> roleMenuList = roleMenuService.list(new QueryWrapper<RoleMenu>().in(RoleMenu.ROLE_ID, userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList())));
        if (CollectionUtil.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }
        List<MenuModule> menuModuleList = this.listByIds(roleMenuList.stream().map(RoleMenu::getMenuModuleId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(menuModuleList)) {
            return new ArrayList<>();
        }
        return menuModuleList.stream().map(MenuModuleMapStruct.INSTANCE::converToVO).collect(Collectors.toList());


    }
}
