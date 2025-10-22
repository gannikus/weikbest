package com.weikbest.pro.saas.merchat.busi.controller;

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
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.busi.entity.CustBusinessBind;
import com.weikbest.pro.saas.merchat.busi.module.dto.CustBusinessBindDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.CustBusinessBindQO;
import com.weikbest.pro.saas.merchat.busi.service.CustBusinessBindService;
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
 * 分账商户绑定表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Slf4j
@Api(tags = {"busi::分账商户绑定表接口"})
@RestController
@RequestMapping("/busi/cust-business-bind")
public class CustBusinessBindController {

    @Resource
    private CustBusinessBindService custBusinessBindService;

    @UseToken
    @SaveLog(value = "新增分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "custBusinessBindDTO", value = "保存数据信息", required = true)
            @Valid CustBusinessBindDTO custBusinessBindDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = custBusinessBindService.insert(custBusinessBindDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "custBusinessBindDTO", value = "更新数据信息", required = true)
            @Valid CustBusinessBindDTO custBusinessBindDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = custBusinessBindService.updateById(id, custBusinessBindDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        custBusinessBindService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        custBusinessBindService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<CustBusinessBind> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CustBusinessBind custBusinessBind = custBusinessBindService.findById(id);
        return DataResp.ok(custBusinessBind);
    }

    @UseToken
    @QueryLog(value = "分页查询分账商户绑定表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CustBusinessBind>> queryPage(
            @ApiParam(name = "custBusinessBindQO", value = "查询条件")
                    CustBusinessBindQO custBusinessBindQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CustBusinessBind> pageModel = custBusinessBindService.queryPage(custBusinessBindQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
