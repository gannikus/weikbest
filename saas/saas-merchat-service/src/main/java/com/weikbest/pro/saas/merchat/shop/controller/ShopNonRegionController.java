package com.weikbest.pro.saas.merchat.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.shop.entity.ShopNonRegion;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopNonRegionDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopNonRegionQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopNonRegionService;
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
 * 不配送地区表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shop::不配送地区表接口"})
@RestController
@RequestMapping("/shop/shop-non-region")
public class ShopNonRegionController {

    @Resource
    private ShopNonRegionService shopNonRegionService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新不配送地区表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增或更新数据")
    @PostMapping("/saveOrUpdate/{shopId}")
    public DataResp<Boolean> saveOrUpdate(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "shopNonRegionDTOList", value = "保存数据信息集合", required = true)
            @RequestBody @Valid List<ShopNonRegionDTO> shopNonRegionDTOList, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = shopNonRegionService.saveOrUpdate(shopId, shopNonRegionDTOList);
        return DataResp.ok(save);
    }


    @UseToken
    @QueryLog(value = "根据ID查询不配送地区表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ShopNonRegion> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopNonRegion shopNonRegion = shopNonRegionService.findById(id);
        return DataResp.ok(shopNonRegion);
    }

    @UseToken
    @QueryLog(value = "分页查询不配送地区表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ShopNonRegion>> queryPage(
            @ApiParam(name = "shopNonRegionQO", value = "查询条件")
                    ShopNonRegionQO shopNonRegionQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopNonRegion> pageModel = shopNonRegionService.queryPage(shopNonRegionQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }


    @UseToken
    @QueryLog(value = "查询不配送地区行政区划数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询不配送地区行政区划数据")
    @GetMapping("/queryChooseTree/{shopId}")
    public DataResp<List<Dtree>> queryChooseTree(@ApiParam(name = "shopId", value = "店铺ID", required = true)
                                                 @PathVariable Long shopId) {

        List<Dtree> dtreeList = shopNonRegionService.queryChooseTree(shopId);
        return DataResp.ok(dtreeList);
    }
}
