package com.weikbest.pro.saas.sys.capital.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalRecordQO;
import com.weikbest.pro.saas.sys.capital.service.CapitalRecordService;
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
 * 平台资金出入账记录表  前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"capital::平台资金出入账记录表 接口"})
@RestController
@RequestMapping("/capital/capital-record")
public class CapitalRecordController {

    @Resource
    private CapitalRecordService capitalRecordService;

//    @UseToken
//    @SaveLog(value = "新增平台资金出入账记录表 数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "capitalRecordDTO", value = "保存数据信息", required = true)
//            @Valid CapitalRecordDTO capitalRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = capitalRecordService.insert(capitalRecordDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新平台资金出入账记录表 数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "capitalRecordDTO", value = "更新数据信息", required = true)
//            @Valid CapitalRecordDTO capitalRecordDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = capitalRecordService.updateById(id, capitalRecordDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除平台资金出入账记录表 数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        capitalRecordService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除平台资金出入账记录表 数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        capitalRecordService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询平台资金出入账记录表 数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<CapitalRecord> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        CapitalRecord capitalRecord = capitalRecordService.findById(id);
        return DataResp.ok(capitalRecord);
    }

    @UseToken
    @QueryLog(value = "分页查询平台资金出入账记录表 数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<CapitalRecord>> queryPage(
            @ApiParam(name = "capitalRecordQO", value = "查询条件")
                    CapitalRecordQO capitalRecordQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<CapitalRecord> pageModel = capitalRecordService.queryPage(capitalRecordQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
