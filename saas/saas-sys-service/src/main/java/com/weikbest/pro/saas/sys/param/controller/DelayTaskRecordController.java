//package com.weikbest.pro.saas.sys.param.controller;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.weikbest.pro.saas.common.annotation.log.QueryLog;
//import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
//import com.weikbest.pro.saas.common.annotation.log.SaveLog;
//import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
//import com.weikbest.pro.saas.common.annotation.token.PassToken;
//import com.weikbest.pro.saas.common.annotation.token.UseToken;
//import com.weikbest.pro.saas.common.delay.DelayQueueFactory;
//import com.weikbest.pro.saas.common.delay.DelayQueueManager;
//import com.weikbest.pro.saas.common.delay.DelayTaskVO;
//import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
//import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
//import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
//import com.weikbest.pro.saas.common.util.JsrCheckUtil;
//import com.weikbest.pro.saas.sys.common.constant.DictConstant;
//import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
//import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
//import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskRecordDTO;
//import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskRecordQO;
//import com.weikbest.pro.saas.sys.param.service.DelayTaskConfigService;
//import com.weikbest.pro.saas.sys.param.service.DelayTaskRecordService;
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
// * 系统延时任务执行记录表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-10-23
// */
//@Slf4j
//@Api(tags = {"param::系统延时任务执行记录表接口"})
//@RestController
//@RequestMapping("/param/delay-task-record")
//public class DelayTaskRecordController {
//
//    @Resource
//    private DelayTaskConfigService delayTaskConfigService;
//
//    @Resource
//    private DelayTaskRecordService delayTaskRecordService;
//
//    @Resource
//    private DelayQueueManager delayQueueManager;
//
//
//    @PassToken
//    @SaveLog(value = "新增系统延时任务执行记录表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Object> testInit() {
//        List<DelayTaskVO> delayQueueTask = delayQueueManager.getDelayQueueTask();
//        delayQueueTask.forEach(delayTaskVO -> {
//            String taskId = delayTaskVO.getTaskId();
//            String redisKeyPrefix = DelayQueueFactory.getRedisKeyPrefix(taskId);
//            DelayTaskConfig delayTaskConfig = delayTaskConfigService.findByNumber(redisKeyPrefix);
//
//            DelayTaskRecord delayTaskRecord = delayTaskRecordService.getOne(new QueryWrapper<DelayTaskRecord>().eq(DelayTaskRecord.DELAY_TASK_ID, delayTaskConfig.getId()).eq(DelayTaskRecord.DELAY_TASK, taskId));
//            if(ObjectUtil.isNull(delayTaskRecord)) {
//                // 保存记录
//                delayTaskRecord = new DelayTaskRecord();
//                delayTaskRecord.setDelayTaskId(delayTaskConfig.getId())
//                        .setDelayTask(taskId)
//                        .setTaskStatus(DictConstant.DelayTaskStatus.waiting.getCode())
//                        .setName(delayTaskConfig.getName())
//                        .setTimeoutDate(delayTaskVO.getDate());
//                delayTaskRecordService.save(delayTaskRecord);
//            }
//        });
//
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @SaveLog(value = "新增系统延时任务执行记录表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "delayTaskRecordDTO", value = "保存数据信息", required = true)
//            @Valid DelayTaskRecordDTO delayTaskRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = delayTaskRecordService.insert(delayTaskRecordDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新系统延时任务执行记录表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "delayTaskRecordDTO", value = "更新数据信息", required = true)
//            @Valid DelayTaskRecordDTO delayTaskRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = delayTaskRecordService.updateById(id, delayTaskRecordDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统延时任务执行记录表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        delayTaskRecordService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统延时任务执行记录表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        delayTaskRecordService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询系统延时任务执行记录表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<DelayTaskRecord> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        DelayTaskRecord delayTaskRecord = delayTaskRecordService.findById(id);
//        return DataResp.ok(delayTaskRecord);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询系统延时任务执行记录表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<DelayTaskRecord>> queryPage(
//            @ApiParam(name = "delayTaskRecordQO", value = "查询条件")
//                    DelayTaskRecordQO delayTaskRecordQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<DelayTaskRecord> pageModel = delayTaskRecordService.queryPage(delayTaskRecordQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
