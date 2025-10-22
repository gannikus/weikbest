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
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateRegion;
import com.weikbest.pro.saas.merchat.feight.mapper.FeightTemplateRegionMapper;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateRegionDTO;
import com.weikbest.pro.saas.merchat.feight.module.mapstruct.FeightTemplateRegionMapStruct;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateRegionQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateRegionVO;
import com.weikbest.pro.saas.merchat.feight.service.FeightTemplateRegionService;
import com.weikbest.pro.saas.sys.param.service.RegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 运费模板可配送地区详情拆分多行表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
@Service
public class FeightTemplateRegionServiceImpl extends ServiceImpl<FeightTemplateRegionMapper, FeightTemplateRegion> implements FeightTemplateRegionService {

    @Resource
    private RegionService regionService;

    @Override
    public boolean insert(FeightTemplateRegionDTO feightTemplateRegionDTO) {
        FeightTemplateRegion feightTemplateRegion = FeightTemplateRegionMapStruct.INSTANCE.converToEntity(feightTemplateRegionDTO);
        return this.save(feightTemplateRegion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWithFeightTemplateAndDetail(Long feightTemplateId, Long entryId, FeightTemplateDetailDTO feightTemplateDetailDTO) {
        // 删除动作在父类

        // 在新增
        List<FeightTemplateRegionDTO> feightTemplateRegionDTOList = feightTemplateDetailDTO.getFeightTemplateRegionDTOList();
        List<FeightTemplateRegion> feightTemplateRegionList = feightTemplateRegionDTOList.stream().map(feightTemplateRegionDTO -> {
            FeightTemplateRegion feightTemplateRegion = FeightTemplateRegionMapStruct.INSTANCE.converToEntity(feightTemplateRegionDTO);
            feightTemplateRegion.setId(feightTemplateId);
            feightTemplateRegion.setEntryId(entryId);
            return feightTemplateRegion;
        }).collect(Collectors.toList());
        this.saveBatch(feightTemplateRegionList);
    }

    @Override
    public boolean updateById(Long id, FeightTemplateRegionDTO feightTemplateRegionDTO) {
        FeightTemplateRegion feightTemplateRegion = this.findById(id);
        FeightTemplateRegionMapStruct.INSTANCE.copyProperties(feightTemplateRegionDTO, feightTemplateRegion);
        feightTemplateRegion.setId(id);
        return this.updateById(feightTemplateRegion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByFeightTemplateIds(List<Long> feightTemplateIdList) {
        this.remove(new QueryWrapper<FeightTemplateRegion>().in(FeightTemplateRegion.ID, feightTemplateIdList));
    }

    @Override
    public FeightTemplateRegion findById(Long id) {
        FeightTemplateRegion feightTemplateRegion = this.getById(id);
        if (ObjectUtil.isNull(feightTemplateRegion)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return feightTemplateRegion;
    }

    @Override
    public IPage<FeightTemplateRegion> queryPage(FeightTemplateRegionQO feightTemplateRegionQO, PageReq pageReq) {
        QueryWrapper<FeightTemplateRegion> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(feightTemplateRegionQO.getRegionName())) {
            wrapper.eq(FeightTemplateRegion.REGION_NAME, feightTemplateRegionQO.getRegionName());
        }
        return this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
    }

    @Override
    public List<FeightTemplateRegionVO> queryVOByFeightTemplateIdListAndDetailEntryIdList(List<Long> feightTemplateIdList, List<Long> feightTemplateDetailEntryIdList) {
        if (CollectionUtil.isEmpty(feightTemplateDetailEntryIdList)) {
            return new ArrayList<>();
        }

        List<FeightTemplateRegion> feightTemplateRegionList = this.list(new QueryWrapper<FeightTemplateRegion>().in(FeightTemplateRegion.ENTRY_ID, feightTemplateDetailEntryIdList)
                .in(FeightTemplateRegion.ID, feightTemplateIdList));

        return feightTemplateRegionList.stream().map(FeightTemplateRegionMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Override
    public List<Dtree> queryChooseTree(Long id, Long entryId) {
        List<FeightTemplateRegion> feightTemplateRegionList = this.list(new QueryWrapper<FeightTemplateRegion>().eq(FeightTemplateRegion.ID, id).eq(FeightTemplateRegion.ENTRY_ID, entryId));
        if (CollectionUtil.isEmpty(feightTemplateRegionList)) {
            return regionService.queryTree();
        }

        Set<Integer> regionAdcodeList = feightTemplateRegionList.stream().map(FeightTemplateRegion::getRegionAdcode).collect(Collectors.toSet());
        return regionService.queryTree(regionAdcodeList);
    }
}
