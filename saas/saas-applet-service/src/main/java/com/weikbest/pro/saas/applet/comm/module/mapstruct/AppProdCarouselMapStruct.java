package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppProdCarouselVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序首页商品轮播图表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Mapper
public interface AppProdCarouselMapStruct extends BaseMapStruct {
    AppProdCarouselMapStruct INSTANCE = Mappers.getMapper(AppProdCarouselMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodCarousel entity
     * @return vo
     */
    AppProdCarouselVO converToVO(AppletDecConfigEntry prodCarousel);


}