package com.weikbest.pro.saas.sys.param.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.sys.param.entity.ExcelTemplate;
import com.weikbest.pro.saas.sys.param.mapper.ExcelTemplateMapper;
import com.weikbest.pro.saas.sys.param.module.dto.ExcelTemplateDTO;
import com.weikbest.pro.saas.sys.param.module.mapstruct.ExcelTemplateMapStruct;
import com.weikbest.pro.saas.sys.param.module.qo.ExcelTemplateQO;
import com.weikbest.pro.saas.sys.param.service.ExcelTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统excel模板表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
@Service
public class ExcelTemplateServiceImpl extends ServiceImpl<ExcelTemplateMapper, ExcelTemplate> implements ExcelTemplateService {

    @Override
    public boolean insert(ExcelTemplateDTO excelTemplateDTO) {
        ExcelTemplate excelTemplate = ExcelTemplateMapStruct.INSTANCE.converToEntity(excelTemplateDTO);
        return this.save(excelTemplate);
    }

    @Override
    public boolean updateById(Long id, ExcelTemplateDTO excelTemplateDTO) {
        ExcelTemplate excelTemplate = this.findById(id);
        ExcelTemplateMapStruct.INSTANCE.copyProperties(excelTemplateDTO, excelTemplate);
        excelTemplate.setId(id);
        return this.updateById(excelTemplate);
    }

    @Override
    public boolean deleteBatchByIds(List<Long> ids) {
        return this.removeBatchByIds(ids);
    }

    @Override
    public ExcelTemplate findById(Long id) {
        ExcelTemplate excelTemplate = this.getById(id);
        if (ObjectUtil.isNull(excelTemplate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return excelTemplate;
    }

    @Override
    public ExcelTemplate findByNumber(String number) {
        ExcelTemplate excelTemplate = this.getOne(new QueryWrapper<ExcelTemplate>().eq(ExcelTemplate.NUMBER, number));
        if (ObjectUtil.isNull(excelTemplate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return excelTemplate;
    }

    @Override
    public IPage<ExcelTemplate> queryPage(ExcelTemplateQO excelTemplateQO, PageReq pageReq) {
        QueryWrapper<ExcelTemplate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(excelTemplateQO.getNumber())) {
            wrapper.eq(ExcelTemplate.NUMBER, excelTemplateQO.getNumber());
        }
        if (StrUtil.isNotBlank(excelTemplateQO.getName())) {
            wrapper.eq(ExcelTemplate.NAME, excelTemplateQO.getName());
        }
        if (StrUtil.isNotBlank(excelTemplateQO.getTemplateUrl())) {
            wrapper.eq(ExcelTemplate.TEMPLATE_URL, excelTemplateQO.getTemplateUrl());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }
}
