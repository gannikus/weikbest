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
import com.weikbest.pro.saas.merchat.category.entity.ProdAttr;
import com.weikbest.pro.saas.merchat.category.mapper.ProdAttrMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdAttrDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.ProdAttrMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdAttrQO;
import com.weikbest.pro.saas.merchat.category.service.ProdAttrService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdAttrServiceImpl extends ServiceImpl<ProdAttrMapper, ProdAttr> implements ProdAttrService {

    @Override
    public boolean insert(ProdAttrDTO prodAttrDTO) {
        ProdAttr prodAttr = ProdAttrMapStruct.INSTANCE.converToEntity(prodAttrDTO);
        return this.save(prodAttr);
    }

    @Override
    public boolean updateById(Long id, ProdAttrDTO prodAttrDTO) {
        ProdAttr prodAttr = this.findById(id);
        ProdAttrMapStruct.INSTANCE.copyProperties(prodAttrDTO, prodAttr);
        prodAttr.setId(id);
        return this.updateById(prodAttr);
    }

    @Override
    public ProdAttr findById(Long id) {
        ProdAttr prodAttr = this.getById(id);
        if (ObjectUtil.isNull(prodAttr)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodAttr;
    }

    @Override
    public IPage<ProdAttr> queryPage(ProdAttrQO prodAttrQO, PageReq pageReq) {
        QueryWrapper<ProdAttr> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodAttrQO.getNumber())) {
            wrapper.eq(ProdAttr.NUMBER, prodAttrQO.getNumber());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getName())) {
            wrapper.eq(ProdAttr.NAME, prodAttrQO.getName());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getAttrImg())) {
            wrapper.eq(ProdAttr.ATTR_IMG, prodAttrQO.getAttrImg());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getAttrType())) {
            wrapper.eq(ProdAttr.ATTR_TYPE, prodAttrQO.getAttrType());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getAttrValType())) {
            wrapper.eq(ProdAttr.ATTR_VAL_TYPE, prodAttrQO.getAttrValType());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getIsSearch())) {
            wrapper.eq(ProdAttr.IS_SEARCH, prodAttrQO.getIsSearch());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getDescription())) {
            wrapper.eq(ProdAttr.DESCRIPTION, prodAttrQO.getDescription());
        }
        if (StrUtil.isNotBlank(prodAttrQO.getDataStatus())) {
            wrapper.eq(ProdAttr.DATA_STATUS, prodAttrQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
