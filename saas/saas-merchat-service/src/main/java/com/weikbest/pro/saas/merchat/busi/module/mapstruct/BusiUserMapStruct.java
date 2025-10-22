package com.weikbest.pro.saas.merchat.busi.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserLoginInfoVO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户账户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-12
 */
@Mapper
public interface BusiUserMapStruct extends BaseMapStruct {
    BusiUserMapStruct INSTANCE = Mappers.getMapper(BusiUserMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param busiUser entity
     * @return dto
     */
    BusiUserDTO converToDTO(BusiUser busiUser);

    /**
     * DTO转换为entity
     *
     * @param busiUserDTO dto
     * @return entity
     */
    BusiUser converToEntity(BusiUserDTO busiUserDTO);


    /**
     * entity转换为VO
     *
     * @param busiUser entity
     * @return vo
     */
    BusiUserVO converToVO(BusiUser busiUser);

    /**
     * VO转换为entity
     *
     * @param busiUserVO vo
     * @return entity
     */
    BusiUser converToEntity(BusiUserVO busiUserVO);

    /**
     * entity转换为VO
     *
     * @param busiUser entity
     * @return VO
     */
    @Mapping(target = "id", source = "busiUser.id")
    @Mapping(target = "name", source = "busiUser.name")
    @Mapping(target = "avatar", source = "busiUser.avatar")
    @Mapping(target = "phone", source = "busiUser.phone")
    @Mapping(target = "description", source = "busiUser.description")
    @Mapping(target = "dataStatus", source = "busiUser.dataStatus")
    BusiUserLoginInfoVO converToLoginInfoVO(BusiUser busiUser);
}