package com.weikbest.pro.saas.applet.shop.module.mapstruct;

import com.weikbest.pro.saas.applet.shop.module.vo.AppShopListVO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户店铺表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppShopMapStruct extends BaseMapStruct {
    AppShopMapStruct INSTANCE = Mappers.getMapper(AppShopMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param shop entity
     * @return vo
     */
    AppShopVO converToVO(Shop shop);

}