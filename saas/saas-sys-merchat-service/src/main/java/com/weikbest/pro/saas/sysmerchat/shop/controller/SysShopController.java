package com.weikbest.pro.saas.sysmerchat.shop.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.common.annotation.log.*;
import com.weikbest.pro.saas.common.annotation.token.UseToken;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.DataResp;
import com.weikbest.pro.saas.common.transfervo.resp.PageResp;
import com.weikbest.pro.saas.common.util.JsrCheckUtil;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThirdReceive;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRelatedAppletDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdReceiveDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopQO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopThirdReceiveQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopThirdVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopAppletRelatedService;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdReceiveService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.merchat.shop.util.ShopJsrCheckUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Slf4j
@Api(tags = {"SysShop::平台商户店铺表接口"})
@RestController
@RequestMapping("/sys/shop")
public class SysShopController {

    @Resource
    private ShopJsrCheckUtil shopJsrCheckUtil;

    @Resource
    private ShopService shopService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private ShopThirdReceiveService shopThirdReceiveService;

    @Resource
    private ShopAppletRelatedService shopAppletRelatedService;

    @UseToken
    @SaveLog(value = "快速开店")
    @ApiOperation(value = "快速开店")
    @PostMapping("/fastInsert")
    public DataResp<Boolean> fastInsert(
            @ApiParam(name = "shopDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopDTO shopDTO, BindingResult bindingResult) {

        shopJsrCheckUtil.validShopDTO(bindingResult);

        boolean save = shopService.insert(shopDTO);
        return DataResp.ok(save);
    }

    @UseToken
    @UpdateLog(value = "更新店铺信息")
    @ApiOperation(value = "更新店铺信息")
    @PutMapping("/update/{id}")
    public DataResp<Boolean> update(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "shopDTO", value = "更新数据信息", required = true)
            @RequestBody @Valid ShopDTO shopDTO, BindingResult bindingResult) {

        shopJsrCheckUtil.validShopDTO(bindingResult);

        boolean update = shopService.updateById(id, shopDTO);
        return DataResp.ok(update);
    }


    @UseToken
    @UpdateLog(value = "配置商家券事件通知")
    @ApiOperation(value = "配置商家券事件通知")
    @PutMapping("/updateCreateBusiFavorCallbacks/{id}")
    public DataResp<Boolean> updateCreateBusiFavorCallbacks(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        boolean update = shopService.updateCreateBusiFavorCallbacks(id);
        return DataResp.ok(update);
    }

    @UseToken
    @QueryLog(value = "根据ID查询企业微信客服")
    @ApiOperation(value = "根据ID查询企业微信客服")
    @GetMapping("/findWxBusiness/{id}")
    public DataResp<Tuple2<String, String>> findWxBusiness(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopDetailVO shopDetailVO = shopService.findDetailVOById(id);
        ShopThirdVO shopThirdVO = shopDetailVO.getShopThirdVO();
        Tuple2<String, String> result = Tuples.of(String.valueOf(id), shopThirdVO.getWxBusiness());
        return DataResp.ok(result);
    }

    @UseToken
    @UpdateLog(value = "配置企业微信客服")
    @ApiOperation(value = "配置企业微信客服")
    @PutMapping("/updateWxBusiness/{id}")
    public DataResp<Boolean> updateWxBusiness(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "wxBusiness", value = "企业微信客服", required = true)
            @RequestParam String wxBusiness) {

        boolean update = shopService.updateWxBusiness(id, wxBusiness);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "禁用/启动商户店铺")
    @ApiOperation(value = "禁用/启动商户店铺")
    @PutMapping("/updateDataStatus/{id}")
    public DataResp<Boolean> updateDataStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "dataStatus", value = "数据状态 0-禁用 1-可用", required = true)
            @RequestParam String dataStatus) {

        boolean update = shopService.updateDataStatusById(id, dataStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "禁用/启动商户店铺")
    @ApiOperation(value = "禁用/启动商户店铺")
    @PutMapping("/update-adcallback-switch-status/{id}")
    public DataResp<Boolean> updateAdcallbackSwitchStatus(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "switchStatus", value = "数据状态 0-关闭 1-开启", required = true)
            @RequestParam String switchStatus) {

        boolean update = shopService.updateAdcallbackSwitchStatus(id, switchStatus);
        return DataResp.ok(update);
    }

    @UseToken
    @UpdateLog(value = "禁用/启动商户店铺")
    @ApiOperation(value = "禁用/启动商户店铺手动回传开关")
    @PutMapping("/update-switch-switch-manual-ad-callback/{id}")
    public DataResp<Boolean> updateSwitchSwitchManualAdCallback(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id,
            @ApiParam(name = "switchStatus", value = "数据状态 0-关闭 1-开启", required = true)
            @RequestParam String switchStatus) {

        boolean update = shopService.updateSwitchSwitchManualAdCallback(id, switchStatus);
        return DataResp.ok(update);
    }

//    @UseToken
//    @SaveLog(value = "创建店铺")
//    @ApiOperation(value = "创建店铺")
//    @PostMapping("/createShop")
//    public DataResp<Boolean> createShop(
//            @ApiParam(name = "shopSetupDTO", value = "保存数据信息", required = true)
//            @RequestBody @Valid ShopSetupDTO shopSetupDTO, BindingResult bindingResult) {
//
//        JsrCheckUtil.valid(bindingResult);
//
//        boolean save = shopService.insert(shopSetupDTO);
//        return DataResp.ok(save);
//    }

    @UseToken
    @SaveOrUpdateLog(value = "关联或修改店铺微信商户号信息")
    @ApiOperation(value = "关联或修改店铺微信商户号信息")
    @PostMapping("/saveOrUpdateShopThird/{shopId}")
    public DataResp<Boolean> saveOrUpdateShopThird(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "shopThirdDTO", value = "更新数据信息", required = true)
            @Valid ShopThirdDTO shopThirdDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        // 校验商户号:1个店铺对应1个微信支付商户号
        String wxPayMchId = shopThirdDTO.getWxPayMchId();
        long count = shopThirdService.count(new QueryWrapper<ShopThird>().eq(ShopThird.WX_PAY_MCH_ID, wxPayMchId));
        if (count > WeikbestConstant.ZERO_LONG) {
            throw new WeikbestException("该商户号已绑定别的店铺，请重新填写！");
        }

        // 查询id是否存在记录
        boolean update;
        ShopThird shopThird = shopThirdService.getById(shopId);
        if (ObjectUtil.isEmpty(shopThird)) {
            // 新增
            update = shopThirdService.insertWithShop(shopId, shopThirdDTO);
        } else {
            // 更新
            update = shopThirdService.updateWithShop(shopId, shopThirdDTO);
        }

        return DataResp.ok(update);
    }

    @UseToken
    @SaveLog(value = "添加店铺分账接收方信息")
    @ApiOperation(value = "添加店铺分账接收方信息")
    @PostMapping("/updateThirdReceive/{shopId}")
    public DataResp<Boolean> updateShopThird(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "shopThirdReceiveDTO", value = "保存数据信息", required = true)
            @RequestBody @Valid ShopThirdReceiveDTO shopThirdReceiveDTO, BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        if (StrUtil.isBlank(shopThirdReceiveDTO.getWxPayAppId()) && CollectionUtil.isEmpty(shopThirdReceiveDTO.getWxPayAppIds())) {
            throw new WeikbestException("微信支付-微信公众号或者小程序等的appid 不为空，请填写一个或填写集合！");
        }

        // 新增
        boolean save = shopThirdReceiveService.insertWithShop(shopId, shopThirdReceiveDTO);

        return DataResp.ok(save);
    }


    @UseToken
    @RemoveLog(value = "删除店铺分账接收方信息")
    @ApiOperation(value = "删除店铺分账接收方信息")
    @DeleteMapping("/deleteThirdReceive/{shopId}/{id}")
    public DataResp<Object> deleteThirdReceive(
            @ApiParam(name = "shopId", value = "店铺ID", required = true)
            @PathVariable Long shopId,
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        // 删除
        shopThirdReceiveService.removeWithShop(shopId, id);
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID删除商户店铺表数据")
    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("/delete/{id}")
    public DataResp<Object> delete(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        shopService.deleteById(id);
        return DataResp.ok();
    }

    @UseToken
    @RemoveLog(value = "根据ID列表删除商户店铺表数据")
    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("/delete")
    public DataResp<Object> delete(
            @ApiParam(name = "ids", value = "ID列表", required = true)
            @RequestBody List<Long> ids) {

        shopService.removeBatchByIds(ids);
        return DataResp.ok();
    }

    @UseToken
    @QueryLog(value = "根据ID查询商户店铺表数据")
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/find/{id}")
    public DataResp<ShopDetailVO> find(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {

        ShopDetailVO shopDetailVO = shopService.findDetailVOById(id);
        return DataResp.ok(shopDetailVO);
    }

    @UseToken
    @QueryLog(value = "分页查询商户店铺表数据")
    @ApiOperation(value = "分页查询")
    @GetMapping("/queryPage")
    public DataResp<List<ShopListVO>> queryPage(
            @ApiParam(name = "shopQO", value = "查询条件")
                    ShopQO shopQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopListVO> pageModel = shopService.queryPage(shopQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "分页查询店铺第三方平台分账接收方数据")
    @ApiOperation(value = "分页查询店铺第三方平台分账接收方数据")
    @GetMapping("/queryPageByShopThirdReceive")
    public DataResp<List<ShopThirdReceive>> queryPage(
            @ApiParam(name = "shopThirdReceiveQO", value = "查询条件")
                    ShopThirdReceiveQO shopThirdReceiveQO,
            @ApiParam(name = "pageReq", value = "分页参数", required = true)
                    PageReq pageReq) {

        IPage<ShopThirdReceive> pageModel = shopThirdReceiveService.queryPage(shopThirdReceiveQO, pageReq);
        return PageResp.ok(pageModel.getTotal(), pageModel.getRecords());
    }

    @UseToken
    @QueryLog(value = "店铺关联小程序")
    @ApiOperation(value = "店铺关联小程序")
    @PostMapping("/relate-applet")
    public DataResp<?> relateApplet(@ApiParam(name = "shopRelatedAppletDTO", value = "店铺关联小程序dto")
                                        @RequestBody @Valid ShopRelatedAppletDTO shopRelatedAppletDTO,BindingResult bindingResult) {

        JsrCheckUtil.valid(bindingResult);

        shopAppletRelatedService.relateApplet(shopRelatedAppletDTO);

        return DataResp.ok();
    }

}
