package com.weikbest.pro.saas.merchat.shop.controller;

import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.util.ThirdConfigUtil;
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
 * 商户店铺表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Slf4j
@Api(tags = {"shop::商户店铺表接口"})
@RestController
@RequestMapping("/shop/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @Resource
    private ShopThirdService shopThirdService;

//    @UseToken
//    @SaveLog(value = "开店", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "开店")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "shopSetupDTO", value = "保存数据信息", required = true)
//            @Valid ShopSetupDTO shopSetupDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        // 校验商户号:1个店铺对应1个微信支付商户号
//        String wxPayMchId = shopSetupDTO.getWxPayMchId();
//        long count = shopThirdService.count(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_MCH_ID, wxPayMchId));
//        if (count > WeikbestConstant.ZERO_LONG) {
//            throw new WeikbestException("该商户号已绑定别的店铺，请重新填写！");
//        }
////        // 1个商户的所有店铺能有1个商户号成为分账收款方账号
////        String isRoutingReceive = shopSetupDTO.getIsRoutingReceive();
////        if (StrUtil.equals(isRoutingReceive, DictConstant.Whether.yes.getCode())) {
////            List<Shop> shopList = shopService.list(new QueryWrapper<Shop>().eq(Shop.BUSINESS_ID, shopSetupDTO.getBusinessId()));
////            if (CollectionUtil.isNotEmpty(shopList)) {
////                List<Long> shopIdList = shopList.stream().map(Shop::getId).collect(Collectors.toList());
////                long isRoutingReceiveCount = shopThirdService.count(new QueryWrapper<ShopThird>().in(ShopThird.ID, shopIdList)
////                        .eq(ShopThird.IS_ROUTING_RECEIVE, DictConstant.Whether.yes.getCode()));
////                if (isRoutingReceiveCount > WeikbestConstant.ZERO_LONG) {
////                    throw new WeikbestException("您已有分账收款账号！");
////                }
////            }
////        }
//
//        boolean save = shopService.insert(shopSetupDTO);
//        return DataResp.ok(save);
//    }


    @UseToken
    @QueryLog(value = "根据ID查询商户店铺表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ShopVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopVO shopVO = shopService.findDetailVOById(id);
        return DataResp.ok(shopVO);
    }

    @UseToken
    @QueryLog(value = "根据商户ID查询商户店铺表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据商户ID查询商户店铺表")
    @GetMapping("/queryByBusinessId/{busienssId}")
    public DataResp<List<Shop>> queryByBusinessId(
            @ApiParam(name = "busienssId", value = "商户ID", required = true)
            @PathVariable Long busienssId) {

        List<Shop> shopList = shopService.queryByBusinessId(busienssId);
        return DataResp.ok(shopList);
    }

    @UseToken
    @QueryLog(value = "根据商户ID查询商户店铺,返回数据字典", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据商户ID查询商户店铺,返回数据字典")
    @GetMapping("/queryDictByBusinessId/{busienssId}")
    public DataResp<List<DictEntry>> queryDictByBusinessId(
            @ApiParam(name = "busienssId", value = "商户ID", required = true)
            @PathVariable Long busienssId) {

        List<DictEntry> shopList = shopService.queryDictByBusinessId(busienssId);
        return DataResp.ok(shopList);
    }


    @UseToken
    @QueryLog(value = "根据店铺ID查询微信合作关系列表", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据店铺ID查询微信合作关系列表")
    @GetMapping("/queryPartnerships/{shopId}")
    public DataResp<Map> queryPartnerships(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId) {

        WxPayService wxPayService = shopThirdService.findWxPayServiceById(shopId);

        String v3 = ThirdConfigUtil.partnerships(wxPayService);
        Map map = JsonUtils.json2Bean(v3, Map.class);
        return DataResp.ok(map);
    }

    @UseToken
    @QueryLog(value = "查询店铺小程序配置表数据")
    @ApiOperation(value = "查询店铺小程序配置表数据")
    @GetMapping("/queryByShop")
    public DataResp<List<AppletConfig>> queryByShop(@ApiParam(name = "shopId", value = "店铺ID", required = true) @RequestParam(value = "shopId") Long shopId) {
        List<AppletConfig> appletConfigList = shopService.listByShopId(shopId);
        return DataResp.ok(appletConfigList);
    }
}
