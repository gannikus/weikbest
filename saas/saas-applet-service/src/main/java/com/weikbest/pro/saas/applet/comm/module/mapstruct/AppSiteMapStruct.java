package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.vo.AppSiteVO;
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
public interface AppSiteMapStruct extends BaseMapStruct {
    AppSiteMapStruct INSTANCE = Mappers.getMapper(AppSiteMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param site entity
     * @return vo
     */
    AppSiteVO converToVO(Site site);

}