package com.weikbest.pro.saas.merchat.complaint.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.complaint.module.dto.OrderComplaintBusinessConfirmDTO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintWxQO;
import com.weikbest.pro.saas.merchat.complaint.module.resp.WxOrderComplaintResp;
import com.weikbest.pro.saas.merchat.complaint.module.vo.ComplaintBusiStatusGroupVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintPageVO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单投诉表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Slf4j
@Api(tags = {"complaint::订单投诉表接口"})
@RestController
@RequestMapping("/complaint/order-complaint")
public class OrderComplaintController {

    @Resource
    private OrderComplaintService orderComplaintService;

    @UseToken
    @UpdateLog(value = "商户处理投诉数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "商户处理投诉")
    @PutMapping("/businessExecuteComplaint/{id}")
    public DataResp<Boolean> businessExecuteComplaint(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "orderComplaintBusinessConfirmDTO", value = "更新数据信息", required = true)
            @Valid OrderComplaintBusinessConfirmDTO orderComplaintBusinessConfirmDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = orderComplaintService.businessExecuteComplaint(id, orderComplaintBusinessConfirmDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "分页查询微信订单投诉表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询微信订单投诉表")
    @GetMapping("/queryPageWithWx")
    public WxOrderComplaintResp queryPageWithWx(
            @ApiParam(name = "orderComplaintWxQO", value = "查询条件")
                    OrderComplaintWxQO orderComplaintWxQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        if (ObjectUtil.isNull(orderComplaintWxQO.getBeginDate())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "查询开始日期不为空！");
        }
        if (ObjectUtil.isNull(orderComplaintWxQO.getEndDate())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "查询结束日期不为空！");
        }

        return orderComplaintService.queryPageWithWx(orderComplaintWxQO, pageReq);
    }

    @UseToken
    @QueryLog(value = "分页查询订单投诉表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<OrderComplaintPageVO>> queryPage(
            @ApiParam(name = "orderComplaintQO", value = "查询条件")
                    OrderComplaintQO orderComplaintQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderComplaintPageVO> pageModel = orderComplaintService.queryPage(orderComplaintQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "查询订单投诉处理状态汇总数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询订单投诉处理状态汇总数据")
    @GetMapping("/queryGroupComplaintBusiStatus/{businessId}/{shopId}")
    public DataResp<List<ComplaintBusiStatusGroupVO>> queryGroupComplaintBusiStatus(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId,
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "complaintType", value = "投诉类型 1-微信支付投诉(不支持) 2-店铺投诉", required = true)
            @RequestParam String complaintType) {

        List<ComplaintBusiStatusGroupVO> complaintBusiStatusGroupVO = orderComplaintService.queryGroupComplaintBusiStatus(businessId, shopId, complaintType);
        return DataResp.ok(complaintBusiStatusGroupVO);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单投诉表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderComplaintDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderComplaintDetailVO orderComplaintDetailVO = orderComplaintService.findOrderComplaintDetailVOById(id);
        return DataResp.ok(orderComplaintDetailVO);
    }


    @PassToken
    @QueryLog(value = "获取微信订单投诉未处理数量", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "获取微信订单投诉未处理数量")
    @GetMapping("/getPageWithWxCount")
    public DataResp<Map<String , Integer>> getPageWithWxCount(
            @ApiParam(name = "orderComplaintWxQO", value = "查询条件")
            OrderComplaintWxQO orderComplaintWxQO) {
        return DataResp.ok(orderComplaintService.getPageWithWxCount(orderComplaintWxQO));
    }
}
