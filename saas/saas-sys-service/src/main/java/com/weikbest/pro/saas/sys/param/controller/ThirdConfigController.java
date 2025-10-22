package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.module.dto.ThirdConfigDTO;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 第三方平台配置表   前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
@Slf4j
@Api(tags = {"param::第三方平台配置表  接口"})
@RestController
@RequestMapping("/param/third-config")
public class ThirdConfigController {

    @Resource
    private ThirdConfigService thirdConfigService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新新增第三方平数据")
    @ApiOperation(value = "新增或更新新增第三方平数据")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(
            @ApiParam(name = "thirdConfigDTO", value = "保存数据信息", required = true)
            @Valid ThirdConfigDTO thirdConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = thirdConfigService.saveOrUpdate(thirdConfigDTO);
        return DataResp.ok(save);
    }

//    @UseToken
//    @SaveLog(value = "新增第三方平台配置表  数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "thirdConfigDTO", value = "保存数据信息", required = true)
//            @Valid ThirdConfigDTO thirdConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = thirdConfigService.insert(thirdConfigDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新第三方平台配置表  数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "thirdConfigDTO", value = "更新数据信息", required = true)
//            @Valid ThirdConfigDTO thirdConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = thirdConfigService.updateById(id, thirdConfigDTO);
//        return DataResp.ok(update);
//    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除第三方平台配置表  数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        thirdConfigService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除第三方平台配置表  数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        thirdConfigService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

//    @UseToken
//    @QueryLog(value = "根据ID查询第三方平台配置表  数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ThirdConfig thirdConfig = thirdConfigService.findById(id);
//        return DataResp.ok().data(thirdConfig);
//    }

    @UseToken
    @QueryLog(value = "查询第三方平台数据")
    @ApiOperation(value = "查询第三方平台数据")
    @GetMapping("/find")
    public DataResp<ThirdConfig> find() {

        ThirdConfig thirdConfig = thirdConfigService.findThirdConfig(false);
        return DataResp.ok(thirdConfig);
    }

//    @UseToken
//    @QueryLog(value = "分页查询第三方平台配置表  数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp queryPage(
//            @ApiParam(name = "thirdConfigQO", value = "查询条件")
//                    ThirdConfigQO thirdConfigQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<ThirdConfig> pageModel = thirdConfigService.queryPage(thirdConfigQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
