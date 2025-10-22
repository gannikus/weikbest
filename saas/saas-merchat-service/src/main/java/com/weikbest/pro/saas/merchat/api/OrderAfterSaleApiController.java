package com.weikbest.pro.saas.merchat.api;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.ApiToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmAndReDeliveryDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmCustomerApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmCustomerDeliveryDTO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 订单售后api控制层
 *
 * @author 甘之萌  2023/07/10 16:43
 */
@Api(tags = {"api::对外接口"})
@Slf4j
@RestController
@RequestMapping("/api/order-after-sale")
public class OrderAfterSaleApiController {

    @Resource
    private BusiUserService busiUserService;
    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    /**
     *批量同意仅退款
     * @param orderNumbers
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "批量同意仅退款", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量同意仅退款")
    @PostMapping("/batchUpdateOnlyRefund")
    public DataResp<Boolean> batchUpdateOnlyRefund(
            @ApiParam(name = "orderNumbers", value = "订单列表", required = true)
            @RequestBody List<String> orderNumbers,HttpServletRequest request) {
        if (orderNumbers.size() > 100) {
            throw new WeikbestException("订单数量需小于100个！");
        }
        BusiUser busiUser = getBusiUserFromHeader(request);
        List<OrderAfterSale> orderAfterSaleList = orderAfterSaleService.listByOrderNumbers(orderNumbers,busiUser);
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            // 售后单类型为“仅退款”、状态为“买家申请售后，待商家处理”
            if(!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的申请类型不是 仅退款！", orderAfterSale.getId()));
            }
            if(!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的售后状态不是 客户申请售后，待商家处理！", orderAfterSale.getId()));
            }
        }
        if (CollectionUtil.isEmpty(orderAfterSaleList)){
            throw new WeikbestException("没有需要处理的订单！");
        }
        List<Long> ids = orderAfterSaleList.stream().map(OrderAfterSale::getId).collect(Collectors.toList());
        Boolean update = orderAfterSaleService.batchUpdateOnlyRefund(ids);
        return DataResp.ok(update);
    }

    /**
     * 批量同意退货退款申请
     * @param orderNumbers
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "批量同意退货退款申请", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量同意退货退款申请")
    @PostMapping("/batchUpdateReturnAndRefundApply")
    public DataResp<Boolean> batchUpdateReturnAndRefundApply(
            @ApiParam(name = "orderNumbers", value = "订单列表", required = true)
            @RequestBody List<String> orderNumbers,HttpServletRequest request) {
        if (orderNumbers.size() > 100) {
            throw new WeikbestException("订单数量需小于100个！");
        }
        BusiUser busiUser = getBusiUserFromHeader(request);
        List<OrderAfterSale> orderAfterSaleList = orderAfterSaleService.listByOrderNumbers(orderNumbers, busiUser);

        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            // 售后单类型为“退货退款”、状态为“客户申请售后，待商家处理”
            if(!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的申请类型不是 退货退款！", orderAfterSale.getId()));
            }
            if(!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的售后状态不是 客户申请售后，待商家处理！", orderAfterSale.getId()));
            }
        }
        if (CollectionUtil.isEmpty(orderAfterSaleList)){
            throw new WeikbestException("没有需要处理的订单！");
        }
        List<Long> ids = orderAfterSaleList.stream().map(OrderAfterSale::getId).collect(Collectors.toList());

        Boolean update = orderAfterSaleService.batchUpdateReturnAndRefundApply(ids);
        return DataResp.ok(update);
    }

    /**
     * 批量同意换货申请
     * @param orderNumbers
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "批量同意换货申请", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量同意换货申请")
    @PostMapping("/batchUpdateExchangeApply")
    public DataResp<Boolean> batchUpdateExchangeApply(
            @ApiParam(name = "orderNumbers", value = "订单列表", required = true)
            @RequestBody List<String> orderNumbers,HttpServletRequest request) {
        if (orderNumbers.size() > 100) {
            throw new WeikbestException("订单数量需小于100个！");
        }
        BusiUser busiUser = getBusiUserFromHeader(request);
        List<OrderAfterSale> orderAfterSaleList = orderAfterSaleService.listByOrderNumbers(orderNumbers, busiUser);
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            // 售后单类型为“换货”、状态为“客户申请售后，待商家处理”
            if(!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.exchange.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的申请类型不是 退货退款！", orderAfterSale.getId()));
            }
            if(!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的售后状态不是 客户申请售后，待商家处理！", orderAfterSale.getId()));
            }
        }
        if (CollectionUtil.isEmpty(orderAfterSaleList)){
            throw new WeikbestException("没有需要处理的订单！");
        }
        List<Long> ids = orderAfterSaleList.stream().map(OrderAfterSale::getId).collect(Collectors.toList());

        Boolean update = orderAfterSaleService.batchUpdateExchangeApply(ids);
        return DataResp.ok(update);
    }

    /**
     * 批量同意退货退款
     * @param orderNumbers
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "批量同意退货退款", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量同意退货退款")
    @PostMapping("/batchUpdateReturnAndRefund")
    public DataResp<Boolean> batchUpdateExchange(
            @ApiParam(name = "orderNumbers", value = "订单列表", required = true)
            @RequestBody List<String> orderNumbers,HttpServletRequest request) {
        if (orderNumbers.size() > 100) {
            throw new WeikbestException("订单数量需小于100个！");
        }
        BusiUser busiUser = getBusiUserFromHeader(request);
        List<OrderAfterSale> orderAfterSaleList = orderAfterSaleService.listByOrderNumbers(orderNumbers, busiUser);
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            // 售后单类型为“退货退款”、状态为“客户已寄回商品，待商家收货”
            if(!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.return_and_refund.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的申请类型不是 退货退款！", orderAfterSale.getId()));
            }
            if(!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_6.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的售后状态不是 客户已寄回商品，待商家收货！", orderAfterSale.getId()));
            }
        }
        if (CollectionUtil.isEmpty(orderAfterSaleList)){
            throw new WeikbestException("没有需要处理的订单！");
        }
        List<Long> ids = orderAfterSaleList.stream().map(OrderAfterSale::getId).collect(Collectors.toList());

        Boolean update = orderAfterSaleService.batchUpdateReturnAndRefund(ids);
        return DataResp.ok(update);
    }
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "客户发起申请后，商家确认是否同意客户申请，并更新售后状态", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "客户发起申请后，商家确认是否同意客户申请，并更新售后状态")
    @PutMapping("/businessConfirmCustomerApply/{id}")
    public DataResp<Boolean> businessConfirmCustomerApply(
            @ApiParam(name = "id", value = "售后单id", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderAfterSaleBusinessConfirmCustomerApplyDTO", value = "客户发起申请后，商家确认是否同意客户申请实体对象", required = true)
            @Valid OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmCustomerApplyForApi(id, orderAfterSaleBusinessConfirmCustomerApplyDTO);
        return DataResp.ok(update);
    }
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "客户寄回商品后，商家确认是否收货，并更新售后状态", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "客户寄回商品后，商家确认是否收货，并更新售后状态")
    @PutMapping("/businessConfirmCustomerDelivery/{id}")
    public DataResp<Boolean> businessConfirmCustomerDelivery(
            @ApiParam(name = "id", value = "售后单id", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderAfterSaleBusinessConfirmCustomerApplyDTO", value = "客户寄回商品后，商家确认是否收货实体对象", required = true)
            @Valid OrderAfterSaleBusinessConfirmCustomerDeliveryDTO orderAfterSaleBusinessConfirmCustomerApplyDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmCustomerDelivery(id, orderAfterSaleBusinessConfirmCustomerApplyDTO);
        return DataResp.ok(update);
    }
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "商家确认收货后，商家确认退款，并更新售后状态", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商家确认收货后，商家确认退款，并更新售后状态")
    @PutMapping("/businessConfirmDeliveryAndRefund/{id}")
    public DataResp<Boolean> businessConfirmDeliveryAndRefund(
            @ApiParam(name = "id", value = "售后单id", required = true)
            @PathVariable Long id) {

        Boolean update = orderAfterSaleService.businessConfirmDeliveryAndRefund(id);
        return DataResp.ok(update);
    }

    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @UpdateLog(value = "商家确认收货后，商家重新发货，并更新售后状态", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商家确认收货后，商家重新发货，并更新售后状态")
    @PutMapping("/businessConfirmAndReDelivery")
    public DataResp<Boolean> businessConfirmAndReDelivery(
            @ApiParam(name = "orderAfterSaleBusinessConfirmAndReDeliveryDTO", value = "商家确认收货后，商家重新发货实体对象", required = true)
            @RequestBody @Valid OrderAfterSaleBusinessConfirmAndReDeliveryDTO orderAfterSaleBusinessConfirmAndReDeliveryDTO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmAndReDelivery(orderAfterSaleBusinessConfirmAndReDeliveryDTO);
        return DataResp.ok(update);
    }

    private BusiUser getBusiUserFromHeader(HttpServletRequest request) {
        String apiKey = request.getHeader("apiKey");
        return busiUserService.findByApiKey(apiKey);
    }
}
