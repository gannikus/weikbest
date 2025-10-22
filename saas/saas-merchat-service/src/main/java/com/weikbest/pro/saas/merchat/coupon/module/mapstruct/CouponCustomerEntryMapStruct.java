package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CouponCustomerEntry;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CouponCustomerEntryDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CouponCustomerEntryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 优惠券与适用客户拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-04
 */
@Mapper
public interface CouponCustomerEntryMapStruct extends BaseMapStruct {
    CouponCustomerEntryMapStruct INSTANCE = Mappers.getMapper(CouponCustomerEntryMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param couponCustomerEntry entity
     * @return dto
     */
    CouponCustomerEntryDTO converToDTO(CouponCustomerEntry couponCustomerEntry);

    /**
     * DTO转换为entity
     *
     * @param couponCustomerEntryDTO dto
     * @return entity
     */
    CouponCustomerEntry converToEntity(CouponCustomerEntryDTO couponCustomerEntryDTO);

    /**
     * entity转换为VO
     *
     * @param couponCustomerEntry entity
     * @return vo
     */
    CouponCustomerEntryVO converToVO(CouponCustomerEntry couponCustomerEntry);

    /**
     * VO转换为entity
     *
     * @param couponCustomerEntryVO vo
     * @return entity
     */
    CouponCustomerEntry converToEntity(CouponCustomerEntryVO couponCustomerEntryVO);
}