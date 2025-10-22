package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppAppletDecConfigEntryVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 小程序装修配置分录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Mapper
public interface AppAppletDecConfigEntryMapStruct extends BaseMapStruct {
    AppAppletDecConfigEntryMapStruct INSTANCE = Mappers.getMapper(AppAppletDecConfigEntryMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param appletDecConfigEntry entity
     * @return vo
     */
    AppAppletDecConfigEntryVO converToVO(AppletDecConfigEntry appletDecConfigEntry);


}