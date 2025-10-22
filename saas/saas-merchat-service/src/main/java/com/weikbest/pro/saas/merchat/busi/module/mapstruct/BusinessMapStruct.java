package com.weikbest.pro.saas.merchat.busi.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRegistDTO;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusinessDTO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusinessVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface BusinessMapStruct extends BaseMapStruct {
    BusinessMapStruct INSTANCE = Mappers.getMapper(BusinessMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param business entity
     * @return dto
     */
    BusinessDTO converToDTO(Business business);

    /**
     * DTO转换为entity
     *
     * @param businessDTO dto
     * @return entity
     */
    Business converToEntity(BusinessDTO businessDTO);

    /**
     * DTO转换为entity
     *
     * @param busiUserRegistDTO dto
     * @return entity
     */
    @Mapping(target = "name", source = "businessName")
    Business converToEntity(BusiUserRegistDTO busiUserRegistDTO);

    /**
     * entity转换为VO
     *
     * @param business entity
     * @return vo
     */
    BusinessVO converToVO(Business business);

    /**
     * VO转换为entity
     *
     * @param businessVO vo
     * @return entity
     */
    Business converToEntity(BusinessVO businessVO);

    /**
     * DTO转换为entity
     *
     * @param businessDTO dto
     * @param businessId  businessId
     * @return entity
     */
//    @Mapping(target = "businessId", source = "businessId")
    @Mapping(target = "name", source = "businessDTO.userName")
    BusiUserDTO converToBusiUserDTO(BusinessDTO businessDTO, Long businessId);

    /**
     * DTO转换为dto
     *
     * @param busiUserRegistDTO dto
     * @return dto
     */
    @Mapping(target = "name", source = "businessName")
    @Mapping(target = "userName", source = "name")
    BusinessDTO converToBusinessDTO(BusiUserRegistDTO busiUserRegistDTO);
}