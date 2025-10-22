package com.weikbest.pro.saas.merchat.feight.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplate;
import com.weikbest.pro.saas.merchat.feight.mapper.FeightTemplateMapper;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.mapstruct.FeightTemplateMapStruct;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateDetailVO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateVO;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateDetailService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateRegionService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdFeight;
import com.weikbest.pro.saas.merchat.prod.service.ProdFeightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 运费模板表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class FeightTemplateServiceImpl extends ServiceImpl<FeightTemplateMapper, FeightTemplate> implements FeightTemplateService {

    @Resource
    private FeightTemplateDetailService feightTemplateDetailService;

    @Resource
    private FeightTemplateRegionService feightTemplateRegionService;

    @Resource
    private ProdFeightService prodFeightService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(FeightTemplateDTO feightTemplateDTO) {
        FeightTemplate feightTemplate = FeightTemplateMapStruct.INSTANCE.converToEntity(feightTemplateDTO);
        boolean save = this.save(feightTemplate);

        Long feightTemplateId = feightTemplate.getId();
        feightTemplateDetailService.saveWithFeightTemplate(feightTemplateId, feightTemplateDTO);

        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertReturnId(FeightTemplateDTO feightTemplateDTO) {
        FeightTemplate feightTemplate = FeightTemplateMapStruct.INSTANCE.converToEntity(feightTemplateDTO);
        this.save(feightTemplate);

        Long feightTemplateId = feightTemplate.getId();
        feightTemplateDetailService.saveWithFeightTemplate(feightTemplateId, feightTemplateDTO);

        return feightTemplateId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, FeightTemplateDTO feightTemplateDTO) {
        FeightTemplate feightTemplate = this.findById(id);
        FeightTemplateMapStruct.INSTANCE.copyProperties(feightTemplateDTO, feightTemplate);
        feightTemplate.setId(id);
        boolean update = this.updateById(feightTemplate);

        Long feightTemplateId = feightTemplate.getId();
        feightTemplateDetailService.saveWithFeightTemplate(feightTemplateId, feightTemplateDTO);
        return update;
    }

    @Override
    public FeightTemplate findById(Long id) {
        FeightTemplate feightTemplate = this.getById(id);
        if (ObjectUtil.isNull(feightTemplate)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return feightTemplate;
    }

    @Override
    public FeightTemplateVO findVOById(Long id) {
        FeightTemplate feightTemplate = this.findById(id);

        // 查询模板详情
        List<FeightTemplateDetailVO> feightTemplateDetailVOList = feightTemplateDetailService.queryVOByFeightTemplateIdList(Collections.singletonList(feightTemplate.getId()));
        Map<Long, List<FeightTemplateDetailVO>> feightTemplateDetailVOListMap = feightTemplateDetailVOList.stream().collect(Collectors.groupingBy(FeightTemplateDetailVO::getId));

        // 组装返回数据
        FeightTemplateVO feightTemplateVO = FeightTemplateMapStruct.INSTANCE.converToVO(feightTemplate);
        feightTemplateVO.setFeightTemplateDetailVOList(feightTemplateDetailVOListMap.get(feightTemplateVO.getId()));

        return feightTemplateVO;
    }

    @Override
    public IPage<FeightTemplateVO> queryPage(FeightTemplateQO feightTemplateQO, PageReq pageReq) {
        QueryWrapper<FeightTemplate> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(feightTemplateQO.getName())) {
            wrapper.eq(FeightTemplate.NAME, feightTemplateQO.getName());
        }
        if (StrUtil.isNotBlank(feightTemplateQO.getPieceworkType())) {
            wrapper.eq(FeightTemplate.PIECEWORK_TYPE, feightTemplateQO.getPieceworkType());
        }
        if (StrUtil.isNotBlank(feightTemplateQO.getDescription())) {
            wrapper.eq(FeightTemplate.DESCRIPTION, feightTemplateQO.getDescription());
        }
        if (StrUtil.isNotBlank(feightTemplateQO.getDataStatus())) {
            wrapper.eq(FeightTemplate.DATA_STATUS, feightTemplateQO.getDataStatus());
        }

        Page<FeightTemplate> feightTemplatePage = this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
        List<FeightTemplate> records = feightTemplatePage.getRecords();
        List<Long> feightTemplateIdList = records.stream().map(FeightTemplate::getId).collect(Collectors.toList());

        // 查询模板详情
        List<FeightTemplateDetailVO> feightTemplateDetailVOList = feightTemplateDetailService.queryVOByFeightTemplateIdList(feightTemplateIdList);
        Map<Long, List<FeightTemplateDetailVO>> feightTemplateDetailVOListMap = feightTemplateDetailVOList.stream().collect(Collectors.groupingBy(FeightTemplateDetailVO::getId));

        // 组装返回数据
        return feightTemplatePage.convert(feightTemplate -> {
            FeightTemplateVO feightTemplateVO = FeightTemplateMapStruct.INSTANCE.converToVO(feightTemplate);
            feightTemplateVO.setFeightTemplateDetailVOList(feightTemplateDetailVOListMap.get(feightTemplateVO.getId()));
            return feightTemplateVO;
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> ids) {
        // 查询该模板是否做了商品关联
        List<ProdFeight> prodFeightList = prodFeightService.list(new QueryWrapper<ProdFeight>().in(ProdFeight.FEIGHT_TEMPLATE_ID, ids));
        if (CollectionUtil.isNotEmpty(prodFeightList)) {
            throw new WeikbestException("有运费模板已经关联了商品，请先删除商品的关联！");
        }

        // 删除
        this.removeByIds(ids);
        feightTemplateDetailService.removeByFeightTemplateIds(ids);
        feightTemplateRegionService.removeByFeightTemplateIds(ids);

    }
}
