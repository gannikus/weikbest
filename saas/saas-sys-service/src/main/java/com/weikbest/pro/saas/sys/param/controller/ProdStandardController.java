package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.ProdStandard;
import com.weikbest.pro.saas.sys.param.module.dto.ProdStandardDTO;
import com.weikbest.pro.saas.sys.param.service.ProdStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 系统商品规范表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-02
 */
@Slf4j
@Api(tags = {"param::系统商品规范表接口"})
@RestController
@RequestMapping("/param/prod-standard")
public class ProdStandardController {

    @Resource
    private ProdStandardService prodStandardService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新系统商品规范表数据")
    @ApiOperation(value = "新增或更新系统商品规范")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(@ApiParam(name = "prodStandardDTO", value = "保存数据信息", required = true)
                                          @Valid ProdStandardDTO prodStandardDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = prodStandardService.saveOrUpdate(prodStandardDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "查询系统商品规范表数据")
    @ApiOperation(value = "查询系统商品规范")
    @GetMapping("/find")
    public DataResp<ProdStandard> find() {

        ProdStandard prodStandard = prodStandardService.findProdStandard(false);
        return DataResp.ok(prodStandard);
    }
//
//    @UseToken
//    @SaveLog(value = "新增系统商品规范表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "prodStandardDTO", value = "保存数据信息", required = true)
//            @Valid ProdStandardDTO prodStandardDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = prodStandardService.insert(prodStandardDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新系统商品规范表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "prodStandardDTO", value = "更新数据信息", required = true)
//            @Valid ProdStandardDTO prodStandardDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = prodStandardService.updateById(id, prodStandardDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统商品规范表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        prodStandardService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统商品规范表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        prodStandardService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询系统商品规范表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp<ProdStandard> find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        ProdStandard prodStandard = prodStandardService.findById(id);
//        return DataResp.ok(prodStandard);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询系统商品规范表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp<List<ProdStandard>> queryPage(
//            @ApiParam(name = "prodStandardQO", value = "查询条件")
//                    ProdStandardQO prodStandardQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<ProdStandard> pageModel = prodStandardService.queryPage(prodStandardQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
