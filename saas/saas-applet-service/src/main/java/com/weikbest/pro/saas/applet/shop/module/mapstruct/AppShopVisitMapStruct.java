package com.weikbest.pro.saas.applet.shop.module.mapstruct;

import com.weikbest.pro.saas.applet.shop.module.dto.AppShopVisitDTO;
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
public interface AppShopVisitMapStruct extends BaseMapStruct {
    AppShopVisitMapStruct INSTANCE = Mappers.getMapper(AppShopVisitMapStruct.class);


    /**
     * DTO转换为entity
     *
     * @param appShopVisitDTO dto
     * @return entity
     */
    ShopVisit converToEntity(AppShopVisitDTO appShopVisitDTO);

}