package com.weikbest.pro.saas.applet.coupon.service;


import com.weikbest.pro.saas.applet.coupon.module.qo.AppCustProdCouponQO;
import com.weikbest.pro.saas.applet.coupon.module.vo.AppCouponSceneVO;
import com.weikbest.pro.saas.applet.coupon.module.vo.AppCouponVO;
import com.weikbest.pro.saas.applet.coupon.module.vo.AppCustCouponRestrictVO;
import com.weikbest.pro.saas.applet.coupon.module.vo.AppProdCouponVO;
import com.weikbest.pro.saas.applet.shop.module.qo.AppShopCouponQO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;

import java.util.List;
import java.util.Map;


public interface AppCouponService {

    /**
     * 获取首页轮播广告位
     *
     * @return
     */
    List<AppCouponVO> queryshopcoupons(AppShopCouponQO appShopCouponQO);

    /**
     * 根据商品ID查询该商品优惠券信息（回流券和平台券）
     * @param prodId
     * @return
     */
    AppProdCouponVO queryCouponsByProdId(Long prodId,String appId);


    /**
     * 根据商品ID查询该商品立减优惠券信息（立减券）
     * @param prodId
     * @return
     */
    AppProdCouponVO queryReduceCouponByProdId(Long prodId,String appId);

    /**
     * 领取平台优惠券信息（平台券）
     * @return
     */
    AppCouponSceneVO queryCouponScene(String appId);

    /**
     * 领券回调业务层
     */
    void notifybusiFavor(Map<String, Object> result);

    /**
     * 获取可用优惠券总数
     * @return
     */
    int queryCountUseableCustCouponRestricts();

    /**
     * 获取我的已过期优惠券总数
     * @return
     */
    int queryCountExpiredCustCouponRestricts();

    /**
     * 获取我的已使用优惠券总数
     * @return
     */
    int queryCountUsedCustCouponRestricts();

    /**
     * 获取当前商品下我领取的可用优惠券总数
     * @param appCustProdCouponQO
     * @return
     */
    int queryCountUseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO);

    /**
     * 获取当前商品下我领取的不可用优惠券总数
     * @param appCustProdCouponQO
     * @return
     */
    int queryCountUnuseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO);

    /**
     * 获取可用优惠券列表，分页
     * @param pageReq
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponRestricts(PageReq pageReq);

    /**
     * 获取已过期优惠券列表，分页
     * @param pageReq
     * @return
     */
    List<AppCustCouponRestrictDTO> queryExpiredCustCouponRestricts(PageReq pageReq);

    /**
     * 获取已使用优惠券列表，分页
     * @param pageReq
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUsedCustCouponRestricts(PageReq pageReq);

    /**
     * 获取当前商品下我领取的可用优惠券信息
     * @param appCustProdCouponQO
     * @param pageReq
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO,PageReq pageReq);

    /**
     * 获取当前商品下我领取的不可用优惠券信息
     * @param appCustProdCouponQO
     * @param pageReq
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUnuseableCustCouponProds(AppCustProdCouponQO appCustProdCouponQO,PageReq pageReq);

    /**
     * 通过优惠券ID获取立减优惠券关联的商品信息
     * @param couponId
     * @param pageReq
     * @return
     */
    Map<String,Object> queryPageCommodityByCouponId(Long couponId,PageReq pageReq);

    /**
     * 保存优惠券领券信息
     * @param appCustCouponRestrictVOS
     * @return
     */
    boolean saveCustCouponRestricts(List<AppCustCouponRestrictVO> appCustCouponRestrictVOS);

    /**
     * 获取coupon_code
     * @param url
     * @return
     */
    AppCustCouponRestrictVO getCouponCodeByURL(String url);

}
