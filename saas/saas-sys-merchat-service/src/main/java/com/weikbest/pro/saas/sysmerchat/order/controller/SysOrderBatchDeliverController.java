package com.weikbest.pro.saas.sysmerchat.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.PassToken;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.order.entity.OrderBatchDeliver;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderBatchDeliverQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderBatchDeliverListVO;
import com.weikbest.pro.saas.merchat.order.service.OrderBatchDeliverService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/11/20
 */
@Slf4j
@Api(tags = {"SysOrder::平台端订单批量发货记录表接口"})
@Controller
@RequestMapping("/sys/order/order-batch-deliver")
public class SysOrderBatchDeliverController {


    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private OrderBatchDeliverService orderBatchDeliverService;

    @UseToken
    @SaveLog(value = "批量发货，修改订单状态为已发货")
    @ApiOperation(value = "批量发货，修改订单状态为已发货")
    @ResponseBody
    @PostMapping("/batchOrderDeliverAndUpdateOrderStatus/{businessId}/{shopId}")
            public DataResp<String> batchOrderDeliverAndUpdateOrderStatus(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId,
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "file", value = "导入excel文件", required = true)
            @RequestParam("file") MultipartFile excelFile) {

        if (excelFile == null || excelFile.isEmpty()) {
            throw new WeikbestException(ResultConstant.UPLOADFILE_FAIL);
        }

        Long id = orderBatchDeliverService.batchOrderDeliverAndUpdateOrderStatus(businessId, shopId, excelFile);

        return DataResp.ok(String.valueOf(id));
    }

    @UseToken
    @QueryLog(value = "根据ID查询订单批量发货记录表数据")
    @ApiOperation(value = "根据ID查询")
    @ResponseBody
    @GetMapping("/find/{id}")
    public DataResp<OrderBatchDeliver> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        OrderBatchDeliver orderBatchDeliver = orderBatchDeliverService.findById(id);
        return DataResp.ok(orderBatchDeliver);
    }

    @UseToken
    @QueryLog(value = "分页查询订单批量发货记录表数据")
    @ApiOperation(value = "分页查询")
    @ResponseBody
    @GetMapping("/queryPage")
    public DataResp<List<OrderBatchDeliverListVO>> queryPage(
            @ApiParam(name = "orderBatchDeliverQO", value = "查询条件")
                    OrderBatchDeliverQO orderBatchDeliverQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<OrderBatchDeliverListVO> pageModel = orderBatchDeliverService.queryPage(orderBatchDeliverQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @PassToken
    @ApiOperation(value = "excel文件下载")
    @GetMapping("/download/{id}")
    public void downloadExcel(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(name = "id", value = "批量发货记录ID", required = true)
            @PathVariable Long id) {

        // 获取excel模板
        OrderBatchDeliver orderBatchDeliver = orderBatchDeliverService.findById(id);

        // excel下载
        thirdConfigService.aliyunOssService().downloadFileAvatarToWeb(orderBatchDeliver.getOperateExcelUrl(), orderBatchDeliver.getOperateExcelName(), response);
    }
}
