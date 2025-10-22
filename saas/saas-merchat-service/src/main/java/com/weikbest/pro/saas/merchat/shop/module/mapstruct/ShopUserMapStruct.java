package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.shop.entity.ShopUser;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopUserSaveDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户店铺用户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface ShopUserMapStruct extends BaseMapStruct {
    ShopUserMapStruct INSTANCE = Mappers.getMapper(ShopUserMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopUser entity
     * @return dto
     */
    ShopUserDTO converToDTO(ShopUser shopUser);

    /**
     * DTO转换为entity
     *
     * @param shopUserDTO dto
     * @return entity
     */
    ShopUser converToEntity(ShopUserDTO shopUserDTO);

    /**
     * DTO转换为entity
     *
     * @param shopUserSaveDTO dto
     * @param businessUserId  businessUserId
     * @return entity
     */
    @Mapping(target = "businessUserId", source = "businessUserId")
    ShopUser converToEntity(ShopUserSaveDTO shopUserSaveDTO, Long businessUserId);

    /**
     * entity转换为VO
     *
     * @param shopUser entity
     * @return vo
     */
    ShopUserVO converToVO(ShopUser shopUser);

    /**
     * entity转换为VO
     *
     * @param shopUser entity
     * @return vo
     */
    ShopUserDetailVO converToDetailVO(ShopUser shopUser);

    /**
     * VO转换为entity
     *
     * @param shopUserVO vo
     * @return entity
     */
    ShopUser converToEntity(ShopUserVO shopUserVO);

    /**
     * DTO转换为BusiUserDTO
     *
     * @param shopUserSaveDTO dto
     * @return BusiUserDTO
     */
//    @Mapping(target = "businessId", source = "businessId")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "name", source = "name")
    BusiUserDTO converToBusiUserDTO(ShopUserSaveDTO shopUserSaveDTO);
}