package com.weikbest.pro.saas.sysmerchat.statistics.service;

import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.qo.SysSaleStatisticsQO;
import com.weikbest.pro.saas.sysmerchat.statistics.module.vo.SysSaleStatisticsVO;

import java.util.List;

/**
 * <p>
 * 数据中心 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface SysSaleStatisticsService {

    /**
     * 根据ID查询
     *
     * @param sysRefundStatisticsQO 查询条件
     * @return 查询结果
     */
    SysSaleStatisticsVO queryData(SysSaleStatisticsQO sysRefundStatisticsQO);


}
