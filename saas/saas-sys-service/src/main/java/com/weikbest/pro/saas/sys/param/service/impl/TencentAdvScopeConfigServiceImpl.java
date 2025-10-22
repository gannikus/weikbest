package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.ads.ApiException;
import com.tencent.ads.model.AuthorizerStruct;
import com.tencent.ads.model.OauthTokenResponseData;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.tencent.adv.GetAccessToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.util.json.JsonUtils;
import com.weikbest.pro.saas.sys.common.cache.MemoryService;
import com.weikbest.pro.saas.sys.common.constant.ConfigConstant;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvScopeConfig;
import com.weikbest.pro.saas.sys.param.mapper.TencentAdvScopeConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvScopeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.TencentAdvScopeConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvScopeConfigQO;
import com.weikbest.pro.saas.sys.param.service.ConfigService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvConfigService;
import com.weikbest.pro.saas.sys.param.service.TencentAdvScopeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 腾讯广告主授权腾讯广告第三方应用表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Slf4j
@Service
public class TencentAdvScopeConfigServiceImpl extends ServiceImpl<TencentAdvScopeConfigMapper, TencentAdvScopeConfig> implements TencentAdvScopeConfigService {

    @Resource
    private MemoryService memoryService;

    @Resource
    private TencentAdvConfigService tencentAdvConfigService;

    @Override
    public boolean insert(TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO) {
        TencentAdvScopeConfig tencentAdvScopeConfig = TencentAdvScopeConfigMapStruct.INSTANCE.converToEntity(tencentAdvScopeConfigDTO);
        return this.save(tencentAdvScopeConfig);
    }

    @Override
    public boolean updateById(Long id, TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO) {
        TencentAdvScopeConfig tencentAdvScopeConfig = this.findById(id);
        TencentAdvScopeConfigMapStruct.INSTANCE.copyProperties(tencentAdvScopeConfigDTO, tencentAdvScopeConfig);
        tencentAdvScopeConfig.setId(id);
        return this.updateById(tencentAdvScopeConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public TencentAdvScopeConfig findById(Long id) {
        TencentAdvScopeConfig tencentAdvScopeConfig = this.getById(id);
        if (ObjectUtil.isNull(tencentAdvScopeConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvScopeConfig;
    }

    @Override
    public TencentAdvScopeConfig findByClientId(Long clientId) {
        TencentAdvScopeConfig tencentAdvScopeConfig = this.getOne(new QueryWrapper<TencentAdvScopeConfig>().eq(TencentAdvScopeConfig.CLIENT_ID, clientId));
        if (ObjectUtil.isNull(tencentAdvScopeConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvScopeConfig;
    }

    @Override
    public IPage<TencentAdvScopeConfig> queryPage(TencentAdvScopeConfigQO tencentAdvScopeConfigQO, PageReq pageReq) {
        QueryWrapper<TencentAdvScopeConfig> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(tencentAdvScopeConfigQO.getClientId())) {
            wrapper.eq(TencentAdvScopeConfig.CLIENT_ID, tencentAdvScopeConfigQO.getClientId());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getAuthorizationCode())) {
            wrapper.eq(TencentAdvScopeConfig.AUTHORIZATION_CODE, tencentAdvScopeConfigQO.getAuthorizationCode());
        }
        if (ObjectUtil.isNotNull(tencentAdvScopeConfigQO.getAccountId())) {
            wrapper.eq(TencentAdvScopeConfig.ACCOUNT_ID, tencentAdvScopeConfigQO.getAccountId());
        }
        if (ObjectUtil.isNotNull(tencentAdvScopeConfigQO.getAccountUin())) {
            wrapper.eq(TencentAdvScopeConfig.ACCOUNT_UIN, tencentAdvScopeConfigQO.getAccountUin());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getScopeList())) {
            wrapper.eq(TencentAdvScopeConfig.SCOPE_LIST, tencentAdvScopeConfigQO.getScopeList());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getWechatAccountId())) {
            wrapper.eq(TencentAdvScopeConfig.WECHAT_ACCOUNT_ID, tencentAdvScopeConfigQO.getWechatAccountId());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getAccountRoleType())) {
            wrapper.eq(TencentAdvScopeConfig.ACCOUNT_ROLE_TYPE, tencentAdvScopeConfigQO.getAccountRoleType());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getAccountType())) {
            wrapper.eq(TencentAdvScopeConfig.ACCOUNT_TYPE, tencentAdvScopeConfigQO.getAccountType());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getRoleType())) {
            wrapper.eq(TencentAdvScopeConfig.ROLE_TYPE, tencentAdvScopeConfigQO.getRoleType());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getAccessToken())) {
            wrapper.eq(TencentAdvScopeConfig.ACCESS_TOKEN, tencentAdvScopeConfigQO.getAccessToken());
        }
        if (StrUtil.isNotBlank(tencentAdvScopeConfigQO.getRefreshToken())) {
            wrapper.eq(TencentAdvScopeConfig.REFRESH_TOKEN, tencentAdvScopeConfigQO.getRefreshToken());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getAccessToken(String authorizationCode) {
        TencentAdvConfig tencentAdvConfig = tencentAdvConfigService.findTencentAdvConfig();

        GetAccessToken getAccessToken = new GetAccessToken();
        getAccessToken.init();
        try {
            OauthTokenResponseData oauthTokenResponseData = getAccessToken.getOauthTokenResponseData(tencentAdvConfig.getClientId(), tencentAdvConfig.getClientSecret(), authorizationCode, memoryService.queryConfig(ConfigConstant.TENCENT_ADS_OAUTH_TOKEN));
            // 获取结果入库
            if (ObjectUtil.isNotNull(oauthTokenResponseData)) {
                AuthorizerStruct authorizerInfo = oauthTokenResponseData.getAuthorizerInfo();

                TencentAdvScopeConfig tencentAdvScopeConfig = this.getOne(new QueryWrapper<TencentAdvScopeConfig>().eq(TencentAdvScopeConfig.CLIENT_ID, tencentAdvConfig.getClientId()));
                if(ObjectUtil.isNull(tencentAdvScopeConfig)) {
                    // 新增
                    tencentAdvScopeConfig = new TencentAdvScopeConfig();
                }
                log.info("腾讯广告获取accessToken,获取的OauthTokenResponseData: {}", oauthTokenResponseData);
                // 赋值
                tencentAdvScopeConfig.setAccessToken(oauthTokenResponseData.getAccessToken())
                        .setAccessTokenExpiresIn(NumberUtil.parseInt(oauthTokenResponseData.getAccessTokenExpiresIn() + ""))
                        .setRefreshToken(oauthTokenResponseData.getRefreshToken())
                        .setRefreshTokenExpiresIn(NumberUtil.parseInt(oauthTokenResponseData.getRefreshTokenExpiresIn() + ""))
                        .setAccountId(authorizerInfo.getAccountId())
                        .setAccountType(authorizerInfo.getAccountType().getValue())
                        .setAccountUin(authorizerInfo.getAccountUin())
                        .setScopeList(CollectionUtil.isEmpty(authorizerInfo.getScopeList()) ? JsonUtils.bean2Json(new ArrayList<>()) : JsonUtils.bean2Json(authorizerInfo.getScopeList()))
                        .setWechatAccountId(authorizerInfo.getWechatAccountId())
                        .setAccountRoleType(authorizerInfo.getAccountRoleType().getValue())
                        .setRoleType(authorizerInfo.getRoleType().getValue())
                        .setClientId(tencentAdvConfig.getClientId())
                        .setAuthorizationCode(authorizationCode);
                this.saveOrUpdate(tencentAdvScopeConfig);
                return oauthTokenResponseData.getAccessToken();
            }
            else {
                log.info("腾讯广告获取accessToken,获取的OauthTokenResponseData 为空！");
            }
        } catch (ApiException e) {
            log.error("腾讯广告获取accessToken失败！", e);
        }
        return null;
    }
}
