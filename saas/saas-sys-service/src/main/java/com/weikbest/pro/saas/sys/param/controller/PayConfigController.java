package com.weikbest.pro.saas.sys.param.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.module.dto.PayConfigDTO;
import com.weikbest.pro.saas.sys.param.service.PayConfigService;
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
 * 系统支付商户号配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
@Slf4j
@Api(tags = {"param::系统支付商户号配置表接口"})
@RestController
@RequestMapping("/param/pay-config")
public class PayConfigController {

    @Resource
    private PayConfigService payConfigService;

    @SaveOrUpdateLog
    @UpdateLog(value = "新增或更新系统支付商户号配置表数据")
    @ApiOperation(value = "新增或更新系统支付商户号配置表数据")
    @PutMapping("/saveOrUpdate/{payConfigType}")
    public DataResp<Boolean> update(
            @ApiParam(name = "payConfigType", value = "支付商户号类型 1-普通商户 3-特约商户", required = true)
            @PathVariable String payConfigType,
            @ApiParam(name = "payConfigDTO", value = "更新数据信息", required = true)
            @Valid PayConfigDTO payConfigDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);
        boolean result = false;

        PayConfig payConfig = payConfigService.getOne(new QueryWrapper<PayConfig>().eq(PayConfig.PAY_CONFIG_TYPE, payConfigType));
        if (ObjectUtil.isNull(payConfig)) {
            // 新增
            result = payConfigService.insert(payConfigType, payConfigDTO);
        } else {
            // 更新
            result = payConfigService.updateById(payConfig.getId(), payConfigType, payConfigDTO);
        }
        return DataResp.ok(result);
    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统支付商户号配置表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        payConfigService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统支付商户号配置表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        payConfigService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "查询系统支付商户号配置表数据")
    @ApiOperation(value = "查询系统支付商户号配置表数据")
    @GetMapping("/queryAll")
    public DataResp<List<PayConfig>> queryAll() {

        List<PayConfig> payConfigList = payConfigService.list();
        return DataResp.ok(payConfigList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统支付商户号配置表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<PayConfig> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        PayConfig payConfig = payConfigService.findById(id);
        return DataResp.ok(payConfig);
    }

//    @UseToken
//    @QueryLog(value = "分页查询系统支付商户号配置表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<PayConfig>> queryPage(
//            @ApiParam(name = "payConfigQO", value = "查询条件")
//                    PayConfigQO payConfigQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<PayConfig> pageModel = payConfigService.queryPage(payConfigQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
