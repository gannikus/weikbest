package com.weikbest.pro.saas.merchat.busi.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.busi.entity.BusiAddress;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiAddressDTO;
import com.weikbest.pro.saas.merchat.busi.module.vo.BusiAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 商家详细地址信息表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-06
 */
@Mapper
public interface BusiAddressMapStruct extends BaseMapStruct {
    BusiAddressMapStruct INSTANCE = Mappers.getMapper(BusiAddressMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param busiAddress entity
     * @return dto
     */
    BusiAddressDTO converToDTO(BusiAddress busiAddress);

    /**
     * DTO转换为entity
     *
     * @param busiAddressDTO dto
     * @return entity
     */
    BusiAddress converToEntity(BusiAddressDTO busiAddressDTO);

    /**
     * entity转换为VO
     *
     * @param busiAddress entity
     * @return vo
     */
    BusiAddressVO converToVO(BusiAddress busiAddress);

    /**
     * VO转换为entity
     *
     * @param busiAddressVO vo
     * @return entity
     */
    BusiAddress converToEntity(BusiAddressVO busiAddressVO);
}