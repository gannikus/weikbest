package com.weikbest.pro.saas.merchat.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weikbest.pro.saas.merchat.datacenter.module.qo.DataCenterQO;
import com.weikbest.pro.saas.merchat.shop.entity.ShopVisit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺访问表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2023-02-04
 */
public interface ShopVisitMapper extends BaseMapper<ShopVisit> {

    /**
     * 查询店铺访问量数据
     *
     * @param shopId
     * @param dataCenterQO
     * @return
     */
    List<ShopVisit> queryDataCenter(@Param("shopId") Long shopId, @Param("dataCenter") DataCenterQO dataCenterQO);
}
