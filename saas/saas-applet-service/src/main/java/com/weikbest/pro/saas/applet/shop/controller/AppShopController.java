package com.weikbest.pro.saas.applet.shop.controller;

import com.weikbest.pro.saas.applet.shop.module.dto.AppShopVisitDTO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopListVO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopVO;
import com.weikbest.pro.saas.applet.shop.service.AppShopService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商户店铺表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shop::商户店铺表接口"})
@RestController
@RequestMapping("/appshop")
public class AppShopController {

    @Resource
    private AppShopService appShopService;


    @QueryLog(value = "根据ID查询商户店铺表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<AppShopVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "customerId", value = "客户ID")
            @RequestParam Long customerId) {

        AppShopVO appShopVO = appShopService.findshop(id,customerId);
        return DataResp.ok(appShopVO);
    }

    @AppToken
    @QueryLog(value = "查询我关注的店铺列表信息")
    @ApiOperation(value = "查询我关注的店铺列表信息")
    @GetMapping("/queryShopListByCustomerId")
    public DataResp<List<AppShopListVO>> queryShopListByCustomerId(
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
            PageReq pageReq) {

        List<AppShopListVO> appShopListVOList = appShopService.queryShopListByCustomerId(pageReq);
        return DataResp.ok(appShopListVOList);
    }


    @AppToken
    @UpdateLog(value = "用户取消或关注店铺")
    @ApiOperation(value = "用户取消或关注店铺（0-取消关注，1-关注）")
    @PutMapping("/doAttent/{id}/{isAttent}")
    public DataResp<Boolean> doAttent(
            @ApiParam(name = "id", value = "店铺ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "isAttent", value = "0-取消关注，1-关注", required = true)
            @PathVariable String isAttent) {

        boolean bool = appShopService.doAttent(id,isAttent);

        return DataResp.ok(bool);
    }


    @AppToken
    @UpdateLog(value = "保存店铺访问信息")
    @ApiOperation(value = "保存店铺访问信息",notes = "关联店铺ID,关联客户ID不为空，关联商品ID为空，同客户同店铺同天只保存一条数据")
    @PutMapping("/saveShopVisit")
    public DataResp<Boolean> saveShopVisit(
            @ApiParam(name = "appShopVisitDTO", value = "保存店铺访问信息")
                    AppShopVisitDTO appShopVisitDTO) {

        boolean bool = appShopService.saveShopVisit(appShopVisitDTO);

        return DataResp.ok(bool);
    }

    @AppToken
    @UpdateLog(value = "保存店铺商品访问信息")
    @ApiOperation(value = "保存店铺商品访问信息",notes = "关联商品ID和页面URl不能为空，调用一次保存一次")
    @PutMapping("/saveShopProdVisit")
    public DataResp<Boolean> saveShopProdVisit(
            @ApiParam(name = "appShopVisitDTO", value = "保存店铺访问信息")
                    AppShopVisitDTO appShopVisitDTO) {

        boolean bool = appShopService.saveShopProdVisit(appShopVisitDTO);

        return DataResp.ok(bool);
    }

}
