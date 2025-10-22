package com.weikbest.pro.saas.merchat.order.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoDetailVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoListVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderStatusGroupVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"order::订单表接口"})
@RestController
@RequestMapping("/order/order-info")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @UseToken
    @UpdateLog(value = "更新订单备注", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新订单备注")
    @PutMapping("/updateDescription/{id}")
    public DataResp<Boolean> updateDescription(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "description", value = "订单备注信息", required = true)
            @RequestParam String description) {
        if (StrUtil.isBlank(description)) {
            throw new WeikbestException("订单备注信息不为空！");
        }

        boolean update = orderInfoService.updateDescriptionById(id, description);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "取消到付订单", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "取消到付订单")
    @PutMapping("/channel-delivery-order/{id}")
    public DataResp<Boolean> channelDeliveryOrder(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        boolean update = orderInfoService.channelDeliveryOrder(id);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "查询订单状态汇总数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询订单状态汇总数据")
    @GetMapping("/queryGroupOrderStatus")
    public DataResp<List<OrderStatusGroupVO>> queryGroupOrderStatus(
            @ApiParam(name = "orderInfoQO", value = "查询条件")
            @Valid OrderInfoQO orderInfoQO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        List<OrderStatusGroupVO> orderStatusGroupVOList = orderInfoService.queryGroupOrderStatus(orderInfoQO);
        return DataResp.ok(orderStatusGroupVOList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询订单详情")
    @GetMapping("/find/{id}")
    public DataResp<OrderInfoDetailVO> find(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        OrderInfoDetailVO orderInfo = orderInfoService.findDetailVOById(id);
        return DataResp.ok(orderInfo);
    }

    @UseToken
    @QueryLog(value = "根据number查询订单表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据number查询订单表数据")
    @GetMapping("/findByNumber/{number}")
    public DataResp<OrderInfo> findByNumber(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number) {

        OrderInfo orderInfo = orderInfoService.findByOrderNumber(number);
        return DataResp.ok(orderInfo);
    }

    @UseToken
    @QueryLog(value = "分页查询订单表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<OrderInfoListVO>> queryPage(
            @ApiParam(name = "orderInfoQO", value = "查询条件")
                    OrderInfoQO orderInfoQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderInfoListVO> pageModel = orderInfoService.queryPage(orderInfoQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @UpdateLog(value = "订单广告回传", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "订单广告回传")
    @GetMapping("/callback")
    public DataResp<?> adCallback(
            @ApiParam(name = "id", value = "订单ID", required = true) Long id) {
        orderInfoService.adCallback(id);
        return DataResp.ok();
    }
}
