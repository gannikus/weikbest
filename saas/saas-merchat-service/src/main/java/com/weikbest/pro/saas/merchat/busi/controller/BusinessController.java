package com.weikbest.pro.saas.merchat.busi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.token.TokenUser;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusinessQO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserLoginInfoVO;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.sys.common.service.CurrentUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 商户表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"busi::商户表接口"})
@RestController
@RequestMapping("/busi/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @Resource
    private CurrentUserService currentUserService;

    @UseToken
    @QueryLog(value = "根据登录用户获取商户信息", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据登录用户获取商户信息")
    @GetMapping("/findIdByCurrent")
    public DataResp<BusiUserLoginInfoVO> findIdByCurrent(HttpServletRequest request) {

        TokenUser tokenUser = currentUserService.getTokenUser(request);

        BusiUserLoginInfoVO busiUserLoginInfoVOList = businessService.findByTokenUser(tokenUser);
        return DataResp.ok(busiUserLoginInfoVOList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Business> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Business business = businessService.findById(id);
        return DataResp.ok(business);
    }

    @UseToken
    @QueryLog(value = "分页查询商户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Business>> queryPage(
            @ApiParam(name = "businessQO", value = "查询条件")
                    BusinessQO businessQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Business> pageModel = businessService.queryPage(businessQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
