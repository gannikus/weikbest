package com.weikbest.pro.saas.common.third.aliyun.logistics;

import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuCompanyResult;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
public interface AliyunWuliuService {

    /***
     * 顺丰定制
     * @param courierNumber
     * @param phone
     * @return
     * @throws Exception
     */
    String queryAliyunWuliu(String courierNumber, String phone) throws Exception;

    /**
     * 普通快递公司查询物流信息
     *
     * @param courierNumber
     * @return
     * @throws Exception
     */
    String queryAliyunWuliu(String courierNumber) throws Exception;

    /**
     * 查询物流公司信息
     *
     * @return
     * @throws Exception
     */
    AliyunWuliuCompanyResult queryAliyunWuliuCompany() throws Exception;
}
