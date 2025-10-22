package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUsersource;
import com.weikbest.pro.saas.sys.param.mapper.TencentAdvUsersourceMapper;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUsersourceDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.TencentAdvUsersourceMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvUsersourceQO;
import com.weikbest.pro.saas.sys.param.service.TencentAdvUsersourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据源表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
@Service
public class TencentAdvUsersourceServiceImpl extends ServiceImpl<TencentAdvUsersourceMapper, TencentAdvUsersource> implements TencentAdvUsersourceService {

    @Override
    public boolean insert(TencentAdvUsersourceDTO tencentAdvUsersourceDTO) {
        TencentAdvUsersource tencentAdvUsersource = TencentAdvUsersourceMapStruct.INSTANCE.converToEntity(tencentAdvUsersourceDTO);
        return this.save(tencentAdvUsersource);
    }

    @Override
    public boolean updateById(Long id, TencentAdvUsersourceDTO tencentAdvUsersourceDTO) {
        TencentAdvUsersource tencentAdvUsersource = this.findById(id);
        TencentAdvUsersourceMapStruct.INSTANCE.copyProperties(tencentAdvUsersourceDTO, tencentAdvUsersource);
        tencentAdvUsersource.setId(id);
        return this.updateById(tencentAdvUsersource);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public TencentAdvUsersource findById(Long id) {
        TencentAdvUsersource tencentAdvUsersource = this.getById(id);
        if (ObjectUtil.isNull(tencentAdvUsersource)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvUsersource;
    }

    @Override
    public TencentAdvUsersource findByClientId(Long clientId) {
        TencentAdvUsersource tencentAdvUsersource = this.getOne(new QueryWrapper<TencentAdvUsersource>().eq(TencentAdvUsersource.CLIENT_ID, clientId));
        if (ObjectUtil.isNull(tencentAdvUsersource)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return tencentAdvUsersource;
    }

    @Override
    public IPage<TencentAdvUsersource> queryPage(TencentAdvUsersourceQO tencentAdvUsersourceQO, PageReq pageReq) {
        QueryWrapper<TencentAdvUsersource> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(tencentAdvUsersourceQO.getClientId())) {
            wrapper.eq(TencentAdvUsersource.CLIENT_ID, tencentAdvUsersourceQO.getClientId());
        }
        if (ObjectUtil.isNotNull(tencentAdvUsersourceQO.getAccountId())) {
            wrapper.eq(TencentAdvUsersource.ACCOUNT_ID, tencentAdvUsersourceQO.getAccountId());
        }
        if (ObjectUtil.isNotNull(tencentAdvUsersourceQO.getUserActionSetId())) {
            wrapper.eq(TencentAdvUsersource.USER_ACTION_SET_ID, tencentAdvUsersourceQO.getUserActionSetId());
        }
        if (StrUtil.isNotBlank(tencentAdvUsersourceQO.getName())) {
            wrapper.eq(TencentAdvUsersource.NAME, tencentAdvUsersourceQO.getName());
        }
        if (StrUtil.isNotBlank(tencentAdvUsersourceQO.getType())) {
            wrapper.eq(TencentAdvUsersource.TYPE, tencentAdvUsersourceQO.getType());
        }
        if (StrUtil.isNotBlank(tencentAdvUsersourceQO.getEnableConversionClaim())) {
            wrapper.eq(TencentAdvUsersource.ENABLE_CONVERSION_CLAIM, tencentAdvUsersourceQO.getEnableConversionClaim());
        }
        if (StrUtil.isNotBlank(tencentAdvUsersourceQO.getWechatAppId())) {
            wrapper.eq(TencentAdvUsersource.WECHAT_APP_ID, tencentAdvUsersourceQO.getWechatAppId());
        }
        if (StrUtil.isNotBlank(tencentAdvUsersourceQO.getDescription())) {
            wrapper.eq(TencentAdvUsersource.DESCRIPTION, tencentAdvUsersourceQO.getDescription());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
