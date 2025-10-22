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
import com.weikbest.pro.saas.merchat.prod.entity.ProdSku;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdSkuMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdSkuDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdSkuMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdSkuQO;
import com.weikbest.pro.saas.merchat.prod.service.ProdSkuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品销售属性表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdSkuServiceImpl extends ServiceImpl<ProdSkuMapper, ProdSku> implements ProdSkuService {

    @Override
    public boolean insert(ProdSkuDTO prodSkuDTO) {
        ProdSku prodSku = ProdSkuMapStruct.INSTANCE.converToEntity(prodSkuDTO);
        return this.save(prodSku);
    }

    @Override
    public boolean updateById(Long id, ProdSkuDTO prodSkuDTO) {
        ProdSku prodSku = this.findById(id);
        ProdSkuMapStruct.INSTANCE.copyProperties(prodSkuDTO, prodSku);
        prodSku.setId(id);
        return this.updateById(prodSku);
    }

    @Override
    public ProdSku findById(Long id) {
        ProdSku prodSku = this.getById(id);
        if (ObjectUtil.isNull(prodSku)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodSku;
    }

    @Override
    public IPage<ProdSku> queryPage(ProdSkuQO prodSkuQO, PageReq pageReq) {
        QueryWrapper<ProdSku> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodSkuQO.getSkuTitle())) {
            wrapper.eq(ProdSku.SKU_TITLE, prodSkuQO.getSkuTitle());
        }
        if (StrUtil.isNotBlank(prodSkuQO.getSellPoint())) {
            wrapper.eq(ProdSku.SELL_POINT, prodSkuQO.getSellPoint());
        }
        if (StrUtil.isNotBlank(prodSkuQO.getSkuConnAttrIds())) {
            wrapper.eq(ProdSku.SKU_CONN_ATTR_IDS, prodSkuQO.getSkuConnAttrIds());
        }
        if (StrUtil.isNotBlank(prodSkuQO.getSkuConnAttrNames())) {
            wrapper.eq(ProdSku.SKU_CONN_ATTR_NAMES, prodSkuQO.getSkuConnAttrNames());
        }
        if (StrUtil.isNotBlank(prodSkuQO.getSkuConnAttrValues())) {
            wrapper.eq(ProdSku.SKU_CONN_ATTR_VALUES, prodSkuQO.getSkuConnAttrValues());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
