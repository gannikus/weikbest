package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImg;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后图片拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface OrderAfterSaleImgMapStruct extends BaseMapStruct {
    OrderAfterSaleImgMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleImgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSaleImg entity
     * @return dto
     */
    OrderAfterSaleImgDTO converToDTO(OrderAfterSaleImg orderAfterSaleImg);

    /**
     * DTO转换为entity
     *
     * @param orderAfterSaleImgDTO dto
     * @return entity
     */
    OrderAfterSaleImg converToEntity(OrderAfterSaleImgDTO orderAfterSaleImgDTO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleImg entity
     * @return vo
     */
    OrderAfterSaleImgVO converToVO(OrderAfterSaleImg orderAfterSaleImg);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleImgVO vo
     * @return entity
     */
    OrderAfterSaleImg converToEntity(OrderAfterSaleImgVO orderAfterSaleImgVO);
}