package com.weikbest.pro.saas.merchat.coupon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponProdEntry;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponProdEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponProdEntryQO;

import java.util.List;

/**
 * <p>
 * 优惠券与适用商品拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface CouponProdEntryService extends IService<CouponProdEntry> {

    /**
     * 新增数据
     *
     * @param couponProdEntryDTO couponProdEntryDTO
     * @return 新增结果
     */
    boolean insert(CouponProdEntryDTO couponProdEntryDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param couponProdEntryDTO couponProdEntryDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CouponProdEntryDTO couponProdEntryDTO);

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
    CouponProdEntry findById(Long id);

    /**
     * 分页查询
     *
     * @param couponProdEntryQO
     * @param pageReq
     * @return
     */
    IPage<CouponProdEntry> queryPage(CouponProdEntryQO couponProdEntryQO, PageReq pageReq);

    /**
     * 添加优惠券关联商品
     *
     * @param id
     * @param shopCouponType
     * @param prodIdList
     */
    void saveBatchWithCouponId(Long id, String shopCouponType, List<Long> prodIdList);

    /**
     * 根据店铺优惠券ID查询
     *
     * @param id
     * @return
     */
    List<CouponProdEntry> queryByCouponId(Long id);

    /**
     * 添加优惠券关联商品
     *
     * @param id
     * @param shopCouponType
     * @param prodId
     */
    void saveWithCouponId(Long id, String shopCouponType, Long prodId);

    /**
     * 查询优惠券关联商品信息
     *
     * @param couponProdEntryQO
     * @return
     */
    List<CouponProdEntry> queryCouponProd(CouponProdEntryQO couponProdEntryQO);
}
