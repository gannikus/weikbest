package com.weikbest.pro.saas.merchat.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.QueryLog;
import com.weikbest.pro.saas.common.annotation.log.RemoveLog;
import com.weikbest.pro.saas.common.annotation.log.SaveLog;
import com.weikbest.pro.saas.common.annotation.log.UpdateLog;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserSaveDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopUserQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserRoleVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopUserService;
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
 * 商户店铺用户表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Slf4j
@Api(tags = {"shop::商户店铺用户表接口"})
@RestController
@RequestMapping("/shop/shop-user")
public class ShopUserController {

    @Resource
    private ShopUserService shopUserService;

    @UseToken
    @SaveLog(value = "新增商户店铺账号", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增商户店铺账号")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "shopUserSaveDTO", value = "保存数据信息", required = true)
            @Valid ShopUserSaveDTO shopUserSaveDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = shopUserService.insert(shopUserSaveDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新商户店铺用户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新商户店铺账号")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "shopUserSaveDTO", value = "更新数据信息", required = true)
            @Valid ShopUserSaveDTO shopUserSaveDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = shopUserService.updateById(id, shopUserSaveDTO);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "禁用/启用商户店铺账户", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "禁用/启用商户店铺账户")
    @PutMapping("/updateDataStatus/{id}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "dataStatus", value = "数据状态 0-禁用 1-可用", required = true)
            @RequestParam String dataStatus) {

        boolean update = shopUserService.updateDataStatusById(id, dataStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除商户店铺用户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        shopUserService.removeById(id);
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除商户店铺用户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        shopUserService.removeBatchByIds(ids);
        return DataResp.ok();
    }

//    @UseToken
//    @SaveLog(value = "店铺账号分配角色", recordSource = WeikbestConstant.BUSINESS_RECORD)
//    @ApiOperation(value = "店铺账号分配角色")
//    @PostMapping("/associateRole")
//    public DataResp<Boolean> associateRole(
//            @ApiParam(name = "shopUserDTO", value = "商户店铺用户实体", required = true)
//            @Valid ShopUserDTO shopUserDTO,
//            @ApiParam(name = "roleIdList", value = "关联角色数据信息", required = true)
//            @RequestBody List<Long> roleIdList, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = shopUserService.associateRoleList(shopUserDTO, roleIdList);
//        return DataResp.ok(save);
//    }

    @UseToken
    @QueryLog(value = "查询所有角色和店铺用户关联的角色", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询所有角色和店铺用户关联的角色")
    @GetMapping("/queryShopUserRole")
    public DataResp<List<ShopUserRoleVO>> queryUserRole(
            @ApiParam(name = "shopUserDTO", value = "商户店铺用户实体", required = true)
            @Valid ShopUserDTO shopUserDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        List<ShopUserRoleVO> shopUserRoleVOList = shopUserService.queryShopUserRole(shopUserDTO);
        return DataResp.ok(shopUserRoleVOList);
    }

    @UseToken
    @QueryLog(value = "根据ID查询商户店铺用户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ShopUserDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopUserDetailVO shopUserDetailVO = shopUserService.findDetailById(id);
        return DataResp.ok(shopUserDetailVO);
    }

    @UseToken
    @QueryLog(value = "分页查询商户店铺用户表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ShopUserListVO>> queryPage(
            @ApiParam(name = "shopUserQO", value = "查询条件")
                    ShopUserQO shopUserQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopUserListVO> pageModel = shopUserService.queryPage(shopUserQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }
}
