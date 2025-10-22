package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdThemeVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdTheme;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品样式拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppProdThemeMapStruct extends BaseMapStruct {
    AppProdThemeMapStruct INSTANCE = Mappers.getMapper(AppProdThemeMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param prodTheme entity
     * @return vo
     */
    AppProdThemeVO converToVO(ProdTheme prodTheme);

}