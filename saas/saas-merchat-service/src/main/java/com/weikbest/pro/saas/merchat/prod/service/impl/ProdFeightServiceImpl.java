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
import com.weikbest.pro.saas.merchat.prod.entity.ProdFeight;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdFeightMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdFeightDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdFeightMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdFeightQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdFeightVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdFeightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品运费信息表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdFeightServiceImpl extends ServiceImpl<ProdFeightMapper, ProdFeight> implements ProdFeightService {

    @Override
    public boolean insert(ProdFeightDTO prodFeightDTO) {
        ProdFeight prodFeight = ProdFeightMapStruct.INSTANCE.converToEntity(prodFeightDTO);
        return this.save(prodFeight);
    }

    @Override
    public boolean updateById(Long id, ProdFeightDTO prodFeightDTO) {
        ProdFeight prodFeight = this.findById(id);
        ProdFeightMapStruct.INSTANCE.copyProperties(prodFeightDTO, prodFeight);
        prodFeight.setId(id);
        return this.updateById(prodFeight);
    }

    @Override
    public ProdFeight findById(Long id) {
        ProdFeight prodFeight = this.getById(id);
        if (ObjectUtil.isNull(prodFeight)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodFeight;
    }

    @Override
    public IPage<ProdFeight> queryPage(ProdFeightQO prodFeightQO, PageReq pageReq) {
        QueryWrapper<ProdFeight> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodFeightQO.getFeightType())) {
            wrapper.eq(ProdFeight.FEIGHT_TYPE, prodFeightQO.getFeightType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateWithProd(Long prodId, ProdFeightDTO prodFeightDTO) {
        ProdFeight converToEntity = ProdFeightMapStruct.INSTANCE.converToEntity(prodFeightDTO, prodId);

        // 根据商品ID查询，存在则更新，不存在则为新增
        ProdFeight prodFeight = this.getById(prodId);
        if (ObjectUtil.isEmpty(prodFeight)) {
            this.save(converToEntity);
        } else {
            ProdFeightMapStruct.INSTANCE.copyProperties(converToEntity, prodFeight);
            prodFeight.setId(prodId);
            this.updateById(prodFeight);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProdFeight(List<Long> ids, ProdFeightDTO prodFeightDTO) {
        List<ProdFeight> prodFeightList = this.listByIds(ids);
        List<ProdFeight> updateProdFeightList = prodFeightList.stream().map(prodFeight -> {
            ProdFeight updateProdFeight = ProdFeightMapStruct.INSTANCE.converToEntity(prodFeightDTO);
            updateProdFeight.setId(prodFeight.getId());
            return updateProdFeight;
        }).collect(Collectors.toList());

        return this.updateBatchById(updateProdFeightList);
    }

    @Override
    public ProdFeightVO getVOById(Long id) {
        ProdFeight prodFeight = this.findById(id);
        return ProdFeightMapStruct.INSTANCE.converToVO(prodFeight);
    }
}
