package com.weikbest.pro.saas.merchat.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopStatementDetailQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopStatementDetailPageVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopStatementDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺对账单明细表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shop::店铺对账单明细表接口"})
@RestController
@RequestMapping("/shop/shop-statement-detail")
public class ShopStatementDetailController {

    @Resource
    private ShopStatementDetailService shopStatementDetailService;

    @UseToken
    @QueryLog(value = "查询交易类型", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询交易类型")
    @GetMapping("/findStatementTypeGroup")
    public DataResp<Map<String, String>> findStatementTypeGroup() {

        Map<String, String> shopStatementDetail = shopStatementDetailService.queryStatementTypeGroup();
        return DataResp.ok(shopStatementDetail);
    }

//    @UseToken
//    @QueryLog(value = "根据ID查询店铺对账单明细表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<ShopStatementDetail> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ShopStatementDetail shopStatementDetail = shopStatementDetailService.findById(id);
//        return DataResp.ok(shopStatementDetail);
//    }

    @UseToken
    @QueryLog(value = "分页查询店铺对账单明细表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ShopStatementDetailPageVO>> queryPage(
            @ApiParam(name = "shopStatementDetailQO", value = "查询条件")
                    ShopStatementDetailQO shopStatementDetailQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopStatementDetailPageVO> pageModel = shopStatementDetailService.queryPage(shopStatementDetailQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
