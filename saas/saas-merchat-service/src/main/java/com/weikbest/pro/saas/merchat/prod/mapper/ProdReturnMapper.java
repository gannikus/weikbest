package com.weikbest.pro.saas.merchat.prod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.prod.entity.ProdReturn;

/**
 * @author Mr.Wang
 */
public interface ProdReturnMapper extends BaseMapper<ProdReturn> {

    int deleteByProdId(Long prodId);

}
