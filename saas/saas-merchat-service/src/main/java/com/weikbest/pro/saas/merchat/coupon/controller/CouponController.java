//package com.weikbest.pro.saas.merchat.coupon.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.weikbest.pro.saas.common.annotation.log.QueryLog;
//import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
//import com.weikbest.pro.saas.common.annotation.log.SaveLog;
//import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
//import com.weikbest.pro.saas.common.annotation.token.UseToken;
//import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
//import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
//import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
//import com.weikbest.pro.saas.common.util.JsrCheckUtil;
//import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
//import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponDTO;
//import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponQO;
//import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//import java.util.Collections;
//import java.util.List;
//
///**
// * <p>
// * 优惠券表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-11-09
// */
//@Slf4j
//@Api(tags = {"coupon::优惠券表接口"})
//@RestController
//@RequestMapping("/coupon/coupon")
//public class CouponController {
//
//    @Resource
//    private CouponService couponService;
//
//    @UseToken
//    @SaveLog(value = "新增优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "couponDTO", value = "保存数据信息", required = true)
//            @Valid CouponDTO couponDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = couponService.insert(couponDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "couponDTO", value = "更新数据信息", required = true)
//            @Valid CouponDTO couponDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = couponService.updateById(id, couponDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        couponService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        couponService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<Coupon> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        Coupon coupon = couponService.findById(id);
//        return DataResp.ok(coupon);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询优惠券表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<Coupon>> queryPage(
//            @ApiParam(name = "couponQO", value = "查询条件")
//                    CouponQO couponQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<Coupon> pageModel = couponService.queryPage(couponQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
