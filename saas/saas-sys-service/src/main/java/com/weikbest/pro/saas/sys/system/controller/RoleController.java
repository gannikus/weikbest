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
import com.weikbest.pro.saas.sys.system.entity.Role;
import com.weikbest.pro.saas.sys.system.module.dto.RoleDTO;
import com.weikbest.pro.saas.sys.system.module.qo.RoleQO;
import com.weikbest.pro.saas.sys.system.service.RoleService;
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
 * 系统角色表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Slf4j
@Api(tags = {"system::系统角色表接口"})
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @UseToken
    @SaveLog(value = "新增系统角色表数据")
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "roleDTO", value = "保存数据信息", required = true)
            @Valid RoleDTO roleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = roleService.insert(roleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统角色表数据")
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "roleDTO", value = "更新数据信息", required = true)
            @Valid RoleDTO roleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = roleService.updateById(id, roleDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @SaveLog(value = "角色分配用户")
    @ApiOperation(value = "角色分配用户")
    @PostMapping("/associateUser/{id}")
    public DataResp associateUser(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "userIdList", value = "关联用户数据信息", required = true)
            @RequestBody List<Long> userIdList) {

        boolean save = roleService.associateUserList(id, userIdList);
        return DataResp.ok(save);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除系统角色表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        roleService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除系统角色表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        roleService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统角色表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<Role> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        Role role = roleService.findById(id);
        return DataResp.ok(role);
    }

    @UseToken
    @QueryLog(value = "分页查询系统角色表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<Role>> queryPage(
            @ApiParam(name = "roleQO", value = "查询条件")
                    RoleQO roleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<Role> pageModel = roleService.queryPage(roleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
