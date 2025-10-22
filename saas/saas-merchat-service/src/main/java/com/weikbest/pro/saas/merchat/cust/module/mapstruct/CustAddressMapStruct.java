package com.weikbest.pro.saas.merchat.cust.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.cust.entity.CustAddress;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustAddressDTO;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户收货地址表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface CustAddressMapStruct extends BaseMapStruct {
    CustAddressMapStruct INSTANCE = Mappers.getMapper(CustAddressMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param custAddress entity
     * @return dto
     */
    CustAddressDTO converToDTO(CustAddress custAddress);

    /**
     * DTO转换为entity
     *
     * @param custAddressDTO dto
     * @return entity
     */
    CustAddress converToEntity(CustAddressDTO custAddressDTO);

    /**
     * entity转换为VO
     *
     * @param custAddress entity
     * @return vo
     */
    CustAddressVO converToVO(CustAddress custAddress);

    /**
     * VO转换为entity
     *
     * @param custAddressVO vo
     * @return entity
     */
    CustAddress converToEntity(CustAddressVO custAddressVO);
}