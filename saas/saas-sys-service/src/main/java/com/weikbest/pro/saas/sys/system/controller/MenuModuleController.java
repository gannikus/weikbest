package com.weikbest.pro.saas.sys.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.system.entity.MenuModule;
import com.weikbest.pro.saas.sys.system.module.dto.MenuModuleDTO;
import com.weikbest.pro.saas.sys.system.module.qo.MenuModuleQO;
import com.weikbest.pro.saas.sys.system.service.MenuModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 系统模块表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"system::系统模块表接口"})
@RestController
@RequestMapping("/system/menu-module")
public class MenuModuleController {

    @Resource
    private MenuModuleService menuModuleService;

    @UseToken
    @SaveLog(value = "新增系统模块表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "menuModuleDTO", value = "保存数据信息", required = true)
            @Valid MenuModuleDTO menuModuleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = menuModuleService.insert(menuModuleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统模块表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "menuModuleDTO", value = "更新数据信息", required = true)
            @Valid MenuModuleDTO menuModuleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = menuModuleService.updateById(id, menuModuleDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除系统模块表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        menuModuleService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除系统模块表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        menuModuleService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统模块表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<MenuModule> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        MenuModule menuModule = menuModuleService.findById(id);
        return DataResp.ok(menuModule);
    }

    @UseToken
    @QueryLog(value = "查询系统模块表数据,返回数据字典")
    @ApiOperation(value = "查询系统模块表数据,返回数据字典")
    @GetMapping("/queryDict")
    public DataResp<List<DictEntry>> queryDict() {

        List<DictEntry> dictEntryList = menuModuleService.queryDictByType(null);
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "根据系统模块表数据,返回数据字典")
    @ApiOperation(value = "根据类型系统模块表数据,返回数据字典")
    @GetMapping("/queryDictByType/{type}")
    public DataResp<List<DictEntry>> queryDictByType(
            @ApiParam(name = "type", value = "模块类型/菜单类型 0-平台 1-商家端 2-APP端", required = true)
            @PathVariable String type) {

        List<DictEntry> dictEntryList = menuModuleService.queryDictByType(type);
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "分页查询系统模块表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<MenuModule>> queryPage(
            @ApiParam(name = "menuModuleQO", value = "查询条件")
                    MenuModuleQO menuModuleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<MenuModule> pageModel = menuModuleService.queryPage(menuModuleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
