package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.aliyun.sms.config.AliyunSmsResponse;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.SmsRecord;
import com.weikbest.pro.saas.sys.param.entity.SmsTemplate;
import com.weikbest.pro.saas.sys.param.mapper.SmsRecordMapper;
import com.weikbest.pro.saas.sys.param.module.dto.SmsRecordDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.SmsRecordMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.SmsRecordQO;
import com.weikbest.pro.saas.sys.param.service.SmsRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信发送记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
@Service
public class SmsRecordServiceImpl extends ServiceImpl<SmsRecordMapper, SmsRecord> implements SmsRecordService {

    @Override
    public boolean insert(SmsRecordDTO smsRecordDTO) {
        SmsRecord smsRecord = SmsRecordMapStruct.INSTANCE.converToEntity(smsRecordDTO);
        return this.save(smsRecord);
    }

    @Override
    public boolean updateById(Long id, SmsRecordDTO smsRecordDTO) {
        SmsRecord smsRecord = this.findById(id);
        SmsRecordMapStruct.INSTANCE.copyProperties(smsRecordDTO, smsRecord);
        smsRecord.setId(id);
        return this.updateById(smsRecord);
    }

    @Override
    public SmsRecord findById(Long id) {
        SmsRecord smsRecord = this.getById(id);
        if (ObjectUtil.isNull(smsRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return smsRecord;
    }

    @Override
    public IPage<SmsRecord> queryPage(SmsRecordQO smsRecordQO, PageReq pageReq) {
        QueryWrapper<SmsRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(smsRecordQO.getSmsNumber())) {
            wrapper.eq(SmsRecord.SMS_NUMBER, smsRecordQO.getSmsNumber());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSmsName())) {
            wrapper.eq(SmsRecord.SMS_NAME, smsRecordQO.getSmsName());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSmsType())) {
            wrapper.eq(SmsRecord.SMS_TYPE, smsRecordQO.getSmsType());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSendContent())) {
            wrapper.eq(SmsRecord.SEND_CONTENT, smsRecordQO.getSendContent());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSendParam())) {
            wrapper.eq(SmsRecord.SEND_PARAM, smsRecordQO.getSendParam());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSendPhone())) {
            wrapper.eq(SmsRecord.SEND_PHONE, smsRecordQO.getSendPhone());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getReceiptUsername())) {
            wrapper.eq(SmsRecord.RECEIPT_USERNAME, smsRecordQO.getReceiptUsername());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSendStatus())) {
            wrapper.eq(SmsRecord.SEND_STATUS, smsRecordQO.getSendStatus());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getSendResponse())) {
            wrapper.eq(SmsRecord.SEND_RESPONSE, smsRecordQO.getSendResponse());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getRelevanceId())) {
            wrapper.eq(SmsRecord.RELEVANCE_ID, smsRecordQO.getRelevanceId());
        }
        if (StrUtil.isNotBlank(smsRecordQO.getRelevanceType())) {
            wrapper.eq(SmsRecord.RELEVANCE_TYPE, smsRecordQO.getRelevanceType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void saveRecord(String phoneNum, SmsTemplate smsTemplate, String templateParam, AliyunSmsResponse aliyunSmsResponse) {
        // 保存发送记录
        SmsRecord record = new SmsRecord();
        record.setSmsTemplateId(smsTemplate.getId());
        record.setSmsType(smsTemplate.getSmsType());
        record.setSmsName(smsTemplate.getName());
        record.setSendPhone(phoneNum);
        record.setSendContent(smsTemplate.getContent());
        record.setSendParam(templateParam);
        record.setSendStatus(aliyunSmsResponse.getStatus());
        record.setSendTime(DateUtil.date());
        record.setSendResponse(aliyunSmsResponse.getSendResponse());
        try {
            this.save(record);
        } catch (Exception e) {
            log.error("短信记录失败！", e);
        }
    }
}
