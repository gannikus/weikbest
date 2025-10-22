package com.weikbest.pro.saas.sysmerchat.aftersale.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmAndReDeliveryDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmCustomerApplyDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleBusinessConfirmCustomerDeliveryDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.qo.OrderAfterSaleQO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleKeyGroupVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleListVO;
import com.weikbest.pro.saas.merchat.aftersale.service.OrderAfterSaleService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sysmerchat.aftersale.module.qo.SysOrderAfterSaleGroupQO;
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
 * 订单售后表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"SysAftersale::平台订单售后表接口"})
@RestController
@RequestMapping("/sys/aftersale/order-after-sale")
public class SysOrderAfterSaleController {

    @Resource
    private OrderAfterSaleService orderAfterSaleService;


    @UseToken
    @QueryLog(value = "查询订单售后关键节点汇总数据")
    @ApiOperation(value = "查询订单售后关键节点汇总数据")
    @GetMapping("/queryGroupOrderAfterSaleKey")
    public DataResp<List<OrderAfterSaleKeyGroupVO>> queryGroupOrderAfterSaleKey(SysOrderAfterSaleGroupQO sysOrderAfterSaleGroupQO) {

        List<OrderAfterSaleKeyGroupVO> orderAfterSaleStatusGroupVO = orderAfterSaleService.queryGroupOrderAfterSaleKey(sysOrderAfterSaleGroupQO.getBusinessId(), sysOrderAfterSaleGroupQO.getShopId());
        return DataResp.ok(orderAfterSaleStatusGroupVO);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单售后表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderAfterSaleDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderAfterSaleDetailVO orderAfterSaleDetailVO = orderAfterSaleService.findOrderAfterSaleDetailVOById(id);
        return DataResp.ok(orderAfterSaleDetailVO);
    }

    @UseToken
    @QueryLog(value = "分页查询订单售后表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<OrderAfterSaleListVO>> queryPage(
            @ApiParam(name = "orderAfterSaleQO", value = "查询条件")
                    OrderAfterSaleQO orderAfterSaleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderAfterSaleListVO> pageModel = orderAfterSaleService.queryPage(orderAfterSaleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @UpdateLog(value = "批量同意仅退款")
    @ApiOperation(value = "批量同意仅退款")
    @PutMapping("/batchUpdateOnlyRefund")
    public DataResp<Boolean> batchUpdateOnlyRefund(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        List<OrderAfterSale> orderAfterSaleList = orderAfterSaleService.listByIds(ids);
        for (OrderAfterSale orderAfterSale : orderAfterSaleList) {
            // 售后单类型为“仅退款”、状态为“买家申请售后，待商家处理”
            if(!StrUtil.equals(orderAfterSale.getApplyType(), DictConstant.AfterSaleApplyType.only_refund.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的申请类型不是 仅退款！", orderAfterSale.getId()));
            }
            if(!StrUtil.equals(orderAfterSale.getAfterSaleStatus(), DictConstant.AfterSaleStatus.afterSaleStatus_2.getCode())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), StrUtil.format("售后单：{}的售后状态不是 客户申请售后，待商家处理！", orderAfterSale.getId()));
            }
        }

        Boolean update = orderAfterSaleService.batchUpdateOnlyRefund(ids);
        return DataResp.ok(update);
    }
    @UseToken
    @UpdateLog(value = "客户发起申请后，商家确认是否同意客户申请，并更新售后状态")
    @ApiOperation(value = "客户发起申请后，商家确认是否同意客户申请，并更新售后状态")
    @PutMapping("/businessConfirmCustomerApply/{id}")
    public DataResp<Boolean> businessConfirmCustomerApply(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderAfterSaleBusinessConfirmCustomerApplyDTO")
            @Valid OrderAfterSaleBusinessConfirmCustomerApplyDTO orderAfterSaleBusinessConfirmCustomerApplyDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmCustomerApply(id, orderAfterSaleBusinessConfirmCustomerApplyDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "客户寄回商品后，商家确认是否收货，并更新售后状态")
    @ApiOperation(value = "客户寄回商品后，商家确认是否收货，并更新售后状态")
    @PutMapping("/businessConfirmCustomerDelivery/{id}")
    public DataResp<Boolean> businessConfirmCustomerDelivery(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderAfterSaleBusinessConfirmCustomerApplyDTO", value = "客户寄回商品后，商家确认是否收货实体对象", required = true)
            @Valid OrderAfterSaleBusinessConfirmCustomerDeliveryDTO orderAfterSaleBusinessConfirmCustomerApplyDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmCustomerDelivery(id, orderAfterSaleBusinessConfirmCustomerApplyDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "商家确认收货后，商家确认退款，并更新售后状态")
    @ApiOperation(value = "商家确认收货后，商家确认退款，并更新售后状态")
    @PutMapping("/businessConfirmDeliveryAndRefund/{id}")
    public DataResp<Boolean> businessConfirmDeliveryAndRefund(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Boolean update = orderAfterSaleService.businessConfirmDeliveryAndRefund(id);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "商家确认收货后，商家重新发货，并更新售后状态")
    @ApiOperation(value = "商家确认收货后，商家重新发货，并更新售后状态")
    @PutMapping("/businessConfirmAndReDelivery")
    public DataResp<Boolean> businessConfirmAndReDelivery(
            @ApiParam(name = "orderAfterSaleBusinessConfirmAndReDeliveryDTO", value = "商家确认收货后，商家重新发货实体对象", required = true)
            @RequestBody @Valid OrderAfterSaleBusinessConfirmAndReDeliveryDTO orderAfterSaleBusinessConfirmAndReDeliveryDTO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        Boolean update = orderAfterSaleService.businessConfirmAndReDelivery(orderAfterSaleBusinessConfirmAndReDeliveryDTO);
        return DataResp.ok(update);
    }

    @UseToken
//    @PassToken
    @UpdateLog(value = "立即退款")
    @ApiOperation(value = "立即退款（除了已退款的订单，其他转态都可操作）")
    @PutMapping("/refundRightNow/{id}")
    public DataResp<?> refundRightNow(@ApiParam(name = "id", value = "ID", required = true)
                                      @PathVariable Long id){
        Boolean update = orderAfterSaleService.refundRightNow(id);
        return DataResp.ok(update);
    }

}
