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
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRoleDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopRoleQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopRoleVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopRoleService;
import com.weikbest.pro.saas.sys.system.module.vo.MenuVO;
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
 * 店铺角色表 前端控制器
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Slf4j
@Api(tags = {"shop::店铺角色表接口"})
@RestController
@RequestMapping("/shop/shop-role")
public class ShopRoleController {

    @Resource
    private ShopRoleService shopRoleService;

    @UseToken
    @SaveLog(value = "新增店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "新增数据")
    @PostMapping("/insert")
    public DataResp<Boolean> insert(
            @ApiParam(name = "shopRoleDTO", value = "保存数据信息", required = true)
            @Valid ShopRoleDTO shopRoleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean save = shopRoleService.insert(shopRoleDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "更新数据")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "shopRoleDTO", value = "更新数据信息", required = true)
            @Valid ShopRoleDTO shopRoleDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        boolean update = shopRoleService.updateById(id, shopRoleDTO);
        return DataResp.ok(update);
    }

    @UseToken
    @RemoveLog(value = "根据ID删除店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        shopRoleService.deleteBatchByIds(Collections.singletonList(id));
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        shopRoleService.deleteBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ShopRoleVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopRoleVO shopRoleVO = shopRoleService.findVOById(id);
        return DataResp.ok(shopRoleVO);
    }

    @UseToken
    @QueryLog(value = "分页查询店铺角色表数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ShopRoleVO>> queryPage(
            @ApiParam(name = "shopRoleQO", value = "查询条件")
                    ShopRoleQO shopRoleQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopRoleVO> pageModel = shopRoleService.queryPage(shopRoleQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }


    @UseToken
    @QueryLog(value = "查询店铺所有角色数据", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询店铺所有角色数据")
    @GetMapping("/queryAllRole")
    public DataResp<List<ShopRoleVO>> queryAllRole(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @RequestParam Long businessId,
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @RequestParam Long shopId) {

        List<ShopRoleVO> shopRoleVOList = shopRoleService.queryAllRole(businessId, shopId);
        return DataResp.ok(shopRoleVOList);
    }


    @UseToken
    @QueryLog(value = "查询店铺用户拥有的角色权限", recordSource = WeikbestConstant.BUSINESS_RECORD)
    @ApiOperation(value = "查询店铺用户拥有的角色权限")
    @GetMapping("/queryAuth")
    public DataResp<List<MenuVO>> queryAuth(
            @ApiParam(name = "businessId", value = "商户ID", required = true)
            @RequestParam Long businessId,
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @RequestParam Long shopId,
            @ApiParam(name = "busiUserId", value = "商户端用户ID", required = true)
            @RequestParam Long busiUserId) {

        List<MenuVO> menuVOList = shopRoleService.queryAuth(businessId, shopId, busiUserId);
        return DataResp.ok(menuVOList);
    }
}
