package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCouponRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdCouponRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdCouponRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品与优惠券绑定拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdCouponRelateMapStruct extends BaseMapStruct {
    ProdCouponRelateMapStruct INSTANCE = Mappers.getMapper(ProdCouponRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodCouponRelate entity
     * @return dto
     */
    ProdCouponRelateDTO converToDTO(ProdCouponRelate prodCouponRelate);

    /**
     * DTO转换为entity
     *
     * @param prodCouponRelateDTO dto
     * @return entity
     */
    ProdCouponRelate converToEntity(ProdCouponRelateDTO prodCouponRelateDTO);

    /**
     * DTO转换为entity
     *
     * @param prodCouponRelateDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "id")
    ProdCouponRelate converToEntity(ProdCouponRelateDTO prodCouponRelateDTO, Long id);

    /**
     * entity转换为VO
     *
     * @param prodCouponRelate entity
     * @return vo
     */
    ProdCouponRelateVO converToVO(ProdCouponRelate prodCouponRelate);

    /**
     * VO转换为entity
     *
     * @param prodCouponRelateVO vo
     * @return entity
     */
    ProdCouponRelate converToEntity(ProdCouponRelateVO prodCouponRelateVO);
}