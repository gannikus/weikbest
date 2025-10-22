package com.weikbest.pro.saas.sysmerchat.common.service;

import com.weikbest.pro.saas.sysmerchat.common.module.qo.OrderStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.qo.SalesStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.OrderStatisticsVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.RealtimeVO;
import com.weikbest.pro.saas.sysmerchat.common.module.vo.SalesStatisticsVO;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2023/2/18
 */
public interface SysIndexService {

    /**
     * 查询首页实时概况
     *
     * @return
     */
    RealtimeVO queryRealtime();

    /**
     * 查询首页销售统计
     *
     * @param salesStatisticsQO
     * @return
     */
    SalesStatisticsVO querySalesStatistics(SalesStatisticsQO salesStatisticsQO);

    /**
     * 查询首页订单统计
     *
     * @param orderStatisticsQO
     * @return
     */
    OrderStatisticsVO queryOrderStatistics(OrderStatisticsQO orderStatisticsQO);
}
