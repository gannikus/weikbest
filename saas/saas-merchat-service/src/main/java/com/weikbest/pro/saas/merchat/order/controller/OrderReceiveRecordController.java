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
import com.weikbest.pro.saas.merchat.order.entity.OrderReceiveRecord;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderReceiveRecordDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderReceiveRecordQO;
import com.weikbest.pro.saas.merchat.order.service.OrderReceiveRecordService;
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
 * 订单分账记录表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-06
 */
@Slf4j
@Api(tags = {"order::订单分账记录表接口"})
@RestController
@RequestMapping("/order/order-receive-record")
public class OrderReceiveRecordController {

    @Resource
    private OrderReceiveRecordService orderReceiveRecordService;

    @UseToken
    @SaveLog(value = "新增订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "orderReceiveRecordDTO", value = "保存数据信息", required = true)
            @Valid OrderReceiveRecordDTO orderReceiveRecordDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = orderReceiveRecordService.insert(orderReceiveRecordDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderReceiveRecordDTO", value = "更新数据信息", required = true)
            @Valid OrderReceiveRecordDTO orderReceiveRecordDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = orderReceiveRecordService.updateById(id, orderReceiveRecordDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        orderReceiveRecordService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        orderReceiveRecordService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderReceiveRecord> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderReceiveRecord orderReceiveRecord = orderReceiveRecordService.findById(id);
        return DataResp.ok(orderReceiveRecord);
    }

    @UseToken
    @QueryLog(value = "分页查询订单分账记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<OrderReceiveRecord>> queryPage(
            @ApiParam(name = "orderReceiveRecordQO", value = "查询条件")
                    OrderReceiveRecordQO orderReceiveRecordQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderReceiveRecord> pageModel = orderReceiveRecordService.queryPage(orderReceiveRecordQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
