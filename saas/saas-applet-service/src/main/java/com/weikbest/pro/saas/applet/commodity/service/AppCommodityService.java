package com.weikbest.pro.saas.applet.commodity.service;


import com.weikbest.pro.saas.applet.commodity.module.qo.AppProdQO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AddParametersVo;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdDecFloorVO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdDetailVO;
import com.weikbest.pro.saas.applet.commodity.module.vo.AppProdLeftSlideVO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;

import java.util.List;

public interface AppCommodityService {

    /**
     * 获取商品列表
     * @param appProdQO
     * @param pageReq
     * @return
     */
    List<AppProdDTO> queryPageCommodity(AppProdQO appProdQO, PageReq pageReq);

    /**
     * 获取商品列表-聚合页
     * @param appProdQO
     * @param pageReq
     * @return
     */
    List<AppProdDTO> queryPageCommodityByAggregationPage(AppProdQO appProdQO, PageReq pageReq);

    /**
     * 获取商品详情信息
     * @param prodId
     * @param click
     * @return
     */
    AppProdDetailVO findProdDetail(Long prodId, Integer click);

    /**
     * 获取左滑设置信息
     * @param prodId
     * @return
     */
//    @Deprecated
    AppProdLeftSlideVO findProdLeftSlide(Long prodId);

    /**
     * 获取装修落地页信息
     * @param prodId
     * @return
     */
    AppProdDecFloorVO findProdDecFloor(Long prodId);

    /**
     * 根据商品ID查询装修落地页信息
     * @param id
     * @return
     */
    AddParametersVo addParameters(Long id);

    /**
     * 根据商品Id获取商品关联随手购信息
     * @param id
     * @return
     */
    AppProdDetailVO shoppingProdById(Long id);
}
