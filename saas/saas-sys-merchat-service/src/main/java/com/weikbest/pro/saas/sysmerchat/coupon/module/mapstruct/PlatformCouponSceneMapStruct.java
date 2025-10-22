package com.weikbest.pro.saas.sysmerchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponScene;
import com.weikbest.pro.saas.sysmerchat.coupon.module.dto.PlatformCouponSceneDTO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneDetailVO;
import com.weikbest.pro.saas.sysmerchat.coupon.module.vo.PlatformCouponSceneVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 平台优惠券使用场景表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface PlatformCouponSceneMapStruct extends BaseMapStruct {
    PlatformCouponSceneMapStruct INSTANCE = Mappers.getMapper(PlatformCouponSceneMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param couponScene entity
     * @return dto
     */
    PlatformCouponSceneDTO converToDTO(CouponScene couponScene);

    /**
     * DTO转换为entity
     *
     * @param platformCouponSceneDTO dto
     * @return entity
     */
   CouponScene converToEntity(PlatformCouponSceneDTO platformCouponSceneDTO);

    /**
     * entity转换为VO
     *
     * @param couponScene entity
     * @return vo
     */
    PlatformCouponSceneVO converToVO(CouponScene couponScene);

    /**
     * VO转换为entity
     *
     * @param platformCouponSceneVO vo
     * @return entity
     */
    CouponScene converToEntity(PlatformCouponSceneVO platformCouponSceneVO);

    /**
     * entity转换为VO
     *
     * @param couponScene entity
     * @return vo
     */
    PlatformCouponSceneDetailVO converToDetailVO(CouponScene couponScene);
}