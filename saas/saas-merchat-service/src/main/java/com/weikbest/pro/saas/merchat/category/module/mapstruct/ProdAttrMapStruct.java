package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.ProdAttr;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.ProdAttrVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品属性表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdAttrMapStruct extends BaseMapStruct {
    ProdAttrMapStruct INSTANCE = Mappers.getMapper(ProdAttrMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodAttr entity
     * @return dto
     */
    ProdAttrDTO converToDTO(ProdAttr prodAttr);

    /**
     * DTO转换为entity
     *
     * @param prodAttrDTO dto
     * @return entity
     */
    ProdAttr converToEntity(ProdAttrDTO prodAttrDTO);

    /**
     * entity转换为VO
     *
     * @param prodAttr entity
     * @return vo
     */
    ProdAttrVO converToVO(ProdAttr prodAttr);

    /**
     * VO转换为entity
     *
     * @param prodAttrVO vo
     * @return entity
     */
    ProdAttr converToEntity(ProdAttrVO prodAttrVO);
}