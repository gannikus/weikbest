package com.weikbest.pro.saas.sysmerchat.category.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.AppletProdCategoryRelateMapStruct;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryRelateService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdBindingApplet;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdListVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdBindingAppletService;
import com.weikbest.pro.saas.merchat.prod.service.ProdService;
import com.weikbest.pro.saas.sysmerchat.category.module.qo.SysAppletProdCategoryRelateQO;
import com.weikbest.pro.saas.sysmerchat.category.service.SysAppletProdCategoryRelateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wisdomelon
 * @project weikbest
 * @jdk 1.8
 * @since 2022/10/2
 */
@Slf4j
@Service
public class SysAppletProdCategoryRelateServiceImpl implements SysAppletProdCategoryRelateService {

    @Resource
    private ProdService prodService;

    @Resource
    private AppletProdCategoryRelateService appletProdCategoryRelateService;

    @Autowired
    private ProdBindingAppletService prodBindingAppletService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean associateProdList(List<AppletProdCategoryRelateDTO> appletProdCategoryRelateDTOList) {
        List<String> appIds = appletProdCategoryRelateDTOList.get(0).getAppIds();
        // 类目和商品分组
        Map<Long, List<AppletProdCategoryRelateDTO>> appletProdCategoryIdListMap = appletProdCategoryRelateDTOList.stream().collect(Collectors.groupingBy(AppletProdCategoryRelateDTO::getAppletProdCategotyId));
        // 删除每个分组对应的关联数据
        appletProdCategoryIdListMap.forEach((appletProdCategoryId, entryValue) -> {
            List<Long> prodIdList = entryValue.stream().map(AppletProdCategoryRelateDTO::getProdId).collect(Collectors.toList());
            appletProdCategoryRelateService.remove(new QueryWrapper<AppletProdCategoryRelate>().eq(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, appletProdCategoryId).in(AppletProdCategoryRelate.PROD_ID, prodIdList));

            //添加商品关联小程序和类目
            prodBindingAppletService.prodIdRelevancyAppId(prodIdList , appIds , appletProdCategoryId);
        });

        List<AppletProdCategoryRelate> appletProdCategoryRelateList = appletProdCategoryRelateDTOList.stream().map(AppletProdCategoryRelateMapStruct.INSTANCE::converToEntity).collect(Collectors.toList());
        return appletProdCategoryRelateService.saveBatch(appletProdCategoryRelateList);
    }

    @Override
    @Transactional
    public void deleteByAppletProdCategotyIdAndProdId(Long appletProdCategotyId, List<Long> prodIdList) {
        appletProdCategoryRelateService.remove(new QueryWrapper<AppletProdCategoryRelate>().eq(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, appletProdCategotyId).in(AppletProdCategoryRelate.PROD_ID, prodIdList));
        //删除商品对应的小程序访问
        prodBindingAppletService.removeByProdIds(prodIdList , appletProdCategotyId);
    }

    @Override
    public IPage<ProdListVO> queryPage(SysAppletProdCategoryRelateQO sysAppletProdCategoryRelateQO, PageReq pageReq) {
        if (sysAppletProdCategoryRelateQO.getAppletProdCategotyId() != null && sysAppletProdCategoryRelateQO.getAppletProdCategotyId() > WeikbestConstant.ZERO_LONG) {
            // 先查询关联的商品ID
            List<AppletProdCategoryRelate> appletProdCategoryRelateList = appletProdCategoryRelateService.list(new QueryWrapper<AppletProdCategoryRelate>().eq(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, sysAppletProdCategoryRelateQO.getAppletProdCategotyId()));
            List<Long> prodIdList = appletProdCategoryRelateList.stream().map(AppletProdCategoryRelate::getProdId).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(prodIdList)) {
                return new Page<>(pageReq.getPage(), pageReq.getLimit(), WeikbestConstant.ZERO_INT);
            }
            sysAppletProdCategoryRelateQO.setIds(prodIdList);
        }

        IPage<ProdListVO> listVOIPage = prodService.queryPage(sysAppletProdCategoryRelateQO, pageReq);
        if (CollectionUtil.isNotEmpty(listVOIPage.getRecords())){
            List<ProdBindingApplet> bindingApplets = prodBindingAppletService.selectByProdIds(sysAppletProdCategoryRelateQO.getIds() , sysAppletProdCategoryRelateQO.getAppletProdCategotyId());
            Map<Long, List<String>> prodIdMapAppIds = bindingApplets.stream().collect(Collectors.groupingBy(ProdBindingApplet::getProdId, Collectors.mapping(ProdBindingApplet::getAppId, Collectors.toList())));
            for (ProdListVO record : listVOIPage.getRecords()) {
                if (prodIdMapAppIds.containsKey(record.getId())){
                    record.setAppIds(prodIdMapAppIds.get(record.getId()));
                }
            }
        }
        return listVOIPage;
    }
}
