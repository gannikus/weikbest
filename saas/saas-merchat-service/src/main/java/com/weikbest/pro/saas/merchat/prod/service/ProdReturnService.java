package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdReturn;

/**
 * @author Mr.Wang
 */
public interface ProdReturnService extends IService<ProdReturn> {

    /**
     * 根据商品Id删除商品返回页
     * @param prodId
     * @return
     */
    int deleteByProdId(Long prodId);

    /**
     * 根据商品Id和当前返回次数获取下一个返回页的数据
     *
     * @param prodId
     * @param clicks
     * @param isSkip
     * @return
     */
    ProdReturn getReturnByProdAndClick(Long prodId, Integer[] clicks, boolean isSkip);
}
