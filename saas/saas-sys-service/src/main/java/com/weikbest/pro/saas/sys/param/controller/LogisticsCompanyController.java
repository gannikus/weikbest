package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sys.param.entity.LogisticsCompany;
import com.weikbest.pro.saas.sys.param.module.qo.LogisticsCompanyQO;
import com.weikbest.pro.saas.sys.param.service.LogisticsCompanyService;
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
 * <p>
 * 物流快递公司表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-20
 */
@Slf4j
@Api(tags = {"param::物流快递公司表接口"})
@RestController
@RequestMapping("/param/logistics-company")
public class LogisticsCompanyController {

    @Resource
    private LogisticsCompanyService logisticsCompanyService;

//    @UseToken
//    @SaveLog(value = "同步平台物流快递公司表数据")
//    @ApiOperation(value = "同步平台物流快递公司")
//    @PostMapping("/sync")
//    public DataResp<Boolean> sync() {
//
//        boolean save = logisticsCompanyService.sync();
//        return DataResp.ok(save);
//    }

//    @UseToken
//    @SaveLog(value = "新增物流快递公司表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "logisticsCompanyDTO", value = "保存数据信息", required = true)
//            @Valid LogisticsCompanyDTO logisticsCompanyDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = logisticsCompanyService.insert(logisticsCompanyDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新物流快递公司表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "logisticsCompanyDTO", value = "更新数据信息", required = true)
//            @Valid LogisticsCompanyDTO logisticsCompanyDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = logisticsCompanyService.updateById(id, logisticsCompanyDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除物流快递公司表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        logisticsCompanyService.deleteBatchByIds(Collections.singletonList(id));
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除物流快递公司表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        logisticsCompanyService.deleteBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询物流快递公司表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<LogisticsCompany> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        LogisticsCompany logisticsCompany = logisticsCompanyService.findById(id);
        return DataResp.ok(logisticsCompany);
    }

    @UseToken
    @QueryLog(value = "分页查询物流快递公司表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<LogisticsCompany>> queryPage(
            @ApiParam(name = "logisticsCompanyQO", value = "查询条件")
                    LogisticsCompanyQO logisticsCompanyQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<LogisticsCompany> pageModel = logisticsCompanyService.queryPage(logisticsCompanyQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
