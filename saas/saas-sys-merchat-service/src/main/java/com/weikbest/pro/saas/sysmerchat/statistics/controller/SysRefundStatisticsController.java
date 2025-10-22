package com.weikbest.pro.saas.sysmerchat.statistics.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysRefundStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysRefundStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.statistics.service.SysRefundStatisticsService;
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
 * @since :2023/2/11
 */
@Slf4j
@Api(tags = {"SysRefundStatistics::平台退款统计接口"})
@RestController
@RequestMapping("/sys/refund-statistics")
public class SysRefundStatisticsController {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private SysRefundStatisticsService sysRefundStatisticsService;

    @UseToken
    @QueryLog(value = "查询小程序信息")
    @ApiOperation(value = "查询小程序信息,返回数据字典")
    @GetMapping("/queryApplet")
    public DataResp<List<DictEntry>> queryApplet() {

        List<DictEntry> dictEntryList = appletConfigService.queryDict();
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "退款数据")
    @ApiOperation(value = "退款数据")
    @GetMapping("/queryRefund")
    public DataResp<SysRefundStatisticsVO> queryRefund(@ApiParam(name = "sysRefundStatisticsQO", value = "平台退款数据查询")
                                                               SysRefundStatisticsQO sysRefundStatisticsQO) {

        SysRefundStatisticsVO sysRefundStatisticsVO = sysRefundStatisticsService.queryData(sysRefundStatisticsQO);
        return DataResp.ok(sysRefundStatisticsVO);
    }
}
