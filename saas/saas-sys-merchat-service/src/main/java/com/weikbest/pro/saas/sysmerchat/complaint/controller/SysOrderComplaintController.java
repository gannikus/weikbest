package com.weikbest.pro.saas.sysmerchat.complaint.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintWxQO;
import com.weikbest.pro.saas.merchat.complaint.module.resp.WxOrderComplaintResp;
import com.weikbest.pro.saas.merchat.complaint.module.vo.ComplaintBusiStatusGroupVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintPageVO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import com.weikbest.pro.saas.sysmerchat.complaint.module.qo.SysComplaintGroupQO;
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
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/19
 */
@Slf4j
@Api(tags = {"SysComplaint::平台端订单投诉表接口"})
@RestController
@RequestMapping("/sys/complaint/order-complaint")
public class SysOrderComplaintController {

    @Resource
    private OrderComplaintService orderComplaintService;

    @UseToken
    @QueryLog(value = "分页查询微信订单投诉表数据")
    @ApiOperation(value = "分页查询微信订单投诉表")
    @GetMapping("/queryPageWithWx")
    public DataResp<WxOrderComplaintResp> queryPageWithWx(
            @ApiParam(name = "orderComplaintWxQO", value = "查询条件")
                    OrderComplaintWxQO orderComplaintWxQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        return DataResp.ok(orderComplaintService.queryPageWithWx(orderComplaintWxQO, pageReq));
    }

    @UseToken
    @QueryLog(value = "分页查询订单投诉表数据")
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
    @QueryLog(value = "查询订单投诉处理状态汇总数据")
    @ApiOperation(value = "查询订单投诉处理状态汇总数据")
    @GetMapping("/queryGroupComplaintBusiStatus")
    public DataResp<List<ComplaintBusiStatusGroupVO>> queryGroupComplaintBusiStatus(
            @Valid SysComplaintGroupQO sysComplaintGroupQO, BindingResult bindingResult) {
        JsrCheckUtil.valid(bindingResult);

        List<ComplaintBusiStatusGroupVO> complaintBusiStatusGroupVO = orderComplaintService.queryGroupComplaintBusiStatus(sysComplaintGroupQO.getBusinessId(), sysComplaintGroupQO.getShopId(), sysComplaintGroupQO.getComplaintType());
        return DataResp.ok(complaintBusiStatusGroupVO);
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单投诉表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderComplaintDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderComplaintDetailVO orderComplaintDetailVO = orderComplaintService.findOrderComplaintDetailVOById(id);
        return DataResp.ok(orderComplaintDetailVO);
    }
}
