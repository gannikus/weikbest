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
import com.weikbest.pro.saas.sys.param.entity.TencentAdvScopeConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvScopeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvScopeConfigQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvScopeConfigService;
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
 * 腾讯广告主授权腾讯广告第三方应用表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Slf4j
@Api(tags = {"param::腾讯广告主授权腾讯广告第三方应用表接口"})
@RestController
@RequestMapping("/param/tencent-adv-scope-config")
public class TencentAdvScopeConfigController {

    @Resource
    private TencentAdvScopeConfigService tencentAdvScopeConfigService;

    @UseToken
    @SaveLog(value = "新增腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "tencentAdvScopeConfigDTO", value = "保存数据信息", required = true)
            @Valid TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = tencentAdvScopeConfigService.insert(tencentAdvScopeConfigDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "tencentAdvScopeConfigDTO", value = "更新数据信息", required = true)
            @Valid TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = tencentAdvScopeConfigService.updateById(id, tencentAdvScopeConfigDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        tencentAdvScopeConfigService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        tencentAdvScopeConfigService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<TencentAdvScopeConfig> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        TencentAdvScopeConfig tencentAdvScopeConfig = tencentAdvScopeConfigService.findById(id);
        return DataResp.ok(tencentAdvScopeConfig);
    }

    @UseToken
    @QueryLog(value = "分页查询腾讯广告主授权腾讯广告第三方应用表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<TencentAdvScopeConfig>> queryPage(
            @ApiParam(name = "tencentAdvScopeConfigQO", value = "查询条件")
                    TencentAdvScopeConfigQO tencentAdvScopeConfigQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<TencentAdvScopeConfig> pageModel = tencentAdvScopeConfigService.queryPage(tencentAdvScopeConfigQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
