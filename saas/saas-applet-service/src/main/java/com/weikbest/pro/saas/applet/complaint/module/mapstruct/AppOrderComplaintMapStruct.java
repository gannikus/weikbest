package com.weikbest.pro.saas.applet.complaint.module.mapstruct;

import com.weikbest.pro.saas.applet.complaint.module.dto.AppOrderComplaintDTO;
import com.weikbest.pro.saas.applet.complaint.module.vo.AppOrderComplaintVO;
import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.complaint.entity.OrderComplaint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 订单投诉表实体转换映射
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-19
 */
@Mapper
public interface AppOrderComplaintMapStruct extends BaseMapStruct {
    AppOrderComplaintMapStruct INSTANCE = Mappers.getMapper(AppOrderComplaintMapStruct.class);

    /**
     * DTO转换为entity
     *
     * @param appOrderComplaintDTO dto
     * @return entity
     */
    OrderComplaint converToEntity(AppOrderComplaintDTO appOrderComplaintDTO);

    /**
     * entity转换为VO
     *
     * @param orderComplaint entity
     * @return vo
     */
    AppOrderComplaintVO converToVO(OrderComplaint orderComplaint);
}