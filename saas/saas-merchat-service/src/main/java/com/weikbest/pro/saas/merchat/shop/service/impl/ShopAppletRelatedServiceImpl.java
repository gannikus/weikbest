package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.shop.entity.ShopAppletRelated;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopAppletRelatedMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopRelatedAppletDTO;
import com.weikbest.pro.saas.merchat.shop.service.ShopAppletRelatedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
* @author Administrator
* @description 针对表【t_mmdm_shop_applet_related(店铺小程序关联关系表)】的数据库操作Service实现
* @createDate 2023-06-29 13:57:30
*/
@Service
public class ShopAppletRelatedServiceImpl extends ServiceImpl<ShopAppletRelatedMapper, ShopAppletRelated>
    implements ShopAppletRelatedService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void relateApplet(ShopRelatedAppletDTO shopRelatedAppletDTO) {
        if (CollectionUtil.isEmpty(shopRelatedAppletDTO.getAppletIds())){
            this.remove(new QueryWrapper<ShopAppletRelated>().lambda().eq(ShopAppletRelated::getShopId, shopRelatedAppletDTO.getShopId()));
        }else {
            //先删除绑定
            this.remove(new QueryWrapper<ShopAppletRelated>().lambda().eq(ShopAppletRelated::getShopId, shopRelatedAppletDTO.getShopId()));

            ArrayList<ShopAppletRelated> list = new ArrayList<>();

            shopRelatedAppletDTO.getAppletIds().forEach(appletId ->{
                ShopAppletRelated shopAppletRelated = new ShopAppletRelated();
                shopAppletRelated.setId(GenerateIDUtil.nextId());
                shopAppletRelated.setShopId(shopRelatedAppletDTO.getShopId());
                shopAppletRelated.setAppletId(appletId);
                list.add(shopAppletRelated);
            });

            this.saveBatch(list);
        }


    }
}




