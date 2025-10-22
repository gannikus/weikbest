package com.weikbest.pro.saas.sys.param.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.param.entity.Config;
import com.weikbest.pro.saas.sys.param.module.dto.ConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ConfigQO;
import com.weikbest.pro.saas.sys.param.service.ConfigService;
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
 * 系统配置表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"param::系统配置表接口"})
@RestController
@RequestMapping("/param/config")
public class ConfigController {

    @Resource
    private ConfigService configService;

    @UseToken
    @SaveLog(value = "新增系统配置表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "configDTO", value = "保存数据信息", required = true)
            @Valid ConfigDTO configDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = configService.insert(configDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统配置表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "configDTO", value = "更新数据信息", required = true)
            @Valid ConfigDTO configDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = configService.updateById(id, configDTO);
        return DataResp.ok(update);
    }

//    @UseToken
//    @RemoveLog(value = "根据ID删除系统配置表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        configService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统配置表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        configService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }

    @UseToken
    @QueryLog(value = "根据ID查询系统配置表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Config> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Config config = configService.findById(id);
        return DataResp.ok(config);
    }

    @UseToken
    @QueryLog(value = "分页查询系统配置表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Config>> queryPage(
            @ApiParam(name = "configQO", value = "查询条件")
                    ConfigQO configQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Config> pageModel = configService.queryPage(configQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
