package com.weikbest.pro.saas.sys.system.controller;

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
import com.weikbest.pro.saas.sys.system.entity.Perm;
import com.weikbest.pro.saas.sys.system.module.dto.PermDTO;
import com.weikbest.pro.saas.sys.system.module.qo.PermQO;
import com.weikbest.pro.saas.sys.system.service.PermService;
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
 * 系统权限项表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"system::系统权限项表接口"})
@RestController
@RequestMapping("/system/perm")
public class PermController {

    @Resource
    private PermService permService;

    @UseToken
    @SaveLog(value = "新增系统权限项表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "permDTO", value = "保存数据信息", required = true)
            @Valid PermDTO permDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = permService.insert(permDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统权限项表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "permDTO", value = "更新数据信息", required = true)
            @Valid PermDTO permDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = permService.updateById(id, permDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除系统权限项表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        permService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除系统权限项表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        permService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统权限项表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Perm> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Perm perm = permService.findById(id);
        return DataResp.ok(perm);
    }

    @UseToken
    @QueryLog(value = "分页查询系统权限项表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Perm>> queryPage(
            @ApiParam(name = "permQO", value = "查询条件")
                    PermQO permQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Perm> pageModel = permService.queryPage(permQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
