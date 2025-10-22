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
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiAddressDTO;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiAddressQO;
import com.weikbest.pro.saas.merchat.busi.service.BusiAddressService;
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
 * 商家详细地址信息表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Slf4j
@Api(tags = {"busi::商家详细地址信息表接口"})
@RestController
@RequestMapping("/busi/busi-address")
public class BusiAddressController {

    @Resource
    private BusiAddressService busiAddressService;

    @UseToken
    @SaveLog(value = "新增商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "busiAddressDTO", value = "保存数据信息", required = true)
            @Valid BusiAddressDTO busiAddressDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = busiAddressService.insert(busiAddressDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "busiAddressDTO", value = "更新数据信息", required = true)
            @Valid BusiAddressDTO busiAddressDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = busiAddressService.updateById(id, busiAddressDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        busiAddressService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        busiAddressService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<BusiAddress> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        BusiAddress busiAddress = busiAddressService.findById(id);
        return DataResp.ok(busiAddress);
    }

    @UseToken
    @QueryLog(value = "分页查询商家详细地址信息表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<BusiAddress>> queryPage(
            @ApiParam(name = "busiAddressQO", value = "查询条件")
                    BusiAddressQO busiAddressQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<BusiAddress> pageModel = busiAddressService.queryPage(busiAddressQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
