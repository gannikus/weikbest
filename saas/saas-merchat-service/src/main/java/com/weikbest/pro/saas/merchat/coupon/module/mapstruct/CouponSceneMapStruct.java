package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponSceneDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponSceneVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 优惠券使用场景表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-09
 */
@Mapper
public interface CouponSceneMapStruct extends BaseMapStruct {
    CouponSceneMapStruct INSTANCE = Mappers.getMapper(CouponSceneMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param couponScene entity
     * @return dto
     */
    CouponSceneDTO converToDTO(CouponScene couponScene);

    /**
     * DTO转换为entity
     *
     * @param couponSceneDTO dto
     * @return entity
     */
    CouponScene converToEntity(CouponSceneDTO couponSceneDTO);

    /**
     * entity转换为VO
     *
     * @param couponScene entity
     * @return vo
     */
    CouponSceneVO converToVO(CouponScene couponScene);

    /**
     * VO转换为entity
     *
     * @param couponSceneVO vo
     * @return entity
     */
    CouponScene converToEntity(CouponSceneVO couponSceneVO);
}