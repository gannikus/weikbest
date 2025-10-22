package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.Dict;
import com.weikbest.pro.saas.sys.param.module.dto.DictDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DictQO;
import com.weikbest.pro.saas.sys.param.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Api(tags = {"param::字典表接口"})
@RestController
@RequestMapping("/param/dict")
public class DictController {

    @Resource
    private DictService dictService;

    @UseToken
    @SaveLog(value = "新增字典表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "dictDTO", value = "保存数据信息", required = true)
            @Valid DictDTO dictDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = dictService.insert(dictDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新字典表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "dictDTO", value = "更新数据信息", required = true)
            @Valid DictDTO dictDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = dictService.updateById(id, dictDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除字典表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        dictService.removeById(id);
        return DataResp.ok();
    }

//    @UseToken
//    @RemoveLog(value = "根据ID列表删除字典表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        dictService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询字典表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        Dict dict = dictService.findById(id);
//        return DataResp.ok().data(dict);
//    }

    @UseToken
    @QueryLog(value = "分页查询字典表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Dict>> queryPage(
            @ApiParam(name = "dictQO", value = "查询条件")
                    DictQO dictQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Dict> pageModel = dictService.queryPage(dictQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
