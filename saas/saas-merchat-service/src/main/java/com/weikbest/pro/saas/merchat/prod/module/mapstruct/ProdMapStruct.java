package com.weikbest.pro.saas.merchat.prod.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDTO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdShowDetailVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdVO;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品基本信息表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdMapStruct extends BaseMapStruct {
    ProdMapStruct INSTANCE = Mappers.getMapper(ProdMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prod entity
     * @return dto
     */
    ProdDTO converToDTO(Prod prod);

    /**
     * DTO转换为entity
     *
     * @param prodDTO dto
     * @return entity
     */
    @Mapping(target ="goodsType",expression = "java(prodDTO.getGoodsType()!=null?Integer.valueOf(prodDTO.getGoodsType()):null)")
    Prod converToEntity(ProdDTO prodDTO);

    /**
     * entity转换为VO
     *
     * @param prod entity
     * @return vo
     */
    @Mapping(target ="goodsType",expression = "java(prod.getGoodsType()!=null?String.valueOf(prod.getGoodsType()):\"\")")
    ProdVO converToVO(Prod prod);

    /**
     * VO转换为entity
     *
     * @param prodVO vo
     * @return entity
     */
    Prod converToEntity(ProdVO prodVO);

    /**
     * entity转换为VO
     *
     * @param prod entity
     * @return vo
     */
    ProdShowDetailVO converToShowDetailVO(Prod prod);
}
