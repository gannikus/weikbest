//package com.weikbest.pro.saas.merchat.shop.controller;
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
//import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
//import com.weikbest.pro.saas.merchat.shop.module.dto.ShopVisitDTO;
//import com.weikbest.pro.saas.merchat.shop.module.qo.ShopVisitQO;
//import com.weikbest.pro.saas.merchat.shop.service.ShopVisitService;
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
// * 店铺访问表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2023-02-04
// */
//@Slf4j
//@Api(tags = {"shop::店铺访问表接口"})
//@RestController
//@RequestMapping("/shop/shop-visit")
//public class ShopVisitController {
//
//    @Resource
//    private ShopVisitService shopVisitService;
//
//    @UseToken
//    @SaveLog(value = "新增店铺访问表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "shopVisitDTO", value = "保存数据信息", required = true)
//            @Valid ShopVisitDTO shopVisitDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = shopVisitService.insert(shopVisitDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新店铺访问表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "shopVisitDTO", value = "更新数据信息", required = true)
//            @Valid ShopVisitDTO shopVisitDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = shopVisitService.updateById(id, shopVisitDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除店铺访问表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        shopVisitService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除店铺访问表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        shopVisitService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询店铺访问表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<ShopVisit> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ShopVisit shopVisit = shopVisitService.findById(id);
//        return DataResp.ok(shopVisit);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询店铺访问表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<ShopVisit>> queryPage(
//            @ApiParam(name = "shopVisitQO", value = "查询条件")
//                    ShopVisitQO shopVisitQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<ShopVisit> pageModel = shopVisitService.queryPage(shopVisitQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
