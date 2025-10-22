package com.weikbest.pro.saas.sys.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.system.entity.Menu;
import com.weikbest.pro.saas.sys.system.module.dto.MenuDTO;
import com.weikbest.pro.saas.sys.system.module.qo.MenuQO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuPermVO;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
import com.weikbest.pro.saas.sys.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"system::系统菜单表接口"})
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @UseToken
    @SaveLog(value = "新增系统菜单表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "menuDTO", value = "保存数据信息", required = true)
            @Valid MenuDTO menuDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = menuService.insert(menuDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统菜单表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "menuDTO", value = "更新数据信息", required = true)
            @Valid MenuDTO menuDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = menuService.updateById(id, menuDTO);
        return DataResp.ok(update);
    }


    @UseToken
    @SaveLog(value = "关联权限项")
    @ApiOperation(value = "关联权限项")
    @PostMapping("/associatePerm/{id}")
    public DataResp<Boolean> associatePerm(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "permIdList", value = "关联权限项数据信息", required = true)
            @RequestBody List<Long> permIdList) {

        if (CollectionUtil.isEmpty(permIdList)) {
            // 清空权限项
            permIdList = new ArrayList<>();
        }

        boolean save = menuService.saveAssociatePermList(id, permIdList);

        return DataResp.ok(save);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除系统菜单表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        menuService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除系统菜单表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        menuService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询菜单被哪些角色使用")
    @ApiOperation(value = "根据ID查询菜单被哪些角色使用")
    @GetMapping("/queryFromRuleMenu/{id}")
    public DataResp<List<String>> queryFromRuleMenu(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        List<String> roleNameList = menuService.queryFromRuleMenu(id);
        return DataResp.ok(roleNameList);
    }

    @UseToken
    @QueryLog(value = "查询所有权限项和菜单ID关联的权限项")
    @ApiOperation(value = "查询所有权限项和菜单ID关联的权限项")
    @GetMapping("/queryMenuPerm/{id}")
    public DataResp<List<MenuPermVO>> queryMenuPerm(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        List<MenuPermVO> menuPermVOList = menuService.queryMenuPerm(id);
        return DataResp.ok(menuPermVOList);
    }

    @UseToken
    @QueryLog(value = "根据模块ID查询模块下所有生效菜单数据")
    @ApiOperation(value = "根据模块ID查询模块下所有生效菜单数据")
    @GetMapping("/queryByModuleId/{moduleId}")
    public DataResp<List<MenuVO>> queryByModuleId(
            @ApiParam(name = "moduleId", value = "模块ID", required = true)
            @PathVariable Long moduleId) {

        List<MenuVO> menuVOList = menuService.queryByModuleId(moduleId);
        return DataResp.ok(menuVOList);
    }

    @UseToken
    @QueryLog(value = "根据类型查询模块下所有生效菜单数据")
    @ApiOperation(value = "根据类型查询模块下所有生效菜单数据")
    @GetMapping("/queryByType/{type}")
    public DataResp<List<MenuVO>> queryByType(
            @ApiParam(name = "type", value = "模块类型/菜单类型 0-平台 1-商家端 2-APP端", required = true)
            @PathVariable String type) {

        List<MenuVO> menuVOList = menuService.queryByType(type);
        return DataResp.ok(menuVOList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统菜单表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Menu> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Menu menu = menuService.findById(id);
        return DataResp.ok(menu);
    }

    @UseToken
    @QueryLog(value = "分页查询系统菜单表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Menu>> queryPage(
            @ApiParam(name = "menuQO", value = "查询条件")
                    MenuQO menuQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Menu> pageModel = menuService.queryPage(menuQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
