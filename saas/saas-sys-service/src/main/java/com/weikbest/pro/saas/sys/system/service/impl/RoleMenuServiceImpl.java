package com.weikbest.pro.saas.sys.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
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
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.DtreeUtil;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.*;
import com.weikbest.pro.saas.sys.system.mapper.RoleMenuMapper;
import com.weikbest.pro.saas.sys.system.module.dto.RoleMenuDTO;
import com.weikbest.pro.saas.sys.system.module.mapstruct.RoleMenuMapStruct;
import com.weikbest.pro.saas.sys.system.module.qo.RoleMenuQO;
import com.weikbest.pro.saas.sys.system.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色菜单关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private MenuModuleService menuModuleService;

    @Resource
    private MenuService menuService;

    @Resource
    private MenuPermService menuPermService;

    @Resource
    private PermService permService;

    @Override
    public boolean insert(RoleMenuDTO roleMenuDTO) {
        RoleMenu roleMenu = RoleMenuMapStruct.INSTANCE.converToEntity(roleMenuDTO);
        return this.save(roleMenu);
    }

    @Override
    public boolean updateById(Long id, RoleMenuDTO roleMenuDTO) {
        RoleMenu roleMenu = this.findById(id);
        RoleMenuMapStruct.INSTANCE.copyProperties(roleMenuDTO, roleMenu);
        roleMenu.setId(id);
        return this.updateById(roleMenu);
    }

    @Override
    public RoleMenu findById(Long id) {
        RoleMenu roleMenu = this.getById(id);
        if (ObjectUtil.isNull(roleMenu)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return roleMenu;
    }

    @Override
    public IPage<RoleMenu> queryPage(RoleMenuQO roleMenuQO, PageReq pageReq) {
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refreshAssociatePermList(Long menuId, List<Long> permIdList) {
        List<RoleMenu> roleMenus = this.list(new QueryWrapper<RoleMenu>().eq(RoleMenu.MENU_ID, menuId));
        if (CollectionUtil.isEmpty(roleMenus)) {
            return;
        }

        // 过滤出不在最新菜单权限项中的权限数据
        List<RoleMenu> notpermList = roleMenus.stream().filter(roleMenu -> !permIdList.contains(roleMenu.getPermId())).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(notpermList)) {
            return;
        }
        // 删除这些数据
        this.removeBatchByIds(notpermList.stream().map(RoleMenu::getId).collect(Collectors.toList()));

    }

    @Override
    public List<Dtree> queryAuthRoleMenuPerm(Long roleId) {
        Role role = roleService.findById(roleId);

        // 根据角色类型（也就是菜单类型），查询全部模块、全部菜单和所有菜单关联的权限项
        List<MenuModule> menuModuleList = menuModuleService.list(new QueryWrapper<MenuModule>().eq(MenuModule.MODULE_TYPE, role.getRoleType()).eq(MenuModule.DATA_STATUS, DictConstant.Status.enable.getCode()));
        List<Menu> menuList = menuService.list(new QueryWrapper<Menu>().eq(Menu.MENU_TYPE, role.getRoleType()).eq(Menu.DATA_STATUS, DictConstant.Status.enable.getCode()));


        // 查询角色模块菜单权限项关联
        List<RoleMenu> roleMenuList = this.list(new QueryWrapper<RoleMenu>().eq(RoleMenu.ROLE_ID, roleId));
        Set<Long> hasModuleIdSet = new HashSet<>();
        Map<Long, Set<Long>> hasMenuIdAndHasPermIdMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(roleMenuList)) {
            roleMenuList.forEach(roleMenu -> {
                hasModuleIdSet.add(roleMenu.getMenuModuleId());
                Set<Long> hasPermIdSet = hasMenuIdAndHasPermIdMap.get(roleMenu.getMenuId());
                if(CollectionUtil.isEmpty(hasPermIdSet)) {
                    hasPermIdSet = new HashSet<>();
                }
                hasPermIdSet.add(roleMenu.getPermId());
                hasMenuIdAndHasPermIdMap.put(roleMenu.getMenuId(), hasPermIdSet);

            });
        }

        // 构建返回结果
        List<Dtree> dtreeList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(menuModuleList)) {
            // 构建模块
            dtreeList.addAll(menuModuleList.stream().map(menuModule ->
                    new Dtree(String.valueOf(menuModule.getId()), menuModule.getName(), WeikbestConstant.DEFAULT_PARENT_ID, hasModuleIdSet.contains(menuModule.getId()) ? "1" : "0", "Module")).collect(Collectors.toList()));
        }

        if (CollectionUtil.isNotEmpty(menuList)) {
            // 构建菜单
            dtreeList.addAll(menuList.stream().map(menu ->
                    new Dtree(String.valueOf(menu.getId()), menu.getName(), ObjectUtil.equal(menu.getParentId(), WeikbestConstant.ZERO_LONG) ? String.valueOf(menu.getMenuModuleId()) : String.valueOf(menu.getParentId()), hasMenuIdAndHasPermIdMap.containsKey(menu.getId()) ? "1" : "0", "Menu")).collect(Collectors.toList()));


            // 构建权限项
            List<MenuPerm> menuPermList = menuPermService.list(new QueryWrapper<MenuPerm>().in(MenuPerm.MENU_ID, menuList.stream().map(Menu::getId).collect(Collectors.toList())));
            if (CollectionUtil.isNotEmpty(menuPermList)) {
                List<Perm> permList = permService.listByIds(menuPermList.stream().map(MenuPerm::getPermId).collect(Collectors.toList()));
                Map<Long, Perm> permMap = permList.stream().collect(Collectors.toMap(Perm::getId, perm -> perm));

                menuPermList.forEach(menuPerm -> {
                    Long menuId = menuPerm.getMenuId();
                    Long permId = menuPerm.getPermId();
                    Perm perm = permMap.get(permId);
                    if (ObjectUtil.isNotEmpty(perm)) {
                        Set<Long> hasPermIdSet = hasMenuIdAndHasPermIdMap.getOrDefault(menuId, new HashSet<>());
                        dtreeList.add(new Dtree(String.valueOf(perm.getId()), perm.getName(), String.valueOf(menuId), hasPermIdSet.contains(perm.getId()) ? "1" : "0", "Perm"));
                    }
                });
            }
        }

        // 修正选中状态
        DtreeUtil.correctionChecked(dtreeList);

        return dtreeList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void authRoleMenuPerm(List<String> purviewList, Long roleId) {
        // 1. 根据角色ID删除之前的授权角色菜单权限项
        this.remove(new QueryWrapper<RoleMenu>().eq(RoleMenu.ROLE_ID, roleId));

        // 2. 新建关联数据
        List<RoleMenu> roleMenuList = purviewList.stream().map(purviewString -> {
            String[] purviewArr = StrUtil.split(purviewString, WeikbestConstant.PURVIEW_SPLIT);
            RoleMenu roleMenu = new RoleMenu().setRoleId(roleId).setMenuModuleId(Long.parseLong(purviewArr[WeikbestConstant.ZERO_INT]));
            if (purviewArr.length > WeikbestConstant.ONE_INT) {
                roleMenu.setMenuId(Long.parseLong(purviewArr[WeikbestConstant.ONE_INT]));
            }
            if (purviewArr.length > WeikbestConstant.TWO_INT) {
                roleMenu.setPermId(Long.parseLong(purviewArr[WeikbestConstant.TWO_INT]));
            }
            return roleMenu;
        }).collect(Collectors.toList());
        this.saveBatch(roleMenuList);
    }

    @Override
    public List<Perm> queryPurviewMenuByTokenUser(TokenUser tokenUser, Long menuId) {
        // 查询菜单
        Menu menu = menuService.findById(menuId);

        // 根据系统用户ID查询用户角色
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq(UserRole.USER_ID, tokenUser.getUserId()));
        if (CollectionUtil.isEmpty(userRoleList)) {
            return new ArrayList<>();
        }
        List<Role> roleList = roleService.list(new QueryWrapper<Role>()
                .in(Role.ID, userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList()))
                .eq(Role.ROLE_TYPE, menu.getMenuType())
                .eq(Role.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(roleList)) {
            return new ArrayList<>();
        }

        List<RoleMenu> roleMenuList = this.list(new QueryWrapper<RoleMenu>()
                .in(RoleMenu.ROLE_ID, roleList.stream().map(Role::getId).collect(Collectors.toList()))
                .eq(RoleMenu.MENU_ID, menuId)
        );
        if (CollectionUtil.isEmpty(roleMenuList)) {
            return new ArrayList<>();
        }

        List<Long> permIdList = roleMenuList.stream()
                .map(RoleMenu::getPermId)
                .distinct()
                .collect(Collectors.toList());
        // 查询权限项的编码
        return permService.listByIds(permIdList);
    }
}
