package com.weikbest.pro.saas.merchat.order.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsVO;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 订单物流记录表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"order::订单物流记录表接口"})
@RestController
@RequestMapping("/order/order-logistics")
public class OrderLogisticsController {

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @UseToken
    @SaveLog(value = "去发货，修改订单状态为已发货", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "去发货，修改订单状态为已发货")
    @PostMapping("/insertAndUpdateOrderStatus")
    public DataResp<Boolean> insertAndUpdateOrderStatus(
            @ApiParam(name = "orderLogisticsDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid OrderLogisticsDTO orderLogisticsDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = orderLogisticsService.orderDeliverAndUpdateOrderStatus(orderLogisticsDTO);
        return DataResp.ok(save);
    }


    @UseToken
    @UpdateLog(value = "更新订单物流记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "订单物流ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderLogisticsDTO", value = "更新数据信息", required = true)
            @RequestBody @Valid OrderLogisticsDTO orderLogisticsDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = orderLogisticsService.updateById(id, orderLogisticsDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单物流记录表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "订单物流ID查询物流记录")
    @GetMapping("/find/{id}")
    public DataResp<OrderLogisticsVO> find(
            @ApiParam(name = "id", value = "订单物流ID", required = true)
            @PathVariable Long id) {

        OrderLogisticsVO orderLogisticsVO = orderLogisticsService.findVOAndResetContentById(id);
        return DataResp.ok(orderLogisticsVO);
    }

}
