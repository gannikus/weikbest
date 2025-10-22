package com.weikbest.pro.saas.sysmerchat.statistics.mapper;

import com.weikbest.pro.saas.merchat.order.module.dto.OrderInfoDataCenterDTO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysRefundStatisticsQO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/11
 *
 */
public interface SysRefundStatisticsMapper {

    /**
     * 查询退款总计数据
     *
     * @param sysRefundStatisticsQO 查询条件
     * @return
     */
    List<OrderInfoDataCenterDTO> queryRefundStatistics(@Param("refundStatistics") SysRefundStatisticsQO sysRefundStatisticsQO);
}
