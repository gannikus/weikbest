package com.weikbest.pro.saas.sys.param.controller;

import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveOrUpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.Deal;
import com.weikbest.pro.saas.sys.param.module.dto.DealDTO;
import com.weikbest.pro.saas.sys.param.service.DealService;
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
 * 系统交易规则表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Slf4j
@Api(tags = {"param::系统交易规则表接口"})
@RestController
@RequestMapping("/param/deal")
public class DealController {

    @Resource
    private DealService dealService;

    @UseToken
    @SaveOrUpdateLog(value = "新增或更新系统交易规则表数据")
    @ApiOperation(value = "新增或更新系统交易规则")
    @PostMapping("/saveOrUpdate")
    public DataResp<Boolean> saveOrUpdate(@ApiParam(name = "dealDTO", value = "保存数据信息", required = true)
                                          @Valid DealDTO dealDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = dealService.saveOrUpdate(dealDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "查询系统交易规则表数据")
    @ApiOperation(value = "查询系统交易规则")
    @GetMapping("/find")
    public DataResp<Deal> find() {

        Deal deal = dealService.findDeal(false);
        return DataResp.ok(deal);
    }

//    @UseToken
//    @SaveLog(value = "新增系统交易规则表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "dealDTO", value = "保存数据信息", required = true)
//            @Valid DealDTO dealDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = dealService.insert(dealDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新系统交易规则表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "dealDTO", value = "更新数据信息", required = true)
//            @Valid DealDTO dealDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = dealService.updateById(id, dealDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统交易规则表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        dealService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统交易规则表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        dealService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询系统交易规则表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        Deal deal = dealService.findById(id);
//        return DataResp.ok().data(deal);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询系统交易规则表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp queryPage(
//            @ApiParam(name = "dealQO", value = "查询条件")
//                    DealQO dealQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<Deal> pageModel = dealService.queryPage(dealQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
}
