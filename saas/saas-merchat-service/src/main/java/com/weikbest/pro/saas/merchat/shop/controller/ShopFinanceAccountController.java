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
//import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
//import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceAccountDTO;
//import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceAccountQO;
//import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * <p>
// * 店铺资金账户表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-09-11
// */ TODO
//@Slf4j
//@Api(tags = {"shop::店铺资金账户表接口"})
//@RestController
//@RequestMapping("/shop/shop-finance-account")
//public class ShopFinanceAccountController {
//
//    @Resource
//    private ShopFinanceAccountService shopFinanceAccountService;
//
//    @UseToken
//    @SaveLog(value = "新增店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "shopFinanceAccountDTO", value = "保存数据信息", required = true)
//            @Valid ShopFinanceAccountDTO shopFinanceAccountDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = shopFinanceAccountService.insert(shopFinanceAccountDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "shopFinanceAccountDTO", value = "更新数据信息", required = true)
//            @Valid ShopFinanceAccountDTO shopFinanceAccountDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = shopFinanceAccountService.updateById(id, shopFinanceAccountDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        shopFinanceAccountService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        shopFinanceAccountService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<ShopFinanceAccount> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ShopFinanceAccount shopFinanceAccount = shopFinanceAccountService.findById(id);
//        return DataResp.ok(shopFinanceAccount);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询店铺资金账户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<ShopFinanceAccount>> queryPage(
//            @ApiParam(name = "shopFinanceAccountQO", value = "查询条件")
//                    ShopFinanceAccountQO shopFinanceAccountQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<ShopFinanceAccount> pageModel = shopFinanceAccountService.queryPage(shopFinanceAccountQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
