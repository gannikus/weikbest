package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAppletRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAppletRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAppletRelateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品关联小程序拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdAppletRelateMapStruct extends BaseMapStruct {
    ProdAppletRelateMapStruct INSTANCE = Mappers.getMapper(ProdAppletRelateMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodAppletRelate entity
     * @return dto
     */
    ProdAppletRelateDTO converToDTO(ProdAppletRelate prodAppletRelate);

    /**
     * DTO转换为entity
     *
     * @param prodAppletRelateDTO dto
     * @return entity
     */
    ProdAppletRelate converToEntity(ProdAppletRelateDTO prodAppletRelateDTO);

    /**
     * DTO转换为entity
     *
     * @param prodAppletRelateDTO dto
     * @return entity
     */
    @Mapping(target = "id", source = "prodId")
    ProdAppletRelate converToEntity(ProdAppletRelateDTO prodAppletRelateDTO, Long prodId);

    /**
     * entity转换为VO
     *
     * @param prodAppletRelate entity
     * @return vo
     */
    ProdAppletRelateVO converToVO(ProdAppletRelate prodAppletRelate);

    /**
     * VO转换为entity
     *
     * @param prodAppletRelateVO vo
     * @return entity
     */
    ProdAppletRelate converToEntity(ProdAppletRelateVO prodAppletRelateVO);
}