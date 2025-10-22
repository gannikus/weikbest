package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImg;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleLogisticsImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后物流图片拆分表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface OrderAfterSaleLogisticsImgMapStruct extends BaseMapStruct {
    OrderAfterSaleLogisticsImgMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleLogisticsImgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSaleLogisticsImg entity
     * @return dto
     */
    OrderAfterSaleLogisticsImgDTO converToDTO(OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg);

    /**
     * DTO转换为entity
     *
     * @param orderAfterSaleLogisticsImgDTO dto
     * @return entity
     */
    OrderAfterSaleLogisticsImg converToEntity(OrderAfterSaleLogisticsImgDTO orderAfterSaleLogisticsImgDTO);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleLogisticsImg entity
     * @return vo
     */
    OrderAfterSaleLogisticsImgVO converToVO(OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleLogisticsImgVO vo
     * @return entity
     */
    OrderAfterSaleLogisticsImg converToEntity(OrderAfterSaleLogisticsImgVO orderAfterSaleLogisticsImgVO);
}