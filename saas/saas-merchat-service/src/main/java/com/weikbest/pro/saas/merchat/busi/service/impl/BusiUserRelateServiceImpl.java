package com.weikbest.pro.saas.merchat.busi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUserRelate;
import com.weikbest.pro.saas.merchat.busi.mapper.BusiUserRelateMapper;
import com.weikbest.pro.saas.merchat.busi.module.dto.BusiUserRelateDTO;
import com.weikbest.pro.saas.merchat.busi.module.mapstruct.BusiUserRelateMapStruct;
import com.weikbest.pro.saas.merchat.busi.module.qo.BusiUserRelateQO;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserRelateService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商户与商户账户关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-23
 */
@Service
public class BusiUserRelateServiceImpl extends ServiceImpl<BusiUserRelateMapper, BusiUserRelate> implements BusiUserRelateService {

    @Override
    public boolean insert(BusiUserRelateDTO busiUserRelateDTO) {
        BusiUserRelate busiUserRelate = BusiUserRelateMapStruct.INSTANCE.converToEntity(busiUserRelateDTO);
        return this.save(busiUserRelate);
    }

    @Override
    public boolean updateById(Long id, BusiUserRelateDTO busiUserRelateDTO) {
        BusiUserRelate busiUserRelate = this.findById(id);
        BusiUserRelateMapStruct.INSTANCE.copyProperties(busiUserRelateDTO, busiUserRelate);
        busiUserRelate.setId(id);
        return this.updateById(busiUserRelate);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public BusiUserRelate findById(Long id) {
        BusiUserRelate busiUserRelate = this.getById(id);
        if (ObjectUtil.isNull(busiUserRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return busiUserRelate;
    }

    @Override
    public IPage<BusiUserRelate> queryPage(BusiUserRelateQO busiUserRelateQO, PageReq pageReq) {
        QueryWrapper<BusiUserRelate> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public BusiUserRelate findMainByBusinessId(Long businessId) {
        BusiUserRelate busiUserRelate = this.getOne(new QueryWrapper<BusiUserRelate>().eq(BusiUserRelate.BUSINESS_ID, businessId).eq(BusiUserRelate.IS_MAIN_USER, DictConstant.Whether.yes.getCode()));
        if (ObjectUtil.isNull(busiUserRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return busiUserRelate;
    }

    @Override
    public BusiUserRelate getMainByBusinessUserId(Long busiUserId) {
        return this.getOne(new QueryWrapper<BusiUserRelate>().eq(BusiUserRelate.BUSINESS_USER_ID, busiUserId).eq(BusiUserRelate.IS_MAIN_USER, DictConstant.Whether.yes.getCode()));
    }

    @Override
    public BusiUserRelate getByBusinessIdAndBusinessUserId(Long businessId, Long busiUserId) {
        return this.getOne(new QueryWrapper<BusiUserRelate>().eq(BusiUserRelate.BUSINESS_ID, businessId).eq(BusiUserRelate.BUSINESS_USER_ID, busiUserId));
    }

    @Override
    public List<BusiUserRelate> queryByBusinessUserId(Long busiUserId) {
        return this.list(new QueryWrapper<BusiUserRelate>().eq(BusiUserRelate.BUSINESS_USER_ID, busiUserId));
    }
}
