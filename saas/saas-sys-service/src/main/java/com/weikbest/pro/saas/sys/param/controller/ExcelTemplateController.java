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
import com.weikbest.pro.saas.sys.param.entity.ExcelTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.ExcelTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ExcelTemplateQO;
import com.weikbest.pro.saas.sys.param.service.ExcelTemplateService;
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
 * 系统excel模板表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Slf4j
@Api(tags = {"param::系统excel模板表接口"})
@RestController
@RequestMapping("/param/excel-template")
public class ExcelTemplateController {

    @Resource
    private ExcelTemplateService excelTemplateService;

    @UseToken
    @SaveLog(value = "新增系统excel模板表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "excelTemplateDTO", value = "保存数据信息", required = true)
            @Valid ExcelTemplateDTO excelTemplateDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = excelTemplateService.insert(excelTemplateDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统excel模板表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "excelTemplateDTO", value = "更新数据信息", required = true)
            @Valid ExcelTemplateDTO excelTemplateDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = excelTemplateService.updateById(id, excelTemplateDTO);
        return DataResp.ok(update);
    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除系统excel模板表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        excelTemplateService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统excel模板表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        excelTemplateService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询系统excel模板表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ExcelTemplate> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ExcelTemplate excelTemplate = excelTemplateService.findById(id);
        return DataResp.ok(excelTemplate);
    }

    @UseToken
    @QueryLog(value = "分页查询系统excel模板表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ExcelTemplate>> queryPage(
            @ApiParam(name = "excelTemplateQO", value = "查询条件")
                    ExcelTemplateQO excelTemplateQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ExcelTemplate> pageModel = excelTemplateService.queryPage(excelTemplateQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
