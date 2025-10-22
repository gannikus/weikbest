package com.weikbest.pro.saas.merchat.category.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.ProdBrand;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdBrandDTO;
import com.weikbest.pro.saas.merchat.category.module.vo.ProdBrandVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品品牌表（本期不用）实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ProdBrandMapStruct extends BaseMapStruct {
    ProdBrandMapStruct INSTANCE = Mappers.getMapper(ProdBrandMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param prodBrand entity
     * @return dto
     */
    ProdBrandDTO converToDTO(ProdBrand prodBrand);

    /**
     * DTO转换为entity
     *
     * @param prodBrandDTO dto
     * @return entity
     */
    ProdBrand converToEntity(ProdBrandDTO prodBrandDTO);

    /**
     * entity转换为VO
     *
     * @param prodBrand entity
     * @return vo
     */
    ProdBrandVO converToVO(ProdBrand prodBrand);

    /**
     * VO转换为entity
     *
     * @param prodBrandVO vo
     * @return entity
     */
    ProdBrand converToEntity(ProdBrandVO prodBrandVO);
}