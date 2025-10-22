package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAttrRelate;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdAttrRelateMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdAttrRelateMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAttrRelateQO;
import com.weikbest.pro.saas.merchat.prod.service.ProdAttrRelateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品销售规格属性关联表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdAttrRelateServiceImpl extends ServiceImpl<ProdAttrRelateMapper, ProdAttrRelate> implements ProdAttrRelateService {

    @Override
    public boolean insert(ProdAttrRelateDTO prodAttrRelateDTO) {
        ProdAttrRelate prodAttrRelate = ProdAttrRelateMapStruct.INSTANCE.converToEntity(prodAttrRelateDTO);
        return this.save(prodAttrRelate);
    }

    @Override
    public boolean updateById(Long id, ProdAttrRelateDTO prodAttrRelateDTO) {
        ProdAttrRelate prodAttrRelate = this.findById(id);
        ProdAttrRelateMapStruct.INSTANCE.copyProperties(prodAttrRelateDTO, prodAttrRelate);
        prodAttrRelate.setId(id);
        return this.updateById(prodAttrRelate);
    }

    @Override
    public ProdAttrRelate findById(Long id) {
        ProdAttrRelate prodAttrRelate = this.getById(id);
        if (ObjectUtil.isNull(prodAttrRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodAttrRelate;
    }

    @Override
    public IPage<ProdAttrRelate> queryPage(ProdAttrRelateQO prodAttrRelateQO, PageReq pageReq) {
        QueryWrapper<ProdAttrRelate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodAttrRelateQO.getAttrName())) {
            wrapper.eq(ProdAttrRelate.ATTR_NAME, prodAttrRelateQO.getAttrName());
        }
        if (StrUtil.isNotBlank(prodAttrRelateQO.getAttrValues())) {
            wrapper.eq(ProdAttrRelate.ATTR_VALUES, prodAttrRelateQO.getAttrValues());
        }
        if (StrUtil.isNotBlank(prodAttrRelateQO.getIsValuation())) {
            wrapper.eq(ProdAttrRelate.IS_VALUATION, prodAttrRelateQO.getIsValuation());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
