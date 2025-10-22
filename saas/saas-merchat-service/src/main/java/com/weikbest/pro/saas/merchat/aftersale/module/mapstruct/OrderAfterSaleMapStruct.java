package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleDetailVO;
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
public interface OrderAfterSaleMapStruct extends BaseMapStruct {
    OrderAfterSaleMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleMapStruct.class);

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
     * @param orderAfterSaleDTO dto
     * @return entity
     */
    OrderAfterSale converToEntity(OrderAfterSaleDTO orderAfterSaleDTO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSale entity
     * @return vo
     */
    OrderAfterSaleVO converToVO(OrderAfterSale orderAfterSale);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleVO vo
     * @return entity
     */
    OrderAfterSale converToEntity(OrderAfterSaleVO orderAfterSaleVO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSale entity
     * @return orderAfterSaleDetailVO
     */
    OrderAfterSaleDetailVO converToOrderAfterSaleDetailVO(OrderAfterSale orderAfterSale);
}