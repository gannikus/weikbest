package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeRecord;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSubscribeRecordQO;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 小程序订阅消息发送记录表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Slf4j
@Api(tags = {"param::小程序订阅消息发送记录表接口"})
@RestController
@RequestMapping("/param/applet-subscribe-record")
public class AppletSubscribeRecordController {

    @Resource
    private AppletSubscribeRecordService appletSubscribeRecordService;

//    @UseToken
//    @SaveLog(value = "新增小程序订阅消息发送记录表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "appletSubscribeRecordDTO", value = "保存数据信息", required = true)
//            @Valid AppletSubscribeRecordDTO appletSubscribeRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = appletSubscribeRecordService.insert(appletSubscribeRecordDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新小程序订阅消息发送记录表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "appletSubscribeRecordDTO", value = "更新数据信息", required = true)
//            @Valid AppletSubscribeRecordDTO appletSubscribeRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = appletSubscribeRecordService.updateById(id, appletSubscribeRecordDTO);
//        return DataResp.ok(update);
//    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除小程序订阅消息发送记录表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        appletSubscribeRecordService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除小程序订阅消息发送记录表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        appletSubscribeRecordService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询小程序订阅消息发送记录表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<AppletSubscribeRecord> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        AppletSubscribeRecord appletSubscribeRecord = appletSubscribeRecordService.findById(id);
        return DataResp.ok(appletSubscribeRecord);
    }

    @UseToken
    @QueryLog(value = "分页查询小程序订阅消息发送记录表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<AppletSubscribeRecord>> queryPage(
            @ApiParam(name = "appletSubscribeRecordQO", value = "查询条件")
                    AppletSubscribeRecordQO appletSubscribeRecordQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<AppletSubscribeRecord> pageModel = appletSubscribeRecordService.queryPage(appletSubscribeRecordQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
