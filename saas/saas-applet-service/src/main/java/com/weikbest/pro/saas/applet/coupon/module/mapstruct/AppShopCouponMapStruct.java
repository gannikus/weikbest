package com.weikbest.pro.saas.applet.coupon.module.mapstruct;

import com.weikbest.pro.saas.applet.coupon.module.vo.AppCouponVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺优惠券表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface AppShopCouponMapStruct extends BaseMapStruct {
    AppShopCouponMapStruct INSTANCE = Mappers.getMapper(AppShopCouponMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    AppCouponVO converToVO(Coupon coupon);

}