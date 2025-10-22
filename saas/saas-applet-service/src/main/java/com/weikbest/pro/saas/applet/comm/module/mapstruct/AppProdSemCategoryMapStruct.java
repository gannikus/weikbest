package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppProdSemCategoryVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * <p>
 * 小程序营销广告分类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-14
 */
@Mapper
public interface AppProdSemCategoryMapStruct extends BaseMapStruct {
    AppProdSemCategoryMapStruct INSTANCE = Mappers.getMapper(AppProdSemCategoryMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodSemCategory entity
     * @return vo
     */
    AppProdSemCategoryVO converToVO(AppletDecConfigEntry prodSemCategory);


}