package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.ads.model.ActionType;
import com.tencent.ads.model.UserActionsAddResponse;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.tencent.adv.AddUserActions;
import com.weikbest.pro.saas.common.third.wx.util.WxPayAmountConvertUtil;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.WeikbestObjectUtil;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvScopeConfig;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUserrecord;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUsersource;
import com.weikbest.pro.saas.sys.param.mapper.TencentAdvUserrecordMapper;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordDTO;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUserrecordSendDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.TencentAdvUserrecordMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvUserrecordQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvConfigService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvScopeConfigService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvUserrecordService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvUsersourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据记录表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Slf4j
@Service
public class TencentAdvUserrecordServiceImpl extends ServiceImpl<TencentAdvUserrecordMapper, TencentAdvUserrecord> implements TencentAdvUserrecordService {

    /** 腾讯广告类型： 下单, 购买 */
    private static final ActionType[] ACTION_TYPE_ARR = new ActionType[]{ActionType.COMPLETE_ORDER, ActionType.PURCHASE};

    @Resource
    private TencentAdvConfigService tencentAdvConfigService;

    @Resource
    private TencentAdvScopeConfigService tencentAdvScopeConfigService;

    @Resource
    private TencentAdvUsersourceService tencentAdvUsersourceService;

    @Override
    public boolean insert(TencentAdvUserrecordDTO tencentAdvUserrecordDTO) {
        TencentAdvUserrecord tencentAdvUserrecord = TencentAdvUserrecordMapStruct.INSTANCE.converToEntity(tencentAdvUserrecordDTO);
        return this.save(tencentAdvUserrecord);
    }

    @Override
    public boolean updateById(Long id, TencentAdvUserrecordDTO tencentAdvUserrecordDTO) {
        TencentAdvUserrecord tencentAdvUserrecord = this.findById(id);
        TencentAdvUserrecordMapStruct.INSTANCE.copyProperties(tencentAdvUserrecordDTO, tencentAdvUserrecord);
        tencentAdvUserrecord.setId(id);
        return this.updateById(tencentAdvUserrecord);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public TencentAdvUserrecord findById(Long id) {
        TencentAdvUserrecord tencentAdvUserrecord = this.getById(id);
        if (ObjectUtil.isNull(tencentAdvUserrecord)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvUserrecord;
    }

    @Override
    public IPage<TencentAdvUserrecord> queryPage(TencentAdvUserrecordQO tencentAdvUserrecordQO, PageReq pageReq) {
        QueryWrapper<TencentAdvUserrecord> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getActionType())) {
            wrapper.eq(TencentAdvUserrecord.ACTION_TYPE, tencentAdvUserrecordQO.getActionType());
        }
        if (ObjectUtil.isNotNull(tencentAdvUserrecordQO.getAccountId())) {
            wrapper.eq(TencentAdvUserrecord.ACCOUNT_ID, tencentAdvUserrecordQO.getAccountId());
        }
        if (ObjectUtil.isNotNull(tencentAdvUserrecordQO.getUserActionSetId())) {
            wrapper.eq(TencentAdvUserrecord.USER_ACTION_SET_ID, tencentAdvUserrecordQO.getUserActionSetId());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getAdAid())) {
            wrapper.eq(TencentAdvUserrecord.AD_AID, tencentAdvUserrecordQO.getAdAid());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getClickId())) {
            wrapper.eq(TencentAdvUserrecord.CLICK_ID, tencentAdvUserrecordQO.getClickId());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getWechatAppId())) {
            wrapper.eq(TencentAdvUserrecord.WECHAT_APP_ID, tencentAdvUserrecordQO.getWechatAppId());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getWechatOpenid())) {
            wrapper.eq(TencentAdvUserrecord.WECHAT_OPENID, tencentAdvUserrecordQO.getWechatOpenid());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getWechatUnionid())) {
            wrapper.eq(TencentAdvUserrecord.WECHAT_UNIONID, tencentAdvUserrecordQO.getWechatUnionid());
        }
        if (StrUtil.isNotBlank(tencentAdvUserrecordQO.getReturnResults())) {
            wrapper.eq(TencentAdvUserrecord.RETURN_RESULTS, tencentAdvUserrecordQO.getReturnResults());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String sendWithRatio(TencentAdvUserrecordSendDTO tencentAdvUserrecordSendDTO,String source,String payType) {
        //从微信支付回调入口进来的 需要按回传比例做概率事件
        if (DictConstant.AdReturnSource.RETURN_PAGES_INPUT.getCode().equals(source)){
            boolean hitRandom = WeikbestObjectUtil.hitRandom(tencentAdvUserrecordSendDTO.getBackRatio());
            if(!hitRandom) {
                // 未命中
                log.info("订单ID：{}， 命中比率：{}，未命中，不进行广告回传", tencentAdvUserrecordSendDTO.getOrderId(), tencentAdvUserrecordSendDTO.getBackRatio());
                return DictConstant.Whether.no.getCode();
            }
        }
        // 进行广告回传
        TencentAdvConfig tencentAdvConfig = tencentAdvConfigService.findTencentAdvConfig();
        TencentAdvScopeConfig tencentAdvScopeConfig = tencentAdvScopeConfigService.findByClientId(tencentAdvConfig.getClientId());
        TencentAdvUsersource tencentAdvUsersource = tencentAdvUsersourceService.findByClientId(tencentAdvConfig.getClientId());

        //如果支付类型为到付，只回传下单
        if (DictConstant.PayType.TYPE_2.getCode().equals(payType)){
            UserActionsAddResponse userActionsAddResponse;
            try {
                // 发送腾讯广告
                userActionsAddResponse = sendAddUserActions(ActionType.COMPLETE_ORDER, tencentAdvUserrecordSendDTO, tencentAdvConfig, tencentAdvScopeConfig, tencentAdvUsersource);
            } catch (Exception e) {
                log.error("sendAddUserActions error!", e);
                throw new WeikbestException(e);
            }
            TencentAdvUserrecord tencentAdvUserrecord = new TencentAdvUserrecord();
            tencentAdvUserrecord.setOrderId(tencentAdvUserrecordSendDTO.getOrderId())
                    .setActionType(ActionType.COMPLETE_ORDER.getValue())
                    .setAccountId(tencentAdvUsersource.getAccountId())
                    .setUserActionSetId(tencentAdvUsersource.getUserActionSetId())
                    .setAdAid(tencentAdvUserrecordSendDTO.getAdAid())
                    .setClickId(tencentAdvUserrecordSendDTO.getClickId())
                    .setProdId(tencentAdvUserrecordSendDTO.getProdId())
                    .setPayAmount(tencentAdvUserrecordSendDTO.getPayAmount())
                    .setWechatAppId(tencentAdvUserrecordSendDTO.getWechatAppId())
                    .setWechatOpenid(tencentAdvUserrecordSendDTO.getWechatOpenid())
                    .setWechatUnionid(tencentAdvUserrecordSendDTO.getWechatUnionid())
                    .setReturnResults(JsonUtils.bean2Json(userActionsAddResponse));
            this.save(tencentAdvUserrecord);
        }else { //否则全部回传
            for (ActionType actionType : ACTION_TYPE_ARR) {
                UserActionsAddResponse userActionsAddResponse;
                try {
                    // 发送腾讯广告
                    userActionsAddResponse = sendAddUserActions(actionType, tencentAdvUserrecordSendDTO, tencentAdvConfig, tencentAdvScopeConfig, tencentAdvUsersource);
                } catch (Exception e) {
                    log.error("sendAddUserActions error!", e);
                    throw new WeikbestException(e);
                }
                // 保存记录
                TencentAdvUserrecord tencentAdvUserrecord = new TencentAdvUserrecord();
                tencentAdvUserrecord.setOrderId(tencentAdvUserrecordSendDTO.getOrderId())
                        .setActionType(actionType.getValue())
                        .setAccountId(tencentAdvUsersource.getAccountId())
                        .setUserActionSetId(tencentAdvUsersource.getUserActionSetId())
                        .setAdAid(tencentAdvUserrecordSendDTO.getAdAid())
                        .setClickId(tencentAdvUserrecordSendDTO.getClickId())
                        .setProdId(tencentAdvUserrecordSendDTO.getProdId())
                        .setPayAmount(tencentAdvUserrecordSendDTO.getPayAmount())
                        .setWechatAppId(tencentAdvUserrecordSendDTO.getWechatAppId())
                        .setWechatOpenid(tencentAdvUserrecordSendDTO.getWechatOpenid())
                        .setWechatUnionid(tencentAdvUserrecordSendDTO.getWechatUnionid())
                        .setReturnResults(JsonUtils.bean2Json(userActionsAddResponse));
                this.save(tencentAdvUserrecord);
            }
        }

        return DictConstant.Whether.yes.getCode();
    }

    /**
     * 发送腾讯广告
     *
     * @param actionType ActionType.COMPLETE_ORDER -- 下单  ActionType.PURCHASE -- 购买
     * @param tencentAdvUserrecordSendDTO
     * @param tencentAdvConfig
     * @param tencentAdvScopeConfig
     * @param tencentAdvUsersource
     * @throws Exception
     */
    private UserActionsAddResponse sendAddUserActions(ActionType actionType, TencentAdvUserrecordSendDTO tencentAdvUserrecordSendDTO, TencentAdvConfig tencentAdvConfig, TencentAdvScopeConfig tencentAdvScopeConfig, TencentAdvUsersource tencentAdvUsersource) throws Exception {
        AddUserActions addUserActions = new AddUserActions();
        // 构建
        Long accountId = tencentAdvUsersource.getAccountId();
        Long userActionSetId = tencentAdvUsersource.getUserActionSetId();
        String adClickId = tencentAdvUserrecordSendDTO.getClickId();
        int advalue = WxPayAmountConvertUtil.multiplyConvert(tencentAdvUserrecordSendDTO.getPayAmount());
        String accessToken = tencentAdvScopeConfig.getAccessToken();
        String appid = tencentAdvUserrecordSendDTO.getWechatAppId();
        String openid = tencentAdvUserrecordSendDTO.getWechatOpenid();
        String unionid = tencentAdvUserrecordSendDTO.getWechatUnionid();
        addUserActions.init(actionType, accountId, userActionSetId, adClickId, advalue,
                accessToken, appid, openid,
                unionid);
        // 发送
        UserActionsAddResponse userActionsAddResponse = addUserActions.addUserActions();
        log.info("send addUserActions, orderId: {}, actionType: {}, response: {}", tencentAdvUserrecordSendDTO.getOrderId(), actionType, userActionsAddResponse);
        return userActionsAddResponse;
    }
}
