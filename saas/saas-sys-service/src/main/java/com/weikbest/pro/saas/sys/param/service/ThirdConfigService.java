package com.weikbest.pro.saas.sys.param.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.weikbest.pro.saas.common.third.aliyun.logistics.AliyunWuliuService;
import com.weikbest.pro.saas.common.third.aliyun.oss.AliyunOssService;
import com.weikbest.pro.saas.common.third.aliyun.sms.AliyunSendSmsService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.ThirdConfig;
import com.weikbest.pro.saas.sys.param.module.dto.ThirdConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ThirdConfigQO;

/**
 * <p>
 * 第三方平台配置表   服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-17
 */
public interface ThirdConfigService extends IService<ThirdConfig> {

    /**
     * 新增数据
     *
     * @param thirdConfigDTO thirdConfigDTO
     * @return 新增结果
     */
    boolean insert(ThirdConfigDTO thirdConfigDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param thirdConfigDTO thirdConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ThirdConfigDTO thirdConfigDTO);

    /**
     * 添加或更新数据
     *
     * @param thirdConfigDTO
     * @return
     */
    boolean saveOrUpdate(ThirdConfigDTO thirdConfigDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ThirdConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param thirdConfigQO
     * @param pageReq
     * @return
     */
    IPage<ThirdConfig> queryPage(ThirdConfigQO thirdConfigQO, PageReq pageReq);


    /**
     * 查找服务商第三方平台配置记录，默认不存在则报错
     *
     * @return
     */
    ThirdConfig findThirdConfig();

    /**
     * 查找服务商第三方平台配置记录,不存在则报错
     *
     * @param notnullFlag
     * @return
     */
    ThirdConfig findThirdConfig(boolean notnullFlag);

    /**
     * 获取阿里云对象服务
     *
     * @return
     */
    AliyunOssService aliyunOssService();


    /**
     * 获取阿里云物流服务
     *
     * @return
     */
    AliyunWuliuService aliyunWuliuService();


    /**
     * 获取阿里云短信服务
     *
     * @return
     */
    AliyunSendSmsService aliyunSmsService();

    /**
     * 获取微信支付服务
     *
     * @return
     */
    WxPayService wxPayService();

    /**
     * 获取微信支付服务
     *
     * @param wxPayConfig 微信配置参数
     * @return
     */
    WxPayService wxPayService(WxPayConfig wxPayConfig);

    /**
     * 获取微信小程序服务
     *
     * @return
     */
    WxMaService wxMaService();

    /**
     * 获取微信小程序服务
     *
     * @param appId
     * @return
     */
    WxMaService wxMaService(String appId);


    /**
     * 获取微信小程序服务
     *
     * @param wxMaConfig
     * @return
     */
    WxMaService wxMaService(WxMaConfig wxMaConfig);


}
