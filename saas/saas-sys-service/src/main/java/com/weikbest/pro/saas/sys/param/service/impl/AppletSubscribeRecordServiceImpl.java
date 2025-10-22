package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.ext.user.UserExtServiceFactory;
import com.weikbest.pro.saas.common.ext.user.entity.UserExt;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeRecord;
import com.weikbest.pro.saas.sys.param.mapper.AppletSubscribeRecordMapper;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeRecordDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.AppletSubscribeRecordMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSubscribeRecordQO;
import com.weikbest.pro.saas.sys.param.service.AppletSubscribeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 小程序订阅消息发送记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
@Service
public class AppletSubscribeRecordServiceImpl extends ServiceImpl<AppletSubscribeRecordMapper, AppletSubscribeRecord> implements AppletSubscribeRecordService {

    @Resource
    private UserExtServiceFactory userExtServiceFactory;

    @Override
    public boolean insert(AppletSubscribeRecordDTO appletSubscribeRecordDTO) {
        AppletSubscribeRecord appletSubscribeRecord = AppletSubscribeRecordMapStruct.INSTANCE.converToEntity(appletSubscribeRecordDTO);
        return this.save(appletSubscribeRecord);
    }

    @Override
    public void saveRecord(AppletSubscribeConfig appletSubscribeConfig, String openid, String sendContent, String sendParam, String response) {
        // 根openid获取用户信息
        UserExt userExt = userExtServiceFactory.getUserExtService(DictConstant.UserRelateType.applet.getCode()).getUser(MapUtil.of("openid", openid));
        // 保存数据
        AppletSubscribeRecord appletSubscribeRecord = AppletSubscribeRecordMapStruct.INSTANCE.converToEntity(appletSubscribeConfig);
        appletSubscribeRecord.setSendContent(sendContent);
        appletSubscribeRecord.setSendParam(sendParam);
        appletSubscribeRecord.setSendPhone(userExt.getPhone());
        appletSubscribeRecord.setReceiptCustName(userExt.getName());
        appletSubscribeRecord.setReceiptOpenid(openid);
        appletSubscribeRecord.setSendTime(DateUtil.date());
        appletSubscribeRecord.setSendStatus(StrUtil.isNotBlank(response) ? DictConstant.Whether.no.getCode() : DictConstant.Whether.yes.getCode());
        appletSubscribeRecord.setSendException(response);
        this.save(appletSubscribeRecord);
    }

    @Override
    public boolean updateById(Long id, AppletSubscribeRecordDTO appletSubscribeRecordDTO) {
        AppletSubscribeRecord appletSubscribeRecord = this.findById(id);
        AppletSubscribeRecordMapStruct.INSTANCE.copyProperties(appletSubscribeRecordDTO, appletSubscribeRecord);
        appletSubscribeRecord.setId(id);
        return this.updateById(appletSubscribeRecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public AppletSubscribeRecord findById(Long id) {
        AppletSubscribeRecord appletSubscribeRecord = this.getById(id);
        if (ObjectUtil.isNull(appletSubscribeRecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletSubscribeRecord;
    }

    @Override
    public IPage<AppletSubscribeRecord> queryPage(AppletSubscribeRecordQO appletSubscribeRecordQO, PageReq pageReq) {
        QueryWrapper<AppletSubscribeRecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getAppId())) {
            wrapper.eq(AppletSubscribeRecord.APP_ID, appletSubscribeRecordQO.getAppId());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSendContent())) {
            wrapper.eq(AppletSubscribeRecord.SEND_CONTENT, appletSubscribeRecordQO.getSendContent());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSendParam())) {
            wrapper.eq(AppletSubscribeRecord.SEND_PARAM, appletSubscribeRecordQO.getSendParam());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSendPhone())) {
            wrapper.eq(AppletSubscribeRecord.SEND_PHONE, appletSubscribeRecordQO.getSendPhone());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getBindUrl())) {
            wrapper.eq(AppletSubscribeRecord.BIND_URL, appletSubscribeRecordQO.getBindUrl());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getReceiptCustName())) {
            wrapper.eq(AppletSubscribeRecord.RECEIPT_CUST_NAME, appletSubscribeRecordQO.getReceiptCustName());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSendStatus())) {
            wrapper.eq(AppletSubscribeRecord.SEND_STATUS, appletSubscribeRecordQO.getSendStatus());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSendException())) {
            wrapper.eq(AppletSubscribeRecord.SEND_EXCEPTION, appletSubscribeRecordQO.getSendException());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getNumber())) {
            wrapper.eq(AppletSubscribeRecord.NUMBER, appletSubscribeRecordQO.getNumber());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getName())) {
            wrapper.eq(AppletSubscribeRecord.NAME, appletSubscribeRecordQO.getName());
        }
        if (StrUtil.isNotBlank(appletSubscribeRecordQO.getSubscribeType())) {
            wrapper.eq(AppletSubscribeRecord.SUBSCRIBE_TYPE, appletSubscribeRecordQO.getSubscribeType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
