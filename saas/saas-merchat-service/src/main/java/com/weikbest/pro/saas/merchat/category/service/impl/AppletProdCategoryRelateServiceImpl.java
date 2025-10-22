package com.weikbest.pro.saas.merchat.category.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.mapper.AppletProdCategoryRelateMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryRelateDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.AppletProdCategoryRelateMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletProdCategoryRelateQO;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryRelateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序商品类目关联商品表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Service
public class AppletProdCategoryRelateServiceImpl extends ServiceImpl<AppletProdCategoryRelateMapper, AppletProdCategoryRelate> implements AppletProdCategoryRelateService {

    @Override
    public boolean insert(AppletProdCategoryRelateDTO appletProdCategoryRelateDTO) {
        AppletProdCategoryRelate appletProdCategoryRelate = AppletProdCategoryRelateMapStruct.INSTANCE.converToEntity(appletProdCategoryRelateDTO);
        return this.save(appletProdCategoryRelate);
    }

    @Override
    public boolean updateById(Long id, AppletProdCategoryRelateDTO appletProdCategoryRelateDTO) {
        AppletProdCategoryRelate appletProdCategoryRelate = this.findById(id);
        AppletProdCategoryRelateMapStruct.INSTANCE.copyProperties(appletProdCategoryRelateDTO, appletProdCategoryRelate);
        appletProdCategoryRelate.setId(id);
        return this.updateById(appletProdCategoryRelate);
    }

    @Override
    public AppletProdCategoryRelate findById(Long id) {
        AppletProdCategoryRelate appletProdCategoryRelate = this.getById(id);
        if (ObjectUtil.isNull(appletProdCategoryRelate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletProdCategoryRelate;
    }

    @Override
    public IPage<AppletProdCategoryRelate> queryPage(AppletProdCategoryRelateQO appletProdCategoryRelateQO, PageReq pageReq) {
        QueryWrapper<AppletProdCategoryRelate> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
