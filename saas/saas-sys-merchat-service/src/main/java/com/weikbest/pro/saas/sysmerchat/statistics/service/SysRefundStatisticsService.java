package com.weikbest.pro.saas.sysmerchat.statistics.service;

import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysRefundStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysRefundStatisticsVO;

/**
 * <p>
 * 平台统计 退款统计
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface SysRefundStatisticsService {

    /**
     * 根据ID查询
     *
     * @param sysRefundStatisticsQO 查询条件
     * @return 查询结果
     */
    SysRefundStatisticsVO queryData(SysRefundStatisticsQO sysRefundStatisticsQO);

}
