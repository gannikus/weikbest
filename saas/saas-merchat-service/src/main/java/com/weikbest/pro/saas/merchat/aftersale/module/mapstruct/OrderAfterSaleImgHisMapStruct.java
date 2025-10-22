package com.weikbest.pro.saas.merchat.aftersale.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImg;
import com.weikbest.pro.saas.merchat.aftersale.entity.OrderAfterSaleImgHis;
import com.weikbest.pro.saas.merchat.aftersale.module.dto.OrderAfterSaleImgHisDTO;
import com.weikbest.pro.saas.merchat.aftersale.module.vo.OrderAfterSaleImgHisVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单售后图片拆分历史表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-25
 */
@Mapper
public interface OrderAfterSaleImgHisMapStruct extends BaseMapStruct {
    OrderAfterSaleImgHisMapStruct INSTANCE = Mappers.getMapper(OrderAfterSaleImgHisMapStruct.class);

    /**
     * entity转换为DTO
     *
     * @param orderAfterSaleImgHis entity
     * @return dto
     */
    OrderAfterSaleImgHisDTO converToDTO(OrderAfterSaleImgHis orderAfterSaleImgHis);

    /**
     * DTO转换为entity
     *
     * @param orderAfterSaleImgHisDTO dto
     * @return entity
     */
    OrderAfterSaleImgHis converToEntity(OrderAfterSaleImgHisDTO orderAfterSaleImgHisDTO);

    /**
     * orderAfterSaleImg转换为entity
     *
     * @param orderAfterSaleImg 订单售后凭证
     * @return entity
     */
    @Mapping(target = "entryId", ignore = true)
    OrderAfterSaleImgHis converToEntity(OrderAfterSaleImg orderAfterSaleImg);

    /**
     * entity转换为VO
     *
     * @param orderAfterSaleImgHis entity
     * @return vo
     */
    OrderAfterSaleImgHisVO converToVO(OrderAfterSaleImgHis orderAfterSaleImgHis);

    /**
     * VO转换为entity
     *
     * @param orderAfterSaleImgHisVO vo
     * @return entity
     */
    OrderAfterSaleImgHis converToEntity(OrderAfterSaleImgHisVO orderAfterSaleImgHisVO);
}