package com.weikbest.pro.saas.applet.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingFinishRequest;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingOrderAmountQueryRequest;
import com.github.binarywang.wxpay.bean.profitsharingV3.ProfitSharingResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.applet.order.module.dto.AppOrderInfoDTO;
import com.weikbest.pro.saas.applet.order.module.qo.AppOrderInfoListQO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderDetailVO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderLogisticsVO;
import com.weikbest.pro.saas.applet.order.service.AppOrderService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.web.IpUtil;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.AppOrderInfoListDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsVO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import com.weikbest.pro.saas.merchat.order.service.OrderLogisticsService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单信息 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"apporder::订单接口"})
@RestController
@RequestMapping("/apporder")
public class AppOrderController {

    @Resource
    private AppOrderService appOrderService;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private ShopThirdService shopThirdService;


    @AppToken
    @RemoveLog(value = "下单操作")
    @ApiOperation(value = "下单")
    @PutMapping("/doorder")
    public DataResp<String> doorder(
            @ApiParam(name = "appOrderInfoDTO", value = "订单信息")
            @Valid @RequestBody AppOrderInfoDTO appOrderInfoDTO) {

        String number = appOrderService.doOrder(appOrderInfoDTO);

        return DataResp.ok(number);
    }

    @AppToken
//    @PassToken
    @RemoveLog(value = "批量下单操作")
    @ApiOperation(value = "批量下单操作")
    @PostMapping("/batch-order")
    public DataResp<String> batchDoOrder(
            @ApiParam(name = "appOrderInfoDTOList", value = "订单信息列表")
            @Valid @RequestBody List<AppOrderInfoDTO> appOrderInfoDTOList) {

        String number = appOrderService.batchOrder(appOrderInfoDTOList);

        return DataResp.ok(number);
    }

    @AppToken
    @RemoveLog(value = "到付下单")
    @ApiOperation(value = "到付下单")
    @PutMapping("/doorderByCashOnDelivery")
    public DataResp<String> doorderByCashOnDelivery(
            @ApiParam(name = "appOrderInfoDTO", value = "订单信息")
            @Valid @RequestBody AppOrderInfoDTO appOrderInfoDTO) {

        String number = appOrderService.doorderByCashOnDelivery(appOrderInfoDTO);

        return DataResp.ok(number);
    }

    @AppToken
    @SaveLog(value = "保存订单订阅消息")
    @ApiOperation(value = "保存订单订阅消息")
    @PostMapping("/saveOrderInfoMessage/{number}/{deliverNotify}/{waitPayNotify}")
    public DataResp<Object> saveOrderInfoMessage(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number,
            @ApiParam(name = "deliverNotify", value = "开启订单发货通知 0-否 1-是", required = true)
            @PathVariable String deliverNotify,
            @ApiParam(name = "waitPayNotify", value = "开启订单待付款通知 0-否 1-是", required = true)
            @PathVariable String waitPayNotify) {

        appOrderService.saveOrderInfoMessage(number,deliverNotify,waitPayNotify);

        return DataResp.ok();
    }

    @AppToken
    @UpdateLog(value = "订单支付")
    @ApiOperation(value = "订单支付")
    @PostMapping("/placeOrder/{number}")
    public DataResp<Object> placeOrder(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number) throws WxPayException {

        JSONObject resp = appOrderService.placeOrder(number);

        return DataResp.ok(resp);
    }

    @AppToken
//    @PassToken
    @UpdateLog(value = "订单合单支付")
    @ApiOperation(value = "订单合单支付")
    @PostMapping("/placeCombineOrder/{number}")
    public DataResp<Object> placeCombineOrder(
            @ApiParam(name = "number", value = "主订单编号", required = true)
            @PathVariable String number)  {

        JSONObject resp = appOrderService.placeCombineOrder(number);

        return DataResp.ok(resp);
    }

    @AppToken
    @QueryLog(value = "我的订单列表")
    @ApiOperation(value = "查询我的订单列表")
    @GetMapping("/queryPageOrderInfo")
    public DataResp<Map<String, Object>> queryPageOrderInfo(
            @ApiParam(name = "AppOrderInfoListQO", value = "我的订单查询条件")
                    AppOrderInfoListQO appOrderInfoListQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        List<AppOrderInfoListDTO> appOrderInfoListDTOS = appOrderService.queryPageAppOrderInfo(appOrderInfoListQO, pageReq);
        return DataResp.BuilderMap.create().ok().put("list", appOrderInfoListDTOS).build();
    }

    @AppToken
    @QueryLog(value = "订单详情")
    @ApiOperation(value = "根据订单号查询订单详情")
    @GetMapping("/findOrderInfo/{number}")
    public DataResp<AppOrderDetailVO> findOrderInfo(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number) {

        AppOrderDetailVO orderDetailVO = appOrderService.findOrderInfo(number);
        return DataResp.ok(orderDetailVO);
    }

    @AppToken
    @QueryLog(value = "订单查询")
    @ApiOperation(value = "根据主单号查询订单")
    @GetMapping("/findOrderByMainNum/{mainNumber}")
    public DataResp<List<String>> findOrderByMainNumber(
            @ApiParam(name = "mainNumber", value = "主单号", required = true)
            @PathVariable String mainNumber) {

        List<String> numbers = appOrderService.findOrderByMainNumber(mainNumber);
        return DataResp.ok(numbers);
    }

    @AppToken
    @QueryLog(value = "根据订单ID查询订单物流记录表数据")
    @ApiOperation(value = "订单ID查询物流记录")
    @GetMapping("/queryOrderLogistics/{id}")
    public DataResp<AppOrderLogisticsVO> queryOrderLogistics(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        AppOrderLogisticsVO appOrderLogisticsVO = appOrderService.queryOrderLogistics(id);
        return DataResp.ok(appOrderLogisticsVO);
    }


    @AppToken
    @UpdateLog(value = "订单状态变更")
    @ApiOperation(value = "订单状态变更")
    @PostMapping("/changeOrderStatus/{number}/{orderStatus}")
    public DataResp<Object> changeOrderStatus(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number,
            @ApiParam(name = "orderStatus", value = "订单状态", required = true)
            @PathVariable String orderStatus) {

        Boolean bool = appOrderService.changeOrderStatus(number, orderStatus);

        return DataResp.ok(bool);
    }

    @AppToken
    @UpdateLog(value = "客户删除订单")
    @ApiOperation(value = "客户删除订单")
    @PostMapping("/delOrderInfo/{number}")
    public DataResp<Object> delOrderInfo(
            @ApiParam(name = "number", value = "订单编号", required = true)
            @PathVariable String number) {

        Boolean bool = appOrderService.delOrderInfo(number);

        return DataResp.ok(bool);
    }

    @AppToken
    @QueryLog(value = "查询我的订单列表个数")
    @ApiOperation(value = "查询我的订单列表个数")
    @GetMapping("/queryCountOrderByStatus")
    public DataResp<List<Map<String,Object>>> queryCountOrderByStatus() {

        List<Map<String,Object>> list = appOrderService.queryCountOrderByStatus();
        return DataResp.ok(list);
    }


    @UpdateLog(value = "根据订单ID解冻剩余资金")
    @ApiOperation(value = "------根据订单ID解冻剩余资金")
    @PutMapping("/doprofitsharing/{id}")
    public DataResp doprofitsharing(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        String out = orderInfoService.orderFundReleaseHourTimeout(id);

        return DataResp.ok(out);
    }

    @UpdateLog(value = "根据订单ID执行清算")
    @ApiOperation(value = "------根据订单ID执行清算")
    @PutMapping("/doOrderSettlement/{id}")
    public DataResp doOrderSettlement(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        orderInfoService.doOrderSettlement(id);

        return DataResp.ok();
    }


    @QueryLog(value = "查询资金解冻结果")
    @ApiOperation(value = "------查询资金解冻结果")
    @GetMapping("/queryresult/{id}/{out}/{trid}")
    public DataResp queryresult(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "out", value = "客户请求号", required = true)
            @PathVariable String out,
            @ApiParam(name = "trid", value = "trid", required = true)
            @PathVariable String trid) {

        OrderInfo orderInfo = orderInfoService.findById(id);
        String state = "";

        try {
            WxPayService wxPayService = shopThirdService.findWxPayServiceById(orderInfo.getShopId());
            ProfitSharingResult response  = wxPayService.getProfitSharingV3Service().getProfitSharingResult(out, trid);
            state = response.getState();
        }catch(Exception e){

        }

        return DataResp.ok(state);
    }

    @AppToken
    @QueryLog(value = "校验验证码")
    @ApiOperation(value = "校验验证码")
    @GetMapping("/checkVerifyCode")
    public DataResp<?> checkVerifyCode(HttpServletRequest request,
                                       @ApiParam(name = "phone", value = "手机号", required = true) @RequestParam(value = "phone") String phone,
                                       @ApiParam(name = "code",value = "验证码",required = true)  @RequestParam(value = "code") String code
            ) {
        String ip = IpUtil.getIpAddress(request);
        appOrderService.checkVerifyCode(ip,phone,code);
        return DataResp.ok();
    }
}
