package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.MinProdComboVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售套餐表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdComboMapStruct extends BaseMapStruct {
    ProdComboMapStruct INSTANCE = Mappers.getMapper(ProdComboMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodCombo entity
     * @return dto
     */
    ProdComboDTO converToDTO(ProdCombo prodCombo);

    /**
     * DTO转换为entity
     *
     * @param prodComboDTO dto
     * @return entity
     */
    ProdCombo converToEntity(ProdComboDTO prodComboDTO);

    /**
     * DTO转换为entity
     *
     * @param prodComboDTO dto
     * @return entity
     */
    @Mapping(target = "prodId", source = "prodId")
    ProdCombo converToEntity(ProdComboDTO prodComboDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodCombo entity
     * @return vo
     */
    ProdComboVO converToVO(ProdCombo prodCombo);

    /**
     * VO转换为entity
     *
     * @param prodComboVO vo
     * @return entity
     */
    ProdCombo converToEntity(ProdComboVO prodComboVO);

    /**
     * entity转换为VO
     *
     * @param prodCombo entity
     * @return vo
     */
    MinProdComboVO converToMinProdComboVO(ProdCombo prodCombo);
}