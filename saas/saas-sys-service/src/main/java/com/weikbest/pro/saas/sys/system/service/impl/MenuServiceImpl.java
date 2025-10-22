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
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.*;
import com.weikbest.pro.saas.sys.system.mapper.MenuMapper;
import com.weikbest.pro.saas.sys.system.module.dto.MenuDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.MenuMapStruct;
import com.weikbest.pro.saas.sys.system.module.mapstruct.MenuPermMapStruct;
import com.weikbest.pro.saas.sys.system.module.mapstruct.PermMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.MenuQO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuPermVO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
import com.weikbest.pro.saas.sys.system.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private MenuPermService menuPermService;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermService permService;

    @Override
    public boolean insert(MenuDTO menuDTO) {
        Menu menu = MenuMapStruct.INSTANCE.converToEntity(menuDTO);
        return this.save(menu);
    }

    @Override
    public boolean updateById(Long id, MenuDTO menuDTO) {
        Menu menu = this.findById(id);
        MenuMapStruct.INSTANCE.copyProperties(menuDTO, menu);
        menu.setId(id);
        return this.updateById(menu);
    }

    @Override
    public Menu findById(Long id) {
        Menu menu = this.getById(id);
        if (ObjectUtil.isNull(menu)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return menu;
    }

    @Override
    public IPage<Menu> queryPage(MenuQO menuQO, PageReq pageReq) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(menuQO.getMenuModuleId())) {
            wrapper.eq(Menu.MENU_MODULE_ID, menuQO.getMenuModuleId());
        }
        if (StrUtil.isNotBlank(menuQO.getName())) {
            wrapper.like(Menu.NAME, menuQO.getName());
        }
        if (ObjectUtil.isNotNull(menuQO.getParentId())) {
            wrapper.eq(Menu.PARENT_ID, menuQO.getParentId());
        }
        if (StrUtil.isNotBlank(menuQO.getTips())) {
            wrapper.like(Menu.TIPS, menuQO.getTips());
        }
        if (StrUtil.isNotBlank(menuQO.getUrl())) {
            wrapper.eq(Menu.URL, menuQO.getUrl());
        }
        if (ObjectUtil.isNotNull(menuQO.getMenuLevel())) {
            wrapper.eq(Menu.MENU_LEVEL, menuQO.getMenuLevel());
        }
        if (ObjectUtil.isNotNull(menuQO.getMenuOrd())) {
            wrapper.eq(Menu.MENU_ORD, menuQO.getMenuOrd());
        }
        if (StrUtil.isNotBlank(menuQO.getMenuType())) {
            wrapper.eq(Menu.MENU_TYPE, menuQO.getMenuType());
        }
        if (StrUtil.isNotBlank(menuQO.getIconClass())) {
            wrapper.eq(Menu.ICON_CLASS, menuQO.getIconClass());
        }
        if (StrUtil.isNotBlank(menuQO.getDescription())) {
            wrapper.eq(Menu.DESCRIPTION, menuQO.getDescription());
        }
        if (StrUtil.isNotBlank(menuQO.getDataStatus())) {
            wrapper.eq(Menu.DATA_STATUS, menuQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> idList) {

        // 删除菜单
        this.removeBatchByIds(idList);

        // 删除角色菜单关联
        roleMenuService.remove(new QueryWrapper<RoleMenu>().in(RoleMenu.MENU_ID, idList));

        // 删除菜单权限关联
        menuPermService.remove(new QueryWrapper<MenuPerm>().in(MenuPerm.MENU_ID, idList));

    }

    @Override
    public void queryAllSubIdByParentId(Long parentId, List<Long> subIds) {
        // 添加ID到集合中
        subIds.add(parentId);
        List<Menu> menuList = this.baseMapper.selectList(new QueryWrapper<Menu>().eq(Menu.PARENT_ID, parentId));
        if (CollectionUtil.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                Long subParentId = menu.getId();
                queryAllSubIdByParentId(subParentId, subIds);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAssociatePermList(Long id, List<Long> permIdList) {

        // 1. 更新系统菜单权限项关联表
        boolean save = menuPermService.saveAssociatePermList(id, permIdList);

        // 2. 更新系统角色模块菜单权限项关联表
        // 如果现在已有角色关联了菜单下不存在的权限项，则将这个权限项删掉
        roleMenuService.refreshAssociatePermList(id, permIdList);

        return save;
    }

    @Override
    public List<String> queryFromRuleMenu(Long id) {
        return roleService.queryFromRuleMenu(id);
    }

    @Override
    public List<MenuPermVO> queryMenuPerm(Long id) {
        // 查询所有权限项
        List<Perm> permList = permService.list();
        if (CollectionUtil.isEmpty(permList)) {
            return new ArrayList<>();
        }

        // 查询菜单关联的权限项
        List<MenuPerm> menuPermList = menuPermService.list(new QueryWrapper<MenuPerm>().eq(MenuPerm.MENU_ID, id));
        Set<Long> menuPermPermIdSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(menuPermList)) {
            menuPermPermIdSet.addAll(menuPermList.stream().map(MenuPerm::getPermId).collect(Collectors.toSet()));
        }

        // 返回菜单关联权限项
        return permList.stream().map(perm -> {
            MenuPermVO menuPermVO = PermMapStruct.INSTANCE.converToMenuPermVO(perm, id);
            menuPermVO.setChecked(menuPermPermIdSet.contains(perm.getId()));
            return menuPermVO;
        }).collect(Collectors.toList());
    }

    public Map<Long, List<MenuPermVO>> queryMenuPerm(List<Long> menuIdList) {
        Map<Long, List<MenuPermVO>> resultMap = new HashMap<>(WeikbestConstant.HASHMAP_INITIALCAPACITY);

        // 查询所有权限项
        List<Perm> permList = permService.list();
        if (CollectionUtil.isEmpty(permList)) {
            return resultMap;
        }

        // 查询菜单关联的权限项
        List<MenuPerm> menuPermList = menuPermService.list(new QueryWrapper<MenuPerm>().in(MenuPerm.MENU_ID, menuIdList));
        if (CollectionUtil.isEmpty(menuPermList)) {
            for (Long menuId : menuIdList) {
                List<MenuPermVO> menuPermVOList = permList.stream().map(perm -> PermMapStruct.INSTANCE.converToMenuPermVO(perm, menuId)).collect(Collectors.toList());
                resultMap.put(menuId, menuPermVOList);
            }
            return resultMap;
        }

        Map<Long, List<MenuPerm>> menuPermListMap = menuPermList.stream().collect(Collectors.groupingBy(MenuPerm::getMenuId));
        for (Long menuId : menuIdList) {
            List<MenuPerm> menuPerms = menuPermListMap.get(menuId);
            if (CollectionUtil.isEmpty(menuPerms)) {
                List<MenuPermVO> menuPermVOList = permList.stream().map(perm -> PermMapStruct.INSTANCE.converToMenuPermVO(perm, menuId)).collect(Collectors.toList());
                resultMap.put(menuId, menuPermVOList);
            }
            else {
                Set<Long> menuPermPermIdSet = menuPerms.stream().map(MenuPerm::getPermId).collect(Collectors.toSet());
                List<MenuPermVO> menuPermVOList = permList.stream().map(perm -> {
                    MenuPermVO menuPermVO = PermMapStruct.INSTANCE.converToMenuPermVO(perm, menuId);
                    menuPermVO.setChecked(menuPermPermIdSet.contains(perm.getId()));
                    return menuPermVO;
                }).collect(Collectors.toList());
                resultMap.put(menuId, menuPermVOList);
            }
        }

        // 返回菜单关联权限项
        return resultMap;
    }

    @Override
    public List<MenuVO> queryByModuleId(Long moduleId) {
        List<Menu> menuList = this.list(new QueryWrapper<Menu>().eq(Menu.MENU_MODULE_ID, moduleId).eq(Menu.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        return converToMenuVoList(menuList);
    }

    private List<MenuVO> converToMenuVoList(List<Menu> menuList) {
        List<Long> menuIdList = menuList.stream().map(Menu::getId).collect(Collectors.toList());
        Map<Long, List<MenuPermVO>> menuPermListMap = queryMenuPerm(menuIdList);
        return menuList.stream().map(menu -> {
            MenuVO menuVO = MenuMapStruct.INSTANCE.converToVO(menu);
            List<MenuPermVO> menuPermVOList = menuPermListMap.get(menu.getId());
            menuVO.setMenuPermVOList(menuPermVOList);
            return menuVO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MenuVO> queryByType(String type) {
        List<Menu> menuList = this.list(new QueryWrapper<Menu>().eq(Menu.MENU_TYPE, type).eq(Menu.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        return converToMenuVoList(menuList);
    }


    @Override
    public List<MenuVO> queryAuthMenuByTokenUser(TokenUser tokenUser, Long moduleId) {
        Long id = tokenUser.getUserId();
        User user = userService.findById(id);

        if (ObjectUtil.equal(WeikbestConstant.SUPERID, user.getId()) || StrUtil.equals(user.getIsSuper(), DictConstant.Whether.yes.getCode())) {
            // 超级管理员或者是有管理员标记的人查看全部菜单
            return queryByModuleId(moduleId);
        }

        // 普通用户查询有权限的菜单
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq(UserRole.USER_ID, user.getId()));
        if (CollectionUtil.isEmpty(userRoleList)) {
            return new ArrayList<>();
        }
        List<RoleMenu> roleMenuList = roleMenuService.list(new QueryWrapper<RoleMenu>().in(RoleMenu.ROLE_ID, userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList())));
        if (CollectionUtil.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }
        Map<Long, List<RoleMenu>> menuIdGroupRoleMenuMap = roleMenuList.stream().collect(Collectors.groupingBy(RoleMenu::getMenuId));

        List<Menu> menuList = this.listByIds(roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<MenuVO> menuVOList = menuList.stream().map(MenuMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
        queryMenuPermVOList(menuIdGroupRoleMenuMap, menuVOList);
        return menuVOList;
    }

    @Override
    public List<MenuVO> queryAuthMenuByRoleIdList(List<Long> roleIdList) {
        // 查询关联信息
        List<RoleMenu> roleMenuList = roleMenuService.list(new QueryWrapper<RoleMenu>().in(RoleMenu.ROLE_ID, roleIdList));
        if (CollectionUtil.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }
        Map<Long, List<RoleMenu>> menuIdGroupRoleMenuMap = roleMenuList.stream().collect(Collectors.groupingBy(RoleMenu::getMenuId));

        List<Menu> menuList = this.list(new QueryWrapper<Menu>().in(Menu.ID, menuIdGroupRoleMenuMap.keySet()).eq(Menu.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<MenuVO> menuVOList = menuList.stream().map(MenuMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
        queryMenuPermVOList(menuIdGroupRoleMenuMap, menuVOList);
        return menuVOList;
    }

    @Override
    public List<MenuVO> queryAuthMenuByType(String type) {
        List<Menu> menuList = this.list(new QueryWrapper<Menu>().eq(Menu.MENU_TYPE, type).eq(Menu.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        List<MenuVO> menuVOList = menuList.stream().map(MenuMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
        menuVOList.forEach(menuVO -> {
            Long menuId = menuVO.getId();
            List<MenuPerm> menuPermList = menuPermService.queryByMenuId(menuId);
            if(CollectionUtil.isNotEmpty(menuPermList)) {
                Map<Long, Perm> menuPermInfoMap = permService.listByIds(menuPermList.stream().map(MenuPerm::getPermId).collect(Collectors.toList())).stream()
                        .collect(Collectors.toMap(Perm::getId, perm -> perm));

                // 转换
                List<MenuPermVO> menuPermVOList = menuPermList.stream().map(menuPerm -> {
                    MenuPermVO menuPermVO = MenuPermMapStruct.INSTANCE.converToVO(menuPerm);
                    Perm perm = menuPermInfoMap.get(menuPerm.getPermId());
                    menuPermVO.setPermNumber(perm.getNumber());
                    menuPermVO.setPermName(perm.getName());
                    menuPermVO.setChecked(true);
                    return menuPermVO;
                }).collect(Collectors.toList());

                menuVO.setMenuPermVOList(menuPermVOList);
            }
        });
        return menuVOList;
    }

    /**
     * 获取菜单关联的权限项数据
     *
     * @param menuIdGroupRoleMenuMap
     * @param menuVOList
     */
    private void queryMenuPermVOList(Map<Long, List<RoleMenu>> menuIdGroupRoleMenuMap, List<MenuVO> menuVOList) {
        menuVOList.forEach(menuVO -> {
            Long menuId = menuVO.getId();
            List<RoleMenu> roleMenus = menuIdGroupRoleMenuMap.get(menuId);
            List<Long> permIdList = roleMenus.stream().map(RoleMenu::getPermId).collect(Collectors.toList());
            // 选中的权限项信息
            List<Perm> permList = permService.listByIds(permIdList);
            Map<Long, Perm> permMap = permList.stream().collect(Collectors.toMap(Perm::getId, perm -> perm));

            List<MenuPerm> menuPermList = menuPermService.queryByMenuId(menuId);
            if(CollectionUtil.isNotEmpty(menuPermList)) {
                Map<Long, Perm> menuPermInfoMap = permService.listByIds(menuPermList.stream().map(MenuPerm::getPermId).collect(Collectors.toList())).stream()
                        .collect(Collectors.toMap(Perm::getId, perm -> perm));

                // 转换
                List<MenuPermVO> menuPermVOList = menuPermList.stream().map(menuPerm -> {
                    MenuPermVO menuPermVO = MenuPermMapStruct.INSTANCE.converToVO(menuPerm);
                    Perm perm = menuPermInfoMap.get(menuPerm.getPermId());
                    menuPermVO.setPermNumber(perm.getNumber());
                    menuPermVO.setPermName(perm.getName());

                    // 匹配
                    if (ObjectUtil.isNotNull(permMap.get(menuPerm.getPermId()))) {
                        menuPermVO.setChecked(true);

                    }
                    return menuPermVO;
                }).collect(Collectors.toList());

                menuVO.setMenuPermVOList(menuPermVOList);
            }
        });
    }
}
