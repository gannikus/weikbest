package com.weikbest.pro.saas.sysmerchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 平台优惠券表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface PlatformCouponMapStruct extends BaseMapStruct {
    PlatformCouponMapStruct INSTANCE = Mappers.getMapper(PlatformCouponMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param coupon entity
     * @return dto
     */
    PlatformCouponDTO converToDTO(Coupon coupon);

    /**
     * DTO转换为entity
     *
     * @param platformCouponDTO dto
     * @return entity
     */
    Coupon converToEntity(PlatformCouponDTO platformCouponDTO);

    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    PlatformCouponDetailVO converToDetailVO(Coupon coupon);
}