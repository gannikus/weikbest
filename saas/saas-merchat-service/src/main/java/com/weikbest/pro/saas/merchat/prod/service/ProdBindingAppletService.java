package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBindingApplet;

import java.util.List;


public interface ProdBindingAppletService  extends IService<ProdBindingApplet> {

    /**
     * 添加商品关联访问小程序
     *
     * @param prodIds
     * @param appIds
     * @param categoryId
     */
    void prodIdRelevancyAppId(List<Long> prodIds, List<String> appIds, Long categoryId);

    /**
     * 根据商品Id和类型查询类型下的商品关联信息
     *
     * @param prodIds
     * @param categoryId
     * @return
     */
    List<ProdBindingApplet> selectByProdIds(List<Long> prodIds, Long categoryId);

    /**
     * 根据商品Id删除商品对应的小程序访问
     *
     * @param prodIds
     * @param categoryId
     */
    void removeByProdIds(List<Long> prodIds, Long categoryId);
}
