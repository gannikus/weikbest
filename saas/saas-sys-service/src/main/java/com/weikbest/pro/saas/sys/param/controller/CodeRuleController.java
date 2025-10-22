package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.CodeRule;
import com.weikbest.pro.saas.sys.param.module.dto.CodeRuleDTO;
import com.weikbest.pro.saas.sys.param.module.qo.CodeRuleQO;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
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
 * 系统编码规则表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Api(tags = {"param::系统编码规则表接口"})
@RestController
@RequestMapping("/param/code-rule")
public class CodeRuleController {

    @Resource
    private CodeRuleService codeRuleService;

    @UseToken
    @SaveLog(value = "新增系统编码规则表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "codeRuleDTO", value = "保存数据信息", required = true)
            @Valid CodeRuleDTO codeRuleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = codeRuleService.insert(codeRuleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统编码规则表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<CodeRule> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CodeRule codeRule = codeRuleService.findById(id);
        return DataResp.ok(codeRule);
    }

    @UseToken
    @QueryLog(value = "分页查询系统编码规则表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CodeRule>> queryPage(
            @ApiParam(name = "codeRuleQO", value = "查询条件")
                    CodeRuleQO codeRuleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CodeRule> pageModel = codeRuleService.queryPage(codeRuleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
