package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.sys.param.entity.Region;
import com.weikbest.pro.saas.sys.param.module.qo.RegionQO;
import com.weikbest.pro.saas.sys.param.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 平台行政区划表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-03
 */
@Slf4j
@Api(tags = {"param::平台行政区划表接口"})
@RestController
@RequestMapping("/param/region")
public class RegionController {

    @Resource
    private RegionService regionService;

    @UseToken
    @SaveLog(value = "同步平台行政区划表数据")
    @ApiOperation(value = "同步平台行政区划")
    @PostMapping("/sync")
    public DataResp<Boolean> sync() {

        boolean save = regionService.sync();
        return DataResp.ok(save);
    }

//    @UseToken
//    @SaveLog(value = "新增平台行政区划表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "regionDTO", value = "保存数据信息", required = true)
//            @Valid RegionDTO regionDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = regionService.insert(regionDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新平台行政区划表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "regionDTO", value = "更新数据信息", required = true)
//            @Valid RegionDTO regionDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = regionService.updateById(id, regionDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除平台行政区划表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        regionService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除平台行政区划表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        regionService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询平台行政区划表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Region> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Region region = regionService.findById(id);
        return DataResp.ok(region);
    }

    @UseToken
    @QueryLog(value = "分页查询平台行政区划表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Region>> queryPage(
            @ApiParam(name = "regionQO", value = "查询条件")
                    RegionQO regionQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Region> pageModel = regionService.queryPage(regionQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
