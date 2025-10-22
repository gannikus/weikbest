package com.weikbest.pro.saas.sysmerchat.coupon.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneAllDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneGroupDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneGroupVO;
import com.weikbest.pro.saas.sysmerchat.coupon.service.PlatformCouponSceneService;
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
 * 平台优惠券使用场景表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Slf4j
@Api(tags = {"coupon::平台优惠券使用场景表接口"})
@RestController
@RequestMapping("/coupon/platform-coupon-scene")
public class PlatformCouponSceneController {

    @Resource
    private PlatformCouponSceneService platformCouponSceneService;

    @UseToken
    @SaveLog(value = "新增平台优惠券使用场景表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "platformCouponSceneGroupDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid PlatformCouponSceneGroupDTO platformCouponSceneGroupDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = platformCouponSceneService.insertBatch(platformCouponSceneGroupDTO);
        return DataResp.ok(save);
    }


//    @UseToken
//    @SaveLog(value = "新增平台优惠券使用场景表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insertAll")
//    public DataResp<Boolean> insertAll(
//            @ApiParam(name = "platformCouponSceneAllDTO", value = "保存数据信息", required = true)
//            @RequestBody @Valid PlatformCouponSceneAllDTO platformCouponSceneAllDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = platformCouponSceneService.insertAll(platformCouponSceneAllDTO);
//        return DataResp.ok(save);
//    }

//    @UseToken
//    @UpdateLog(value = "更新平台优惠券使用场景表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "platformCouponSceneDTO", value = "更新数据信息", required = true)
//            @Valid PlatformCouponSceneDTO platformCouponSceneDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = platformCouponSceneService.updateById(id, platformCouponSceneDTO);
//        return DataResp.ok(update);
//    }

    @UseToken
    @RemoveLog(value = "根据ID删除平台优惠券使用场景表数据")
    @ApiOperation(value = "根据平台优惠券使用场景ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        platformCouponSceneService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

//    @UseToken
//    @RemoveLog(value = "根据ID列表删除平台优惠券使用场景表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        platformCouponSceneService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

//    @UseToken
//    @QueryLog(value = "根据ID查询平台优惠券使用场景表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<PlatformCouponScene> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        PlatformCouponScene platformCouponScene = platformCouponSceneService.findById(id);
//        return DataResp.ok(platformCouponScene);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询平台优惠券使用场景表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPageVO")
//    public DataResp<List<PlatformCouponScene>> queryPageVO(
//            @ApiParam(name = "platformCouponSceneQO", value = "查询条件")
//                    PlatformCouponSceneQO platformCouponSceneQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<PlatformCouponScene> pageModel = platformCouponSceneService.queryPageVO(platformCouponSceneQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }

    @UseToken
    @QueryLog(value = "查询平台优惠券使用场景表")
    @ApiOperation(value = "查询平台优惠券使用场景表")
    @GetMapping("/queryPlatformCouponScene")
    public DataResp<List<PlatformCouponSceneGroupVO>> queryPlatformCouponSceneGroupVOList() {

        List<PlatformCouponSceneGroupVO> platformCouponSceneGroupVOList = platformCouponSceneService.queryPlatformCouponSceneGroupVOList();
        return DataResp.ok(platformCouponSceneGroupVOList);
    }
}
