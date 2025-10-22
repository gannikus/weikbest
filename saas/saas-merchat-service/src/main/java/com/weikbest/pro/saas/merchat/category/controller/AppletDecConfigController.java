//package com.weikbest.pro.saas.merchat.category.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.weikbest.pro.saas.common.annotation.log.QueryLog;
//import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
//import com.weikbest.pro.saas.common.annotation.log.SaveLog;
//import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
//import com.weikbest.pro.saas.common.annotation.token.UseToken;
//import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
//import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
//import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
//import com.weikbest.pro.saas.common.util.JsrCheckUtil;
//import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
//import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
//import com.weikbest.pro.saas.merchat.category.module.qo.AppletDecConfigQO;
//import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * <p>
// * 小程序装修配置表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-10-02
// */
//@Slf4j
//@Api(tags = {"category::小程序装修配置表接口"})
//@RestController
//@RequestMapping("/category/applet-dec-config")
//public class AppletDecConfigController {
//
//    @Resource
//    private AppletDecConfigService appletDecConfigService;
//
//    @UseToken
//    @SaveLog(value = "新增小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "appletDecConfigDTO", value = "保存数据信息", required = true)
//            @Valid AppletDecConfigDTO appletDecConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = appletDecConfigService.insert(appletDecConfigDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "appletDecConfigDTO", value = "更新数据信息", required = true)
//            @Valid AppletDecConfigDTO appletDecConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = appletDecConfigService.updateById(id, appletDecConfigDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        appletDecConfigService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        appletDecConfigService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<AppletDecConfig> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        AppletDecConfig appletDecConfig = appletDecConfigService.findById(id);
//        return DataResp.ok(appletDecConfig);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询小程序装修配置表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<AppletDecConfig>> queryPage(
//            @ApiParam(name = "appletDecConfigQO", value = "查询条件")
//                    AppletDecConfigQO appletDecConfigQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<AppletDecConfig> pageModel = appletDecConfigService.queryPage(appletDecConfigQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
