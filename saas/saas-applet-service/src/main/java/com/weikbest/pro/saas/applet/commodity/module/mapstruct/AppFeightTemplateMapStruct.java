package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppFeightTemplateVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplate;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppFeightTemplateMapStruct extends BaseMapStruct {
    AppFeightTemplateMapStruct INSTANCE = Mappers.getMapper(AppFeightTemplateMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param feightTemplate entity
     * @return vo
     */
    AppFeightTemplateVO converToVO(FeightTemplate feightTemplate);

}