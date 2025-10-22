package com.weikbest.pro.saas.merchat.prod.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.*;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.prod.module.dto.*;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.*;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.merchat.prod.util.ProdJsrCheckUtil;
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
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"prod::商品接口"})
@RestController
@RequestMapping("/prod/prod")
public class ProdController {

    @Resource
    private ProdJsrCheckUtil prodJsrCheckUtil;

    @Resource
    private ProdService prodService;

    @UseToken
    @SaveLog(value = "新增商品", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增商品")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(@ApiParam(name = "prodDTO", value = "保存数据信息", required = true)
                                    @RequestBody @Valid ProdDTO prodDTO, BindingResult bindingResult) {
        prodJsrCheckUtil.validProdDTO(bindingResult);
        boolean save = prodService.insert(prodDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新商品", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商品")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(@ApiParam(name = "id", value = "ID", required = true)
                                    @PathVariable Long id,
                                    @ApiParam(name = "prodDTO", value = "更新数据信息", required = true)
                                    @RequestBody @Valid ProdDTO prodDTO, BindingResult bindingResult) {
        prodJsrCheckUtil.validProdDTO(bindingResult);
        boolean update = prodService.updateById(id, prodDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "商品上下架", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商品上下架")
    @PutMapping("/updateProdStatus/{prodStatus}")
    public DataResp<Boolean> updateProdStatus(@ApiParam(name = "prodStatus", value = "商品状态 1-上架中 2-已下架", required = true)
                                              @PathVariable String prodStatus,
                                              @ApiParam(name = "ids", value = "商品ID集合", required = true)
                                              @RequestBody List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            throw new WeikbestException("请传入商品信息！");
        }
        boolean update = prodService.updateProdStatus(ids, prodStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "商品加入店铺设置", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商品加入店铺设置")
    @PutMapping("/updateJoinShopMainpage/{joinShopMainpage}")
    public DataResp<Boolean> updateJoinShopMainpage(@ApiParam(name = "joinShopMainpage", value = "加入店铺首页 0-关闭 1-加入", required = true)
                                                    @PathVariable String joinShopMainpage,
                                                    @ApiParam(name = "ids", value = "商品ID集合", required = true)
                                                    @RequestBody List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            throw new WeikbestException("请传入商品信息！");
        }
        boolean update = prodService.updateJoinShopMainpage(ids, joinShopMainpage);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "商品批量设置运费", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商品批量设置运费")
    @PutMapping("/updateProdFeight/")
    public DataResp<Boolean> updateProdFeight(@ApiParam(name = "ids", value = "商品ID集合", required = true)
                                              @RequestBody List<Long> ids,
                                              @ApiParam(name = "prodFeightDTO", value = "商品运费数据", required = true)
                                              @Valid @RequestBody ProdFeightDTO prodFeightDTO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);
        boolean update = prodService.updateProdFeight(ids, prodFeightDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除商品基本信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        prodService.delete(id);
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除商品基本信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {
        prodService.deletes(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询商品基本信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ProdShowDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        ProdShowDetailVO prodVO = prodService.findDetailVOById(id);
        return DataResp.ok(prodVO);
    }

    @UseToken
    @QueryLog(value = "分页查询商品基本信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ProdListVO>> queryPage(
            @ApiParam(name = "prodQO", value = "查询条件")
            ProdQO prodQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
            PageReq pageReq) {
        IPage<ProdListVO> pageModel = prodService.queryPage(prodQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "根据ID查询装修落地页数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询装修落地页数据")
    @GetMapping("/getProdDecFloor/{id}")
    public DataResp<ProdDecFloorVO> getProdDecFloor(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        ProdDecFloorVO prodDecFloorVO = prodService.getProdDecFloorVOById(id);
        return DataResp.ok(prodDecFloorVO);
    }

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新装修落地页数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增或更新装修落地页数据")
    @PostMapping("/saveOrUpdateProdDecFloor/{id}")
    public DataResp<Boolean> saveOrUpdateProdDecFloor(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "prodDecFloorDTO", value = "商品装修落地页数据", required = true)
            @Valid @RequestBody ProdDecFloorDTO prodDecFloorDTO, BindingResult bindingResult) {
        prodJsrCheckUtil.validProdDecFloorDTO(bindingResult);
        boolean update = prodService.saveOrUpdateProdDecFloor(id, prodDecFloorDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商品左滑数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询商品左滑数据")
    @GetMapping("/getProdLeftSlide/{id}")
    public DataResp<ProdLeftSlideVO> getProdLeftSlide(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        ProdLeftSlideVO prodLeftSlideVO = prodService.getProdLeftSlideVOById(id);
        return DataResp.ok(prodLeftSlideVO);
    }

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新商品左滑数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增或更新商品左滑数据")
    @PostMapping("/saveOrUpdateProdLeftSlide/{id}")
    public DataResp<Boolean> saveOrUpdateProdLeftSlide(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "prodLeftSlideDTO", value = "商品左滑数据", required = true)
            @Valid @RequestBody ProdLeftSlideDTO prodLeftSlideDTO, BindingResult bindingResult) {
        prodJsrCheckUtil.validProdLeftSlideDTO(bindingResult);
        boolean update = prodService.saveOrUpdateProdLeftSlide(id, prodLeftSlideDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商品优惠券数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询商品优惠券数据")
    @GetMapping("/queryProdCoupon/{id}")
    public DataResp<List<ProdCouponVO>> queryProdCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        List<ProdCouponVO> prodCouponVOList = prodService.queryProdCouponVOById(id);
        return DataResp.ok(prodCouponVOList);
    }

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新商品优惠券数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增或更新商品优惠券数据")
    @PostMapping("/saveOrUpdateProdCoupon/{id}")
    public DataResp<Boolean> saveOrUpdateProdCoupon(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "prodCouponDTO", value = "商品广告回流券数据", required = true)
            @Valid @RequestBody ProdCouponDTO prodCouponDTO, BindingResult bindingResult) {
        prodJsrCheckUtil.validProdCouponDTO(bindingResult);
        boolean update = prodService.saveOrUpdateProdCouponDTO(id, prodCouponDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商品关联小程序数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询商品关联小程序数据")
    @GetMapping("/findProdAppletRelate/{id}")
    public DataResp<ProdAppletRelateVO> findProdAppletRelate(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        ProdAppletRelateVO prodAppletRelateVO = prodService.findProdAppletRelateVOById(id);
        return DataResp.ok(prodAppletRelateVO);
    }

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新商品关联小程序数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增或更新商品关联小程序数据")
    @PostMapping("/saveOrUpdateProdAppletRelate/{id}")
    public DataResp<Boolean> saveOrUpdateProdAppletRelate(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "prodAppletRelateDTO", value = "商品关联小程序数据", required = true)
            @Valid @RequestBody ProdAppletRelateDTO prodAppletRelateDTO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        // 生成小程序图片
        String appletPageUrl = prodAppletRelateDTO.getAppletPageUrl();
        String[] appletPageUrlArr = appletPageUrl.split("\\?id=");
        if (appletPageUrlArr.length <= WeikbestConstant.ONE_INT) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "小程序路径参数不合法，请输入正确的参数例如： /pages/goods/details?id=123 ");
        }
        boolean update = prodService.saveOrUpdateProdAppletRelate(id, prodAppletRelateDTO);
        return DataResp.ok(update);
    }


    @UseToken
    @QueryLog(value = "根据ID查询商品最低价格套餐信息", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询商品最低价格套餐信息")
    @GetMapping("/findMinProdCombo/{id}")
    public DataResp<MinProdComboVO> findMinProdComboPrice(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        MinProdComboVO minProdComboVO = prodService.findMinProdComboVOById(id);
        return DataResp.ok(minProdComboVO);
    }


    @UseToken
    @SaveOrUpdateLog(value = "更新商品最低价格套餐信息", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商品最低价格套餐信息")
    @PutMapping("/updateMinProdCombo")
    public DataResp<Boolean> updateMinProdCombo(
            @ApiParam(name = "minProdComboDTO", value = "商品最低价格套餐信息数据", required = true)
            @Valid MinProdComboDTO minProdComboDTO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);
        boolean update = prodService.updateMinProdCombo(minProdComboDTO);
        return DataResp.ok(update);
    }


//    ------------------------------------------------------------------------------------------------------------------


    @UseToken
    @QueryLog(value = "添加修改广告链接", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "添加修改广告链接")
    @PostMapping("/addAdLinks")
    public DataResp<?> addAdLinks(@ApiParam(name = "adLinksDTO", value = "保存数据信息", required = true)
                                  @RequestBody @Valid AdLinksDTO adLinksDTO) {
        if (prodService.addAdLinks(adLinksDTO) < 1) {
            return DataResp.error("添加链接失败!");
        }
        return DataResp.ok("添加链接成功!");
    }

    @UseToken
    @QueryLog(value = "添加修改广告回传", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "添加修改广告回传")
    @PostMapping("/addAdLinksCallback")
    public DataResp<?> addAdLinksCallback(@ApiParam(name = "adLinksDTO", value = "保存数据信息", required = true)
                                  @RequestBody @Valid AdLinksDTO adLinksDTO) {
        if (prodService.addAdLinksCallback(adLinksDTO) < 1) {
            return DataResp.error("添加链接失败!");
        }
        return DataResp.ok("添加链接成功!");
    }


    @UseToken
    @QueryLog(value = "卷后订单回传", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "卷后订单回传")
    @PostMapping("/backHaul")
    public DataResp<?> backHaul(@ApiParam(name = "BackHaulDTO", value = "保存数据信息", required = true)
                                @RequestBody @Valid BackHaulDTO backHaulDTO) {
        if (prodService.backHaul(backHaulDTO) < 1) {
            return DataResp.error("订单回传修改失败!");
        }
        return DataResp.ok("订单回传修改成功!");
    }


    @UseToken
    @QueryLog(value = "广告链接回显", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "广告链接回显")
    @GetMapping("/adLinksEcho/{id}")
    public DataResp<AdLinksDTO> adLinksEcho(@PathVariable Long id) {
        return DataResp.ok(prodService.adLinksEcho(id));
    }


    @UseToken
    @QueryLog(value = "根据ID查询套餐或者多规格信息", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询套餐或者多规格信息")
    @GetMapping("/getComboByProdId/{id}/{setMealType}")
    public DataResp<Object> getComboByProdId(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "setMealType", value = "套餐类型", required = true)
            @PathVariable Integer setMealType) {
        return DataResp.ok(prodService.getComboByProdId(id, setMealType));
    }


    @UseToken
    @QueryLog(value = "随手购列表", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "随手购列表")
    @GetMapping("/shoppingList")
    public DataResp<List<ProdShoppingListVo>> shoppingList(@ApiParam(name = "prodQO", value = "查询条件")
                                                           ProdQO prodQO,
                                                           @ApiParam(name = "pageReq", value = "分页参数", required = true)
                                                           PageReq pageReq) {
        IPage<ProdShoppingListVo> pageModel = prodService.getShoppingList(prodQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "商品关联随手购商品", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商品关联随手购商品")
    @GetMapping("/IdRelevancyShoppingId/{id}/{shoppingId}/{shoppingComboId}")
    public DataResp<Object> IdRelevancyShoppingId(@ApiParam(name = "id", value = "ID", required = true)
                                                  @PathVariable Long id,
                                                  @ApiParam(name = "shoppingId", value = "随手购商品Id", required = true)
                                                  @PathVariable Long shoppingId,
                                                  @ApiParam(name = "shoppingComboId", value = "随手购商品套餐Id", required = true)
                                                  @PathVariable Long shoppingComboId) {
        prodService.IdRelevancyShoppingId(id, shoppingId, shoppingComboId);
        return DataResp.ok();
    }

}
