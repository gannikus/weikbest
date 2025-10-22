package com.weikbest.pro.saas.applet.comm.module.mapstruct;

import com.weikbest.pro.saas.applet.comm.module.dto.AppLoginDTO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.cust.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/10/16
 */
@Mapper
public interface AppLoginMapStruct extends BaseMapStruct {
    AppLoginMapStruct INSTANCE = Mappers.getMapper(AppLoginMapStruct.class);

    /**
     * DTO转换为entity
     *
     * @param appLoginDTO dto
     * @return entity
     */
    @Mapping(target = "wxOpenid", source = "openid")
    @Mapping(target = "wxUnionid", source = "unionid")
    Customer converToCustomerEntity(AppLoginDTO appLoginDTO);

    /**
     * DTO转换为entity
     *
     * @param appLoginDTO dto
     * @param dbCustomer  entity
     * @return entity
     */
    @Mapping(target = "wxOpenid", source = "appLoginDTO.openid")
    @Mapping(target = "wxUnionid", source = "appLoginDTO.unionid")
    @Mapping(target = "appId", source = "appLoginDTO.appId", defaultExpression = "java(dbCustomer.getAppId())")
    @Mapping(target = "phone", source = "appLoginDTO.phone", defaultExpression = "java(dbCustomer.getPhone())")
    @Mapping(target = "userUnique", source = "appLoginDTO.phone", defaultExpression = "java(dbCustomer.getUserUnique())")
    @Mapping(target = "name", source = "appLoginDTO.name", defaultExpression = "java(dbCustomer.getName())")
    @Mapping(target = "avatar", source = "appLoginDTO.avatar", defaultExpression = "java(dbCustomer.getAvatar())")
    @Mapping(target = "sex", source = "appLoginDTO.sex", defaultExpression = "java(dbCustomer.getSex())")
    @Mapping(target = "email", source = "appLoginDTO.email", defaultExpression = "java(dbCustomer.getEmail())")
    @Mapping(target = "custCountry", source = "appLoginDTO.custCountry", defaultExpression = "java(dbCustomer.getCustCountry())")
    @Mapping(target = "custProvince", source = "appLoginDTO.custProvince", defaultExpression = "java(dbCustomer.getCustProvince())")
    @Mapping(target = "custCity", source = "appLoginDTO.custCity", defaultExpression = "java(dbCustomer.getCustCity())")
    @Mapping(target = "custDistrict", source = "appLoginDTO.custDistrict", defaultExpression = "java(dbCustomer.getCustDistrict())")
    @Mapping(target = "custAddr", source = "appLoginDTO.custAddr", defaultExpression = "java(dbCustomer.getCustAddr())")
    Customer converToCustomerEntity(AppLoginDTO appLoginDTO, Customer dbCustomer);
}
