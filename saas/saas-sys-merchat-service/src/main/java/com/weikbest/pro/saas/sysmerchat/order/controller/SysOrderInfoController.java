package com.weikbest.pro.saas.sysmerchat.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoDetailVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoListVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderStatusGroupVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsService;
import com.weikbest.pro.saas.sysmerchat.order.module.mapstruct.SysOrderInfoMapStruct;
import com.weikbest.pro.saas.sysmerchat.order.module.qo.SysOrderGroupQO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = {"SysOrder::平台端订单表接口"})
@RestController
@RequestMapping("/sys/order/order-info")
public class SysOrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private OrderLogisticsService orderLogisticsService;

    @UseToken
    @QueryLog(value = "查询订单状态汇总数据")
    @ApiOperation(value = "查询订单状态汇总数据")
    @GetMapping("/queryGroupOrderStatus")
    public DataResp<List<OrderStatusGroupVO>> queryGroupOrderStatus(
           SysOrderGroupQO sysOrderGroupQO) {

        OrderInfoQO orderInfoQO = SysOrderInfoMapStruct.INSTANCE.SysOrderGroupQOConverToOrderInfoQO(sysOrderGroupQO);
        List<OrderStatusGroupVO> orderStatusGroupVOList = orderInfoService.queryGroupOrderStatus(orderInfoQO);
        return DataResp.ok(orderStatusGroupVOList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单表数据")
    @ApiOperation(value = "根据ID查询订单详情")
    @GetMapping("/find/{id}")
    public DataResp<OrderInfoDetailVO> find(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        OrderInfoDetailVO orderInfo = orderInfoService.findDetailVOById(id);
        return DataResp.ok(orderInfo);
    }

    @UseToken
    @QueryLog(value = "分页查询订单表数据")
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
}
