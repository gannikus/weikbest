package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.ProdAttrVal;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrValDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.ProdAttrValVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品属性值表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdAttrValMapStruct extends BaseMapStruct {
    ProdAttrValMapStruct INSTANCE = Mappers.getMapper(ProdAttrValMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodAttrVal entity
     * @return dto
     */
    ProdAttrValDTO converToDTO(ProdAttrVal prodAttrVal);

    /**
     * DTO转换为entity
     *
     * @param prodAttrValDTO dto
     * @return entity
     */
    ProdAttrVal converToEntity(ProdAttrValDTO prodAttrValDTO);

    /**
     * entity转换为VO
     *
     * @param prodAttrVal entity
     * @return vo
     */
    ProdAttrValVO converToVO(ProdAttrVal prodAttrVal);

    /**
     * VO转换为entity
     *
     * @param prodAttrValVO vo
     * @return entity
     */
    ProdAttrVal converToEntity(ProdAttrValVO prodAttrValVO);
}