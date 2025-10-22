package com.weikbest.pro.saas.sysmerchat.statistics.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysSaleStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysSaleStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.statistics.service.SysSaleStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/11
 */
@Slf4j
@Api(tags = {"SysSaleStatistics::平台销售统计接口"})
@RestController
@RequestMapping("/sys/sale-statistics")
public class SysSaleStatisticsController {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private SysSaleStatisticsService sysSaleStatisticsService;

    @UseToken
    @QueryLog(value = "查询小程序信息")
    @ApiOperation(value = "查询小程序信息,返回数据字典")
    @GetMapping("/queryApplet")
    public DataResp<List<DictEntry>> queryApplet() {

        List<DictEntry> dictEntryList = appletConfigService.queryDict();
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "销售统计数据")
    @ApiOperation(value = "销售统计数据")
    @GetMapping("/querySales")
    public DataResp<SysSaleStatisticsVO> querySales(@ApiParam(name = "sysRefundStatisticsQO", value = "平台销售数据查询实体")
                                                            SysSaleStatisticsQO sysRefundStatisticsQO) {

        SysSaleStatisticsVO sysRefundStatisticsVO = sysSaleStatisticsService.queryData(sysRefundStatisticsQO);
        return DataResp.ok(sysRefundStatisticsVO);
    }
}
