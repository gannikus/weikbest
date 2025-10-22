package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商户店铺表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 店铺列表分页查询
     *
     * @param page
     * @param shopQO
     * @return
     */
    IPage<ShopListVO> queryPage(IPage<ShopListVO> page, @Param("shop") ShopQO shopQO);
}
