package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdTheme;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdThemeDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdThemeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品样式拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdThemeMapStruct extends BaseMapStruct {
    ProdThemeMapStruct INSTANCE = Mappers.getMapper(ProdThemeMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodTheme entity
     * @return dto
     */
    ProdThemeDTO converToDTO(ProdTheme prodTheme);

    /**
     * DTO转换为entity
     *
     * @param prodThemeDTO dto
     * @return entity
     */
    ProdTheme converToEntity(ProdThemeDTO prodThemeDTO);

    /**
     * DTO转换为entity
     *
     * @param prodThemeDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdTheme converToEntity(ProdThemeDTO prodThemeDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodTheme entity
     * @return vo
     */
    ProdThemeVO converToVO(ProdTheme prodTheme);

    /**
     * VO转换为entity
     *
     * @param prodThemeVO vo
     * @return entity
     */
    ProdTheme converToEntity(ProdThemeVO prodThemeVO);
}