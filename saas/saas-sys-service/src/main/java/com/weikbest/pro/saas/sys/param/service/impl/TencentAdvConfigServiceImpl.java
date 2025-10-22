package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.ads.ApiException;
import com.tencent.ads.model.OauthTokenResponseData;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.third.tencent.adv.GetAccessToken;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.mapper.TencentAdvConfigMapper;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvConfigDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.TencentAdvConfigMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvConfigQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 腾讯广告第三方应用配置表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Service
public class TencentAdvConfigServiceImpl extends ServiceImpl<TencentAdvConfigMapper, TencentAdvConfig> implements TencentAdvConfigService {

    /** 固定ID */
    private static final Long ID = 1L;

    @Override
    public boolean insert(TencentAdvConfigDTO tencentAdvConfigDTO) {
        TencentAdvConfig tencentAdvConfig = TencentAdvConfigMapStruct.INSTANCE.converToEntity(tencentAdvConfigDTO);
        return this.save(tencentAdvConfig);
    }

    @Override
    public boolean updateById(Long id, TencentAdvConfigDTO tencentAdvConfigDTO) {
        TencentAdvConfig tencentAdvConfig = this.findById(id);
        TencentAdvConfigMapStruct.INSTANCE.copyProperties(tencentAdvConfigDTO, tencentAdvConfig);
        tencentAdvConfig.setId(id);
        return this.updateById(tencentAdvConfig);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public TencentAdvConfig findById(Long id) {
        TencentAdvConfig tencentAdvConfig = this.getById(id);
        if (ObjectUtil.isNull(tencentAdvConfig)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvConfig;
    }

    @Override
    public IPage<TencentAdvConfig> queryPage(TencentAdvConfigQO tencentAdvConfigQO, PageReq pageReq) {
        QueryWrapper<TencentAdvConfig> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(tencentAdvConfigQO.getClientId())) {
            wrapper.eq(TencentAdvConfig.CLIENT_ID, tencentAdvConfigQO.getClientId());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientType())) {
            wrapper.eq(TencentAdvConfig.CLIENT_TYPE, tencentAdvConfigQO.getClientType());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientIconPath())) {
            wrapper.eq(TencentAdvConfig.CLIENT_ICON_PATH, tencentAdvConfigQO.getClientIconPath());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientSecret())) {
            wrapper.eq(TencentAdvConfig.CLIENT_SECRET, tencentAdvConfigQO.getClientSecret());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientName())) {
            wrapper.eq(TencentAdvConfig.CLIENT_NAME, tencentAdvConfigQO.getClientName());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientLevel())) {
            wrapper.eq(TencentAdvConfig.CLIENT_LEVEL, tencentAdvConfigQO.getClientLevel());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientIntroduce())) {
            wrapper.eq(TencentAdvConfig.CLIENT_INTRODUCE, tencentAdvConfigQO.getClientIntroduce());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientTokenUrl())) {
            wrapper.eq(TencentAdvConfig.CLIENT_TOKEN_URL, tencentAdvConfigQO.getClientTokenUrl());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientAccessToken())) {
            wrapper.eq(TencentAdvConfig.CLIENT_ACCESS_TOKEN, tencentAdvConfigQO.getClientAccessToken());
        }
        if (StrUtil.isNotBlank(tencentAdvConfigQO.getClientRefreshToken())) {
            wrapper.eq(TencentAdvConfig.CLIENT_REFRESH_TOKEN, tencentAdvConfigQO.getClientRefreshToken());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public TencentAdvConfig findTencentAdvConfig() {
        return this.findById(ID);
    }

}
