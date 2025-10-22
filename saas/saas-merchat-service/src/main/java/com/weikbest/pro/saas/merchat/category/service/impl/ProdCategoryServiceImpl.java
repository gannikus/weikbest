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
import com.weikbest.pro.saas.merchat.category.entity.ProdCategory;
import com.weikbest.pro.saas.merchat.category.mapper.ProdCategoryMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.ProdCategoryMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdCategoryQO;
import com.weikbest.pro.saas.merchat.category.service.ProdCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdCategoryServiceImpl extends ServiceImpl<ProdCategoryMapper, ProdCategory> implements ProdCategoryService {

    @Override
    public boolean insert(ProdCategoryDTO prodCategoryDTO) {
        ProdCategory prodCategory = ProdCategoryMapStruct.INSTANCE.converToEntity(prodCategoryDTO);
        return this.save(prodCategory);
    }

    @Override
    public boolean updateById(Long id, ProdCategoryDTO prodCategoryDTO) {
        ProdCategory prodCategory = this.findById(id);
        ProdCategoryMapStruct.INSTANCE.copyProperties(prodCategoryDTO, prodCategory);
        prodCategory.setId(id);
        return this.updateById(prodCategory);
    }

    @Override
    public ProdCategory findById(Long id) {
        ProdCategory prodCategory = this.getById(id);
        if (ObjectUtil.isNull(prodCategory)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodCategory;
    }

    @Override
    public IPage<ProdCategory> queryPage(ProdCategoryQO prodCategoryQO, PageReq pageReq) {
        QueryWrapper<ProdCategory> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodCategoryQO.getNumber())) {
            wrapper.eq(ProdCategory.NUMBER, prodCategoryQO.getNumber());
        }
        if (StrUtil.isNotBlank(prodCategoryQO.getName())) {
            wrapper.eq(ProdCategory.NAME, prodCategoryQO.getName());
        }
        if (StrUtil.isNotBlank(prodCategoryQO.getDescription())) {
            wrapper.eq(ProdCategory.DESCRIPTION, prodCategoryQO.getDescription());
        }
        if (StrUtil.isNotBlank(prodCategoryQO.getDataStatus())) {
            wrapper.eq(ProdCategory.DATA_STATUS, prodCategoryQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
