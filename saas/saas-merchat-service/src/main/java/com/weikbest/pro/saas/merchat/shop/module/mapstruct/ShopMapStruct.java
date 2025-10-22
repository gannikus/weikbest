package com.weikbest.pro.saas.merchat.shop.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopSetupDTO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户店铺表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Mapper
public interface ShopMapStruct extends BaseMapStruct {
    ShopMapStruct INSTANCE = Mappers.getMapper(ShopMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param shop entity
     * @return dto
     */
    ShopDTO converToDTO(Shop shop);

    /**
     * DTO转换为entity
     *
     * @param shopDTO dto
     * @return entity
     */
    Shop converToEntity(ShopDTO shopDTO);

    /**
     * DTO转换为entity
     *
     * @param shopSetupDTO shopSetupDTO
     * @return entity
     */
    Shop converToEntity(ShopSetupDTO shopSetupDTO);

    /**
     * entity转换为VO
     *
     * @param shop entity
     * @return vo
     */
    ShopVO converToVO(Shop shop);

    /**
     * VO转换为entity
     *
     * @param shopVO vo
     * @return entity
     */
    Shop converToEntity(ShopVO shopVO);

    /**
     * entity转换为VO
     *
     * @param shop entity
     * @return vo
     */
    ShopDetailVO converToDetailVO(Shop shop);

    /**
     * 联合对象转换为LISTVO
     *
     * @param shop
     * @param shopThird
     * @return
     */
    @Mapping(target = "id", source = "shop.id")
    @Mapping(target = "modifier", source = "shop.modifier")
    @Mapping(target = "gmtModified", source = "shop.gmtModified")
    ShopListVO converToListVO(Shop shop, ShopThird shopThird);

    /**
     * entity转换为DictEntry
     *
     * @param shop entity
     * @return DictEntry
     */
    @Mapping(target = "key", source = "number")
    @Mapping(target = "value", source = "name")
    @Mapping(target = "icon", source = "logo")
    DictEntry converToDictEntry(Shop shop);

    /**
     * entity转换为VO
     *
     * @param shop      entity
     * @param shopThird entity
     * @return VO
     */
    @Mapping(target = "id", source = "shop.id")
    @Mapping(target = "description", source = "shop.description")
    @Mapping(target = "dataStatus", source = "shop.dataStatus")
    @Mapping(target = "gmtCreate", source = "shop.gmtCreate")
    ShopDetailVO converToDetailVO(Shop shop, ShopThird shopThird);
}