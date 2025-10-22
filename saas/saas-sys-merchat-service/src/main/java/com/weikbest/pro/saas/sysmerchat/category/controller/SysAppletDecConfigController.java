package com.weikbest.pro.saas.sysmerchat.category.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigVO;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigService;
import com.weikbest.pro.saas.merchat.category.util.AppletDecConfigJsrCheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 小程序装修配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Slf4j
@Api(tags = {"category::平台端小程序装修配置表接口"})
@RestController
@RequestMapping("/sys/applet-dec-config")
public class SysAppletDecConfigController {

    @Resource
    private AppletDecConfigJsrCheckUtil appletDecConfigJsrCheckUtil;

    @Resource
    private AppletDecConfigService appletDecConfigService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新小程序装修配置表数据")
    @ApiOperation(value = "新增或更新小程序装修配置表数据")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(
            @ApiParam(name = "appletDecConfigDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid AppletDecConfigDTO appletDecConfigDTO, BindingResult bindingResult) {

        appletDecConfigJsrCheckUtil.validAppletDecConfigDTO(bindingResult);

        boolean save = appletDecConfigService.saveOrUpdate(appletDecConfigDTO);
        return DataResp.ok(save);
    }


    //    @UseToken
//    @RemoveLog(value = "根据ID删除小程序装修配置表数据")
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
//    @RemoveLog(value = "根据ID列表删除小程序装修配置表数据")
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
    @UseToken
    @QueryLog(value = "查询小程序装修配置表数据")
    @ApiOperation(value = "查询小程序装修配置表数据")
    @GetMapping("/find")
    public DataResp<AppletDecConfigVO> find() {

        AppletDecConfigVO appletDecConfigVO = appletDecConfigService.findAppletDecConfigVO();
        return DataResp.ok(appletDecConfigVO);
    }
//
//    @UseToken
//    @QueryLog(value = "分页查询小程序装修配置表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPageVO")
//    public DataResp<List<AppletDecConfig>> queryPageVO(
//            @ApiParam(name = "appletDecConfigQO", value = "查询条件")
//                    AppletDecConfigQO appletDecConfigQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<AppletDecConfig> pageModel = appletDecConfigService.queryPageVO(appletDecConfigQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
