package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.PrivacyPolicy;
import com.weikbest.pro.saas.sys.param.module.dto.PrivacyPolicyDTO;
import com.weikbest.pro.saas.sys.param.service.PrivacyPolicyService;
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
 * 系统隐私声明表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-23
 */
@Slf4j
@Api(tags = {"param::系统隐私声明表接口"})
@RestController
@RequestMapping("/param/privacy-policy")
public class PrivacyPolicyController {

    @Resource
    private PrivacyPolicyService privacyPolicyService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新系统隐私声明表数据")
    @ApiOperation(value = "新增或更新系统隐私声明表")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(@ApiParam(name = "privacyPolicyDTO", value = "保存数据信息", required = true)
                                          @Valid PrivacyPolicyDTO privacyPolicyDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = privacyPolicyService.saveOrUpdate(privacyPolicyDTO);
        return DataResp.ok(save);
    }


    @UseToken
    @QueryLog(value = "根据ID查询系统隐私声明表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find")
    public DataResp<PrivacyPolicy> find() {

        PrivacyPolicy privacyPolicy = privacyPolicyService.findPrivacyPolicy();
        return DataResp.ok(privacyPolicy);
    }
}
