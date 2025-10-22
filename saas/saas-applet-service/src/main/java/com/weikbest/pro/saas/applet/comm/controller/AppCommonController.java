package com.weikbest.pro.saas.applet.comm.controller;

import com.weikbest.pro.saas.applet.comm.module.mapstruct.AppSiteMapStruct;
import com.weikbest.pro.saas.applet.comm.module.qo.AppProdSemPositionQO;
import com.weikbest.pro.saas.applet.comm.module.vo.*;
import com.weikbest.pro.saas.applet.comm.service.AppCommonService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigVO;
import com.weikbest.pro.saas.merchat.category.service.AppletDecConfigService;
import com.weikbest.pro.saas.sys.param.entity.PrivacyPolicy;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.module.vo.LogisticsCompanyVO;
import com.weikbest.pro.saas.sys.param.module.vo.SiteVO;
import com.weikbest.pro.saas.sys.param.service.PrivacyPolicyService;
import com.weikbest.pro.saas.sys.param.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * App首页公共信息
 * </p>
 *
 * @author weik
 * @since 2022-09-06
 */

@Slf4j
@Api(tags = {"appcommon::公共接口"})
@RestController
@RequestMapping("/appcommon")
public class AppCommonController {

    @Resource
    private AppCommonService appCommonService;

    @Resource
    private SiteService siteService;

    @Resource
    private PrivacyPolicyService privacyPolicyService;

    @Resource
    private AppletDecConfigService appletDecConfigService;

    @PassToken
    @QueryLog("app-获取首页商品轮播图表")
    @ApiOperation(value = "【废弃】获取首页商品轮播图表")
    @GetMapping(value = "/queryProdCarousel")
    public DataResp<Map<String, Object>> queryProdCarousel() {

        List<AppProdCarouselVO> appProdCarouselVOList = appCommonService.queryProdCarousels();

        return DataResp.BuilderMap.create().ok().put("list", appProdCarouselVOList).build();
    }

    @PassToken
    @QueryLog("app-获取首页营销广告位及分类信息")
    @ApiOperation(value = "【废弃】获取首页营销广告位及分类信息")
    @GetMapping(value = "/queryProdSemPosition")
    public DataResp<Map<String, Object>> queryProdSemPosition(
            @ApiParam(name = "AppProdSemPositionQO", value = "营销查询参数")
                    AppProdSemPositionQO appProdSemPositionQO) {

        List<AppProdSemPositionVO> appProdSemPositionVOS = appCommonService.queryProdSemPositions(appProdSemPositionQO);

        return DataResp.BuilderMap.create().ok().put("list", appProdSemPositionVOS).build();
    }
    @PassToken
    @QueryLog("通过字典类型number获取下级字段")
    @ApiOperation(value = "通过字典类型number获取下级字段")
    @GetMapping(value = "/queryDictsByNumber/{number}")
    public DataResp<Map<String, Object>> queryDictsByNumber(
            @ApiParam(name = "number", value = "字典分类编码", required = true)
            @PathVariable String number) {

        List<AppDictVO> appDictVOS = appCommonService.queryDictsByNumber(number);

        return DataResp.BuilderMap.create().ok().put("list", appDictVOS).build();

    }
    @PassToken
    @AppToken
    @QueryLog("获取首页区域信息")
    @ApiOperation(value = "获取首页区域信息")
    @GetMapping(value = "/queryIndexInfo/{appletDecConfigId}")
    public DataResp<Map<String, Object>> queryIndexInfo(
            @ApiParam(name = "appletDecConfigId", value = "小程序装修配置表ID", required = true)
            @PathVariable Long appletDecConfigId) {

        List<AppAppletDecConfigEntryVO> appletDecConfigEntries = appCommonService.queryIndexInfo(appletDecConfigId);

        return DataResp.BuilderMap.create().ok().put("list", appletDecConfigEntries).build();

    }


    @PassToken
    @QueryLog("获取小程序类目信息")
    @ApiOperation(value = "获取小程序类目信息")
    @GetMapping(value = "/queryAppletProdCategorys")
    public DataResp<Map<String, Object>> queryAppletProdCategorys(
            @ApiParam(name = "number", value = "类目编码")
            @RequestParam String number) {

        List<AppAppletProdCategoryVO> vos = appCommonService.queryAppletProdCategorys(number);

        return DataResp.BuilderMap.create().ok().put("list", vos).build();

    }
    @PassToken
    @QueryLog("获取物流公司列表信息")
    @ApiOperation(value = "获取物流公司列表信息")
    @GetMapping(value = "/queryLogisticsCompany")
    public DataResp<Map<String, Object>> queryLogisticsCompany() {

        List<LogisticsCompanyVO> list = appCommonService.queryLogisticsCompany();

        return DataResp.BuilderMap.create().ok().put("list", list).build();

    }
    @PassToken
    @QueryLog("获取平台端相关第三方配置")
    @ApiOperation(value = "获取平台端相关第三方配置")
    @GetMapping(value = "/getSysSite")
    public DataResp<AppSiteVO> getSysSite() {

        Site site = siteService.findSite();
        AppSiteVO appSiteVO = AppSiteMapStruct.INSTANCE.converToVO(site);

        return DataResp.ok(appSiteVO);

    }
    @PassToken
    @QueryLog(value = "获取个人隐私声明信息")
    @ApiOperation(value = "获取个人隐私声明信息")
    @GetMapping("/find")
    public DataResp<PrivacyPolicy> find() {

        PrivacyPolicy privacyPolicy = privacyPolicyService.findPrivacyPolicy();
        return DataResp.ok(privacyPolicy);
    }


    @PassToken
    @QueryLog(value = "查询小程序装修配置表数据")
    @ApiOperation(value = "查询小程序装修配置表数据")
    @GetMapping("/findConfig")
    public DataResp<AppletDecConfigVO> findAppDecConfig() {

        AppletDecConfigVO appletDecConfigVO = appletDecConfigService.findAppletDecConfigVO();
        return DataResp.ok(appletDecConfigVO);
    }

}
