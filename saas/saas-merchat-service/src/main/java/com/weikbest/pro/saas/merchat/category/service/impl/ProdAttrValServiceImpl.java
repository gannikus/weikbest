package com.weikbest.pro.saas.merchat.category.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.category.entity.ProdAttrVal;
import com.weikbest.pro.saas.merchat.category.mapper.ProdAttrValMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrValDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.ProdAttrValMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdAttrValQO;
import com.weikbest.pro.saas.merchat.category.service.ProdAttrValService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性值表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdAttrValServiceImpl extends ServiceImpl<ProdAttrValMapper, ProdAttrVal> implements ProdAttrValService {

    @Override
    public boolean insert(ProdAttrValDTO prodAttrValDTO) {
        ProdAttrVal prodAttrVal = ProdAttrValMapStruct.INSTANCE.converToEntity(prodAttrValDTO);
        return this.save(prodAttrVal);
    }

    @Override
    public boolean updateById(Long id, ProdAttrValDTO prodAttrValDTO) {
        ProdAttrVal prodAttrVal = this.findById(id);
        ProdAttrValMapStruct.INSTANCE.copyProperties(prodAttrValDTO, prodAttrVal);
        prodAttrVal.setId(id);
        return this.updateById(prodAttrVal);
    }

    @Override
    public ProdAttrVal findById(Long id) {
        ProdAttrVal prodAttrVal = this.getById(id);
        if (ObjectUtil.isNull(prodAttrVal)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodAttrVal;
    }

    @Override
    public IPage<ProdAttrVal> queryPage(ProdAttrValQO prodAttrValQO, PageReq pageReq) {
        QueryWrapper<ProdAttrVal> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodAttrValQO.getAttrValType())) {
            wrapper.eq(ProdAttrVal.ATTR_VAL_TYPE, prodAttrValQO.getAttrValType());
        }
        if (StrUtil.isNotBlank(prodAttrValQO.getAttrVal())) {
            wrapper.eq(ProdAttrVal.ATTR_VAL, prodAttrValQO.getAttrVal());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
