package com.weikbest.pro.saas.applet.commodity.controller;

import com.weikbest.pro.saas.applet.commodity.module.qo.AppProdQO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AddParametersVo;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdDecFloorVO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdDetailVO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdLeftSlideVO;
import com.weikbest.pro.saas.applet.commodity.service.AppCommodityService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
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
import java.util.Map;

/**
 * <p>
 * App商品信息
 * </p>
 *
 * @author weik
 * @since 2022-09-06
 */

@Slf4j
@Api(tags = {"appcommodity::APP商品接口"})
@RestController
@RequestMapping("/appcommodity")
public class AppCommodityController {

    @Resource
    private ProdService prodService;

    @Resource
    private AppCommodityService appCommodityService;

    @QueryLog(value = "根据ID查询商品详细")
    @ApiOperation(value = "根据ID查询商品详细", notes = "1，获取商品基本信息，t_mmdm_prod\n" +
            "          2，获取商品详情页轮播图信息，t_mmdm_prod_mainimg\n" +
            "          3，获取商品价格信息，t_mmdm_prod_price\n" +
            "          4，获取商品详细图信息，t_mmdm_prod_detail\n" +
            "          5，获取商品样式拆分表，t_mmdm_prod_theme\n" +
            "          7，获取商品销售套餐及规格属性 t_mmdm_prod_combo 和 t_mmdm_prod_combo_attr_relate\n" +
            "          7，获取运费信息共4个表")
    @GetMapping("/find/{id}")
    public DataResp<AppProdDetailVO> find(@ApiParam(name = "id", value = "ID", required = true) @PathVariable Long id ,
                                          @ApiParam(name = "click", value = "点击次数") Integer click) {
        return DataResp.ok(appCommodityService.findProdDetail(id,click));
    }


    @QueryLog(value = "分页查询商品基本信息表数据")
    @ApiOperation(value = "分页查询", notes = "1，如果 name （商品名称）有值，则查询所有营销分类下的商品信息\n" +
            "          2，如果 shopId （关联店铺ID）有值，则查询该店铺下的商品信息\n" +
            "          3，如果 semCategotyId （关联商品小程序类目ID ）有值，则查询该分类下的商品信息\n" +
            "          4，如果 types 不为空，则查询所有商品营销分类下的商品信息")
    @GetMapping("/queryPage")
    public DataResp<Map<String, Object>> queryPage(
            @ApiParam(name = "appProdQO", value = "查询条件")
                    AppProdQO appProdQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        List<AppProdDTO> appProdDTOS = appCommodityService.queryPageCommodity(appProdQO, pageReq);
        return DataResp.BuilderMap.create().ok().put("list", appProdDTOS).build();
    }

    @QueryLog(value = "分页查询商品基本信息表数据")
    @ApiOperation(value = "分页查询-聚合页", notes = "1，如果 name （商品名称）有值，则查询所有营销分类下的商品信息\n" +
            "          2，如果 shopId （关联店铺ID）有值，则查询该店铺下的商品信息\n" +
            "          3，如果 semCategotyId （关联商品小程序类目ID ）有值，则查询该分类下的商品信息\n" +
            "          4，如果 types 不为空，则查询所有商品营销分类下的商品信息\n"+
            "          5. 如果prodId不为空，则将该prodId的商品放在第一个，并返回优惠后的金额")
    @GetMapping("/queryPageByAggregationPage")
    public DataResp<Map<String, Object>> queryPageByAggregationPage(
            @ApiParam(name = "appProdQO", value = "查询条件")
            AppProdQO appProdQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
            PageReq pageReq) {

        List<AppProdDTO> appProdDTOS = appCommodityService.queryPageCommodityByAggregationPage(appProdQO, pageReq);
        return DataResp.BuilderMap.create().ok().put("list", appProdDTOS).build();
    }

    @QueryLog(value = "根据商品ID查询左滑设置信息")
    @ApiOperation(value = "根据商品ID查询左滑设置信息")
    @GetMapping("/findProdLeftSlide/{id}")
    public DataResp<AppProdLeftSlideVO> findProdLeftSlide(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        AppProdLeftSlideVO appProdLeftSlideVO = appCommodityService.findProdLeftSlide(id);
        return DataResp.ok(appProdLeftSlideVO);
    }

    @QueryLog(value = "根据商品ID查询装修落地页信息")
    @ApiOperation(value = "根据商品ID查询装修落地页信息")
    @GetMapping("/findProdDecFloor/{id}")
    public DataResp<AppProdDecFloorVO> findProdDecFloor(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        AppProdDecFloorVO appProdDecFloorVO = appCommodityService.findProdDecFloor(id);

        return DataResp.ok(appProdDecFloorVO);
    }

    @QueryLog(value = "根据商品ID查询装修落地页信息")
    @ApiOperation(value = "根据商品ID查询多添加的参数信息")
    @GetMapping("/addParameters/{id}")
    public DataResp<AddParametersVo> addParameters(@ApiParam(name = "id", value = "ID", required = true) @PathVariable Long id) {
        return DataResp.ok(appCommodityService.addParameters(id));
    }


    @QueryLog(value = "根据商品Id查询随手购商品信息")
    @ApiOperation(value = "根据商品Id查询随手购商品信息")
    @GetMapping("/shoppingProdById/{id}")
    public DataResp<AppProdDetailVO> shoppingProdById(@ApiParam(name = "id", value = "ID", required = true) @PathVariable Long id) {
        return DataResp.ok(appCommodityService.shoppingProdById(id));
    }

}
