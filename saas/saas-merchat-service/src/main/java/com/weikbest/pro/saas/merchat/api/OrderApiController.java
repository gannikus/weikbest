package com.weikbest.pro.saas.merchat.api;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.ApiToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.api.module.dto.OrderLogisticsDto;
import com.weikbest.pro.saas.merchat.api.module.qo.OrderInfoQo;
import com.weikbest.pro.saas.merchat.api.module.vo.OrderDeliveryErrorVo;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoDetailVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 订单api控制层
 *
 * @author 甘之萌  2023/07/10 10:08
 */
@Slf4j
@RestController
@Api(tags = {"api::对外接口"})
@RequestMapping("/api/order")
public class OrderApiController {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    /**
     * 订单批量查询
     * @param orderInfoQo
     * @param pageReq
     * @param request
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
//            @ApiImplicitParam(name = "numberList", value = "订单号list", required = true, dataType = "Array",paramType = "query")
    })
    @ApiToken
    @QueryLog(value = "批量查询订单表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "批量查询订单表数据")
    @GetMapping("/listByBatch")
    public DataResp<IPage<OrderInfoDetailVO>> listByBatch(
            OrderInfoQo orderInfoQo, PageReq pageReq, HttpServletRequest request) {
        BusiUser byApiKey = getBusiUserFromHeader(request);
        IPage<OrderInfoDetailVO> orderInfoDetailVOS = orderInfoService.listByBatch(orderInfoQo,pageReq,byApiKey.getBusinessId());
        return DataResp.ok(orderInfoDetailVOS);
    }

    /**
     * 发货
     * @param orderLogisticsDtoList
     * @param bindingResult
     * @return
     */
    @ApiImplicitParams(value={
            @ApiImplicitParam(name = "apiKey", value = "apiKey", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳（毫秒级）", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "sign", value = "签名,md5(apiKey+apiSecret+timestamp)", required = true, dataType = "String",paramType = "header"),
    })
    @ApiToken
    @SaveLog(value = "发货", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "发货")
    @PostMapping("/delivery")
    public DataResp<?> delivery(
            @ApiParam(name = "orderLogisticsDtoList",value = "物流信息list",required = true)
            @RequestBody @Valid List<OrderLogisticsDto> orderLogisticsDtoList, BindingResult bindingResult,HttpServletRequest request) {

        JsrCheckUtil.valid(bindingResult);
        BusiUser byApiKey = getBusiUserFromHeader(request);
        List<OrderDeliveryErrorVo> delivery = orderLogisticsService.delivery(orderLogisticsDtoList, byApiKey.getBusinessId());
        if (CollectionUtil.isEmpty(delivery)) {
            return DataResp.ok();
        }else {
            return DataResp.ok(delivery);

        }
    }


    private BusiUser getBusiUserFromHeader(HttpServletRequest request) {
        String apiKey = request.getHeader("apiKey");
        return busiUserService.findByApiKey(apiKey);
    }
}
