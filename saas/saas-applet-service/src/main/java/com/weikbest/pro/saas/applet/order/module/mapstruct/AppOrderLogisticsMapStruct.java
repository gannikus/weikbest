package com.weikbest.pro.saas.applet.order.module.mapstruct;

import com.weikbest.pro.saas.applet.order.module.vo.AppOrderLogisticsVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderLogistics;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单物流记录表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Mapper
public interface AppOrderLogisticsMapStruct extends BaseMapStruct {
    AppOrderLogisticsMapStruct INSTANCE = Mappers.getMapper(AppOrderLogisticsMapStruct.class);

    /**
     * entity转换为VO
     *
     * @param orderLogistics entity
     * @return vo
     */
    AppOrderLogisticsVO converToVO(OrderLogistics orderLogistics);

}