package com.weikbest.pro.saas.sys.param.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.module.dto.SiteDTO;
import com.weikbest.pro.saas.sys.param.module.vo.SiteVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统站点设置表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
@Mapper
public interface SiteMapStruct extends BaseMapStruct {
    SiteMapStruct INSTANCE = Mappers.getMapper(SiteMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param site entity
     * @return dto
     */
    SiteDTO converToDTO(Site site);

    /**
     * DTO转换为entity
     *
     * @param siteDTO dto
     * @return entity
     */
    Site converToEntity(SiteDTO siteDTO);

    /**
     * entity转换为VO
     *
     * @param site entity
     * @return vo
     */
    SiteVO converToVO(Site site);

    /**
     * VO转换为entity
     *
     * @param siteVO vo
     * @return entity
     */
    Site converToEntity(SiteVO siteVO);
}