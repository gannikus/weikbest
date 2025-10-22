package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponSceneConfig;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneConfigDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponSceneConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 优惠券使用场景配置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-15
 */
@Mapper
public interface CouponSceneConfigMapStruct extends BaseMapStruct {
    CouponSceneConfigMapStruct INSTANCE = Mappers.getMapper(CouponSceneConfigMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param couponSceneConfig entity
     * @return dto
     */
    CouponSceneConfigDTO converToDTO(CouponSceneConfig couponSceneConfig);

    /**
     * DTO转换为entity
     *
     * @param couponSceneConfigDTO dto
     * @return entity
     */
    CouponSceneConfig converToEntity(CouponSceneConfigDTO couponSceneConfigDTO);

    /**
     * entity转换为VO
     *
     * @param couponSceneConfig entity
     * @return vo
     */
    CouponSceneConfigVO converToVO(CouponSceneConfig couponSceneConfig);

    /**
     * VO转换为entity
     *
     * @param couponSceneConfigVO vo
     * @return entity
     */
    CouponSceneConfig converToEntity(CouponSceneConfigVO couponSceneConfigVO);
}