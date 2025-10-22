package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletConfigQO;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 小程序配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Slf4j
@Api(tags = {"param::小程序配置表接口"})
@RestController
@RequestMapping("/param/applet-config")
public class AppletConfigController {

    @Resource
    private AppletConfigService appletConfigService;

//    @UseToken
//    @SaveLog(value = "新增小程序配置表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "appletConfigDTO", value = "保存数据信息", required = true)
//            @Valid AppletConfigDTO appletConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = appletConfigService.insert(appletConfigDTO);
//        return DataResp.ok(save);
//    }

    @UseToken
    @UpdateLog(value = "更新小程序配置表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "appletConfigDTO", value = "更新数据信息", required = true)
            @Valid AppletConfigDTO appletConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = appletConfigService.updateById(id, appletConfigDTO);
        return DataResp.ok(update);
    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除小程序配置表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        appletConfigService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除小程序配置表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        appletConfigService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询小程序配置表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<AppletConfig> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        AppletConfig appletConfig = appletConfigService.findById(id);
        return DataResp.ok(appletConfig);
    }

    @UseToken
    @QueryLog(value = "分页查询小程序配置表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<AppletConfig>> queryPage(
            @ApiParam(name = "appletConfigQO", value = "查询条件")
                    AppletConfigQO appletConfigQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<AppletConfig> pageModel = appletConfigService.queryPage(appletConfigQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "查询所有小程序配置表数据")
    @ApiOperation(value = "查询所有小程序配置表数据")
    @GetMapping("/queryAll")
    public DataResp<List<AppletConfig>> queryAll() {
        List<AppletConfig> appletConfigList = appletConfigService.list();
        return DataResp.ok(appletConfigList);
    }
}
