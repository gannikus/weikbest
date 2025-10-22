package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdComboVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商品销售套餐表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppProdComboMapStruct extends BaseMapStruct {
    AppProdComboMapStruct INSTANCE = Mappers.getMapper(AppProdComboMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodCombo entity
     * @return vo
     */
    AppProdComboVO converToVO(ProdCombo prodCombo);


}