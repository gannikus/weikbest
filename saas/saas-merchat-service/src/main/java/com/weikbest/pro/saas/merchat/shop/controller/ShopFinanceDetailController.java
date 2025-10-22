package com.weikbest.pro.saas.merchat.shop.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.shop.module.excel.ShopFinanceDetailExcel;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopAccountSettleAmountVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailPageVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceDetailService;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 店铺资金明细表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shop::店铺资金明细表接口"})
@Controller
@RequestMapping("/shop/shop-finance-detail")
public class ShopFinanceDetailController {

    @Resource
    private ThirdConfigService thirdConfigService;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopFinanceDetailService shopFinanceDetailService;

    @UseToken
    @QueryLog(value = "分页查询店铺资金明细表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    @ResponseBody
    public DataResp<List<ShopFinanceDetailPageVO>> queryPage(
            @ApiParam(name = "shopFinanceDetailQO", value = "查询条件")
                    ShopFinanceDetailQO shopFinanceDetailQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        if (ObjectUtil.isNull(shopFinanceDetailQO.getBusinessId())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "关联商户ID不为空！");
        }

        IPage<ShopFinanceDetailPageVO> pageModel = shopFinanceDetailService.queryPage(shopFinanceDetailQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "下载明细", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "下载明细")
    @GetMapping("/downloadDetail")
    public void downloadDetail(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(name = "shopFinanceDetailQO", value = "查询条件")
                    ShopFinanceDetailQO shopFinanceDetailQO) throws IOException {

        if (ObjectUtil.isNull(shopFinanceDetailQO.getBusinessId())) {
            throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "关联商户ID不为空！");
        }

        // 获取excel下载数据
        List<ShopFinanceDetailExcel> shopFinanceDetailExcelList = shopFinanceDetailService.downloadDetail(shopFinanceDetailQO);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(String.format("资金明细_%s.xlsx", DateUtil.now()), "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setHeader("filename", fileName);
        EasyExcel.write(response.getOutputStream(), ShopFinanceDetailExcel.class).sheet("资金明细").doWrite(shopFinanceDetailExcelList);
    }

    @UseToken
    @QueryLog(value = "资产账户-贷款账户待结算金额", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "资产账户-贷款账户待结算金额")
    @GetMapping("/findShopAccountSettleAmount/{businessId}")
    @ResponseBody
    public DataResp<ShopAccountSettleAmountVO> findShopAccountSettleAmount(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @PathVariable Long businessId) {

        ShopAccountSettleAmountVO shopAccountSettleAmountVO = shopFinanceAccountService.findShopAccountSettleAmount(businessId);
        return DataResp.ok(shopAccountSettleAmountVO);
    }


}
