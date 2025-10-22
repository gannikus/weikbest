package com.weikbest.pro.saas.merchat.prod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAdvBackAccount;

/**
 * <p>
 * 商品广告回传信息关联广告账户拆分多行表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAdvBackAccountMapper extends BaseMapper<ProdAdvBackAccount> {

    int deleteByProdId(Long prodId);

    int updateByProdId(ProdAdvBackAccount account);
}
