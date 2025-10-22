package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppProdSemPositionVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * <p>
 * 小程序营销广告位
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-14
 */
@Mapper
public interface AppProdSemPositionMapStruct extends BaseMapStruct {
    AppProdSemPositionMapStruct INSTANCE = Mappers.getMapper(AppProdSemPositionMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param prodSemPosition entity
     * @return vo
     */
    AppProdSemPositionVO converToVO(AppletDecConfig prodSemPosition);


}