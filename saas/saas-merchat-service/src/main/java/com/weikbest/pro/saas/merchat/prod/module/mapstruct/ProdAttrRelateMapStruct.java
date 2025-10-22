package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAttrRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAttrRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售规格属性关联表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdAttrRelateMapStruct extends BaseMapStruct {
    ProdAttrRelateMapStruct INSTANCE = Mappers.getMapper(ProdAttrRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodAttrRelate entity
     * @return dto
     */
    ProdAttrRelateDTO converToDTO(ProdAttrRelate prodAttrRelate);

    /**
     * DTO转换为entity
     *
     * @param prodAttrRelateDTO dto
     * @return entity
     */
    ProdAttrRelate converToEntity(ProdAttrRelateDTO prodAttrRelateDTO);

    /**
     * entity转换为VO
     *
     * @param prodAttrRelate entity
     * @return vo
     */
    ProdAttrRelateVO converToVO(ProdAttrRelate prodAttrRelate);

    /**
     * VO转换为entity
     *
     * @param prodAttrRelateVO vo
     * @return entity
     */
    ProdAttrRelate converToEntity(ProdAttrRelateVO prodAttrRelateVO);
}