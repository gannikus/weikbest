package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCoupon;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdCouponVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 广告商品优惠劵拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface ProdCouponMapStruct extends BaseMapStruct {
    ProdCouponMapStruct INSTANCE = Mappers.getMapper(ProdCouponMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodCoupon entity
     * @return dto
     */
    ProdCouponDTO converToDTO(ProdCoupon prodCoupon);

    /**
     * DTO转换为entity
     *
     * @param prodCouponDTO dto
     * @return entity
     */
    ProdCoupon converToEntity(ProdCouponDTO prodCouponDTO);

    /**
     * entity转换为VO
     *
     * @param prodCoupon entity
     * @return vo
     */
    ProdCouponVO converToVO(ProdCoupon prodCoupon);

    /**
     * VO转换为entity
     *
     * @param prodCouponVO vo
     * @return entity
     */
    ProdCoupon converToEntity(ProdCouponVO prodCouponVO);
}