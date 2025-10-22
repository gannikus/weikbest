package com.weikbest.pro.saas.sys.param.controller;

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
import com.weikbest.pro.saas.sys.param.entity.AppletSmsConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSmsConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSmsConfigQO;
import com.weikbest.pro.saas.sys.param.service.AppletSmsConfigService;
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
 * 小程序绑定短信配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-22
 */
@Slf4j
@Api(tags = {"param::小程序绑定短信配置表接口"})
@RestController
@RequestMapping("/param/applet-sms-config")
public class AppletSmsConfigController {

    @Resource
    private AppletSmsConfigService appletSmsConfigService;

    @UseToken
    @SaveLog(value = "新增小程序绑定短信配置表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "appletSmsConfigDTO", value = "保存数据信息", required = true)
            @Valid AppletSmsConfigDTO appletSmsConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = appletSmsConfigService.insert(appletSmsConfigDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新小程序绑定短信配置表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "appletSmsConfigDTO", value = "更新数据信息", required = true)
            @Valid AppletSmsConfigDTO appletSmsConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = appletSmsConfigService.updateById(id, appletSmsConfigDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除小程序绑定短信配置表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        appletSmsConfigService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除小程序绑定短信配置表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        appletSmsConfigService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询小程序绑定短信配置表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<AppletSmsConfig> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        AppletSmsConfig appletSmsConfig = appletSmsConfigService.findById(id);
        return DataResp.ok(appletSmsConfig);
    }

    @UseToken
    @QueryLog(value = "分页查询小程序绑定短信配置表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<AppletSmsConfig>> queryPage(
            @ApiParam(name = "appletSmsConfigQO", value = "查询条件")
                    AppletSmsConfigQO appletSmsConfigQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<AppletSmsConfig> pageModel = appletSmsConfigService.queryPage(appletSmsConfigQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
