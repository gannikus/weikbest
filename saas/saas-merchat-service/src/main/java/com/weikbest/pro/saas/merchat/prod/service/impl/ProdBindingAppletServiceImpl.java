package com.weikbest.pro.saas.merchat.prod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBindingApplet;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdBindingAppletMapper;
import com.weikbest.pro.saas.merchat.prod.service.ProdBindingAppletService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: weikbest
 * @description: ProdBindingApplet服务类
 * @author: Mr.Wang
 * @create: 2023-06-30 10:45
 **/
@Service
@SuppressWarnings("all")
public class ProdBindingAppletServiceImpl  extends ServiceImpl<ProdBindingAppletMapper, ProdBindingApplet> implements ProdBindingAppletService {


    @Override
    @Transactional
    public void prodIdRelevancyAppId(List<Long> prodIds, List<String> appIds, Long categoryId) {
        List<ProdBindingApplet> applets = new ArrayList<>();
        for (Long prodId : prodIds) {
            for (String appId : appIds) {
                ProdBindingApplet applet = new ProdBindingApplet();
                applet.setProdId(prodId);
                applet.setAppId(appId);
                applet.setCategoryId(categoryId);
                applets.add(applet);
            }
        }
        this.remove(new LambdaQueryWrapper<ProdBindingApplet>().eq(ProdBindingApplet::getCategoryId , categoryId).in(ProdBindingApplet::getProdId , prodIds));
        this.saveBatch(applets);
    }


    @Override
    public List<ProdBindingApplet> selectByProdIds(List<Long> prodIds, Long categoryId) {
        List<ProdBindingApplet> bindingApplets = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(prodIds)){
            bindingApplets.addAll(Optional.ofNullable(this.list(new LambdaQueryWrapper<ProdBindingApplet>().eq(ProdBindingApplet::getCategoryId , categoryId).in(ProdBindingApplet::getProdId , prodIds))).orElseGet(ArrayList::new));
        }
        return bindingApplets;
    }


    @Override
    public void removeByProdIds(List<Long> prodIds, Long categoryId) {
        this.remove(new LambdaQueryWrapper<ProdBindingApplet>().eq(ProdBindingApplet::getCategoryId , categoryId).in(ProdBindingApplet::getProdId , prodIds));
    }

}
