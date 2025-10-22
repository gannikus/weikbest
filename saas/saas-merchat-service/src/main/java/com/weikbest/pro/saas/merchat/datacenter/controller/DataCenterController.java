package com.weikbest.pro.saas.merchat.datacenter.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;
import com.weikbest.pro.saas.merchat.datacenter.service.DataCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/4
 */
@Slf4j
@Api(tags = {"datacenter::数据中心接口"})
@RestController
@RequestMapping("/datacenter")
public class DataCenterController {

    @Resource
    private DataCenterService dataCenterService;

    @UseToken
    @QueryLog(value = "查询小程序信息", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询小程序信息,返回数据字典")
    @GetMapping("/queryApplet")
    public DataResp<List<DictEntry>> queryApplet() {

        List<DictEntry> dictEntryList = dataCenterService.queryApplet();
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "总交易概览", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "总交易概览")
    @GetMapping("/queryData/{businessId}/{shopId}")
    public DataResp<DataCenterVO> queryData(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId,
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @Valid DataCenterQO dataCenterQO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        DataCenterVO dataCenterVO = dataCenterService.queryData(businessId, shopId, dataCenterQO);
        return DataResp.ok(dataCenterVO);
    }
}
