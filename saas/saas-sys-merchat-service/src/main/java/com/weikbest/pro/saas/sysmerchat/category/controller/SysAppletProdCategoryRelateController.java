package com.weikbest.pro.saas.sysmerchat.category.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryRelateService;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdListVO;
import com.weikbest.pro.saas.sysmerchat.category.module.qo.SysAppletProdCategoryRelateQO;
import com.weikbest.pro.saas.sysmerchat.category.service.SysAppletProdCategoryRelateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 小程序商品类目关联商品表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Slf4j
@Api(tags = {"category::平台商品与小程序商品类目关联接口"})
@RestController
@RequestMapping("/sys/applet-prod-category-relate")
public class SysAppletProdCategoryRelateController {

    @Resource
    private SysAppletProdCategoryRelateService sysAppletProdCategoryRelateService;

    @Resource
    private AppletProdCategoryRelateService appletProdCategoryRelateService;

    @UseToken
    @SaveLog(value = "商品分配到指定小程序类目")
    @ApiOperation(value = "商品分配到指定小程序类目")
    @PostMapping("/associateProd")
    public DataResp<Boolean> associateProd(
            @ApiParam(name = "appletProdCategoryRelateDTOList", value = "关联商品数据信息", required = true)
            @RequestBody @Valid List<AppletProdCategoryRelateDTO> appletProdCategoryRelateDTOList, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = sysAppletProdCategoryRelateService.associateProdList(appletProdCategoryRelateDTOList);
        return DataResp.ok(save);
    }

    @UseToken
    @RemoveLog(value = "根据关联商品小程序类目ID和商品ID删除")
    @ApiOperation(value = "根据关联商品小程序类目ID和商品ID删除")
    @DeleteMapping("/deleteByAppletProdCategotyIdAndProdId/{appletProdCategotyId}/{prodId}")
    public DataResp<Object> delete(
            @ApiParam(name = "appletProdCategotyId", value = "关联商品小程序类目ID", required = true)
            @PathVariable Long appletProdCategotyId,
            @ApiParam(name = "prodId", value = "商品ID", required = true)
            @PathVariable Long prodId) {
        List<Long> prodIdList = new ArrayList<>();
        prodIdList.add(prodId);
        sysAppletProdCategoryRelateService.deleteByAppletProdCategotyIdAndProdId(appletProdCategotyId , prodIdList);
        return DataResp.ok();
    }


    @UseToken
    @RemoveLog(value = "根据关联商品小程序类目ID和商品ID集删除")
    @ApiOperation(value = "根据关联商品小程序类目ID和商品ID集删除")
    @DeleteMapping("/deleteByAppletProdCategotyIdAndProdId/{appletProdCategotyId}")
    public DataResp<Object> deleteBatch(
            @ApiParam(name = "appletProdCategotyId", value = "关联商品小程序类目ID", required = true)
            @PathVariable Long appletProdCategotyId,
            @ApiParam(name = "prodIdList", value = "商品ID集合", required = true)
            @RequestBody List<Long> prodIdList) {
        sysAppletProdCategoryRelateService.deleteByAppletProdCategotyIdAndProdId(appletProdCategotyId , prodIdList);
        return DataResp.ok();
    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除小程序商品类目关联商品表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        appletProdCategoryRelateService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "分页查询商品基本信息表数据")
    @ApiOperation(value = "分页查询商品信息，可带小程序类目条件")
    @GetMapping("/queryProdPage")
    public DataResp<List<ProdListVO>> queryPage(
            @ApiParam(name = "sysAppletProdCategoryRelateQO", value = "查询条件")
                    SysAppletProdCategoryRelateQO sysAppletProdCategoryRelateQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ProdListVO> pageModel = sysAppletProdCategoryRelateService.queryPage(sysAppletProdCategoryRelateQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
