package com.weikbest.pro.saas.sysmerchat.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopFinanceDetailQO;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceDetailService;
import com.weikbest.pro.saas.sysmerchat.shop.module.qo.SysShopFinanceDetailQO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailPageVO;
import com.weikbest.pro.saas.sysmerchat.shop.module.vo.SysShopFinanceDetailVO;
import com.weikbest.pro.saas.sysmerchat.shop.service.SysShopFinanceDetailService;
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

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/12/24
 */
@Slf4j
@Api(tags = {"sysShopFinance::平台财务接口"})
@RestController
@RequestMapping("/sys/shop/shop-finance-detail")
public class SysShopFinanceDetailController {

    @Resource
    private SysShopFinanceDetailService sysShopFinanceDetailService;

    @UseToken
    @QueryLog(value = "财务概况-平台概况")
    @ApiOperation(value = "财务概况-平台概况")
    @GetMapping("/findSysShopFinanceDetail")
    public DataResp<SysShopFinanceDetailVO> findSysShopFinanceDetail() {

        SysShopFinanceDetailVO shopFinanceDetail = sysShopFinanceDetailService.findSysShopFinanceDetail();
        return DataResp.ok(shopFinanceDetail);
    }

    @UseToken
    @QueryLog(value = "分页查询店铺资金明细表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<SysShopFinanceDetailPageVO>> queryPage(
            @ApiParam(name = "sysShopFinanceDetailQO", value = "查询条件")
                    SysShopFinanceDetailQO sysShopFinanceDetailQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<SysShopFinanceDetailPageVO> pageModel = sysShopFinanceDetailService.queryPage(sysShopFinanceDetailQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

}
