package com.weikbest.pro.saas.applet.aftersale.module.mapstruct;

import com.weikbest.pro.saas.applet.aftersale.module.dto.AppOrderAfterSaleDTO;
import com.weikbest.pro.saas.applet.aftersale.module.vo.AppOrderAfterSaleDetailVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface AppOrderAfterSaleMapStruct extends BaseMapStruct {
    AppOrderAfterSaleMapStruct INSTANCE = Mappers.getMapper(AppOrderAfterSaleMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSale entity
     * @return dto
     */
    OrderAfterSaleDTO converToDTO(OrderAfterSale orderAfterSale);

    /**
     * DTO转换为entity
     *
     * @param appOrderAfterSaleDTO dto
     * @return entity
     */
    OrderAfterSale converToEntity(AppOrderAfterSaleDTO appOrderAfterSaleDTO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSale entity
     * @return vo
     */
    AppOrderAfterSaleDetailVO converToVO(OrderAfterSale orderAfterSale);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleVO vo
     * @return entity
     */
    OrderAfterSale converToEntity(OrderAfterSaleVO orderAfterSaleVO);
}