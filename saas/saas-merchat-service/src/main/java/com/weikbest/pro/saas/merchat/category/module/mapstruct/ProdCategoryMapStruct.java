package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.ProdCategory;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.ProdCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品分类表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdCategoryMapStruct extends BaseMapStruct {
    ProdCategoryMapStruct INSTANCE = Mappers.getMapper(ProdCategoryMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodCategory entity
     * @return dto
     */
    ProdCategoryDTO converToDTO(ProdCategory prodCategory);

    /**
     * DTO转换为entity
     *
     * @param prodCategoryDTO dto
     * @return entity
     */
    ProdCategory converToEntity(ProdCategoryDTO prodCategoryDTO);

    /**
     * entity转换为VO
     *
     * @param prodCategory entity
     * @return vo
     */
    ProdCategoryVO converToVO(ProdCategory prodCategory);

    /**
     * VO转换为entity
     *
     * @param prodCategoryVO vo
     * @return entity
     */
    ProdCategory converToEntity(ProdCategoryVO prodCategoryVO);
}