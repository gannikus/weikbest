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
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUsersource;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUsersourceDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvUsersourceQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvUsersourceService;
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
 * 腾讯广告数据上报用户行为数据源表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Slf4j
@Api(tags = {"param::腾讯广告数据上报用户行为数据源表接口"})
@RestController
@RequestMapping("/param/tencent-adv-usersource")
public class TencentAdvUsersourceController {

    @Resource
    private TencentAdvUsersourceService tencentAdvUsersourceService;

    @UseToken
    @SaveLog(value = "新增腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "tencentAdvUsersourceDTO", value = "保存数据信息", required = true)
            @Valid TencentAdvUsersourceDTO tencentAdvUsersourceDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = tencentAdvUsersourceService.insert(tencentAdvUsersourceDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "tencentAdvUsersourceDTO", value = "更新数据信息", required = true)
            @Valid TencentAdvUsersourceDTO tencentAdvUsersourceDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = tencentAdvUsersourceService.updateById(id, tencentAdvUsersourceDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        tencentAdvUsersourceService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        tencentAdvUsersourceService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<TencentAdvUsersource> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        TencentAdvUsersource tencentAdvUsersource = tencentAdvUsersourceService.findById(id);
        return DataResp.ok(tencentAdvUsersource);
    }

    @UseToken
    @QueryLog(value = "分页查询腾讯广告数据上报用户行为数据源表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<TencentAdvUsersource>> queryPage(
            @ApiParam(name = "tencentAdvUsersourceQO", value = "查询条件")
                    TencentAdvUsersourceQO tencentAdvUsersourceQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<TencentAdvUsersource> pageModel = tencentAdvUsersourceService.queryPage(tencentAdvUsersourceQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
