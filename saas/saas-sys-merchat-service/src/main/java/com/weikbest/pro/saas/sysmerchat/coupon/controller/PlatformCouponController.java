package com.weikbest.pro.saas.sysmerchat.coupon.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponDataStatisticsVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;
import com.weikbest.pro.saas.merchat.coupon.service.CouponService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponDetailVO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponService;
import com.weikbest.pro.saas.sysmerchat.coupon.module.qo.PlatformCouponQO;
import com.weikbest.pro.saas.sysmerchat.coupon.util.PlatformCouponJsrCheckUtil;
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
 * 平台优惠券表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Slf4j
@Api(tags = {"coupon::平台优惠券表接口"})
@RestController
@RequestMapping("/coupon/platform-coupon")
public class PlatformCouponController {

    @Resource
    private PlatformCouponJsrCheckUtil platformCouponJsrCheckUtil;

    @Resource
    private CouponService couponService;

    @Resource
    private PlatformCouponService platformCouponService;

    @UseToken
    @SaveLog(value = "新增平台优惠券表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "platformCouponDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid PlatformCouponDTO platformCouponDTO, BindingResult bindingResult) {

        platformCouponJsrCheckUtil.validPlatformCouponDTO(bindingResult);

        boolean save = platformCouponService.insert(platformCouponDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新平台优惠券表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "platformCouponDTO", value = "更新数据信息", required = true)
            @RequestBody @Valid PlatformCouponDTO platformCouponDTO, BindingResult bindingResult) {

        platformCouponJsrCheckUtil.validPlatformCouponDTO(bindingResult);

        boolean update = platformCouponService.updateById(id, platformCouponDTO);
        return DataResp.ok(update);
    }
    
    @UseToken
    @UpdateLog(value = "更新平台优惠券表数据")
    @ApiOperation(value = "失效优惠券")
    @PutMapping("/updateDataStatus/{id}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean update = couponService.updateDataStatus(Collections.singletonList(id), DictConstant.Status.disable.getCode());
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "更新平台优惠券表数据")
    @ApiOperation(value = "批量失效优惠券")
    @PutMapping("/updateDataStatus")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        boolean update = couponService.updateDataStatus(ids, DictConstant.Status.disable.getCode());
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "更新平台优惠券表数据")
    @ApiOperation(value = "发布优惠券")
    @PutMapping("/publishCoupon/{id}")
    public DataResp<Boolean> publishCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean update = couponService.publishCoupon(id);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除平台优惠券表数据")
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
    @RemoveLog(value = "根据ID删除平台优惠券表数据")
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
    @QueryLog(value = "根据ID查询平台优惠券数据")
    @ApiOperation(value = "根据ID查询优惠券数据")
    @GetMapping("/find/{id}")
    public DataResp<PlatformCouponDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        PlatformCouponDetailVO platformCouponDetailVO = platformCouponService.findDetailVOById(id);
        return DataResp.ok(platformCouponDetailVO);
    }

    @UseToken
    @QueryLog(value = "根据ID查询平台优惠券数据统计")
    @ApiOperation(value = "根据ID查询平台优惠券数据统计")
    @GetMapping("/findDataStatistics/{id}")
    public DataResp<CouponDataStatisticsVO> findDataStatistics(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CouponDataStatisticsVO couponDataStatisticsVO = couponService.findDataStatisticsVOById(id);
        return DataResp.ok(couponDataStatisticsVO);
    }

    @UseToken
    @QueryLog(value = "分页查询平台优惠券领取数据")
    @ApiOperation(value = "分页查询平台优惠券领取数据")
    @GetMapping("/queryCustCouponRestrictPage/{id}")
    public DataResp<List<CustCouponRestrictPageVO>> queryCustCouponRestrictPage(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "custCouponRestrictQO", value = "查询条件")
                    CustCouponRestrictQO custCouponRestrictQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CustCouponRestrictPageVO> pageModel = platformCouponService.queryCustCouponRestrictPage(id, custCouponRestrictQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "分页查询平台优惠券表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CouponPageVO>> queryPage(
            @ApiParam(name = "platformCouponQO", value = "查询条件")
                    PlatformCouponQO platformCouponQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CouponPageVO> pageModel = platformCouponService.queryPageVO(platformCouponQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
