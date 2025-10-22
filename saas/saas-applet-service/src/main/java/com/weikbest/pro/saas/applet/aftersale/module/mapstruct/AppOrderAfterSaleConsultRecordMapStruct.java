package com.weikbest.pro.saas.applet.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleConsultRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后协商历史记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface AppOrderAfterSaleConsultRecordMapStruct extends BaseMapStruct {
    AppOrderAfterSaleConsultRecordMapStruct INSTANCE = Mappers.getMapper(AppOrderAfterSaleConsultRecordMapStruct.class);

    /**
     * entity转换为entity
     *
     * @param orderAfterSale e
     * @return entity
     */
    OrderAfterSaleConsultRecord converToEntity(OrderAfterSale orderAfterSale);


}