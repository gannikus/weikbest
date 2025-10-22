package com.weikbest.pro.saas.applet.aftersale.controller;

import cn.hutool.core.util.ObjectUtil;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterLogisticsInfoDTO;
import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterSaleDTO;
import com.weikbest.pro.saas.applet.aftersale.module.qo.AppOrderAfterSaleQO;
import com.weikbest.pro.saas.applet.aftersale.service.AppOrderAfterSaleService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.AppOrderAfterSaleListDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单售后表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"orderaftersale::订单售后接口"})
@RestController
@RequestMapping("/apporderaftersale")
public class AppOrderAfterSaleController {

    @Resource
    private AppOrderAfterSaleService appOrderAfterSaleService;

    @Resource
    private OrderAfterSaleService orderAfterSaleService;

    @AppToken
    @SaveLog(value = "售后申请")
    @ApiOperation(value = "售后申请")
    @GetMapping("/apply")
    public DataResp<String> apply(
            @ApiParam(name = "appCustAfterSaleDTO", value = "appCustAfterSaleDTO售后申请信息")
                    AppOrderAfterSaleDTO appOrderAfterSaleDTO) {

        Long orderAftersaleid = 0L;

        if (ObjectUtil.isEmpty(appOrderAfterSaleDTO.getId()) || appOrderAfterSaleDTO.getId() == 0L) {
            orderAftersaleid = appOrderAfterSaleService.apply(appOrderAfterSaleDTO);
        } else {
            orderAftersaleid = appOrderAfterSaleService.reapply(appOrderAfterSaleDTO);
        }

        return DataResp.ok(String.valueOf(orderAftersaleid));
    }

    @AppToken
    @UpdateLog(value = "撤销申请")
    @ApiOperation(value = "撤销申请")
    @GetMapping("/revoke/{id}")
    public DataResp<Boolean> revoke(
            @ApiParam(name = "id", value = "售后单ID", required = true)
            @PathVariable Long id) {

        Boolean bool = appOrderAfterSaleService.revoke(id);

        return DataResp.ok(bool);
    }

    @AppToken
    @UpdateLog(value = "增加客户退货物流信息")
    @ApiOperation(value = "增加客户退货物流信息")
    @GetMapping("/addlogistics")
    public DataResp<Boolean> addlogistics(
            @ApiParam(name = "appOrderAfterLogisticsInfoDTO", value = "客户售后物流信息")
                    AppOrderAfterLogisticsInfoDTO appOrderAfterLogisticsInfoDTO) {

        Boolean bool = appOrderAfterSaleService.addlogistics(appOrderAfterLogisticsInfoDTO);

        return DataResp.ok(bool);
    }

    @AppToken
    @QueryLog(value = "查看订单售后详情信息")
    @ApiOperation(value = "查看订单售后详情信息")
    @GetMapping("/detail/{orderAfterId}")
    public DataResp<OrderAfterSaleDetailVO> detail(
            @ApiParam(name = "orderAfterId", value = "订单售后ID")
            @PathVariable Long orderAfterId) {

        //AppOrderAfterSaleDetailVO detailVO = appOrderAfterSaleService.detail(orderAfterId);

        OrderAfterSaleDetailVO orderAfterSaleDetailVO = orderAfterSaleService.findOrderAfterSaleDetailVOById(orderAfterId);

        return DataResp.ok(orderAfterSaleDetailVO);
    }

    @AppToken
    @QueryLog(value = "售后申请列表")
    @ApiOperation(value = "售后申请列表")
    @GetMapping("/applylist")
    public DataResp<Map<String, Object>> applylist(
            @ApiParam(name = "appOrderAfterSaleQO", value = "查询条件")
                    AppOrderAfterSaleQO appOrderAfterSaleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {


        List<AppOrderAfterSaleApplyDTO> dtoList = appOrderAfterSaleService.queryPageAppOrderAfterApply(appOrderAfterSaleQO, pageReq);

        return DataResp.BuilderMap.create().ok().put("list", dtoList).build();
    }

    @AppToken
    @QueryLog(value = "我的售后列表")
    @ApiOperation(value = "我的售后列表")
    @GetMapping("/list")
    public DataResp<Map<String, Object>> list(
            @ApiParam(name = "appOrderAfterSaleQO", value = "查询条件")
                    AppOrderAfterSaleQO appOrderAfterSaleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {


        List<AppOrderAfterSaleListDTO> dtoList = appOrderAfterSaleService.queryPageAppOrderAfterList(appOrderAfterSaleQO, pageReq);

        return DataResp.BuilderMap.create().ok().put("list", dtoList).build();
    }

    @AppToken
    @UpdateLog(value = "客户确认收货")
    @ApiOperation(value = "客户确认收货")
    @GetMapping("/confirmReceipt/{id}")
    public DataResp<Boolean> confirmReceipt(
            @ApiParam(name = "id", value = "售后单ID", required = true)
            @PathVariable Long id) {

        Boolean bool = appOrderAfterSaleService.confirmReceipt(id);

        return DataResp.ok(bool);
    }

    @AppToken
    @QueryLog(value = "查询是否存在售后单")
    @ApiOperation(value = "查询是否存在售后单")
    @GetMapping("/whetherOrderAfter/{id}")
    public DataResp<String> whetherOrderAfter(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable Long id) {

        String afterId = appOrderAfterSaleService.whetherOrderAfter(id).toString();

        return DataResp.ok(afterId);
    }

}
