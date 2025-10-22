package com.weikbest.pro.saas.merchat.prod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品销售套餐表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdComboMapper extends BaseMapper<ProdCombo> {

    /**
     * 根据商品Id和套餐类型查询
     * @param prodId
     * @param setMealType
     * @return
     */
    List<Long> getIdByProdIdAndSetMealType(@Param("prodId") Long prodId, @Param("setMealType") Integer setMealType);

}
