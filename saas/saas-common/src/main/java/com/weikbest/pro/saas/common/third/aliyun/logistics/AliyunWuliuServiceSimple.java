package com.weikbest.pro.saas.common.third.aliyun.logistics;

import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuAuthConfig;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuCompanyResult;
import com.weikbest.pro.saas.common.third.aliyun.logistics.config.AliyunWuliuConfig;
import com.weikbest.pro.saas.common.third.aliyun.logistics.util.AliyunWuliuServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wisdomelon
 * @date 2021/5/22
 * @project saas
 * @jdk 1.8
 */
@Slf4j
@Service
public class AliyunWuliuServiceSimple implements AliyunWuliuService {

    @Autowired
    private AliyunWuliuConfig aliyunWuliuConfig;

    @Autowired
    private AliyunWuliuAuthConfig aliyunWuliuAuthConfig;

    /***
     * 顺丰定制
     * @param courierNumber
     * @param phone
     * @return
     * @throws Exception
     */
    @Override
    public String queryAliyunWuliu(String courierNumber, String phone) throws Exception {
        // 手机号后四位
        phone = phone.substring(phone.length() - 4);
        return AliyunWuliuServiceUtil.queryAliyunWuliu(aliyunWuliuConfig.getHost(), aliyunWuliuConfig.getPath(),
                aliyunWuliuAuthConfig.getAppcode(), courierNumber + ":" + phone);
    }

    /**
     * 普通快递公司查询物流信息
     *
     * @param courierNumber
     * @return
     * @throws Exception
     */
    @Override
    public String queryAliyunWuliu(String courierNumber) throws Exception {
        return AliyunWuliuServiceUtil.queryAliyunWuliu(aliyunWuliuConfig.getHost(), aliyunWuliuConfig.getPath(),
                aliyunWuliuAuthConfig.getAppcode(), courierNumber);
    }

    @Override
    public AliyunWuliuCompanyResult queryAliyunWuliuCompany() throws Exception {
        return AliyunWuliuServiceUtil.queryAliyunWuliuCompany(aliyunWuliuConfig.getCompanyHost(), aliyunWuliuConfig.getCompanyPath(),
                aliyunWuliuAuthConfig.getAppcode());
    }
}
