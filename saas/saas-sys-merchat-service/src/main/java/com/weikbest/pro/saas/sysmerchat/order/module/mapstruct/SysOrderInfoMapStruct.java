package com.weikbest.pro.saas.sysmerchat.order.module.mapstruct;

import com.weikbest.pro.saas.common.mapstruct.BaseMapStruct;
import com.weikbest.pro.saas.merchat.order.entity.OrderInfo;
import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDTO;
import com.weikbest.pro.saas.merchat.order.module.qo.OrderInfoQO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoCustCouponRestrictVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoDetailVO;
import com.weikbest.pro.saas.merchat.order.module.vo.OrderInfoVO;
import com.weikbest.pro.saas.sysmerchat.order.module.qo.SysOrderGroupQO;
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
public interface SysOrderInfoMapStruct extends BaseMapStruct {
    SysOrderInfoMapStruct INSTANCE = Mappers.getMapper(SysOrderInfoMapStruct.class);

    /**
     * sysOrderGroupQO转换为OrderInfoQO
     *
     * @param sysOrderGroupQO sysOrderGroupQO
     * @return OrderInfoQO
     */
    OrderInfoQO SysOrderGroupQOConverToOrderInfoQO(SysOrderGroupQO sysOrderGroupQO);
}