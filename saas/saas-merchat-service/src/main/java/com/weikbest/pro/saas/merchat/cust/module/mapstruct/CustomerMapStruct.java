package com.weikbest.pro.saas.merchat.cust.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import com.weikbest.pro.saas.merchat.cust.module.dto.CustomerDTO;
import com.weikbest.pro.saas.merchat.cust.module.vo.CustomerVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-16
 */
@Mapper
public interface CustomerMapStruct extends BaseMapStruct {
    CustomerMapStruct INSTANCE = Mappers.getMapper(CustomerMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param customer entity
     * @return dto
     */
    CustomerDTO converToDTO(Customer customer);

    /**
     * DTO转换为entity
     *
     * @param customerDTO dto
     * @return entity
     */
    Customer converToEntity(CustomerDTO customerDTO);


    /**
     * DTO转换为entity
     *
     * @param customerDTO dto
     * @param dbCustomer  entity
     * @return entity
     */
    @Mapping(target = "name", source = "customerDTO.name", defaultExpression = "java(dbCustomer.getName())")
    @Mapping(target = "avatar", source = "customerDTO.avatar", defaultExpression = "java(dbCustomer.getAvatar())")
    @Mapping(target = "phone", source = "customerDTO.phone", defaultExpression = "java(dbCustomer.getPhone())")
    @Mapping(target = "userUnique", source = "customerDTO.phone", defaultExpression = "java(dbCustomer.getUserUnique())")
    @Mapping(target = "sex", source = "customerDTO.sex", defaultExpression = "java(dbCustomer.getSex())")
    @Mapping(target = "email", source = "customerDTO.email", defaultExpression = "java(dbCustomer.getEmail())")
    @Mapping(target = "custCountry", source = "customerDTO.custCountry", defaultExpression = "java(dbCustomer.getCustCountry())")
    @Mapping(target = "custProvince", source = "customerDTO.custProvince", defaultExpression = "java(dbCustomer.getCustProvince())")
    @Mapping(target = "custCity", source = "customerDTO.custCity", defaultExpression = "java(dbCustomer.getCustCity())")
    @Mapping(target = "custDistrict", source = "customerDTO.custDistrict", defaultExpression = "java(dbCustomer.getCustDistrict())")
    @Mapping(target = "custAddr", source = "customerDTO.custAddr", defaultExpression = "java(dbCustomer.getCustAddr())")
    Customer converToEntity(CustomerDTO customerDTO, Customer dbCustomer);


    /**
     * entity转换为VO
     *
     * @param customer entity
     * @return vo
     */
    CustomerVO converToVO(Customer customer);

    /**
     * VO转换为entity
     *
     * @param customerVO vo
     * @return entity
     */
    Customer converToEntity(CustomerVO customerVO);
}