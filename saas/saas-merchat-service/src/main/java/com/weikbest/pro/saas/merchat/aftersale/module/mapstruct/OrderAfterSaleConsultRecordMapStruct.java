package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSale;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleConsultRecord;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleConsultRecordDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordDetailVO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleConsultRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
public interface OrderAfterSaleConsultRecordMapStruct extends BaseMapStruct {
    OrderAfterSaleConsultRecordMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleConsultRecordMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSaleConsultRecord entity
     * @return dto
     */
    OrderAfterSaleConsultRecordDTO converToDTO(OrderAfterSaleConsultRecord orderAfterSaleConsultRecord);

    /**
     * DTO转换为entity
     *
     * @param orderAfterSaleConsultRecordDTO dto
     * @return entity
     */
    OrderAfterSaleConsultRecord converToEntity(OrderAfterSaleConsultRecordDTO orderAfterSaleConsultRecordDTO);

    /**
     * orderAfterSale转换为entity
     *
     * @param orderAfterSale 订单售后实体
     * @return entity
     */
    OrderAfterSaleConsultRecord converToEntity(OrderAfterSale orderAfterSale);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleConsultRecord entity
     * @return vo
     */
    OrderAfterSaleConsultRecordVO converToVO(OrderAfterSaleConsultRecord orderAfterSaleConsultRecord);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleConsultRecordVO vo
     * @return entity
     */
    OrderAfterSaleConsultRecord converToEntity(OrderAfterSaleConsultRecordVO orderAfterSaleConsultRecordVO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleConsultRecord entity
     * @return DetailVO
     */
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "detailVOList", ignore = true)
    OrderAfterSaleConsultRecordDetailVO converToDetailVO(OrderAfterSaleConsultRecord orderAfterSaleConsultRecord);
}