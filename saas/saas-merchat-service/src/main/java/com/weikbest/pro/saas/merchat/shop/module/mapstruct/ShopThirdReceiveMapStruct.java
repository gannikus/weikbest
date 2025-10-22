package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThirdReceive;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdReceiveDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopThirdReceiveVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 店铺第三方平台分账接收方拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
@Mapper
public interface ShopThirdReceiveMapStruct extends BaseMapStruct {
    ShopThirdReceiveMapStruct INSTANCE = Mappers.getMapper(ShopThirdReceiveMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shopThirdReceive entity
     * @return dto
     */
    ShopThirdReceiveDTO converToDTO(ShopThirdReceive shopThirdReceive);

    /**
     * DTO转换为entity
     *
     * @param shopThirdReceiveDTO dto
     * @return entity
     */
    ShopThirdReceive converToEntity(ShopThirdReceiveDTO shopThirdReceiveDTO);

    /**
     * entity转换为VO
     *
     * @param shopThirdReceive entity
     * @return vo
     */
    ShopThirdReceiveVO converToVO(ShopThirdReceive shopThirdReceive);

    /**
     * VO转换为entity
     *
     * @param shopThirdReceiveVO vo
     * @return entity
     */
    ShopThirdReceive converToEntity(ShopThirdReceiveVO shopThirdReceiveVO);
}