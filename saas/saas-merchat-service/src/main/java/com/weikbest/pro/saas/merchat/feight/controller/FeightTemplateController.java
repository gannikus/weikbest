package com.weikbest.pro.saas.merchat.feight.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateVO;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateRegionService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateService;
import com.weikbest.pro.saas.merchat.feight.util.FeightTemplateJsrCheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 运费模板表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"feight::运费模板表接口"})
@RestController
@RequestMapping("/feight/feight-template")
public class FeightTemplateController {

    @Resource
    private FeightTemplateJsrCheckUtil feightTemplateJsrCheckUtil;

    @Resource
    private FeightTemplateService feightTemplateService;

    @Resource
    private FeightTemplateRegionService feightTemplateRegionService;

    @UseToken
    @SaveLog(value = "新增运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<String> insert(
            @ApiParam(name = "feightTemplateDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid FeightTemplateDTO feightTemplateDTO, BindingResult bindingResult) {

        feightTemplateJsrCheckUtil.validFeightTemplateDTO(bindingResult);

        Long id = feightTemplateService.insertReturnId(feightTemplateDTO);
        return DataResp.ok(String.valueOf(id));
    }

    @UseToken
    @UpdateLog(value = "更新运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "feightTemplateDTO", value = "更新数据信息", required = true)
            @RequestBody @Valid FeightTemplateDTO feightTemplateDTO, BindingResult bindingResult) {

        feightTemplateJsrCheckUtil.validFeightTemplateDTO(bindingResult);

        boolean update = feightTemplateService.updateById(id, feightTemplateDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        feightTemplateService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        feightTemplateService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<FeightTemplateVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        FeightTemplateVO feightTemplate = feightTemplateService.findVOById(id);
        return DataResp.ok(feightTemplate);
    }

    @UseToken
    @QueryLog(value = "分页查询运费模板表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<FeightTemplateVO>> queryPage(
            @ApiParam(name = "feightTemplateQO", value = "查询条件")
                    FeightTemplateQO feightTemplateQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<FeightTemplateVO> pageModel = feightTemplateService.queryPage(feightTemplateQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }


    @UseToken
    @QueryLog(value = "查询可配送地区行政区划数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询可配送地区行政区划数据")
    @GetMapping("/queryChooseTree/{id}/{entryId}")
    public DataResp<List<Dtree>> queryChooseTree(@ApiParam(name = "id", value = "运费模板ID", required = true)
                                                 @PathVariable Long id,
                                                 @ApiParam(name = "entryId", value = "可配送区域模板详情ID", required = true)
                                                 @PathVariable Long entryId) {

        List<Dtree> dtreeList = feightTemplateRegionService.queryChooseTree(id, entryId);
        return DataResp.ok(dtreeList);
    }
}
