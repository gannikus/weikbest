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
import com.weikbest.pro.saas.merchat.category.entity.ProdBrand;
import com.weikbest.pro.saas.merchat.category.mapper.ProdBrandMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.ProdBrandDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.ProdBrandMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.ProdBrandQO;
import com.weikbest.pro.saas.merchat.category.service.ProdBrandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品品牌表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdBrandServiceImpl extends ServiceImpl<ProdBrandMapper, ProdBrand> implements ProdBrandService {

    @Override
    public boolean insert(ProdBrandDTO prodBrandDTO) {
        ProdBrand prodBrand = ProdBrandMapStruct.INSTANCE.converToEntity(prodBrandDTO);
        return this.save(prodBrand);
    }

    @Override
    public boolean updateById(Long id, ProdBrandDTO prodBrandDTO) {
        ProdBrand prodBrand = this.findById(id);
        ProdBrandMapStruct.INSTANCE.copyProperties(prodBrandDTO, prodBrand);
        prodBrand.setId(id);
        return this.updateById(prodBrand);
    }

    @Override
    public ProdBrand findById(Long id) {
        ProdBrand prodBrand = this.getById(id);
        if (ObjectUtil.isNull(prodBrand)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodBrand;
    }

    @Override
    public IPage<ProdBrand> queryPage(ProdBrandQO prodBrandQO, PageReq pageReq) {
        QueryWrapper<ProdBrand> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodBrandQO.getNumber())) {
            wrapper.eq(ProdBrand.NUMBER, prodBrandQO.getNumber());
        }
        if (StrUtil.isNotBlank(prodBrandQO.getName())) {
            wrapper.eq(ProdBrand.NAME, prodBrandQO.getName());
        }
        if (StrUtil.isNotBlank(prodBrandQO.getBrandLogo())) {
            wrapper.eq(ProdBrand.BRAND_LOGO, prodBrandQO.getBrandLogo());
        }
        if (StrUtil.isNotBlank(prodBrandQO.getDescription())) {
            wrapper.eq(ProdBrand.DESCRIPTION, prodBrandQO.getDescription());
        }
        if (StrUtil.isNotBlank(prodBrandQO.getDataStatus())) {
            wrapper.eq(ProdBrand.DATA_STATUS, prodBrandQO.getDataStatus());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
