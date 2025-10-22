package com.weikbest.pro.saas.merchat.prod.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.merchat.prod.entity.ProdServiceCommitment;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdServiceCommitmentMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdFeightMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdServiceCommitmentMapStruct;
import com.weikbest.pro.saas.merchat.prod.service.ProdServiceCommitmentService;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 商品服务承诺表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-03-22
 */
@Service
public class ProdServiceCommitmentServiceImpl extends ServiceImpl<ProdServiceCommitmentMapper, ProdServiceCommitment> implements ProdServiceCommitmentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateWithProd(Long prodId, ProdServiceCommitmentDTO prodServiceCommitmentDTO) {
        if (ObjectUtil.isNull(prodServiceCommitmentDTO)){
            return;
        }
        ProdServiceCommitment prodServiceCommitment = ProdServiceCommitmentMapStruct.INSTANCE.converToEntity(prodServiceCommitmentDTO);
        // 根据商品ID查询，存在则更新，不存在则为新增
        ProdServiceCommitment byId = this.getOne(new QueryWrapper<ProdServiceCommitment>().lambda().eq(ProdServiceCommitment::getProdId,prodId));
        if (ObjectUtil.isEmpty(byId)) {
            prodServiceCommitment.setProdId(prodId);
            this.save(prodServiceCommitment);
        } else {
            Long id = byId.getId();
            ProdFeightMapStruct.INSTANCE.copyProperties(prodServiceCommitment, byId);
            byId.setProdId(prodId);
            byId.setId(id);
            this.updateById(byId);
        }
    }

    @Override
    public ProdServiceCommitmentDTO getByProdId(Long id) {
        QueryWrapper<ProdServiceCommitment> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ProdServiceCommitment::getProdId,id);
        ProdServiceCommitment one = this.getOne(wrapper);
        if (one == null) {
           return null;
        }
        ProdServiceCommitmentDTO prodServiceCommitmentDTO = ProdServiceCommitmentMapStruct.INSTANCE.converToDTO(one);
        ArrayList<String> list = new ArrayList<>();
        if (StringUtils.isEmpty(prodServiceCommitmentDTO.getAfterSalesService())){
            prodServiceCommitmentDTO.setServiceList(list);
            return prodServiceCommitmentDTO;
        }
        String[] split = prodServiceCommitmentDTO.getAfterSalesService().split(",");
        List<String> list1 = Arrays.asList(split);
        prodServiceCommitmentDTO.setServiceList(list1);
        return  prodServiceCommitmentDTO;
    }


    @Override
    public ProdServiceCommitment selectByProdId(Long id){
        return Optional.ofNullable(this.getOne(new LambdaQueryWrapper<ProdServiceCommitment>().eq(ProdServiceCommitment::getProdId , id))).orElseGet(ProdServiceCommitment::new);
    }
}
