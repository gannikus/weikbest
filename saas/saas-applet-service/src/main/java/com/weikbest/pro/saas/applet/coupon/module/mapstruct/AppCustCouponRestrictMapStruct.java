package com.weikbest.pro.saas.applet.coupon.module.mapstruct;

import com.weikbest.pro.saas.applet.coupon.module.vo.AppCustCouponRestrictVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户领用优惠券表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface AppCustCouponRestrictMapStruct extends BaseMapStruct {
    AppCustCouponRestrictMapStruct INSTANCE = Mappers.getMapper(AppCustCouponRestrictMapStruct.class);


    /**
     * VO转换为entity
     *
     * @param appCustCouponRestrictVO vo
     * @return entity
     */
    CustCouponRestrict converToEntity(AppCustCouponRestrictVO appCustCouponRestrictVO);

    /**
     * entity转换为VO
     * @param custCouponRestrict
     * @return
     */
    AppCustCouponRestrictVO converToVO(CustCouponRestrict custCouponRestrict);

}