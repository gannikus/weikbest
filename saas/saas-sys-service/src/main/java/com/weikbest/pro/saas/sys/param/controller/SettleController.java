package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.Settle;
import com.weikbest.pro.saas.sys.param.module.dto.SettleDTO;
import com.weikbest.pro.saas.sys.param.service.SettleService;
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
 * 系统结算规则表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Slf4j
@Api(tags = {"param::系统结算规则表接口"})
@RestController
@RequestMapping("/param/settle")
public class SettleController {

    @Resource
    private SettleService settleService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新系统结算规则表数据")
    @ApiOperation(value = "新增或更新系统结算规则表数据")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(
            @ApiParam(name = "settleDTO", value = "保存数据信息", required = true)
            @Valid SettleDTO settleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = settleService.saveOrUpdate(settleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "查询系统结算规则表数据")
    @ApiOperation(value = "查询系统结算规则表数据")
    @GetMapping("/find")
    public DataResp<Settle> find() {

        Settle settle = settleService.findSettle(false);
        return DataResp.ok(settle);
    }

}
