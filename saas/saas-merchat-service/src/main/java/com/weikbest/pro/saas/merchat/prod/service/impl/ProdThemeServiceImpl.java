package com.weikbest.pro.saas.merchat.prod.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.prod.entity.ProdTheme;
import com.weikbest.pro.saas.merchat.prod.mapper.ProdThemeMapper;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdThemeDTO;
import com.weikbest.pro.saas.merchat.prod.module.mapstruct.ProdThemeMapStruct;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdThemeQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdThemeVO;
import com.weikbest.pro.saas.merchat.prod.service.ProdThemeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品样式拆分表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class ProdThemeServiceImpl extends ServiceImpl<ProdThemeMapper, ProdTheme> implements ProdThemeService {

    @Override
    public boolean insert(ProdThemeDTO prodThemeDTO) {
        ProdTheme prodTheme = ProdThemeMapStruct.INSTANCE.converToEntity(prodThemeDTO);
        return this.save(prodTheme);
    }

    @Override
    public boolean updateById(Long id, ProdThemeDTO prodThemeDTO) {
        ProdTheme prodTheme = this.findById(id);
        ProdThemeMapStruct.INSTANCE.copyProperties(prodThemeDTO, prodTheme);
        prodTheme.setId(id);
        return this.updateById(prodTheme);
    }

    @Override
    public ProdTheme findById(Long id) {
        ProdTheme prodTheme = this.getById(id);
        if (ObjectUtil.isNull(prodTheme)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return prodTheme;
    }

    @Override
    public IPage<ProdTheme> queryPage(ProdThemeQO prodThemeQO, PageReq pageReq) {
        QueryWrapper<ProdTheme> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(prodThemeQO.getShowImg())) {
            wrapper.eq(ProdTheme.SHOW_IMG, prodThemeQO.getShowImg());
        }
        if (StrUtil.isNotBlank(prodThemeQO.getMainRatioType())) {
            wrapper.eq(ProdTheme.MAIN_RATIO_TYPE, prodThemeQO.getMainRatioType());
        }
        if (StrUtil.isNotBlank(prodThemeQO.getProdTheme())) {
            wrapper.eq(ProdTheme.PROD_THEME, prodThemeQO.getProdTheme());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public void saveOrUpdateWithProd(Long prodId, ProdThemeDTO prodThemeDTO) {
        ProdTheme converToEntity = ProdThemeMapStruct.INSTANCE.converToEntity(prodThemeDTO, prodId);

        // 根据商品ID查询，存在则更新，不存在则为新增
        ProdTheme prodTheme = this.getById(prodId);
        if (ObjectUtil.isEmpty(prodTheme)) {
            this.save(converToEntity);
        } else {
            ProdThemeMapStruct.INSTANCE.copyProperties(converToEntity, prodTheme);
            prodTheme.setId(prodId);
            this.updateById(prodTheme);
        }
    }

    @Override
    public ProdThemeVO getVOById(Long id) {
        ProdTheme prodTheme = this.findById(id);
        return ProdThemeMapStruct.INSTANCE.converToVO(prodTheme);
    }
}
