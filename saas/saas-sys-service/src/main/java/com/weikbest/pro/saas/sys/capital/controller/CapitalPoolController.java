package com.weikbest.pro.saas.sys.capital.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sys.capital.entity.CapitalPool;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalPoolQO;
import com.weikbest.pro.saas.sys.capital.service.CapitalPoolService;
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
 * 平台资金池表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"capital::平台资金池表接口"})
@RestController
@RequestMapping("/capital/capital-pool")
public class CapitalPoolController {

    @Resource
    private CapitalPoolService capitalPoolService;

//    @UseToken
//    @SaveLog(value = "新增平台资金池表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "capitalPoolDTO", value = "保存数据信息", required = true)
//            @Valid CapitalPoolDTO capitalPoolDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = capitalPoolService.insert(capitalPoolDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新平台资金池表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "capitalPoolDTO", value = "更新数据信息", required = true)
//            @Valid CapitalPoolDTO capitalPoolDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = capitalPoolService.updateById(id, capitalPoolDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除平台资金池表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        capitalPoolService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除平台资金池表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        capitalPoolService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询平台资金池表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<CapitalPool> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CapitalPool capitalPool = capitalPoolService.findById(id);
        return DataResp.ok(capitalPool);
    }

    @UseToken
    @QueryLog(value = "分页查询平台资金池表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CapitalPool>> queryPage(
            @ApiParam(name = "capitalPoolQO", value = "查询条件")
                    CapitalPoolQO capitalPoolQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CapitalPool> pageModel = capitalPoolService.queryPage(capitalPoolQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
