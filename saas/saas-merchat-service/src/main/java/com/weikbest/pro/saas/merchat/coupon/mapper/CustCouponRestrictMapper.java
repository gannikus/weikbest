package com.weikbest.pro.saas.merchat.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.module.dto.AppCustCouponRestrictDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户领用优惠券表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
public interface CustCouponRestrictMapper extends BaseMapper<CustCouponRestrict> {


    /**
     * 获取可用优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountUseableCustCouponRestricts(Long customerId);

    /**
     * 获取我的已过期优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountExpiredCustCouponRestricts(Long customerId);

    /**
     * 获取我的已使用优惠券总数
     *
     * @param customerId
     * @return
     */
    int queryCountUsedCustCouponRestricts(Long customerId);

    /**
     * 获取当前商品下我领取的可用优惠券总数
     *
     * @param paramMap
     * @return
     */
    int queryCountUseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的不可用优惠券总数
     *
     * @param paramMap
     * @return
     */
    int queryCountUnuseableCustCouponProds(Map<String, Object> paramMap);


    /**
     * 获取可用优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取已过期优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryExpiredCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取已使用优惠券列表，分页
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUsedCustCouponRestricts(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的可用优惠券信息
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUseableCustCouponProds(Map<String, Object> paramMap);

    /**
     * 获取当前商品下我领取的不可用优惠券信息
     *
     * @param paramMap
     * @return
     */
    List<AppCustCouponRestrictDTO> queryUnuseableCustCouponProds(Map<String, Object> paramMap);

    List<AppCustCouponRestrictDTO> listUseableCustCouponProds(Map<String, Object> paramMap);
}
