package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.entity.ProdPrice;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPriceDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品价格信息拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdPriceMapStruct extends BaseMapStruct {
    ProdPriceMapStruct INSTANCE = Mappers.getMapper(ProdPriceMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodPrice entity
     * @return dto
     */
    ProdPriceDTO converToDTO(ProdPrice prodPrice);

    /**
     * DTO转换为entity
     *
     * @param prodPriceDTO dto
     * @return entity
     */
    ProdPrice converToEntity(ProdPriceDTO prodPriceDTO);

    /**
     * DTO转换为entity
     *
     * @param prodPriceDTO dto
     * @param prodCombo    entity
     * @param prodId       id
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    @Mapping(target = "prodComboId", source = "prodCombo.id")
    @Mapping(target = "comboMinPrice", source = "prodCombo.comboPrice")
    @Mapping(target = "comboMinStandardPrice", source = "prodCombo.comboStandardPrice")
    @Mapping(target = "gmtCreate", ignore = true)
    @Mapping(target = "gmtModified", ignore = true)
    ProdPrice converToEntity(ProdPriceDTO prodPriceDTO, ProdCombo prodCombo, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodPrice entity
     * @return vo
     */
    ProdPriceVO converToVO(ProdPrice prodPrice);

    /**
     * VO转换为entity
     *
     * @param prodPriceVO vo
     * @return entity
     */
    ProdPrice converToEntity(ProdPriceVO prodPriceVO);
}