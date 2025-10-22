package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.merchat.shop.entity.ShopAppletRelated;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRelatedAppletDTO;

/**
* @author Administrator
* @description 针对表【t_mmdm_shop_applet_related(店铺小程序关联关系表)】的数据库操作Service
* @createDate 2023-06-29 13:57:30
*/
public interface ShopAppletRelatedService extends IService<ShopAppletRelated> {

    /**
     * 店铺关联小程序
     * @param shopRelatedAppletDTO
     */
    void relateApplet(ShopRelatedAppletDTO shopRelatedAppletDTO);
}
