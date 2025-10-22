package com.weikbest.pro.saas.applet.commodity.module.mapstruct;

import com.weikbest.pro.saas.applet.commodity.module.vo.AppFeightTemplateRegionVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateRegion;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateRegionDTO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateRegionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 运费模板可配送地区详情拆分多行表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppFeightTemplateRegionMapStruct extends BaseMapStruct {
    AppFeightTemplateRegionMapStruct INSTANCE = Mappers.getMapper(AppFeightTemplateRegionMapStruct.class);


    /**
     * entity转换为VO
     *
     * @param feightTemplateRegion entity
     * @return vo
     */
    AppFeightTemplateRegionVO converToVO(FeightTemplateRegion feightTemplateRegion);

}