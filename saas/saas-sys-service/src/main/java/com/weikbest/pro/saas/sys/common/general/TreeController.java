package com.weikbest.pro.saas.sys.common.general;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.DtreeUtil;
import com.weikbest.pro.saas.sys.param.service.DictTypeService;
import com.weikbest.pro.saas.sys.param.service.RegionService;
import com.weikbest.pro.saas.sys.system.entity.Org;
import com.weikbest.pro.saas.sys.system.service.MenuService;
import com.weikbest.pro.saas.sys.system.service.OrgService;
import com.weikbest.pro.saas.sys.system.service.RoleMenuService;
import com.weikbest.pro.saas.sys.system.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @date 2019/6/16 0016
 * @project mystery-boxes
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"Manage——树形结构数据查询接口"})
@RestController
@RequestMapping("/dtree")
public class TreeController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private RegionService regionService;

    @UseToken
    @QueryLog("查询当前角色下对应的菜单和权限项,配置菜单权限")
    @ApiOperation(value = "查询当前角色下对应的菜单和权限项,配置菜单权限")
    @GetMapping("/queryAuthRoleMenuPerm/{roleId}")
    public DataResp<List<Dtree>> queryAuthRoleMenuPerm(@ApiParam(name = "roleId", value = "角色ID", required = true)
                                                       @PathVariable Long roleId,
                                                       @ApiParam(name = "level", value = "是否返回层级数据")
                                                       @RequestParam Boolean level) {

        List<Dtree> authRoleMenuPerms = roleMenuService.queryAuthRoleMenuPerm(roleId);
        return DataResp.ok(BooleanUtil.isTrue(level) ? DtreeUtil.converListToLevel(authRoleMenuPerms, WeikbestConstant.DEFAULT_PARENT_ID) : authRoleMenuPerms);
    }

    @UseToken
    @QueryLog("查询平台所有部门")
    @ApiOperation(value = "查询平台所有部门")
    @GetMapping("/queryOrg")
    public DataResp<List<Dtree>> queryOrg(@ApiParam(name = "level", value = "是否返回层级数据")
                                          @RequestParam Boolean level) {
        List<Dtree> dtrees = new ArrayList<>();

        List<Org> orgs = orgService.list(new QueryWrapper<Org>().orderByAsc(Org.ORG_LEVEL).orderByAsc(Org.ORG_ORD));
        if (!CollectionUtil.isEmpty(orgs)) {
            dtrees = orgs.stream().map(org -> new Dtree(String.valueOf(org.getId()), org.getName(),
                    String.valueOf(org.getParentId()))).collect(Collectors.toList());
        }

        return DataResp.ok(BooleanUtil.isTrue(level) ? DtreeUtil.converListToLevel(dtrees, WeikbestConstant.DEFAULT_PARENT_ID) : dtrees);
    }

    @UseToken
    @QueryLog("查询平台行政区划")
    @ApiOperation(value = "查询平台行政区划")
    @GetMapping("/queryRegion")
    public DataResp<List<Dtree>> queryRegion() {
        // 这里返回的就是层级数据
        List<Dtree> dtrees = regionService.queryTree();
        return DataResp.ok(dtrees);
    }
//
//    @UseToken
//    @QueryLog("查询全量菜单记录")
//    @ApiOperation(value = "查询全量菜单记录")
//    @GetMapping("/queryMenu/{menuType}")
//    public DataResp queryMenu(
//            @ApiParam(name = "menuType", value = "菜单类型", required = true)
//            @PathVariable Integer menuType) {
//        List<Dtree> dtrees = new ArrayList<>();
//        // 只要有看到菜单维护的权限，这里就可以查到全部的菜单
//        List<Menu> menus = menuService.list(new QueryWrapper<Menu>()
//                .eq(Menu.MENU_TYPE, menuType)
//                .orderByAsc(Menu.MENU_LEVEL, Menu.MENU_ORD));
//        if (!CollectionUtil.isEmpty(menus)) {
//            dtrees = menus.stream()
//                    .map(menu -> new Dtree(String.valueOf(menu.getId()), menu.getName(),
//                            String.valueOf(menu.getParentId())).setIconClass(menu.getIconClass()))
//                    .collect(Collectors.toList());
//        }
//
//        return DataResp.ok().data(dtrees);
//    }

    //@QueryLog("查询全量Menu记录,追加虚拟根节点")
    /*@UseToken
    @GetMapping("/queryMenuHasRoot")
    public DataResp queryMenuHasRoot(HttpServletRequest request, Menu menu) {

        if(menu == null) {
            menu = new Menu();
        }
        menu.setCurrentUserId(SessionUtils.getInstance(request).getId());
        menu.setCurrentLoginName(SessionUtils.getInstance(request).getLoginName());
        // 只要有看到菜单维护的权限，这里就可以查到全部的菜单
        List<Menu> menus = menuService.list(null);
        // 根据当前登录的用户查询菜单信息
        *//* List<Menu> menus = menuService.queryMenuByUser(menu); *//*

        Menu root = new Menu();
        root.setId("0");
        root.setTitle(Memory.getConfig(ROOTMENUNAME));
        root.setParentId("-1");
        root.setLast(false);
        menus.add(root);

        return DataResp.ok().data(menus);
    }*/

   /* @UseToken
    @QueryLog("查询当前用户可分配的权限")
    @ApiOperation(value = "查询当前用户可分配的权限")
    @GetMapping("/queryAuthMenuHasRoot/{roleCode}")
    public DataResp queryAuthMenuHasRoot(HttpServletRequest request, @PathVariable("roleCode") String roleCode) {
        try {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleCode(roleCode);
            roleMenu.setCurrentUserId(SessionUtils.getInstance(request).getId());
            roleMenu.setCurrentLoginName(SessionUtils.getInstance(request).getLoginName());
            List<Menu> menus = roleMenuService.queryAuthMenuByUser(roleMenu);

            Menu root = new Menu();
            root.setId("0");
            root.setTitle(Memory.getConfig(ROOTMENUNAME));
            root.setParentId("-1");
            root.setLast(false);
            menus.add(root);

            return DataResp.ok().data(menus);
        } catch (MysteryBoxesException e) {
            throw new ControllerException(Constant.FAIL, Constant.SERVER_ERROR_MSG);
        }
    }*/


    //    @UseToken
//    @QueryLog("查询当前角色分配的菜单权限")
//    @ApiOperation(value = "查询当前角色分配的菜单权限")
//    @GetMapping("/queryAuthMenu/{roleCode}")
//    public DataResp queryAuthMenu(
//            @ApiParam(name = "roleCode", value = "待分配角色", required = true)
//            @PathVariable("roleCode") String roleCode) {
//
//        List<RoleMenuDTO> userRoleDTOList = roleMenuService.queryAuthMenuByRoleCode(roleCode);
//
//        List<Dtree> dtrees = new ArrayList<>();
//        if(!CollectionUtil.isEmpty(userRoleDTOList)) {
//            dtrees = userRoleDTOList.stream()
//                    .map(roleMenuDTO -> new Dtree(String.valueOf(roleMenuDTO.getId()), roleMenuDTO.getTitle(),
//                            String.valueOf(roleMenuDTO.getParentId()))
//                            .setBasicData(roleMenuDTO.getRoleMenuId()).setChecked(roleMenuDTO.getChecked()))
//                    .collect(Collectors.toList());
//        }
//
//        return DataResp.ok().data(dtrees);
//    }
//
//    @UseToken
//    @QueryLog("查询当前选择用户分配的角色")
//    @ApiOperation(value = "查询当前选择用户分配的角色")
//    @GetMapping("/queryUserRole/{loginName}")
//    public DataResp queryUserRole(
//              @ApiParam(name = "loginName", value = "待授权用户", required = true)
//              @PathVariable("loginName") String loginName) {
//
//        List<UserRoleDTO> userRoleDTOList = userRoleService.queryAuthRoleByLoginName(loginName);
//
//        List<Dtree> dtrees = new ArrayList<>();
//        if(!CollectionUtil.isEmpty(userRoleDTOList)) {
//            dtrees = userRoleDTOList.stream()
//                    .map(userRoleDTO -> new Dtree(String.valueOf(userRoleDTO.getRoleCode()), userRoleDTO.getRoleName(), MysteryBoxesConstant.DEFAULT_PARENT_ID)
//                            .setBasicData(userRoleDTO.getUserRoleId()).setChecked(userRoleDTO.getChecked()))
//                    .collect(Collectors.toList());
//        }
//
//        return DataResp.ok().data(dtrees);
//    }
//

}
