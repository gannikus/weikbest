package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdStock;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdStockMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdStockDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdStockMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdStockQO;
import com.weikbest.pro.saas.merchat.prod.service.ProdStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存表（本期不用） 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdStockServiceImpl extends ServiceImpl<ProdStockMapper, ProdStock> implements ProdStockService {

    @Override
    public boolean insert(ProdStockDTO prodStockDTO) {
        ProdStock prodStock = ProdStockMapStruct.INSTANCE.converToEntity(prodStockDTO);
        return this.save(prodStock);
    }

    @Override
    public boolean updateById(Long id, ProdStockDTO prodStockDTO) {
        ProdStock prodStock = this.findById(id);
        ProdStockMapStruct.INSTANCE.copyProperties(prodStockDTO, prodStock);
        prodStock.setId(id);
        return this.updateById(prodStock);
    }

    @Override
    public ProdStock findById(Long id) {
        ProdStock prodStock = this.getById(id);
        if (ObjectUtil.isNull(prodStock)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodStock;
    }

    @Override
    public IPage<ProdStock> queryPage(ProdStockQO prodStockQO, PageReq pageReq) {
        QueryWrapper<ProdStock> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
