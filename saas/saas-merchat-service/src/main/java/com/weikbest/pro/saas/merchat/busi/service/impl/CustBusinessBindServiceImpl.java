package com.weikbest.pro.saas.merchat.busi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.busi.entity.CustBusinessBind;
import com.weikbest.pro.saas.merchat.busi.mapper.CustBusinessBindMapper;
import com.weikbest.pro.saas.merchat.busi.module.dto.CustBusinessBindDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.CustBusinessBindMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.qo.CustBusinessBindQO;
import com.weikbest.pro.saas.merchat.busi.service.CustBusinessBindService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分账商户绑定表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-04
 */
@Service
public class CustBusinessBindServiceImpl extends ServiceImpl<CustBusinessBindMapper, CustBusinessBind> implements CustBusinessBindService {

    @Override
    public boolean insert(CustBusinessBindDTO custBusinessBindDTO) {
        CustBusinessBind custBusinessBind = CustBusinessBindMapStruct.INSTANCE.converToEntity(custBusinessBindDTO);
        return this.save(custBusinessBind);
    }

    @Override
    public boolean updateById(Long id, CustBusinessBindDTO custBusinessBindDTO) {
        CustBusinessBind custBusinessBind = this.findById(id);
        CustBusinessBindMapStruct.INSTANCE.copyProperties(custBusinessBindDTO, custBusinessBind);
        custBusinessBind.setId(id);
        return this.updateById(custBusinessBind);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public CustBusinessBind findById(Long id) {
        CustBusinessBind custBusinessBind = this.getById(id);
        if (ObjectUtil.isNull(custBusinessBind)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return custBusinessBind;
    }

    @Override
    public IPage<CustBusinessBind> queryPage(CustBusinessBindQO custBusinessBindQO, PageReq pageReq) {
        QueryWrapper<CustBusinessBind> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(custBusinessBindQO.getNumber())) {
            wrapper.eq(CustBusinessBind.NUMBER, custBusinessBindQO.getNumber());
        }
        if (StrUtil.isNotBlank(custBusinessBindQO.getAppId())) {
            wrapper.eq(CustBusinessBind.APP_ID, custBusinessBindQO.getAppId());
        }
        if (StrUtil.isNotBlank(custBusinessBindQO.getValidityDay())) {
            wrapper.eq(CustBusinessBind.VALIDITY_DAY, custBusinessBindQO.getValidityDay());
        }
        if (StrUtil.isNotBlank(custBusinessBindQO.getBindStatus())) {
            wrapper.eq(CustBusinessBind.BIND_STATUS, custBusinessBindQO.getBindStatus());
        }
        if (StrUtil.isNotBlank(custBusinessBindQO.getDataStatus())) {
            wrapper.eq(CustBusinessBind.DATA_STATUS, custBusinessBindQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
