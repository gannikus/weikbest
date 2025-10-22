package com.weikbest.pro.saas.sysmerchat.common.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.OrderStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.SalesStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.OrderStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.RealtimeVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.SalesStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.common.service.SysIndexService;
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
 * @since :2023/2/18
 */
@Slf4j
@Api(tags = {"SysIndexController::平台首页接口"})
@RestController
@RequestMapping("/sys/index")
public class SysIndexController {

    @Resource
    private AppletConfigService appletConfigService;

    @Resource
    private SysIndexService sysIndexService;

    @UseToken
    @QueryLog(value = "查询小程序信息")
    @ApiOperation(value = "查询小程序信息,返回数据字典")
    @GetMapping("/queryApplet")
    public DataResp<List<DictEntry>> queryApplet() {

        List<DictEntry> dictEntryList = appletConfigService.queryDict();
        return DataResp.ok(dictEntryList);
    }

    @UseToken
    @QueryLog(value = "查询实时概况")
    @ApiOperation(value = "查询实时概况")
    @GetMapping("/queryRealtime")
    public DataResp<RealtimeVO> queryRealtime() {

        RealtimeVO realtimeVO = sysIndexService.queryRealtime();
        return DataResp.ok(realtimeVO);
    }

    @UseToken
    @QueryLog(value = "查询销售统计")
    @ApiOperation(value = "查询销售统计")
    @GetMapping("/querySalesStatistics")
    public DataResp<SalesStatisticsVO> querySalesStatistics(@ApiParam(name = "salesStatisticsQO", value = "销售统计查询")
                                                                    SalesStatisticsQO salesStatisticsQO) {

        SalesStatisticsVO salesStatisticsVO = sysIndexService.querySalesStatistics(salesStatisticsQO);
        return DataResp.ok(salesStatisticsVO);
    }

    @UseToken
    @QueryLog(value = "查询订单统计")
    @ApiOperation(value = "查询订单统计")
    @GetMapping("/queryOrderStatistics")
    public DataResp<OrderStatisticsVO> queryOrderStatistics(@ApiParam(name = "orderStatisticsQO", value = "订单统计查询")
                                                                    OrderStatisticsQO orderStatisticsQO) {

        OrderStatisticsVO orderStatisticsVO = sysIndexService.queryOrderStatistics(orderStatisticsQO);
        return DataResp.ok(orderStatisticsVO);
    }

}
