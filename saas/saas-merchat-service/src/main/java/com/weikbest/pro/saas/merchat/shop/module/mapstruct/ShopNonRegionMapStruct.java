package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopNonRegion;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopNonRegionDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopNonRegionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 不配送地区表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ShopNonRegionMapStruct extends BaseMapStruct {
    ShopNonRegionMapStruct INSTANCE = Mappers.getMapper(ShopNonRegionMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopNonRegion entity
     * @return dto
     */
    ShopNonRegionDTO converToDTO(ShopNonRegion shopNonRegion);

    /**
     * DTO转换为entity
     *
     * @param shopNonRegionDTO dto
     * @return entity
     */
    ShopNonRegion converToEntity(ShopNonRegionDTO shopNonRegionDTO);

    /**
     * entity转换为VO
     *
     * @param shopNonRegion entity
     * @return vo
     */
    ShopNonRegionVO converToVO(ShopNonRegion shopNonRegion);

    /**
     * VO转换为entity
     *
     * @param shopNonRegionVO vo
     * @return entity
     */
    ShopNonRegion converToEntity(ShopNonRegionVO shopNonRegionVO);
}