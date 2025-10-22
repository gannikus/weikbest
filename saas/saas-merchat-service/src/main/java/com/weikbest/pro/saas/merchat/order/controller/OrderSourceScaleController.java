package com.weikbest.pro.saas.merchat.order.controller;

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
import com.weikbest.pro.saas.merchat.order.entity.OrderSourceScale;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderSourceScaleDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderSourceScaleQO;
import com.weikbest.pro.saas.merchat.order.service.OrderSourceScaleService;
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
 * 订单来源分账比例表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Slf4j
@Api(tags = {"order::订单来源分账比例表接口"})
@RestController
@RequestMapping("/order/order-source-scale")
public class OrderSourceScaleController {

    @Resource
    private OrderSourceScaleService orderSourceScaleService;

    @UseToken
    @SaveLog(value = "新增订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "orderSourceScaleDTO", value = "保存数据信息", required = true)
            @Valid OrderSourceScaleDTO orderSourceScaleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = orderSourceScaleService.insert(orderSourceScaleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderSourceScaleDTO", value = "更新数据信息", required = true)
            @Valid OrderSourceScaleDTO orderSourceScaleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = orderSourceScaleService.updateById(id, orderSourceScaleDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        orderSourceScaleService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        orderSourceScaleService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderSourceScale> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderSourceScale orderSourceScale = orderSourceScaleService.findById(id);
        return DataResp.ok(orderSourceScale);
    }

    @UseToken
    @QueryLog(value = "分页查询订单来源分账比例表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<OrderSourceScale>> queryPage(
            @ApiParam(name = "orderSourceScaleQO", value = "查询条件")
                    OrderSourceScaleQO orderSourceScaleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderSourceScale> pageModel = orderSourceScaleService.queryPage(orderSourceScaleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
