package com.weikbest.pro.saas.merchat.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.qo.CouponQO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    /**
     * 分页查询列表数据
     *
     * @param page
     * @param couponQO
     * @return
     */
    IPage<CouponPageVO> queryPage(IPage<CouponPageVO> page, @Param("coupon") CouponQO couponQO);

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

}
