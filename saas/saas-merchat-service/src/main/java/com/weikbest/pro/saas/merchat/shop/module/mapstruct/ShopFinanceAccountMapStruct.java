package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopFinanceAccountDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopFinanceAccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺资金账户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ShopFinanceAccountMapStruct extends BaseMapStruct {
    ShopFinanceAccountMapStruct INSTANCE = Mappers.getMapper(ShopFinanceAccountMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopFinanceAccount entity
     * @return dto
     */
    ShopFinanceAccountDTO converToDTO(ShopFinanceAccount shopFinanceAccount);

    /**
     * DTO转换为entity
     *
     * @param shopFinanceAccountDTO dto
     * @return entity
     */
    ShopFinanceAccount converToEntity(ShopFinanceAccountDTO shopFinanceAccountDTO);

    /**
     * entity转换为VO
     *
     * @param shopFinanceAccount entity
     * @return vo
     */
    ShopFinanceAccountVO converToVO(ShopFinanceAccount shopFinanceAccount);

    /**
     * VO转换为entity
     *
     * @param shopFinanceAccountVO vo
     * @return entity
     */
    ShopFinanceAccount converToEntity(ShopFinanceAccountVO shopFinanceAccountVO);
}