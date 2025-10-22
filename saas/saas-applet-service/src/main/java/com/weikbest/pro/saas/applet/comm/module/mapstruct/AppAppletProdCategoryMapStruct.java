package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppAppletProdCategoryVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序商品类目表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppAppletProdCategoryMapStruct extends BaseMapStruct {
    AppAppletProdCategoryMapStruct INSTANCE = Mappers.getMapper(AppAppletProdCategoryMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param appletProdCategory entity
     * @return vo
     */
    AppAppletProdCategoryVO converToVO(AppletProdCategory appletProdCategory);


}