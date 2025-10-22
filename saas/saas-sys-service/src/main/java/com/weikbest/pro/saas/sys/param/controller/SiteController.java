package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.module.dto.SiteDTO;
import com.weikbest.pro.saas.sys.param.service.SiteService;
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
 * 系统站点设置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
@Slf4j
@Api(tags = {"param::系统站点设置表接口"})
@RestController
@RequestMapping("/param/site")
public class SiteController {

    @Resource
    private SiteService siteService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新系统站点设置数据")
    @ApiOperation(value = "新增或更新系统站点设置")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(@ApiParam(name = "siteDTO", value = "保存数据信息", required = true)
                                          @Valid SiteDTO siteDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = siteService.saveOrUpdate(siteDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "查询系统站点设置")
    @ApiOperation(value = "查询系统站点设置")
    @GetMapping("/find")
    public DataResp<Site> find() {

        Site site = siteService.findSite(false);
        return DataResp.ok(site);
    }

}
