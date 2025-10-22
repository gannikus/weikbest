package com.weikbest.pro.saas.applet.shop.service;


import com.weikbest.pro.saas.applet.shop.module.dto.AppShopVisitDTO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopListVO;
import com.weikbest.pro.saas.applet.shop.module.vo.AppShopVO;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;

import java.util.List;


public interface AppShopService {

    /**
     * 获取店铺详情
     *
     * @return
     */
    AppShopVO findshop(Long id,Long customerId);

    /**
     * 获取我关注的店铺列表信息
     * @param pageReq
     * @return
     */
    List<AppShopListVO> queryShopListByCustomerId(PageReq pageReq);


    /**
     * 用户取消或关注店铺
     * @param shopId
     * @param isAttent
     * @return
     */
    Boolean doAttent(Long shopId,String isAttent);


    /**
     * 保存店铺访问数
     * @param appShopVisitDTO
     * @return
     */
    Boolean saveShopVisit(AppShopVisitDTO appShopVisitDTO);


    /**
     * 保存店铺商品访问数
     * @param appShopVisitDTO
     * @return
     */
    Boolean saveShopProdVisit(AppShopVisitDTO appShopVisitDTO);

}
