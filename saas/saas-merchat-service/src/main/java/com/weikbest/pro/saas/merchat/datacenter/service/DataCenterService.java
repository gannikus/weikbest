package com.weikbest.pro.saas.merchat.datacenter.service;

import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.datacenter.module.vo.DataCenterVO;

import java.util.List;

/**
 * <p>
 * 数据中心 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface DataCenterService {

    /**
     * 查询小程序信息
     *
     * @return 小程序数据字典
     */
    List<DictEntry> queryApplet();


    /**
     * 根据ID查询
     *
     * @param businessId 商户ID
     * @param shopId 店铺ID
     * @param dataCenterQO 查询条件
     * @return 查询结果
     */
    DataCenterVO queryData(Long businessId, Long shopId, DataCenterQO dataCenterQO);


}
