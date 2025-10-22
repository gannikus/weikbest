package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboAttrRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售套餐规格属性关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdComboAttrRelateMapStruct extends BaseMapStruct {
    ProdComboAttrRelateMapStruct INSTANCE = Mappers.getMapper(ProdComboAttrRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodComboAttrRelate entity
     * @return dto
     */
    ProdComboAttrRelateDTO converToDTO(ProdComboAttrRelate prodComboAttrRelate);

    /**
     * DTO转换为entity
     *
     * @param prodComboAttrRelateDTO dto
     * @return entity
     */
    ProdComboAttrRelate converToEntity(ProdComboAttrRelateDTO prodComboAttrRelateDTO);

    /**
     * DTO转换为entity
     *
     * @param prodComboAttrRelateDTO dto
     * @return entity
     */
    @Mapping(target = "prodId", source = "prodId")
    @Mapping(target = "prodComboId", source = "prodComboId")
    ProdComboAttrRelate converToEntity(ProdComboAttrRelateDTO prodComboAttrRelateDTO, Long prodId, Long prodComboId);

    /**
     * entity转换为VO
     *
     * @param prodComboAttrRelate entity
     * @return vo
     */
    ProdComboAttrRelateVO converToVO(ProdComboAttrRelate prodComboAttrRelate);

    /**
     * VO转换为entity
     *
     * @param prodComboAttrRelateVO vo
     * @return entity
     */
    ProdComboAttrRelate converToEntity(ProdComboAttrRelateVO prodComboAttrRelateVO);
}