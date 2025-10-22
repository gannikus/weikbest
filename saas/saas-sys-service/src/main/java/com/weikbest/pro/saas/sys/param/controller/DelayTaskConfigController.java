//package com.weikbest.pro.saas.sys.param.controller;
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
//import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
//import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskConfigDTO;
//import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskConfigQO;
//import com.weikbest.pro.saas.sys.param.service.DelayTaskConfigService;
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
// * 系统延时任务表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-10-23
// */
//@Slf4j
//@Api(tags = {"param::系统延时任务表接口"})
//@RestController
//@RequestMapping("/param/delay-task")
//public class DelayTaskConfigController {
//
//    @Resource
//    private DelayTaskConfigService delayTaskConfigService;
//
//    @UseToken
//    @SaveLog(value = "新增系统延时任务表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "delayTaskDTO", value = "保存数据信息", required = true)
//            @Valid DelayTaskConfigDTO delayTaskConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = delayTaskConfigService.insert(delayTaskConfigDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新系统延时任务表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "delayTaskDTO", value = "更新数据信息", required = true)
//            @Valid DelayTaskConfigDTO delayTaskConfigDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = delayTaskConfigService.updateById(id, delayTaskConfigDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统延时任务表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        delayTaskConfigService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统延时任务表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        delayTaskConfigService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询系统延时任务表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<DelayTaskConfig> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        DelayTaskConfig delayTaskConfig = delayTaskConfigService.findById(id);
//        return DataResp.ok(delayTaskConfig);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询系统延时任务表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<DelayTaskConfig>> queryPage(
//            @ApiParam(name = "delayTaskQO", value = "查询条件")
//                    DelayTaskConfigQO delayTaskConfigQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<DelayTaskConfig> pageModel = delayTaskConfigService.queryPage(delayTaskConfigQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
