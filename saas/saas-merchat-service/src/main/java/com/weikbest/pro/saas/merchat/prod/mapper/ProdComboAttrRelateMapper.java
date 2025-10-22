package com.weikbest.pro.saas.merchat.prod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品销售套餐规格属性关联表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdComboAttrRelateMapper extends BaseMapper<ProdComboAttrRelate> {

    List<Long> getIdByProdId(@Param("prodId") Long prodId);

}
