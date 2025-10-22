package com.weikbest.pro.saas.applet.complaint.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.applet.complaint.module.dto.AppOrderComplaintDTO;
import com.weikbest.pro.saas.applet.complaint.module.vo.AppOrderComplaintVO;
import com.weikbest.pro.saas.applet.complaint.service.AppOrderComplaintService;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.AppToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintQO;
import com.weikbest.pro.saas.merchat.complaint.module.qo.OrderComplaintWxQO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.ComplaintBusiStatusGroupVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintDetailVO;
import com.weikbest.pro.saas.merchat.complaint.module.vo.OrderComplaintPageVO;
import com.weikbest.pro.saas.merchat.complaint.service.OrderComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单投诉表 前端控制器
 * </p>
 *
 * @author weike
 * @since 2022-11-29
 */
@Slf4j
@Api(tags = {"appcomplaint::小程序订单投诉表接口"})
@RestController
@RequestMapping("/appcomplaint")
public class AppOrderComplaintController {

    @Resource
    private OrderComplaintService orderComplaintService;

    @Resource
    private AppOrderComplaintService appOrderComplaintService;


    @AppToken
    @QueryLog(value = "根据ID查询订单投诉表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<OrderComplaintDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderComplaintDetailVO orderComplaintDetailVO = orderComplaintService.findOrderComplaintDetailVOById(id);
        return DataResp.ok(orderComplaintDetailVO);
    }

    @AppToken
    @QueryLog(value = "分页查询订单投诉表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<AppOrderComplaintVO>> queryPage(
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        List<AppOrderComplaintVO> appOrderComplaintVOS = appOrderComplaintService.queryListPage(pageReq);

        return PageResp.ok(appOrderComplaintVOS);
    }

    @AppToken
    @SaveLog(value = "客户创建投诉单")
    @ApiOperation(value = "客户创建投诉单")
    @PutMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "appOrderComplaintDTO", value = "投诉单入参")
                    AppOrderComplaintDTO appOrderComplaintDTO) {

        boolean bool = appOrderComplaintService.insert(appOrderComplaintDTO);

        return DataResp.ok(bool);
    }

    @AppToken
    @UpdateLog(value = "用户撤销投诉单")
    @ApiOperation(value = "用户撤销投诉单")
    @PutMapping("/userUndo/{id}")
    public DataResp<Boolean> userUndo(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean bool = appOrderComplaintService.userUndo(id);

        return DataResp.ok(bool);
    }

    @AppToken
    @UpdateLog(value = "用户是否认可")
    @ApiOperation(value = "用户是否认可（0-不认可 1-认可）")
    @PutMapping("/userDo/{id}/{complaintCustStatus}")
    public DataResp<Boolean> userDo(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "complaintCustStatus", value = "用户是否认可（0-不认可 1-认可）", required = true)
            @PathVariable String complaintCustStatus) {

        boolean bool = appOrderComplaintService.userDo(id,complaintCustStatus);

        return DataResp.ok(bool);
    }

}
