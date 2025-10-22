package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponProdEntry;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponProdEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponProdEntryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 优惠券与适用商品拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface CouponProdEntryMapStruct extends BaseMapStruct {
    CouponProdEntryMapStruct INSTANCE = Mappers.getMapper(CouponProdEntryMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param couponProdEntry entity
     * @return dto
     */
    CouponProdEntryDTO converToDTO(CouponProdEntry couponProdEntry);

    /**
     * DTO转换为entity
     *
     * @param couponProdEntryDTO dto
     * @return entity
     */
    CouponProdEntry converToEntity(CouponProdEntryDTO couponProdEntryDTO);

    /**
     * entity转换为VO
     *
     * @param couponProdEntry entity
     * @return vo
     */
    CouponProdEntryVO converToVO(CouponProdEntry couponProdEntry);

    /**
     * VO转换为entity
     *
     * @param couponProdEntryVO vo
     * @return entity
     */
    CouponProdEntry converToEntity(CouponProdEntryVO couponProdEntryVO);
}