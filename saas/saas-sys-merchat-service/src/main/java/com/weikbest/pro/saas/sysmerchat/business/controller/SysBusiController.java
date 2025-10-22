package com.weikbest.pro.saas.sysmerchat.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.sysmerchat.business.module.qo.SysBusiQO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiListVO;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiVO;
import com.weikbest.pro.saas.sysmerchat.business.service.SysBusiService;
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
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Slf4j
@Api(tags = {"SysBusi::平台商户账号接口"})
@RestController
@RequestMapping("/sys/busi")
public class SysBusiController {

    @Resource
    private SysBusiService sysBusiService;

    @UseToken
    @SaveLog(value = "新商户入驻")
    @ApiOperation(value = "新商户入驻")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "businessDTO", value = "保存数据信息", required = true)
            @Valid BusinessDTO businessDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = sysBusiService.insert(businessDTO);

        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新商户表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{businessId}")
    public DataResp<Boolean> update(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId,
            @ApiParam(name = "businessDTO", value = "更新数据信息", required = true)
            @Valid BusinessDTO businessDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = sysBusiService.updateByBusinessId(businessId, businessDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据商户ID删除商户数据")
    @ApiOperation(value = "根据商户ID删除")
    @DeleteMapping("/delete/{businessId}")
    public DataResp<Object> delete(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId) {

        sysBusiService.deleteBatchByIds(Collections.singletonList(businessId));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据商户ID列表删除商户数据")
    @ApiOperation(value = "根据商户ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "businessIdList", value = "商户ID列表", required = true)
            @RequestBody List<Long> businessIdList) {

        sysBusiService.deleteBatchByIds(businessIdList);
        return DataResp.ok();
    }


    @UseToken
    @UpdateLog(value = "禁用/启用商户")
    @ApiOperation(value = "禁用/启用商户")
    @PutMapping("/updateDataStatus/{busiUserId}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "busiUserId", value = "商户账户ID", required = true)
            @PathVariable Long busiUserId,
            @ApiParam(name = "dataStatus", value = "数据状态 0-禁用 1-可用", required = true)
            @RequestParam String dataStatus) {

        boolean update = sysBusiService.updateDataStatusById(busiUserId, dataStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商户账户表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{busiUserId}")
    public DataResp<SysBusiVO> find(
            @ApiParam(name = "busiUserId", value = "商户账户ID", required = true)
            @PathVariable Long busiUserId) {

        SysBusiVO sysBusiVO = sysBusiService.findVOById(busiUserId);
        return DataResp.ok(sysBusiVO);
    }

    @UseToken
    @QueryLog(value = "分页查询商户账户表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<SysBusiListVO>> queryPage(
            @ApiParam(name = "sysBusiQO", value = "查询条件")
                    SysBusiQO sysBusiQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<SysBusiListVO> pageModel = sysBusiService.queryPage(sysBusiQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
