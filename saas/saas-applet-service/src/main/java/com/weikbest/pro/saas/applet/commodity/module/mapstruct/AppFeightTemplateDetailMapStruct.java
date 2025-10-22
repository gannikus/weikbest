package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppFeightTemplateDetailVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateDetail;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板详情拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppFeightTemplateDetailMapStruct extends BaseMapStruct {
    AppFeightTemplateDetailMapStruct INSTANCE = Mappers.getMapper(AppFeightTemplateDetailMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param feightTemplateDetail entity
     * @return vo
     */
    AppFeightTemplateDetailVO converToVO(FeightTemplateDetail feightTemplateDetail);

}