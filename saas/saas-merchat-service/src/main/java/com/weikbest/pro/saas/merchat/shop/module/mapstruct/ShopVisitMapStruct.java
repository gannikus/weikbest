package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopVisitDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVisitVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺访问表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-04
 */
@Mapper
public interface ShopVisitMapStruct extends BaseMapStruct {
    ShopVisitMapStruct INSTANCE = Mappers.getMapper(ShopVisitMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopVisit entity
     * @return dto
     */
    ShopVisitDTO converToDTO(ShopVisit shopVisit);

    /**
     * DTO转换为entity
     *
     * @param shopVisitDTO dto
     * @return entity
     */
    ShopVisit converToEntity(ShopVisitDTO shopVisitDTO);

    /**
     * entity转换为VO
     *
     * @param shopVisit entity
     * @return vo
     */
    ShopVisitVO converToVO(ShopVisit shopVisit);

    /**
     * VO转换为entity
     *
     * @param shopVisitVO vo
     * @return entity
     */
    ShopVisit converToEntity(ShopVisitVO shopVisitVO);
}