package com.weikbest.pro.saas.applet.coupon.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterSaleDTO;
import com.weikbest.pro.saas.applet.coupon.module.qo.AppCustProdCouponQO;
import com.weikbest.pro.saas.applet.coupon.module.vo.*;
import com.weikbest.pro.saas.applet.coupon.service.AppCouponService;
import com.weikbest.pro.saas.applet.shop.module.qo.AppShopCouponQO;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shopcoupon::商户店铺优惠券接口"})
@RestController
@RequestMapping("/appshopcoupon")
public class AppCouponController {

    @Resource
    private AppCouponService appCouponService;
    @Resource
    private CouponService couponService;

    @QueryLog(value = "【废弃】根据ID查询商户店铺优惠券数据")
    @ApiOperation(value = "【废弃】根据ID查询商户店铺优惠券数据")
    @GetMapping("/querycoupons/{shopId}")
    public DataResp<List<AppCouponVO>> querycoupons(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId) {

        AppShopCouponQO appShopCouponQO = new AppShopCouponQO();
        appShopCouponQO.setShopId(shopId);
        appShopCouponQO.setCustomerId(1L);

        List<AppCouponVO> queryshopcoupons = appCouponService.queryshopcoupons(appShopCouponQO);
        return DataResp.ok(queryshopcoupons);
    }

    @AppToken
    @QueryLog(value = "根据商品ID查询该商品下优惠券信息【回流优惠券及平台券】")
    @ApiOperation(value = "根据商品ID查询该商品下优惠券信息【回流优惠券及平台券】",notes = "回流优惠券和平台优惠券")
    @GetMapping("/queryCouponsByProdId/{prodId}/{appId}")
    public DataResp<AppProdCouponVO> queryCouponsByProdId(
            @ApiParam(name = "prodId", value = "商品ID", required = true)
            @PathVariable Long prodId,
            @ApiParam(name = "appId", value = "小程序ID", required = true)
            @PathVariable String appId) {

        AppProdCouponVO appProdCouponVO = appCouponService.queryCouponsByProdId(prodId,appId);

        return DataResp.ok(appProdCouponVO);
    }

    @AppToken
    @QueryLog(value = "根据商品ID查询该商品下优惠券信息【立减券】")
    @ApiOperation(value = "根据商品ID查询该商品下优惠券信息【立减券】",notes = "立减券")
    @GetMapping("/queryReduceCouponByProdId/{prodId}/{appId}")
    public DataResp<AppProdCouponVO> queryReduceCouponByProdId(
            @ApiParam(name = "prodId", value = "商品ID", required = true)
            @PathVariable Long prodId,
            @ApiParam(name = "appId", value = "小程序ID", required = true)
            @PathVariable String appId) {

        AppProdCouponVO appProdCouponVO = appCouponService.queryReduceCouponByProdId(prodId,appId);

        return DataResp.ok(appProdCouponVO);
    }

    @AppToken
    @QueryLog(value = "获取平台优惠券")
    @ApiOperation(value = "获取平台优惠券",notes = "新用户进入小程序点击领取 & 用户在品质好货banner点击领取")
    @GetMapping("/queryCouponScene/{appId}")
    public DataResp<AppCouponSceneVO> queryCouponScene(
            @ApiParam(name = "appId", value = "小程序ID", required = true)
            @PathVariable String appId) {

        AppCouponSceneVO appCouponSceneVO = appCouponService.queryCouponScene(appId);

        return DataResp.ok(appCouponSceneVO);
    }

    @AppToken
    @SaveLog(value = "保存领券信息")
    @ApiOperation(value = "保存领券信息",notes = "用户点击领取后，先调用该接口保存领券信息后再进行发券操作")
    @PostMapping("/saveCustCouponRestricts")
    public DataResp<Boolean> saveCustCouponRestricts(
            @ApiParam(name = "appCustCouponRestrictVOS", value = "客户领取优惠券信息")
            @RequestBody List<AppCustCouponRestrictVO> appCustCouponRestrictVOS) {

        Boolean bool = appCouponService.saveCustCouponRestricts(appCustCouponRestrictVOS);

        return DataResp.ok(bool);
    }


    @AppToken
    @QueryLog(value = "我的-可使用优惠券")
    @ApiOperation(value = "我的-可使用优惠券")
    @GetMapping("/queryUseableCustCouponRestricts")
    public DataResp<List<AppCustCouponRestrictDTO>> queryUseableCustCouponRestricts(
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        int count = appCouponService.queryCountUseableCustCouponRestricts();
        List<AppCustCouponRestrictDTO> list = appCouponService.queryUseableCustCouponRestricts(pageReq);
        return PageResp.ok((long)count,list);
    }

    @AppToken
    @QueryLog(value = "我的-已过期优惠券")
    @ApiOperation(value = "我的-已过期优惠券")
    @GetMapping("/queryExpiredCustCouponRestricts")
    public DataResp<List<AppCustCouponRestrictDTO>> queryExpiredCustCouponRestricts(
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        int count = appCouponService.queryCountExpiredCustCouponRestricts();
        List<AppCustCouponRestrictDTO> list = appCouponService.queryExpiredCustCouponRestricts(pageReq);
        return PageResp.ok((long)count,list);
    }

    @AppToken
    @QueryLog(value = "我的-已使用优惠券")
    @ApiOperation(value = "我的-已使用优惠券")
    @GetMapping("/queryUsedCustCouponRestricts")
    public DataResp<List<AppCustCouponRestrictDTO>> queryUsedCustCouponRestricts(
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        int count = appCouponService.queryCountUsedCustCouponRestricts();
        List<AppCustCouponRestrictDTO> list = appCouponService.queryUsedCustCouponRestricts(pageReq);
        return PageResp.ok((long)count,list);
    }

    @AppToken
    @QueryLog(value = "当前商品-可用优惠券")
    @ApiOperation(value = "当前商品-可用优惠券")
    @GetMapping("/queryUseableCustCouponProds")
    public DataResp<List<AppCustCouponRestrictDTO>> queryUseableCustCouponProds(
            @ApiParam(name = "appCustProdCouponQO", value = "查询条件")
                    AppCustProdCouponQO appCustProdCouponQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        if (Objects.isNull(appCustProdCouponQO.getIsPage())){
            appCustProdCouponQO.setIsPage("1");
        }
        List<AppCustCouponRestrictDTO> list = appCouponService.queryUseableCustCouponProds(appCustProdCouponQO,pageReq);

        Coupon coupon = couponService.getById(appCustProdCouponQO.getCouponId());
        ArrayList<AppCustCouponRestrictDTO> list1 = new ArrayList<>();
        AppCustCouponRestrictDTO appCustCouponRestrictDTO = new AppCustCouponRestrictDTO();
        long count = (long) list.size();
        if (CollectionUtil.isEmpty(list)) {
            if (Objects.isNull(appCustProdCouponQO.getCouponId())){
                return PageResp.ok(count,list);
            }
            Date date = new Date();
            if (!(DateUtil.compare(date,coupon.getUseStartTime()) > 0 && DateUtil.compare(date,coupon.getUseEndTime())<0)){
                return PageResp.ok(count,list);
            }
            if (appCustProdCouponQO.getComboPrice().compareTo(coupon.getCouponUsePrice()) < 0){
                return PageResp.ok(count,list);

            }
            appCustCouponRestrictDTO.setName(coupon.getName());
            appCustCouponRestrictDTO.setUseEndTime(coupon.getGetEndTime());
            appCustCouponRestrictDTO.setEventEndTime(coupon.getEventEndTime());
            appCustCouponRestrictDTO.setDiscountAmount(coupon.getDiscountAmount());
            appCustCouponRestrictDTO.setCouponType(coupon.getCouponType());
            appCustCouponRestrictDTO.setCouponUseType(coupon.getCouponUseType());
            appCustCouponRestrictDTO.setCouponUsePrice(coupon.getCouponUsePrice());
            appCustCouponRestrictDTO.setAppId(coupon.getAppId());
            list1.add(appCustCouponRestrictDTO);
            return PageResp.ok(Long.parseLong(String.valueOf(list1.size())),list1);
        }else {
            if (Objects.isNull(appCustProdCouponQO.getCouponId())){
                return PageResp.ok(count,list);
            }else {
                List<AppCustCouponRestrictDTO> collect = list.stream().filter(aa -> appCustProdCouponQO.getCouponId().equals(aa.getCouponId())).collect(Collectors.toList());
                if (CollectionUtil.isEmpty(collect)){
                    Date date = new Date();
                    if (!(DateUtil.compare(date,coupon.getUseStartTime()) > 0 && DateUtil.compare(date,coupon.getUseEndTime())<0)){
                        return PageResp.ok(count,list);
                    }
                    if (appCustProdCouponQO.getComboPrice().compareTo(coupon.getCouponUsePrice()) < 0){
                        return PageResp.ok(count,list);
                    }
                    appCustCouponRestrictDTO.setName(coupon.getName());
                    appCustCouponRestrictDTO.setUseEndTime(coupon.getGetEndTime());
                    appCustCouponRestrictDTO.setEventEndTime(coupon.getEventEndTime());
                    appCustCouponRestrictDTO.setDiscountAmount(coupon.getDiscountAmount());
                    appCustCouponRestrictDTO.setCouponType(coupon.getCouponType());
                    appCustCouponRestrictDTO.setCouponUseType(coupon.getCouponUseType());
                    appCustCouponRestrictDTO.setCouponUsePrice(coupon.getCouponUsePrice());
                    appCustCouponRestrictDTO.setAppId(coupon.getAppId());
                    list1.add(appCustCouponRestrictDTO);
                    return PageResp.ok(Long.parseLong(String.valueOf(list1.size())),list1);
                }else {
                    return PageResp.ok((long)collect.size(),collect);
                }

            }

        }
    }

    @AppToken
    @QueryLog(value = "当前商品-不可用优惠券")
    @ApiOperation(value = "当前商品-不可用优惠券")
    @GetMapping("/queryUnuseableCustCouponProds")
    public DataResp<List<AppCustCouponRestrictDTO>> queryUnuseableCustCouponProds(
            @ApiParam(name = "appCustProdCouponQO", value = "查询条件")
                    AppCustProdCouponQO appCustProdCouponQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        int count = appCouponService.queryCountUnuseableCustCouponProds(appCustProdCouponQO);
        List<AppCustCouponRestrictDTO> list = appCouponService.queryUnuseableCustCouponProds(appCustProdCouponQO,pageReq);
        return PageResp.ok((long)count,list);
    }

    @AppToken
    @QueryLog(value = "通过优惠券ID查询限时优惠商品信息【立减券或平台券】")
    @ApiOperation(value = "通过优惠券ID查询限时优惠商品信息【立减券或平台券】",notes = "点击优惠券列表中的具体优惠券，调用该方法返回用券限时优惠商品列表")
    @GetMapping("/queryPageCommodityByCouponId/{couponId}")
    public DataResp<Map<String,Object>> queryPageCommodityByCouponId(
            @ApiParam(name = "couponId", value = "优惠券ID", required = true)
                    @PathVariable Long couponId,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        Map<String,Object> map = appCouponService.queryPageCommodityByCouponId(couponId,pageReq);

        return PageResp.ok(map);
    }

    @AppToken
    @QueryLog(value = "通过卡包URL获取客户领券信息")
    @ApiOperation(value = "通过卡包URL获取客户领券信息")
    @PostMapping("/getCouponCodeByURL")
    public DataResp<AppCustCouponRestrictVO> getCouponCodeByURL(
            @ApiParam(name = "url", value = "卡包进入小程序URL", required = true)
            @RequestParam String url) {

        AppCustCouponRestrictVO vo = appCouponService.getCouponCodeByURL(url);

        return PageResp.ok(vo);
    }

}
