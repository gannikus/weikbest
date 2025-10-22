package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopStatementDetail;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopStatementDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopStatementDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺对账单明细表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ShopStatementDetailMapStruct extends BaseMapStruct {
    ShopStatementDetailMapStruct INSTANCE = Mappers.getMapper(ShopStatementDetailMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopStatementDetail entity
     * @return dto
     */
    ShopStatementDetailDTO converToDTO(ShopStatementDetail shopStatementDetail);

    /**
     * DTO转换为entity
     *
     * @param shopStatementDetailDTO dto
     * @return entity
     */
    ShopStatementDetail converToEntity(ShopStatementDetailDTO shopStatementDetailDTO);

    /**
     * entity转换为VO
     *
     * @param shopStatementDetail entity
     * @return vo
     */
    ShopStatementDetailVO converToVO(ShopStatementDetail shopStatementDetail);

    /**
     * VO转换为entity
     *
     * @param shopStatementDetailVO vo
     * @return entity
     */
    ShopStatementDetail converToEntity(ShopStatementDetailVO shopStatementDetailVO);
}