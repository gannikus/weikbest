package com.weikbest.pro.saas.sysmerchat.business.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.sysmerchat.business.module.vo.SysBusiVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/1
 */
@Mapper
public interface SysBusiMapStruct extends BaseMapStruct {
    SysBusiMapStruct INSTANCE = Mappers.getMapper(SysBusiMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param business business
     * @param busiUser busiUser
     * @return vo
     */
    @Mapping(target = "id", source = "busiUser.id")
    @Mapping(target = "name", source = "busiUser.name")
    @Mapping(target = "businessName", source = "business.name")
    @Mapping(target = "description", source = "busiUser.description")
    @Mapping(target = "dataStatus", source = "busiUser.dataStatus")
    @Mapping(target = "creator", source = "busiUser.creator")
    @Mapping(target = "modifier", source = "busiUser.modifier")
    @Mapping(target = "gmtCreate", source = "busiUser.gmtCreate")
    @Mapping(target = "gmtModified", source = "busiUser.gmtModified")
    SysBusiVO converToVO(Business business, BusiUser busiUser);
}
