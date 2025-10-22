package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.coupon.entity.Coupon;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopPromptlyCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.dto.ShopRefluxCouponDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponPageVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.ShopPromptlyCouponVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.ShopRefluxCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 优惠券表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Mapper
public interface CouponMapStruct extends BaseMapStruct {
    CouponMapStruct INSTANCE = Mappers.getMapper(CouponMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param coupon entity
     * @return dto
     */
    CouponDTO converToDTO(Coupon coupon);

    /**
     * DTO转换为entity
     *
     * @param couponDTO dto
     * @return entity
     */
    Coupon converToEntity(CouponDTO couponDTO);

    /**
     * DTO转换为entity
     *
     * @param shopRefluxCouponDTO dto
     * @return entity
     */
    Coupon converToEntity(ShopRefluxCouponDTO shopRefluxCouponDTO);

    /**
     * DTO转换为entity
     *
     * @param shopPromptlyCouponDTO dto
     * @return entity
     */
    Coupon converToEntity(ShopPromptlyCouponDTO shopPromptlyCouponDTO);

    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    CouponVO converToVO(Coupon coupon);

    /**
     * VO转换为entity
     *
     * @param couponVO vo
     * @return entity
     */
    Coupon converToEntity(CouponVO couponVO);

    /**
     * entity转换为 DictEntry
     *
     * @param coupon entity
     * @return DictEntry
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "value", source = "name")
    DictEntry converToDictEntry(Coupon coupon);

    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    ShopRefluxCouponVO converToShopRefluxCouponVO(Coupon coupon);

    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    ShopPromptlyCouponVO converToShopPromptlyCouponVO(Coupon coupon);

    /**
     * entity转换为VO
     *
     * @param coupon entity
     * @return vo
     */
    CouponPageVO converToShopCouponPageVO(Coupon coupon);
}