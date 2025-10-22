package com.weikbest.pro.saas.sys.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.system.entity.User;
import com.weikbest.pro.saas.sys.system.module.dto.UserDTO;
import com.weikbest.pro.saas.sys.system.module.qo.UserQO;
import com.weikbest.pro.saas.sys.system.module.vo.UserRoleVO;
import com.weikbest.pro.saas.sys.system.module.vo.UserVO;
import com.weikbest.pro.saas.sys.system.service.UserService;
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
 * 系统用户表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
@Slf4j
@Api(tags = {"system::系统用户表接口"})
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    @UseToken
    @SaveLog(value = "添加系统用户")
    @ApiOperation(value = "添加系统用户")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "userDTO", value = "保存数据信息", required = true)
            @Valid UserDTO userDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        if (StrUtil.isNotBlank(userDTO.getPassword())) {
            String password = userDTO.getPassword();
            if (!StrUtil.equals(password, userDTO.getConfirmPassword())) {
                throw new WeikbestException(ResultConstant.VALID_FAIL.getCode(), "两次输入密码不一致！");
            }
            // TODO 密码正则表达式校验？
        }

        // 判断手机号是否已存在（手机号在用户表全局唯一）
        String phone = userDTO.getPhone();
        User user = userService.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
        if (ObjectUtil.isNotEmpty(user)) {
            throw new WeikbestException(ResultConstant.PHONE_REGISTER_FAIL);
        }

        boolean save = userService.insert(userDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新系统用户数据")
    @ApiOperation(value = "更新系统用户数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "userDTO", value = "更新数据信息", required = true)
            @Valid UserDTO userDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        // 判断手机号是否已存在（手机号在用户表全局唯一）
        String phone = userDTO.getPhone();
        User user = userService.getOne(new QueryWrapper<User>().eq(User.PHONE, phone));
        if (ObjectUtil.isNotEmpty(user) && !ObjectUtil.equal(user.getId(), id)) {
            throw new WeikbestException(ResultConstant.PHONE_REGISTER_FAIL);
        }

        boolean update = userService.updateById(id, userDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "将系统用户设为管理员")
    @ApiOperation(value = "将系统用户设为管理员")
    @PutMapping("/setSuper/{id}")
    public DataResp<Boolean> setSuper(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        User user = userService.findById(id);
        user.setIsSysuser(DictConstant.Whether.yes.getCode());
        boolean update = userService.updateById(user);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "将系统用户取消管理员")
    @ApiOperation(value = "将系统用户取消管理员")
    @PutMapping("/cancelSuper/{id}")
    public DataResp<Boolean> cancelSuper(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        User user = userService.findById(id);
        user.setIsSysuser(DictConstant.Whether.no.getCode());
        boolean update = userService.updateById(user);
        return DataResp.ok(update);
    }

    @UseToken
    @SaveLog(value = "用户分配角色")
    @ApiOperation(value = "用户分配角色")
    @PostMapping("/associateRole/{id}")
    public DataResp<Boolean> associateRole(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "roleIdList", value = "关联角色数据信息", required = true)
            @RequestBody List<Long> roleIdList) {

        boolean save = userService.associateRoleList(id, roleIdList);
        return DataResp.ok(save);
    }

    @UseToken
    @QueryLog(value = "查询所有角色和用户ID关联的角色")
    @ApiOperation(value = "查询所有角色和用户ID关联的角色")
    @GetMapping("/queryUserRole/{userId}")
    public DataResp<List<UserRoleVO>> queryUserRole(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @PathVariable Long userId) {

        List<UserRoleVO> userRoleVOList = userService.queryUserRole(userId);
        return DataResp.ok(userRoleVOList);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除系统用户表数据")
    @ApiOperation(value = "根据ID删除")
    @GetMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        userService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除系统用户表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        userService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询系统用户表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<User> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        User user = userService.findById(id);
        return DataResp.ok(user);
    }

    @UseToken
    @QueryLog(value = "分页查询系统用户表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<UserVO>> queryPage(
            @ApiParam(name = "userQO", value = "查询条件")
                    UserQO userQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<UserVO> pageModel = userService.queryPage(userQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
