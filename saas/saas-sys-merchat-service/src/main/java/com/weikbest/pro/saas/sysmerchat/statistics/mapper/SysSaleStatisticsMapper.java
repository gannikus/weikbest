package com.weikbest.pro.saas.sysmerchat.statistics.mapper;

import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysSaleStatisticsQO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/11
 *
 */
public interface SysSaleStatisticsMapper {

    /**
     * 查询销售总计数据
     *
     * @param businessId 商户ID
     * @param sysSaleStatisticsQO 查询条件
     * @return
     */
    List<OrderInfoDataCenterDTO> querySaleStatistics(@Param("saleStatistics") SysSaleStatisticsQO sysSaleStatisticsQO);
}
