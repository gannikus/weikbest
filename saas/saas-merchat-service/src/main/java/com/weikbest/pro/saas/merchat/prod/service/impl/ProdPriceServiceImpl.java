package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.entity.ProdPrice;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdPriceMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPriceDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdPriceMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdPriceQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdPriceVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdPriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品价格信息拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdPriceServiceImpl extends ServiceImpl<ProdPriceMapper, ProdPrice> implements ProdPriceService {

    @Override
    public boolean insert(ProdPriceDTO prodPriceDTO) {
        ProdPrice prodPrice = ProdPriceMapStruct.INSTANCE.converToEntity(prodPriceDTO);
        return this.save(prodPrice);
    }

    @Override
    public boolean updateById(Long id, ProdPriceDTO prodPriceDTO) {
        ProdPrice prodPrice = this.findById(id);
        ProdPriceMapStruct.INSTANCE.copyProperties(prodPriceDTO, prodPrice);
        prodPrice.setId(id);
        return this.updateById(prodPrice);
    }

    @Override
    public ProdPrice findById(Long id) {
        ProdPrice prodPrice = this.getById(id);
        if (ObjectUtil.isNull(prodPrice)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodPrice;
    }

    @Override
    public IPage<ProdPrice> queryPage(ProdPriceQO prodPriceQO, PageReq pageReq) {
        QueryWrapper<ProdPrice> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateByProdComboWithProd(Long prodId, ProdCombo prodCombo, ProdPriceDTO prodPriceDTO) {
        ProdPrice converToEntity = ProdPriceMapStruct.INSTANCE.converToEntity(prodPriceDTO, prodCombo, prodId);

        // 根据商品ID查询，存在则更新，不存在则为新增
        ProdPrice prodPrice = this.getById(prodId);
        if (ObjectUtil.isEmpty(prodPrice)) {
            this.save(converToEntity);
        } else {
            ProdPriceMapStruct.INSTANCE.copyProperties(converToEntity, prodPrice);
            prodPrice.setId(prodId);
            this.updateById(prodPrice);
        }
    }

    @Override
    public ProdPriceVO getVOById(Long id) {
        ProdPrice prodPrice = this.findById(id);
        return ProdPriceMapStruct.INSTANCE.converToVO(prodPrice);
    }
}
