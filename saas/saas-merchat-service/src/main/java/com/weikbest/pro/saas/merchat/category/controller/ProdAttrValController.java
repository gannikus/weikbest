//package com.weikbest.pro.saas.merchat.category.controller;
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
//import com.weikbest.pro.saas.merchat.category.entity.ProdAttrVal;
//import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrValDTO;
//import com.weikbest.pro.saas.merchat.category.module.qo.ProdAttrValQO;
//import com.weikbest.pro.saas.merchat.category.service.ProdAttrValService;
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
// * 商品属性值表（本期不用） 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-10-01
// */
//@Slf4j
//@Api(tags = {"category::商品属性值表（本期不用）接口"})
//@RestController
//@RequestMapping("/category/prod-attr-val")
//public class ProdAttrValController {
//
//    @Resource
//    private ProdAttrValService prodAttrValService;
//
//    @UseToken
//    @SaveLog(value = "新增商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "prodAttrValDTO", value = "保存数据信息", required = true)
//            @Valid ProdAttrValDTO prodAttrValDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = prodAttrValService.insert(prodAttrValDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "prodAttrValDTO", value = "更新数据信息", required = true)
//            @Valid ProdAttrValDTO prodAttrValDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = prodAttrValService.updateById(id, prodAttrValDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        prodAttrValService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        prodAttrValService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<ProdAttrVal> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ProdAttrVal prodAttrVal = prodAttrValService.findById(id);
//        return DataResp.ok(prodAttrVal);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询商品属性值表（本期不用）数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<ProdAttrVal>> queryPage(
//            @ApiParam(name = "prodAttrValQO", value = "查询条件")
//                    ProdAttrValQO prodAttrValQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<ProdAttrVal> pageModel = prodAttrValService.queryPage(prodAttrValQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
