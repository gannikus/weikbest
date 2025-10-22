package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImg;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleLogisticsImgHis;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleLogisticsImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleLogisticsImgHisVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后物流图片拆分历史表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface OrderAfterSaleLogisticsImgHisMapStruct extends BaseMapStruct {
    OrderAfterSaleLogisticsImgHisMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleLogisticsImgHisMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSaleLogisticsImgHis entity
     * @return dto
     */
    OrderAfterSaleLogisticsImgHisDTO converToDTO(OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis);

    /**
     * DTO转换为entity
     *
     * @param orderAfterSaleLogisticsImgHisDTO dto
     * @return entity
     */
    OrderAfterSaleLogisticsImgHis converToEntity(OrderAfterSaleLogisticsImgHisDTO orderAfterSaleLogisticsImgHisDTO);


    /**
     * entity转换为entity
     *
     * @param orderAfterSaleLogisticsImg entity
     * @return entity
     */
    @Mapping(target = "entryId", ignore = true)
    OrderAfterSaleLogisticsImgHis converToEntity(OrderAfterSaleLogisticsImg orderAfterSaleLogisticsImg);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleLogisticsImgHis entity
     * @return vo
     */
    OrderAfterSaleLogisticsImgHisVO converToVO(OrderAfterSaleLogisticsImgHis orderAfterSaleLogisticsImgHis);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleLogisticsImgHisVO vo
     * @return entity
     */
    OrderAfterSaleLogisticsImgHis converToEntity(OrderAfterSaleLogisticsImgHisVO orderAfterSaleLogisticsImgHisVO);
}