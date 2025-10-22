package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.PayConfig;
import com.weikbest.pro.saas.sys.param.module.dto.PayConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.PayConfigQO;

import java.util.List;

/**
 * <p>
 * 系统支付商户号配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-14
 */
public interface PayConfigService extends IService<PayConfig> {

    /**
     * 新增数据
     *
     * @param payConfigType
     * @param payConfigDTO  payConfigDTO
     * @return 新增结果
     */
    boolean insert(String payConfigType, PayConfigDTO payConfigDTO);

    /**
     * 更新数据
     *
     * @param id            ID
     * @param payConfigType
     * @param payConfigDTO  payConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, String payConfigType, PayConfigDTO payConfigDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    PayConfig findById(Long id);

    /**
     * 根据支付商户号类型 查询
     *
     * @param payConfigType 支付商户号类型
     * @return 查询结果
     */
    PayConfig findByPayConfigType(String payConfigType);

    /**
     * 分页查询
     *
     * @param payConfigQO
     * @param pageReq
     * @return
     */
    IPage<PayConfig> queryPage(PayConfigQO payConfigQO, PageReq pageReq);


    /**
     * 根据类型查询微信支付服务
     *
     * @param payConfigType 支付商户号类型
     * @return 查询结果
     */
    WxPayService findWxPayServiceByPayConfigType(String payConfigType);
}
