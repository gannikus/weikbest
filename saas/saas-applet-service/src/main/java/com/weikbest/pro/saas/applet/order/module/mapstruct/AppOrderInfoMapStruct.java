package com.weikbest.pro.saas.applet.order.module.mapstruct;

import com.weikbest.pro.saas.applet.order.module.dto.AppOrderInfoDTO;
import com.weikbest.pro.saas.applet.order.module.vo.AppOrderDetailVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppOrderInfoMapStruct extends BaseMapStruct {
    AppOrderInfoMapStruct INSTANCE = Mappers.getMapper(AppOrderInfoMapStruct.class);


    /**
     * DTO转换为entity
     *
     * @param appOrderInfoDTO dto
     * @return entity
     */
    OrderInfo converToEntity(AppOrderInfoDTO appOrderInfoDTO);

    /**
     * entity转换为VO
     *
     * @param orderInfo entity
     * @return vo
     */
    AppOrderDetailVO converToVO(OrderInfo orderInfo);

}