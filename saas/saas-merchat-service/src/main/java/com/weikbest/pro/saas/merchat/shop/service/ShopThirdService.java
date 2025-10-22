package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopThirdQO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 店铺第三方平台拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
public interface ShopThirdService extends IService<ShopThird> {

    /**
     * 新增数据
     *
     * @param shopThirdDTO shopThirdDTO
     * @return 新增结果
     */
    boolean insert(ShopThirdDTO shopThirdDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param shopThirdDTO shopThirdDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopThirdDTO shopThirdDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopThird findById(Long id);

    /**
     * 分页查询
     *
     * @param shopThirdQO
     * @param pageReq
     * @return
     */
    IPage<ShopThird> queryPage(ShopThirdQO shopThirdQO, PageReq pageReq);

    /**
     * 根据ID列表查询
     *
     * @param shopIdList
     * @return
     */
    Map<Long, ShopThird> queryMapByIds(List<Long> shopIdList);

    /**
     * 开店时添加店铺第三方平台信息
     *
     * @param shopId
     * @param shopThirdDTO
     */
    boolean insertWithShop(Long shopId, ShopThirdDTO shopThirdDTO);


    /**
     * 更新店铺的第三方平台信息
     *
     * @param id
     * @param shopThirdDTO
     * @return
     */
    boolean updateWithShop(Long id, ShopThirdDTO shopThirdDTO);

    /**
     * 根据ID查询微信支付服务
     *
     * @param id ID
     * @return 查询结果
     */
    WxPayService findWxPayServiceById(Long id);


    /**
     * 根据订单号查询微信支付服务
     *
     * @param orderNumber 订单号
     * @return 查询结果
     */
    WxPayService findWxPayServiceByOrderNumber(String orderNumber);

    /**
     * 根据平台证书序列号查询微信支付
     *
     * @param wxPlatformSerialNo
     * @return
     */
    WxPayService findWxPayServiceByWxPlatformSerialNo(String wxPlatformSerialNo);

    /**
     * 配置商家券事件通知
     *
     * @param id
     * @return
     */
    boolean updateCreateBusiFavorCallbacks(Long id);


    /**
     * 根据证书序列号查询优惠券商家信息
     *
     * @param wxPlatformSerialNo
     * @return
     */
    WxPayService findCouponWxPayServiceByWxPlatformSerialNo(String wxPlatformSerialNo);

    /**
     * 获取平台证书
     *
     * @return
     */
    WxPayService findCouponWxPayServicePayConfig();

    /**
     * 配置企业微信客服
     *
     * @param id
     * @param wxBusiness
     * @return
     */
    boolean updateWxBusiness(Long id, String wxBusiness);


}
