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
import com.weikbest.pro.saas.sys.param.entity.PrivacyPolicy;
import com.weikbest.pro.saas.sys.param.mapper.PrivacyPolicyMapper;
import com.weikbest.pro.saas.sys.param.module.dto.PrivacyPolicyDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.PrivacyPolicyMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.PrivacyPolicyQO;
import com.weikbest.pro.saas.sys.param.service.PrivacyPolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统隐私声明表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-23
 */
@Service
public class PrivacyPolicyServiceImpl extends ServiceImpl<PrivacyPolicyMapper, PrivacyPolicy> implements PrivacyPolicyService {

    /**
     * 系统隐私声明表ID
     */
    private static final Long PRIVACY_POLICY_ID = 1010L;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(PrivacyPolicyDTO privacyPolicyDTO) {
        PrivacyPolicy privacyPolicy = PrivacyPolicyMapStruct.INSTANCE.converToEntity(privacyPolicyDTO);
        privacyPolicy.setId(PRIVACY_POLICY_ID);
        return this.save(privacyPolicy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, PrivacyPolicyDTO privacyPolicyDTO) {
        PrivacyPolicy privacyPolicy = this.findById(id);
        PrivacyPolicyMapStruct.INSTANCE.copyProperties(privacyPolicyDTO, privacyPolicy);
        privacyPolicy.setId(id);
        return this.updateById(privacyPolicy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(PrivacyPolicyDTO privacyPolicyDTO) {
        PrivacyPolicy privacyPolicy = this.getById(PRIVACY_POLICY_ID);
        if (ObjectUtil.isEmpty(privacyPolicy)) {
            // 新增
            return insert(privacyPolicyDTO);
        } else {
            // 更新
            return updateById(PRIVACY_POLICY_ID, privacyPolicyDTO);
        }
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public PrivacyPolicy findById(Long id) {
        PrivacyPolicy privacyPolicy = this.getById(id);
        if (ObjectUtil.isNull(privacyPolicy)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return privacyPolicy;
    }

    @Override
    public PrivacyPolicy findPrivacyPolicy() {
        return this.getById(PRIVACY_POLICY_ID);
    }

    @Override
    public IPage<PrivacyPolicy> queryPage(PrivacyPolicyQO privacyPolicyQO, PageReq pageReq) {
        QueryWrapper<PrivacyPolicy> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(privacyPolicyQO.getPrivacyPolicy())) {
            wrapper.eq(PrivacyPolicy.PRIVACY_POLICY, privacyPolicyQO.getPrivacyPolicy());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
