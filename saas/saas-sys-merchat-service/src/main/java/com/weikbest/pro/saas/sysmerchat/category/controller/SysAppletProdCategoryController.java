package com.weikbest.pro.saas.sysmerchat.category.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryInsertDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryUpdateDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletProdCategoryQO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletProdCategoryVO;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 小程序商品类目表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Slf4j
@Api(tags = {"category::平台端小程序商品类目表接口"})
@RestController
@RequestMapping("/sys/applet-prod-category")
public class SysAppletProdCategoryController {

    @Resource
    private AppletProdCategoryService appletProdCategoryService;

    @UseToken
    @SaveLog(value = "新增小程序商品类目表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "appletProdCategoryInsertDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid AppletProdCategoryInsertDTO appletProdCategoryInsertDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = appletProdCategoryService.insert(appletProdCategoryInsertDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新小程序商品类目表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "appletProdCategoryDTO", value = "更新数据信息", required = true)
            @RequestBody @Valid AppletProdCategoryUpdateDTO appletProdCategoryUpdateDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = appletProdCategoryService.updateById(id, appletProdCategoryUpdateDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除小程序商品类目表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        appletProdCategoryService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除小程序商品类目表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        appletProdCategoryService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询小程序商品类目表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<AppletProdCategoryVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        AppletProdCategoryVO appletProdCategoryVO = appletProdCategoryService.findVOById(id);
        return DataResp.ok(appletProdCategoryVO);
    }

    @UseToken
    @QueryLog(value = "分页查询小程序商品类目表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<AppletProdCategoryVO>> queryPage(
            @ApiParam(name = "appletProdCategoryQO", value = "查询条件")
                    AppletProdCategoryQO appletProdCategoryQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<AppletProdCategoryVO> pageModel = appletProdCategoryService.queryPage(appletProdCategoryQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "查询小程序商品类目表数据")
    @ApiOperation(value = "查询全部小程序商品类目表数据")
    @GetMapping("/queryTree")
    public DataResp<List<Dtree>> queryTree(@ApiParam(name = "level", value = "是否返回层级数据")
                                           @RequestParam Boolean level) {
        List<Dtree> dtreeList = appletProdCategoryService.queryTree(level);
        return DataResp.ok(dtreeList);
    }


}
