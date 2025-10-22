package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdComboAttrRelateVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售套餐规格属性关联表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppProdComboAttrRelateMapStruct extends BaseMapStruct {
    AppProdComboAttrRelateMapStruct INSTANCE = Mappers.getMapper(AppProdComboAttrRelateMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodComboAttrRelate entity
     * @return vo
     */
    AppProdComboAttrRelateVO converToVO(ProdComboAttrRelate prodComboAttrRelate);

}