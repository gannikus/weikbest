//package com.weikbest.pro.saas.sys.system.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.weikbest.pro.saas.common.annotation.log.QueryLog;
//import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
//import com.weikbest.pro.saas.common.annotation.log.SaveLog;
//import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
//import com.weikbest.pro.saas.common.annotation.token.UseToken;
//import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
//import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
//import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
//import com.weikbest.pro.saas.common.util.JsrCheckUtil;
//import com.weikbest.pro.saas.sys.system.entity.UserRole;
//import com.weikbest.pro.saas.sys.system.module.dto.UserRoleDTO;
//import com.weikbest.pro.saas.sys.system.module.qo.UserRoleQO;
//import com.weikbest.pro.saas.sys.system.service.UserRoleService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//import java.util.List;
//
///**
// * <p>
// * 系统用户角色关联表 前端控制器
// * </p>
// *
// * @author wisdomelon
// * @since 2022-09-02
// */
//@Slf4j
//@Api(tags = {"system::系统用户角色关联表接口"})
//@RestController
//@RequestMapping("/system/user-role")
//public class UserRoleController {
//
//    @Resource
//    private UserRoleService userRoleService;
//
//    @UseToken
//    @SaveLog(value = "新增系统用户角色关联表数据")
//    @ApiOperation(value = "新增数据")
//    @PostMapping("/insert")
//    public DataResp<Boolean> insert(
//            @ApiParam(name = "userRoleDTO", value = "保存数据信息", required = true)
//            @Valid UserRoleDTO userRoleDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = userRoleService.insert(userRoleDTO);
//        return DataResp.ok(save);
//    }
//
//    @UseToken
//    @UpdateLog(value = "更新系统用户角色关联表数据")
//    @ApiOperation(value = "更新数据")
//    @PutMapping("/update/{id}")
//    public DataResp<Boolean> update(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id,
//            @ApiParam(name = "userRoleDTO", value = "更新数据信息", required = true)
//            @Valid UserRoleDTO userRoleDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean update = userRoleService.updateById(id, userRoleDTO);
//        return DataResp.ok(update);
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID删除系统用户角色关联表数据")
//    @ApiOperation(value = "根据ID删除")
//    @DeleteMapping("/delete/{id}")
//    public DataResp<Object> delete(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        userRoleService.removeById(id);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @RemoveLog(value = "根据ID列表删除系统用户角色关联表数据")
//    @ApiOperation(value = "根据ID列表删除")
//    @DeleteMapping("/delete")
//    public DataResp<Object> delete(
//            @ApiParam(name = "ids", value = "ID列表", required = true)
//            @RequestBody List<Long> ids) {
//
//        userRoleService.removeBatchByIds(ids);
//        return DataResp.ok();
//    }
//
//    @UseToken
//    @QueryLog(value = "根据ID查询系统用户角色关联表数据")
//    @ApiOperation(value = "根据ID查询")
//    @GetMapping("/find/{id}")
//    public DataResp find(
//            @ApiParam(name = "id", value = "ID", required = true)
//            @PathVariable Long id) {
//
//        UserRole userRole = userRoleService.findById(id);
//        return DataResp.ok().data(userRole);
//    }
//
//    @UseToken
//    @QueryLog(value = "分页查询系统用户角色关联表数据")
//    @ApiOperation(value = "分页查询")
//    @GetMapping("/queryPage")
//    public DataResp queryPage(
//            @ApiParam(name = "userRoleQO", value = "查询条件")
//                    UserRoleQO userRoleQO,
//            @ApiParam(name = "pageReq", value = "分页参数", required = true)
//                    PageReq pageReq) {
//
//        IPage<UserRole> pageModel = userRoleService.queryPage(userRoleQO, pageReq);
//        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
//    }
//}
