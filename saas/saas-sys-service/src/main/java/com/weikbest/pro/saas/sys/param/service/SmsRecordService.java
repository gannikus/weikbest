package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.SmsRecord;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.module.dto.SmsRecordDTO;
import com.weikbest.pro.saas.sys.param.module.qo.SmsRecordQO;

/**
 * <p>
 * 短信发送记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface SmsRecordService extends IService<SmsRecord> {

    /**
     * 新增数据
     *
     * @param smsRecordDTO smsRecordDTO
     * @return 新增结果
     */
    boolean insert(SmsRecordDTO smsRecordDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param smsRecordDTO smsRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, SmsRecordDTO smsRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    SmsRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param smsRecordQO
     * @param pageReq
     * @return
     */
    IPage<SmsRecord> queryPage(SmsRecordQO smsRecordQO, PageReq pageReq);

    /**
     * 保存发送记录
     *
     * @param phoneNum
     * @param smsTemplate
     * @param templateParam
     * @param aliyunSmsResponse
     */
    void saveRecord(String phoneNum, SmsTemplate smsTemplate, String templateParam, AliyunSmsResponse aliyunSmsResponse);
}
