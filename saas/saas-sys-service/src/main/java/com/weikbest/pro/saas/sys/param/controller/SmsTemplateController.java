package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.SmsTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.qo.SmsTemplateQO;
import com.weikbest.pro.saas.sys.param.service.SmsTemplateService;
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
 * 短信模板表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Api(tags = {"param::短信模板表接口"})
@RestController
@RequestMapping("/param/sms-template")
public class SmsTemplateController {

    @Resource
    private SmsTemplateService smsTemplateService;

    @UseToken
    @SaveLog(value = "新增短信模板表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "smsTemplateDTO", value = "保存数据信息", required = true)
            @Valid SmsTemplateDTO smsTemplateDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = smsTemplateService.insert(smsTemplateDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新短信模板表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "smsTemplateDTO", value = "更新数据信息", required = true)
            @Valid SmsTemplateDTO smsTemplateDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = smsTemplateService.updateById(id, smsTemplateDTO);
        return DataResp.ok(update);
    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除短信模板表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        smsTemplateService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除短信模板表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        smsTemplateService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询短信模板表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<SmsTemplate> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        SmsTemplate smsTemplate = smsTemplateService.findById(id);
        return DataResp.ok(smsTemplate);
    }

    @UseToken
    @QueryLog(value = "分页查询短信模板表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<SmsTemplate>> queryPage(
            @ApiParam(name = "smsTemplateQO", value = "查询条件")
                    SmsTemplateQO smsTemplateQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<SmsTemplate> pageModel = smsTemplateService.queryPage(smsTemplateQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
