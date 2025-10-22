package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceDetail;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceDetailDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺资金明细表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ShopFinanceDetailMapStruct extends BaseMapStruct {
    ShopFinanceDetailMapStruct INSTANCE = Mappers.getMapper(ShopFinanceDetailMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopFinanceDetail entity
     * @return dto
     */
    ShopFinanceDetailDTO converToDTO(ShopFinanceDetail shopFinanceDetail);

    /**
     * DTO转换为entity
     *
     * @param shopFinanceDetailDTO dto
     * @return entity
     */
    ShopFinanceDetail converToEntity(ShopFinanceDetailDTO shopFinanceDetailDTO);

    /**
     * entity转换为VO
     *
     * @param shopFinanceDetail entity
     * @return vo
     */
    ShopFinanceDetailVO converToVO(ShopFinanceDetail shopFinanceDetail);

    /**
     * VO转换为entity
     *
     * @param shopFinanceDetailVO vo
     * @return entity
     */
    ShopFinanceDetail converToEntity(ShopFinanceDetailVO shopFinanceDetailVO);
}