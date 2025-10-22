//package com.weikbest.pro.saas.merchat.busi.controller;
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
//import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
//import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRelateDTO;
//import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserRelateQO;
//import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
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
// * 商户与商户账户关联表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2023-02-23
// */
//@Slf4j
//@Api(tags = {"busi::商户与商户账户关联表接口"})
//@RestController
//@RequestMapping("/busi/busi-user-relate")
//public class BusiUserRelateController {
//
//    @Resource
//    private BusiUserRelateService busiUserRelateService;
//
//    @UseToken
//    @SaveLog(value = "新增商户与商户账户关联表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "busiUserRelateDTO", value = "保存数据信息", required = true)
//            @Valid BusiUserRelateDTO busiUserRelateDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = busiUserRelateService.insert(busiUserRelateDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新商户与商户账户关联表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "busiUserRelateDTO", value = "更新数据信息", required = true)
//            @Valid BusiUserRelateDTO busiUserRelateDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = busiUserRelateService.updateById(id, busiUserRelateDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除商户与商户账户关联表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        busiUserRelateService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除商户与商户账户关联表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        busiUserRelateService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询商户与商户账户关联表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<BusiUserRelate> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        BusiUserRelate busiUserRelate = busiUserRelateService.findById(id);
//        return DataResp.ok(busiUserRelate);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询商户与商户账户关联表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<BusiUserRelate>> queryPage(
//            @ApiParam(name = "busiUserRelateQO", value = "查询条件")
//                    BusiUserRelateQO busiUserRelateQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<BusiUserRelate> pageModel = busiUserRelateService.queryPage(busiUserRelateQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
