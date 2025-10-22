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
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateDetail;
import com.weikbest.pro.saas.merchat.feight.mapper.FeightTemplateDetailMapper;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.mapstruct.FeightTemplateDetailMapStruct;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateDetailQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateDetailVO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateRegionVO;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateDetailService;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 运费模板详情拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class FeightTemplateDetailServiceImpl extends ServiceImpl<FeightTemplateDetailMapper, FeightTemplateDetail> implements FeightTemplateDetailService {

    @Resource
    private FeightTemplateRegionService feightTemplateRegionService;

    @Override
    public boolean insert(FeightTemplateDetailDTO feightTemplateDetailDTO) {
        FeightTemplateDetail feightTemplateDetail = FeightTemplateDetailMapStruct.INSTANCE.converToEntity(feightTemplateDetailDTO);
        return this.save(feightTemplateDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWithFeightTemplate(Long feightTemplateId, FeightTemplateDTO feightTemplateDTO) {
        // 先删除
        this.removeByFeightTemplateIds(Collections.singletonList(feightTemplateId));
        // 先删除
        feightTemplateRegionService.removeByFeightTemplateIds(Collections.singletonList(feightTemplateId));

        // 在新增
        List<FeightTemplateDetailDTO> feightTemplateDetailDTOList = feightTemplateDTO.getFeightTemplateDetailDTOList();
        List<FeightTemplateDetail> feightTemplateDetailList = feightTemplateDetailDTOList.stream().map(feightTemplateDetailDTO -> {
            Long entryId = GenerateIDUtil.nextId();

            FeightTemplateDetail feightTemplateDetail = FeightTemplateDetailMapStruct.INSTANCE.converToEntity(feightTemplateDetailDTO);
            feightTemplateDetail.setId(feightTemplateId);
            feightTemplateDetail.setEntryId(entryId);
            // 在新增
            feightTemplateRegionService.saveWithFeightTemplateAndDetail(feightTemplateId, entryId, feightTemplateDetailDTO);

            return feightTemplateDetail;
        }).collect(Collectors.toList());

        this.saveBatch(feightTemplateDetailList);
    }


    @Override
    public boolean updateById(Long id, FeightTemplateDetailDTO feightTemplateDetailDTO) {
        FeightTemplateDetail feightTemplateDetail = this.findById(id);
        FeightTemplateDetailMapStruct.INSTANCE.copyProperties(feightTemplateDetailDTO, feightTemplateDetail);
        feightTemplateDetail.setId(id);
        return this.updateById(feightTemplateDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByFeightTemplateIds(List<Long> feightTemplateIdList) {
        this.remove(new QueryWrapper<FeightTemplateDetail>().in(FeightTemplateDetail.ID, feightTemplateIdList));
    }

    @Override
    public FeightTemplateDetail findById(Long id) {
        FeightTemplateDetail feightTemplateDetail = this.getById(id);
        if (ObjectUtil.isNull(feightTemplateDetail)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return feightTemplateDetail;
    }

    @Override
    public IPage<FeightTemplateDetail> queryPage(FeightTemplateDetailQO feightTemplateDetailQO, PageReq pageReq) {
        QueryWrapper<FeightTemplateDetail> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(feightTemplateDetailQO.getIsPinkage())) {
            wrapper.eq(FeightTemplateDetail.IS_PINKAGE, feightTemplateDetailQO.getIsPinkage());
        }
        if (StrUtil.isNotBlank(feightTemplateDetailQO.getPinkageType())) {
            wrapper.eq(FeightTemplateDetail.PINKAGE_TYPE, feightTemplateDetailQO.getPinkageType());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<FeightTemplateDetailVO> queryVOByFeightTemplateIdList(List<Long> feightTemplateIdList) {
        if (CollectionUtil.isEmpty(feightTemplateIdList)) {
            return new ArrayList<>();
        }
        List<FeightTemplateDetail> feightTemplateDetailList = this.list(new QueryWrapper<FeightTemplateDetail>().in(FeightTemplateDetail.ID, feightTemplateIdList));

        List<Long> feightTemplateDetailEntryIdList = feightTemplateDetailList.stream().map(FeightTemplateDetail::getEntryId).collect(Collectors.toList());
        List<FeightTemplateRegionVO> feightTemplateRegionVOList = feightTemplateRegionService.queryVOByFeightTemplateIdListAndDetailEntryIdList(feightTemplateIdList, feightTemplateDetailEntryIdList);
        Map<Long, List<FeightTemplateRegionVO>> feightTemplateRegionVOListMap = feightTemplateRegionVOList.stream().collect(Collectors.groupingBy(FeightTemplateRegionVO::getEntryId));

        List<FeightTemplateDetailVO> feightTemplateDetailVOList = feightTemplateDetailList.stream().map(feightTemplateDetail -> {
            FeightTemplateDetailVO feightTemplateDetailVO = FeightTemplateDetailMapStruct.INSTANCE.converToVO(feightTemplateDetail);
            feightTemplateDetailVO.setFeightTemplateRegionVOList(feightTemplateRegionVOListMap.get(feightTemplateDetail.getEntryId()));
            return feightTemplateDetailVO;
        }).collect(Collectors.toList());

        return feightTemplateDetailVOList;
    }
}
