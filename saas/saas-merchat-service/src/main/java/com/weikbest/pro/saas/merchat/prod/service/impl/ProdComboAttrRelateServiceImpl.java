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
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdComboAttrRelateMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdComboAttrRelateMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdComboAttrRelateQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboAttrRelateVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdComboAttrRelateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品销售套餐规格属性关联表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdComboAttrRelateServiceImpl extends ServiceImpl<ProdComboAttrRelateMapper, ProdComboAttrRelate> implements ProdComboAttrRelateService {

    @Override
    public boolean insert(ProdComboAttrRelateDTO prodComboAttrRelateDTO) {
        ProdComboAttrRelate prodComboAttrRelate = ProdComboAttrRelateMapStruct.INSTANCE.converToEntity(prodComboAttrRelateDTO);
        return this.save(prodComboAttrRelate);
    }

    @Override
    public boolean updateById(Long id, ProdComboAttrRelateDTO prodComboAttrRelateDTO) {
        ProdComboAttrRelate prodComboAttrRelate = this.findById(id);
        ProdComboAttrRelateMapStruct.INSTANCE.copyProperties(prodComboAttrRelateDTO, prodComboAttrRelate);
        prodComboAttrRelate.setId(id);
        return this.updateById(prodComboAttrRelate);
    }

    @Override
    public ProdComboAttrRelate findById(Long id) {
        ProdComboAttrRelate prodComboAttrRelate = this.getById(id);
        if (ObjectUtil.isNull(prodComboAttrRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodComboAttrRelate;
    }

    @Override
    public IPage<ProdComboAttrRelate> queryPage(ProdComboAttrRelateQO prodComboAttrRelateQO, PageReq pageReq) {
        QueryWrapper<ProdComboAttrRelate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodComboAttrRelateQO.getAttrName())) {
            wrapper.eq(ProdComboAttrRelate.ATTR_NAME, prodComboAttrRelateQO.getAttrName());
        }
        if (StrUtil.isNotBlank(prodComboAttrRelateQO.getAttrValues())) {
            wrapper.eq(ProdComboAttrRelate.ATTR_VALUES, prodComboAttrRelateQO.getAttrValues());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBatchWithProd(Long prodId, Long prodComboId, List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOList) {
        // 转换实体
        List<ProdComboAttrRelate> prodComboAttrRelateList = prodComboAttrRelateDTOList.stream()
                .map(prodComboAttrRelateDTO -> ProdComboAttrRelateMapStruct.INSTANCE.converToEntity(prodComboAttrRelateDTO, prodId, prodComboId))
                .collect(Collectors.toList());

        this.saveBatch(prodComboAttrRelateList);
    }

    @Override
    public Map<Long, List<ProdComboAttrRelateVO>> queryVOByProdIdGroupByProdCombo(Long prodId) {
        List<ProdComboAttrRelate> prodComboAttrRelateList = this.list(new QueryWrapper<ProdComboAttrRelate>().eq(ProdComboAttrRelate.PROD_ID, prodId));
        return prodComboAttrRelateList.stream().map(ProdComboAttrRelateMapStruct.INSTANCE::converToVO).collect(Collectors.groupingBy(ProdComboAttrRelateVO::getProdComboId));
    }

    public List<Long> getIdByProdId(Long prodId){
        return Optional.ofNullable(this.baseMapper.getIdByProdId(prodId)).orElseGet(ArrayList::new);
    }
}
