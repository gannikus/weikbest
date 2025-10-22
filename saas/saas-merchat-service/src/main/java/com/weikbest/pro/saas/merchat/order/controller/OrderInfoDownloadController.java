package com.weikbest.pro.saas.merchat.order.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.order.module.excel.OrderInfoDetailExcel;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 订单表下载 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-01
 */
@Slf4j
@Api(tags = {"order::订单下载接口"})
@Controller
@RequestMapping("/order/order-info-download")
public class OrderInfoDownloadController {

    @Resource
    private OrderInfoService orderInfoService;

    @UseToken
    @QueryLog(value = "下载明细", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "下载明细")
    @GetMapping("/downloadDetail")
    public void downloadDetail(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(name = "orderInfoQO", value = "查询条件")
                    OrderInfoQO orderInfoQO) throws IOException {

        if (ObjectUtil.isNull(orderInfoQO.getBusinessId())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "关联商户ID不为空！");
        }

        // 获取excel下载数据
        List<OrderInfoDetailExcel> orderInfoDetailExcelList = orderInfoService.downloadDetail(orderInfoQO);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(String.format("订单列表_%s.xlsx", DateUtil.now()), "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setHeader("filename", fileName);
        EasyExcel.write(response.getOutputStream(), OrderInfoDetailExcel.class).sheet("订单明细").doWrite(orderInfoDetailExcelList);
    }
}
