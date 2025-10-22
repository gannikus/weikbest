package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.SmsTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.qo.SmsTemplateQO;

/**
 * <p>
 * 短信模板表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface SmsTemplateService extends IService<SmsTemplate> {

    /**
     * 新增数据
     *
     * @param smsTemplateDTO smsTemplateDTO
     * @return 新增结果
     */
    boolean insert(SmsTemplateDTO smsTemplateDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param smsTemplateDTO smsTemplateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, SmsTemplateDTO smsTemplateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    SmsTemplate findById(Long id);

    /**
     * 分页查询
     *
     * @param smsTemplateQO
     * @param pageReq
     * @return
     */
    IPage<SmsTemplate> queryPage(SmsTemplateQO smsTemplateQO, PageReq pageReq);

    /**
     * 根据绑定路径匹配短信模板
     *
     * @param bindUrl
     * @return
     */
    SmsTemplate getSmsTemplateByBindUrl(String bindUrl);

    /**
     * 发送单条短信并保存发送记录
     *
     * @param bindUrl
     * @param phoneNum
     * @param smsParam
     */
    void sendOneSmsAndSaveRecord(String bindUrl, String phoneNum, String... smsParam);

    /**
     * 批量发送短信并保存发送记录
     *
     * @param bindUrl
     * @param phoneNum
     * @param smsParam
     */
    void sendBatchSmsAndSaveRecord(String bindUrl, String phoneNum, String... smsParam);

    /**
     * 发送单条短信,短信包含跳转小程序的链接，并保存发送记录
     *
     * @param appId
     * @param bindUrl
     * @param customerPhone
     * @param appletUrlParam
     */
    void sendOneSmsJumpAppletAndSaveRecord(String appId, String bindUrl, String customerPhone, String... appletUrlParam);
}
