package com.weikbest.pro.saas.sys.common.general;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.module.vo.MenuModuleVO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
import com.weikbest.pro.saas.sys.system.service.MenuModuleService;
import com.weikbest.pro.saas.sys.system.service.MenuService;
import com.weikbest.pro.saas.sys.system.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author wisdomelon
 * @date 2019/6/21 0021
 * @project mystery-boxes
 * @jdk 1.8
 */
@Slf4j
@Api(tags = {"Manage——权限接口"})
@RestController
@RequestMapping("/purview")
public class PurviewController {

    @Autowired
    private MenuModuleService menuModuleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private CurrentUserService currentUserService;

    @UseToken
    @SaveLog("授权角色菜单权限项")
    @ApiOperation(value = "授权角色菜单权限项")
    @PostMapping("/authPurview/{roleId}")
    public DataResp<Object> insertRoleMenuBatch(
            @ApiParam(name = "roleId", value = "角色ID", required = true)
            @PathVariable("roleId") Long roleId,
            @ApiParam(name = "purviewList", value = "模块菜单权限项列表， moduleId:menuId:permId", required = true)
            @RequestBody List<String> purviewList) {

        roleMenuService.authRoleMenuPerm(purviewList, roleId);
        return DataResp.ok();
    }


    @UseToken
    @QueryLog("根据模块类型，查询用户有权限的模块列表")
    @ApiOperation(value = "根据模块类型，查询用户有权限的模块列表")
    @GetMapping("/queryAuthModule/{moduleType}")
    public DataResp<List<MenuModuleVO>> queryAuthModule(HttpServletRequest request,
                                                        @ApiParam(name = "moduleType", value = "模块类型 0-平台模块 1-商家端模块 2-APP端模块", required = true)
                                                        @PathVariable String moduleType) {

        TokenUser tokenUser = currentUserService.getTokenUser(request);
        List<MenuModuleVO> menuModuleVOList = menuModuleService.queryAuthModuleByTokenUser(tokenUser, moduleType);

        return DataResp.ok(menuModuleVOList);
    }


    @UseToken
    @QueryLog("根据模块ID，查询用户有权限的菜单列表")
    @ApiOperation(value = "根据模块ID，查询用户有权限的菜单列表")
    @GetMapping("/queryAuthMenu/{moduleId}")
    public DataResp<List<MenuVO>> queryAuthMenu(HttpServletRequest request,
                                                @ApiParam(name = "moduleId", value = "菜单模块ID", required = true)
                                                @PathVariable Long moduleId) {

        // 这里需要根据用户的权限来查询菜单
        TokenUser tokenUser = currentUserService.getTokenUser(request);
        List<MenuVO> menuVoList = menuService.queryAuthMenuByTokenUser(tokenUser, moduleId);

        return DataResp.ok(menuVoList);
    }

    @UseToken
    @QueryLog("查询用户在当前菜单下对应的权限项编码")
    @ApiOperation(value = "查询用户在当前菜单下对应的权限项编码")
    @GetMapping("/queryAuthMenuPurview/{menuId}")
    public DataResp<List<Perm>> queryAuthMenuPurview(HttpServletRequest request,
                                                       @ApiParam(name = "menuId", value = "菜单ID", required = true)
                                                       @PathVariable Long menuId) {

        // 这里需要根据用户的权限来查询菜单
        TokenUser tokenUser = currentUserService.getTokenUser(request);
        List<Perm> purviewList = roleMenuService.queryPurviewMenuByTokenUser(tokenUser, menuId);
        return DataResp.ok(purviewList);
    }

}
