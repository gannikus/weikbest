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
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvConfigQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvConfigService;
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
 * 腾讯广告第三方应用配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Slf4j
@Api(tags = {"param::腾讯广告第三方应用配置表接口"})
@RestController
@RequestMapping("/param/tencent-adv-config")
public class TencentAdvConfigController {

    @Resource
    private TencentAdvConfigService tencentAdvConfigService;

    @UseToken
    @SaveLog(value = "新增腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "tencentAdvConfigDTO", value = "保存数据信息", required = true)
            @Valid TencentAdvConfigDTO tencentAdvConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = tencentAdvConfigService.insert(tencentAdvConfigDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "tencentAdvConfigDTO", value = "更新数据信息", required = true)
            @Valid TencentAdvConfigDTO tencentAdvConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = tencentAdvConfigService.updateById(id, tencentAdvConfigDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        tencentAdvConfigService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        tencentAdvConfigService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<TencentAdvConfig> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        TencentAdvConfig tencentAdvConfig = tencentAdvConfigService.findById(id);
        return DataResp.ok(tencentAdvConfig);
    }

    @UseToken
    @QueryLog(value = "分页查询腾讯广告第三方应用配置表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<TencentAdvConfig>> queryPage(
            @ApiParam(name = "tencentAdvConfigQO", value = "查询条件")
                    TencentAdvConfigQO tencentAdvConfigQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<TencentAdvConfig> pageModel = tencentAdvConfigService.queryPage(tencentAdvConfigQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
