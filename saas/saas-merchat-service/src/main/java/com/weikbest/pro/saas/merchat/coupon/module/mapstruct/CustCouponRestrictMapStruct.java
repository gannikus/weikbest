package com.weikbest.pro.saas.merchat.coupon.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.coupon.entity.CustCouponRestrict;
import com.weikbest.pro.saas.merchat.coupon.module.dto.CustCouponRestrictDTO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictPageVO;
import com.weikbest.pro.saas.merchat.coupon.module.vo.CustCouponRestrictVO;
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
public interface CustCouponRestrictMapStruct extends BaseMapStruct {
    CustCouponRestrictMapStruct INSTANCE = Mappers.getMapper(CustCouponRestrictMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custCouponRestrict entity
     * @return dto
     */
    CustCouponRestrictDTO converToDTO(CustCouponRestrict custCouponRestrict);

    /**
     * DTO转换为entity
     *
     * @param custCouponRestrictDTO dto
     * @return entity
     */
    CustCouponRestrict converToEntity(CustCouponRestrictDTO custCouponRestrictDTO);

    /**
     * entity转换为VO
     *
     * @param custCouponRestrict entity
     * @return vo
     */
    CustCouponRestrictVO converToVO(CustCouponRestrict custCouponRestrict);

    /**
     * VO转换为entity
     *
     * @param custCouponRestrictVO vo
     * @return entity
     */
    CustCouponRestrict converToEntity(CustCouponRestrictVO custCouponRestrictVO);

    /**
     * entity转换为VO
     *
     * @param custCouponRestrict entity
     * @return vo
     */
    CustCouponRestrictPageVO converToPageVO(CustCouponRestrict custCouponRestrict);
}