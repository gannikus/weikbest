package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponProdEntryQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponQO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CustCouponRestrictQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Coupon findById(Long id);

    /**
     * 分页查询
     *
     * @param couponQO
     * @param pageReq
     * @return
     */
    IPage<Coupon> queryPage(CouponQO couponQO, PageReq pageReq);

    /**
     * 创建回流优惠券
     *
     * @param shopRefluxCouponDTO
     * @return
     */
    boolean insertShopRefluxCoupon(ShopRefluxCouponDTO shopRefluxCouponDTO);

    /**
     * 更新回流优惠券
     *
     * @param id
     * @param shopRefluxCouponDTO
     * @return
     */
    boolean updateShopRefluxCouponById(Long id, ShopRefluxCouponDTO shopRefluxCouponDTO);

    /**
     * 创建立减优惠券
     *
     * @param shopPromptlyCouponDTO
     * @return
     */
    boolean insertShopPromptlyCoupon(ShopPromptlyCouponDTO shopPromptlyCouponDTO);

    /**
     * 更新立减优惠券
     *
     * @param id
     * @param shopPromptlyCouponDTO
     * @return
     */
    boolean updateShopPromptlyCouponById(Long id, ShopPromptlyCouponDTO shopPromptlyCouponDTO);

    /**
     * 查询回流优惠券
     *
     * @param id
     * @return
     */
    ShopRefluxCouponVO findShopRefluxCouponVOById(Long id);

    /**
     * 查询立减优惠券
     *
     * @param id
     * @return
     */
    ShopPromptlyCouponVO findShopPromptlyCouponVOById(Long id);

    /**
     * 失效优惠券
     *
     * @param ids
     * @param dataStatus
     * @return
     */
    boolean updateDataStatus(List<Long> ids, String dataStatus);

    /**
     * 发布优惠券
     *
     * @param id
     * @return
     */
    boolean publishCoupon(Long id);

    /**
     * 商家店铺优惠券分页查询
     *
     * @param couponQO
     * @param pageReq
     * @return
     */
    IPage<CouponPageVO> queryPageVO(CouponQO couponQO, PageReq pageReq);

    /**
     * 查询商品关联的优惠券表数据
     *
     * @param couponProdEntryQO
     * @return
     */
    List<Coupon> queryCouponProd(CouponProdEntryQO couponProdEntryQO);

    /**
     * 优惠券数据统计
     *
     * @param id
     * @return
     */
    CouponDataStatisticsVO findDataStatisticsVOById(Long id);

    /**
     * 分页查询平台优惠券领取数据
     *
     * @param custCouponRestrictQO
     * @param pageReq
     * @return
     */
    IPage<CustCouponRestrictPageVO> queryCustCouponRestrictPage(CustCouponRestrictQO custCouponRestrictQO, PageReq pageReq);

    /**
     * 优惠券活动生效
     *
     * @param id
     */
    void couponEventStartExecute(Long id);

    /**
     * 优惠券活动结束
     *
     * @param id
     */
    void couponEventEndExecute(Long id);

    /**
     * 根据优惠券ID列表查询未过期和已过期的优惠券
     *
     * @param idList ID列表
     * @return
     */
    Map<String, List<Coupon>> queryHasExpiredCoupon(List<Long> idList);

    /**
     * 根据优惠券ID获取优惠券信息
     * @param id
     * @return
     */
    Coupon queryCouponById(Long id);

    /**
     * 根据stockId获取优惠券信息
     * @param stockId
     * @return
     */
    Coupon queryCouponByStockId(String stockId);

    /**
     * 修改领取比例
     * @param id
     * @param getPercentage
     */
    void updateShopRefluxCoupon(Long id, BigDecimal getPercentage);
}
