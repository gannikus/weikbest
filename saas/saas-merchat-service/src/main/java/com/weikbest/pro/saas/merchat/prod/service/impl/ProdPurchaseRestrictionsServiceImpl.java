package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.merchat.prod.entity.ProdPurchaseRestrictions;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdPurchaseRestrictionsDto;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdPurchaseRestrictionsMapStruct;
import com.weikbest.pro.saas.merchat.prod.service.ProdPurchaseRestrictionsService;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdPurchaseRestrictionsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【t_mmdm_prod_purchase_restrictions(商品限购设置表)】的数据库操作Service实现
* @createDate 2023-06-15 11:33:43
*/
@Service
public class ProdPurchaseRestrictionsServiceImpl extends ServiceImpl<ProdPurchaseRestrictionsMapper, ProdPurchaseRestrictions>
    implements ProdPurchaseRestrictionsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateWithProd(Long prodId, ProdPurchaseRestrictionsDto prodPurchaseRestrictionsDto) {
        if (ObjectUtil.isNotNull(prodPurchaseRestrictionsDto)){
            ProdPurchaseRestrictions prodPurchaseRestrictions = ProdPurchaseRestrictionsMapStruct.INSTANCE.convertDto(prodPurchaseRestrictionsDto);
            prodPurchaseRestrictions.setProdId(prodId);
            this.saveOrUpdate(prodPurchaseRestrictions);
        }
    }
}




