package com.weikbest.pro.saas.merchat.order.controller;

import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderRecAddrDTO;
import com.weikbest.pro.saas.merchat.order.service.OrderRecAddrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 订单收货地址拆分表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"order::订单收货地址拆分表接口"})
@RestController
@RequestMapping("/order/order-rec-addr")
public class OrderRecAddrController {

    @Resource
    private OrderRecAddrService orderRecAddrService;

    @UseToken
    @UpdateLog(value = "更新订单收货地址数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新订单收货地址数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderRecAddrDTO", value = "更新数据信息", required = true)
            @Valid OrderRecAddrDTO orderRecAddrDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = orderRecAddrService.updateById(id, orderRecAddrDTO);
        return DataResp.ok(update);
    }
}
