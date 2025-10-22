package com.weikbest.pro.saas.merchat.prod.service;

import com.weikbest.pro.saas.merchat.prod.entity.ProdPurchaseRestrictions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPurchaseRestrictionsDto;

/**
* @author Administrator
* @description 针对表【t_mmdm_prod_purchase_restrictions(商品限购设置表)】的数据库操作Service
* @createDate 2023-06-15 11:33:43
*/
public interface ProdPurchaseRestrictionsService extends IService<ProdPurchaseRestrictions> {

    void saveOrUpdateWithProd(Long prodId, ProdPurchaseRestrictionsDto prodPurchaseRestrictionsDto);
}
