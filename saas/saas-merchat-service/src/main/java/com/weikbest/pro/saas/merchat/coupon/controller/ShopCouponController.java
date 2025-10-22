package com.weikbest.pro.saas.merchat.coupon.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponProdEntryQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.*;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.merchat.coupon.util.CouponJsrCheckUtil;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAppletRelate;
import com.weikbest.pro.saas.merchat.prod.service.ProdAppletRelateService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 店铺优惠券表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Slf4j
@Api(tags = {"coupon::店铺优惠券表接口"})
@RestController
@RequestMapping("/coupon/shop-coupon")
public class ShopCouponController {

    @Resource
    private CouponJsrCheckUtil couponJsrCheckUtil;

    @Resource
    private CouponService couponService;

    @Resource
    private ProdAppletRelateService prodAppletRelateService;

    @UseToken
    @SaveLog(value = "创建回流优惠券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "创建回流优惠券")
    @PostMapping("/insertShopRefluxCoupon")
    public DataResp<Boolean> insertShopRefluxCoupon(
            @ApiParam(name = "shopRefluxCouponDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopRefluxCouponDTO shopRefluxCouponDTO, BindingResult bindingResult) {

        couponJsrCheckUtil.validShopRefluxCouponDTO(bindingResult);

        Long prodId = shopRefluxCouponDTO.getProdId();
        ProdAppletRelate prodAppletRelate = prodAppletRelateService.getById(prodId);
        if (ObjectUtil.isNull(prodAppletRelate)) {
            throw new WeikbestException("选择的商品未配置小程序访问信息，请先配置！");
        }

        boolean save = couponService.insertShopRefluxCoupon(shopRefluxCouponDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新回流优惠券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新回流优惠券")
    @PutMapping("/updateShopRefluxCoupon/{id}")
    public DataResp<Boolean> updateShopRefluxCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "shopRefluxCouponDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopRefluxCouponDTO shopRefluxCouponDTO, BindingResult bindingResult) {

        couponJsrCheckUtil.validShopRefluxCouponDTO(bindingResult);

        Long prodId = shopRefluxCouponDTO.getProdId();
        ProdAppletRelate prodAppletRelate = prodAppletRelateService.getById(prodId);
        if (ObjectUtil.isNull(prodAppletRelate)) {
            throw new WeikbestException("选择的商品未配置小程序访问信息，请先配置！");
        }

        boolean update = couponService.updateShopRefluxCouponById(id, shopRefluxCouponDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @SaveLog(value = "创建立减券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "创建立减券")
    @PostMapping("/insertShopPromptlyCouponDTO")
    public DataResp<Boolean> insertShopPromptlyCoupon(
            @ApiParam(name = "shopPromptlyCouponDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopPromptlyCouponDTO shopPromptlyCouponDTO, BindingResult bindingResult) {

        couponJsrCheckUtil.validShopPromptlyCouponDTO(bindingResult);

        boolean save = couponService.insertShopPromptlyCoupon(shopPromptlyCouponDTO);
        return DataResp.ok(save);
    }


    @UseToken
    @UpdateLog(value = "更新立减券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新立减券")
    @PutMapping("/updateShopPromptlyCouponDTO/{id}")
    public DataResp<Boolean> updateShopPromptlyCouponDTO(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "shopPromptlyCouponDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopPromptlyCouponDTO shopPromptlyCouponDTO, BindingResult bindingResult) {

        couponJsrCheckUtil.validShopPromptlyCouponDTO(bindingResult);

        boolean update = couponService.updateShopPromptlyCouponById(id, shopPromptlyCouponDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Coupon coupon = couponService.findById(id);
        if(!StrUtil.equals(coupon.getCouponStatus(), DictConstant.CouponStatus.TYPE_1.getCode()) && StrUtil.equals(coupon.getDataStatus(), DictConstant.Status.enable.getCode())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "生效中的优惠券无法删除，请先进行失效操作！");
        }

        couponService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        List<Coupon> couponList = couponService.listByIds(ids);
        for (Coupon coupon : couponList) {
            if(!StrUtil.equals(coupon.getCouponStatus(), DictConstant.CouponStatus.TYPE_1.getCode()) && StrUtil.equals(coupon.getDataStatus(), DictConstant.Status.enable.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "生效中的优惠券无法删除，请先进行失效操作！");
            }
        }

        couponService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询店铺回流优惠券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询店铺回流优惠券")
    @GetMapping("/findShopRefluxCoupon/{id}")
    public DataResp<ShopRefluxCouponVO> findShopRefluxCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopRefluxCouponVO shopRefluxCouponVO = couponService.findShopRefluxCouponVOById(id);
        return DataResp.ok(shopRefluxCouponVO);
    }

    @UseToken
    @QueryLog(value = "根据ID查询店铺立减优惠券", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询店铺立减优惠券")
    @GetMapping("/findShopPromptlyCoupon/{id}")
    public DataResp<ShopPromptlyCouponVO> findShopPromptlyCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopPromptlyCouponVO shopPromptlyCouponVO = couponService.findShopPromptlyCouponVOById(id);
        return DataResp.ok(shopPromptlyCouponVO);
    }

    @UseToken
    @UpdateLog(value = "更新店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "发布优惠券")
    @PutMapping("/publishCoupon/{id}")
    public DataResp<Boolean> publishCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean update = couponService.publishCoupon(id);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "更新店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "失效优惠券")
    @PutMapping("/updateDataStatus/{id}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean update = couponService.updateDataStatus(Collections.singletonList(id), DictConstant.Status.disable.getCode());
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "更新店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量失效优惠券")
    @PutMapping("/updateDataStatus")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        boolean update = couponService.updateDataStatus(ids, DictConstant.Status.disable.getCode());
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "分页查询店铺优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CouponPageVO>> queryPage(
            @ApiParam(name = "couponQO", value = "查询条件")
                    CouponQO couponQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CouponPageVO> pageModel = couponService.queryPageVO(couponQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }


    @UseToken
    @QueryLog(value = "查询商品关联的优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询商品关联的优惠券表数据")
    @GetMapping("/queryCouponProd")
    public DataResp<List<Coupon>> queryCouponProd(
            @ApiParam(name = "couponProdEntryQO", value = "查询条件")
                    CouponProdEntryQO couponProdEntryQO) {

        List<Coupon> couponList = couponService.queryCouponProd(couponProdEntryQO);
        return DataResp.ok(couponList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询优惠券数据统计", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询优惠券数据统计")
    @GetMapping("/findDataStatistics/{id}")
    public DataResp<CouponDataStatisticsVO> findDataStatistics(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CouponDataStatisticsVO couponDataStatisticsVO = couponService.findDataStatisticsVOById(id);
        return DataResp.ok(couponDataStatisticsVO);
    }

    @UseToken
    @QueryLog(value = "分页查询优惠券领取数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询优惠券领取数据")
    @GetMapping("/queryCustCouponRestrictPage/{id}")
    public DataResp<List<CustCouponRestrictPageVO>> queryCustCouponRestrictPage(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "custCouponRestrictQO", value = "查询条件")
                    CustCouponRestrictQO custCouponRestrictQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        if (StrUtil.isEmpty(custCouponRestrictQO.getCouponType())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "优惠券类型不为空！");
        }
        // 根据优惠券ID查询优惠券
        Coupon coupon = couponService.findById(id);
        custCouponRestrictQO.setCouponId(id);
        // 设置优惠券类型
        custCouponRestrictQO.setCouponType(coupon.getCouponType());

        IPage<CustCouponRestrictPageVO> pageModel = couponService.queryCustCouponRestrictPage(custCouponRestrictQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
