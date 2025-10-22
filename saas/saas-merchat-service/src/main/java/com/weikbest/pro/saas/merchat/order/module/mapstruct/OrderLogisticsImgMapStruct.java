package com.weikbest.pro.saas.merchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogisticsImg;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderLogisticsImgDTO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderLogisticsImgVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单物流图片表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface OrderLogisticsImgMapStruct extends BaseMapStruct {
    OrderLogisticsImgMapStruct INSTANCE = Mappers.getMapper(OrderLogisticsImgMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderLogisticsImg entity
     * @return dto
     */
    OrderLogisticsImgDTO converToDTO(OrderLogisticsImg orderLogisticsImg);

    /**
     * DTO转换为entity
     *
     * @param orderLogisticsImgDTO dto
     * @return entity
     */
    OrderLogisticsImg converToEntity(OrderLogisticsImgDTO orderLogisticsImgDTO);

    /**
     * entity转换为VO
     *
     * @param orderLogisticsImg entity
     * @return vo
     */
    OrderLogisticsImgVO converToVO(OrderLogisticsImg orderLogisticsImg);

    /**
     * VO转换为entity
     *
     * @param orderLogisticsImgVO vo
     * @return entity
     */
    OrderLogisticsImg converToEntity(OrderLogisticsImgVO orderLogisticsImgVO);
}