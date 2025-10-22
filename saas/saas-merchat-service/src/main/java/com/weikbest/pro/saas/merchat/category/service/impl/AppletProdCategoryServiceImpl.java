package com.weikbest.pro.saas.merchat.category.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.DtreeUtil;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategory;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategoryRelate;
import com.weikbest.pro.saas.merchat.category.mapper.AppletProdCategoryMapper;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryInsertDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryUpdateDTO;
import com.weikbest.pro.saas.merchat.category.module.mapstruct.AppletProdCategoryMapStruct;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletProdCategoryQO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletProdCategoryVO;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryRelateService;
import com.weikbest.pro.saas.merchat.category.service.AppletProdCategoryService;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序商品类目表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
@Service
public class AppletProdCategoryServiceImpl extends ServiceImpl<AppletProdCategoryMapper, AppletProdCategory> implements AppletProdCategoryService {

    @Resource
    private AppletProdCategoryRelateService appletProdCategoryRelateService;

    @Override
    public boolean insert(AppletProdCategoryDTO appletProdCategoryDTO) {
        AppletProdCategory appletProdCategory = AppletProdCategoryMapStruct.INSTANCE.converToEntity(appletProdCategoryDTO);
        return this.save(appletProdCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(AppletProdCategoryInsertDTO appletProdCategoryInsertDTO) {
        AppletProdCategory appletProdCategory = AppletProdCategoryMapStruct.INSTANCE.converToEntity(appletProdCategoryInsertDTO);
        boolean save = this.save(appletProdCategory);

        Long id = appletProdCategory.getId();
        List<AppletProdCategoryInsertDTO> children = appletProdCategoryInsertDTO.getChildren();
        if (CollectionUtil.isNotEmpty(children)) {
            List<AppletProdCategory> subAppletProdCategoryList = children.stream().map(subAppletProdCategoryInsertDTO -> {
                AppletProdCategory subAppletProdCategory = AppletProdCategoryMapStruct.INSTANCE.converToEntity(subAppletProdCategoryInsertDTO);
                subAppletProdCategory.setParentId(id);
                return subAppletProdCategory;
            }).collect(Collectors.toList());
            this.saveBatch(subAppletProdCategoryList);
        }

        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, AppletProdCategoryDTO appletProdCategoryDTO) {
        AppletProdCategory appletProdCategory = this.findById(id);
        AppletProdCategoryMapStruct.INSTANCE.copyProperties(appletProdCategoryDTO, appletProdCategory);
        appletProdCategory.setId(id);
        return this.updateById(appletProdCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, AppletProdCategoryUpdateDTO appletProdCategoryUpdateDTO) {
        AppletProdCategory appletProdCategory = this.findById(id);
        AppletProdCategoryMapStruct.INSTANCE.copyProperties(appletProdCategoryUpdateDTO, appletProdCategory);
        appletProdCategory.setId(id);
        boolean update = this.updateById(appletProdCategory);

        List<AppletProdCategoryUpdateDTO> children = appletProdCategoryUpdateDTO.getChildren();
        // 查询之前存在的下级分类数据
        List<AppletProdCategory> subProdCategoryList = this.list(new QueryWrapper<AppletProdCategory>().eq(AppletProdCategory.PARENT_ID, id).eq(AppletProdCategory.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isNotEmpty(subProdCategoryList) && CollectionUtil.isEmpty(children)) {
            // 将之前的下级分类全删掉
            List<Long> subIdList = subProdCategoryList.stream().map(AppletProdCategory::getId).collect(Collectors.toList());
            this.removeBatchByIds(subIdList);
            appletProdCategoryRelateService.remove(new QueryWrapper<AppletProdCategoryRelate>().in(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, subIdList));
            return update;
        }

        if (CollectionUtil.isEmpty(subProdCategoryList) && CollectionUtil.isNotEmpty(children)) {
            // 全部新增下级分类数据
            List<AppletProdCategory> subAppletProdCategoryList = children.stream().map(subAppletProdCategoryInsertDTO -> {
                AppletProdCategory subAppletProdCategory = AppletProdCategoryMapStruct.INSTANCE.converToEntity(subAppletProdCategoryInsertDTO);
                subAppletProdCategory.setParentId(id);
                subAppletProdCategory.setId(GenerateIDUtil.nextId());
                return subAppletProdCategory;
            }).collect(Collectors.toList());
            this.saveBatch(subAppletProdCategoryList);
            return update;
        }

        // 原来有，现在也有，则要区分哪些是新增，哪些是更新，哪些是删除
        if (CollectionUtil.isNotEmpty(subProdCategoryList) && CollectionUtil.isNotEmpty(children)) {
            Map<Long, AppletProdCategory> subMap = subProdCategoryList.stream().collect(Collectors.toMap(AppletProdCategory::getId, appletProdCategory1 -> appletProdCategory1));
            Map<Long, AppletProdCategoryUpdateDTO> childrenMap = children.stream()
                    .filter(appletProdCategoryUpdateDTO1 -> appletProdCategoryUpdateDTO1.getId() != null && appletProdCategoryUpdateDTO1.getId() > WeikbestConstant.ZERO_LONG)
                    .collect(Collectors.toMap(AppletProdCategoryUpdateDTO::getId, appletProdCategoryUpdateDTO1 -> appletProdCategoryUpdateDTO1));

            List<AppletProdCategory> updateList = new ArrayList<>();
            Iterator<Long> iterator = subMap.keySet().iterator();
            while (iterator.hasNext()) {
                Long subId = iterator.next();
                AppletProdCategoryUpdateDTO childrenAppletProdCategoryUpdateDTO = childrenMap.get(subId);
                if (ObjectUtil.isNotEmpty(childrenAppletProdCategoryUpdateDTO)) {
                    // 能找到ID，说明是数据更新
                    AppletProdCategory subAppletProdCategory = subMap.get(subId);
                    AppletProdCategoryMapStruct.INSTANCE.copyProperties(childrenAppletProdCategoryUpdateDTO, subAppletProdCategory);
                    subAppletProdCategory.setParentId(id);
                    subAppletProdCategory.setId(childrenAppletProdCategoryUpdateDTO.getId());
                    updateList.add(subAppletProdCategory);

                    iterator.remove();
                    childrenMap.remove(subId);
                }
            }

            if (CollectionUtil.isNotEmpty(updateList)) {
                // 说明有待更新的数据
                this.updateBatchById(updateList);
            }

            if (CollectionUtil.isNotEmpty(subMap)) {
                // 说明有未匹配到的存量数据，需要把他删掉
                this.removeBatchByIds(subMap.keySet());
                appletProdCategoryRelateService.remove(new QueryWrapper<AppletProdCategoryRelate>().in(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, subMap.keySet()));
            }

            // 找到ID为0，需要新增的数据
            List<AppletProdCategoryUpdateDTO> childInsertList = children.stream()
                    .filter(appletProdCategoryUpdateDTO1 -> appletProdCategoryUpdateDTO1.getId() == null || appletProdCategoryUpdateDTO1.getId() == WeikbestConstant.ZERO_LONG)
                    .collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(childrenMap)) {
                // 说明有未匹配到的新数据，需要新增进去
                childInsertList.addAll(childrenMap.values());
            }
            if (CollectionUtil.isNotEmpty(childInsertList)) {
                // 新增数据集
                // 全部新增下级分类数据
                List<AppletProdCategory> subAppletProdCategoryList = childInsertList.stream().map(subAppletProdCategoryUpdateDTO -> {
                    AppletProdCategory subAppletProdCategory = AppletProdCategoryMapStruct.INSTANCE.converToEntity(subAppletProdCategoryUpdateDTO);
                    subAppletProdCategory.setParentId(id);
                    subAppletProdCategory.setId(GenerateIDUtil.nextId());
                    return subAppletProdCategory;
                }).collect(Collectors.toList());
                this.saveBatch(subAppletProdCategoryList);
            }
        }

        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchByIds(List<Long> ids) {
        this.removeBatchByIds(ids);
        List<AppletProdCategory> subProdCategoryList = this.list(new QueryWrapper<AppletProdCategory>().in(AppletProdCategory.PARENT_ID, ids));
        List<Long> subIdList = subProdCategoryList.stream().map(AppletProdCategory::getId).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(subIdList)) {
            this.removeBatchByIds(subIdList);
        } else {
            subIdList = new ArrayList<>();

        }
        subIdList.addAll(ids);
        appletProdCategoryRelateService.remove(new QueryWrapper<AppletProdCategoryRelate>().in(AppletProdCategoryRelate.APPLET_PROD_CATEGOTY_ID, subIdList));
    }

    @Override
    public AppletProdCategory findById(Long id) {
        AppletProdCategory appletProdCategory = this.getById(id);
        if (ObjectUtil.isNull(appletProdCategory)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return appletProdCategory;
    }

    @Override
    public AppletProdCategoryVO findVOById(Long id) {
        AppletProdCategory appletProdCategory = this.findById(id);
        AppletProdCategoryVO appletProdCategoryVO = AppletProdCategoryMapStruct.INSTANCE.converToVO(appletProdCategory);
        List<AppletProdCategory> subProdCategoryList = this.list(new QueryWrapper<AppletProdCategory>().eq(AppletProdCategory.PARENT_ID, id).eq(AppletProdCategory.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isNotEmpty(subProdCategoryList)) {
            appletProdCategoryVO.setChildren(subProdCategoryList.stream().map(AppletProdCategoryMapStruct.INSTANCE::converToVO).collect(Collectors.toList()));
        }
        return appletProdCategoryVO;
    }

    @Override
    public IPage<AppletProdCategoryVO> queryPage(AppletProdCategoryQO appletProdCategoryQO, PageReq pageReq) {
        QueryWrapper<AppletProdCategory> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(appletProdCategoryQO.getNumber())) {
            wrapper.eq(AppletProdCategory.NUMBER, appletProdCategoryQO.getNumber());
        }
        if (StrUtil.isNotBlank(appletProdCategoryQO.getName())) {
            wrapper.eq(AppletProdCategory.NAME, appletProdCategoryQO.getName());
        }
        if (StrUtil.isNotBlank(appletProdCategoryQO.getIcon())) {
            wrapper.eq(AppletProdCategory.ICON, appletProdCategoryQO.getIcon());
        }
        if (StrUtil.isNotBlank(appletProdCategoryQO.getDescription())) {
            wrapper.eq(AppletProdCategory.DESCRIPTION, appletProdCategoryQO.getDescription());
        }
        if (StrUtil.isNotBlank(appletProdCategoryQO.getDataStatus())) {
            wrapper.eq(AppletProdCategory.DATA_STATUS, appletProdCategoryQO.getDataStatus());
        }
        // 默认查询一级分类
        wrapper.eq(AppletProdCategory.CATEGORY_LEVEL, "1");
        Page<AppletProdCategory> categoryPage = this.page(new Page<>(pageReq.getPage(), pageReq.getLimit()), wrapper);
        List<AppletProdCategory> records = categoryPage.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            Page<AppletProdCategoryVO> page = new Page<>(pageReq.getPage(), pageReq.getLimit(), categoryPage.getTotal());
            page.setRecords(new ArrayList<>());
            return page;
        }

        // 根据ID分组
        List<Long> idList = records.stream().map(AppletProdCategory::getId).collect(Collectors.toList());
        List<AppletProdCategory> subProdCategoryList = this.list(new QueryWrapper<AppletProdCategory>().in(AppletProdCategory.PARENT_ID, idList).eq(AppletProdCategory.DATA_STATUS, DictConstant.Status.enable.getCode()));
        Map<Long, List<AppletProdCategory>> subProdCategoryListMap = subProdCategoryList.stream().collect(Collectors.groupingBy(AppletProdCategory::getParentId));
        // 构造返回数据集
        List<AppletProdCategoryVO> categoryVOList = records.stream().map(appletProdCategory -> {
            AppletProdCategoryVO appletProdCategoryVO = AppletProdCategoryMapStruct.INSTANCE.converToVO(appletProdCategory);
            Long id = appletProdCategory.getId();
            List<AppletProdCategory> childrenList = subProdCategoryListMap.get(id);
            if (CollectionUtil.isNotEmpty(childrenList)) {
                appletProdCategoryVO.setChildren(childrenList.stream().map(AppletProdCategoryMapStruct.INSTANCE::converToVO).collect(Collectors.toList()));
            }
            return appletProdCategoryVO;
        }).collect(Collectors.toList());

        Page<AppletProdCategoryVO> shopPage = new Page<>(pageReq.getPage(), pageReq.getLimit(), categoryPage.getTotal());
        shopPage.setRecords(categoryVOList);
        return shopPage;
    }

    @Override
    public List<Dtree> queryTree(Boolean level) {
        List<AppletProdCategory> appletProdCategoryList = this.list(new QueryWrapper<AppletProdCategory>().eq(AppletProdCategory.DATA_STATUS, DictConstant.Status.enable.getCode()));
        List<Dtree> dtreeList = appletProdCategoryList.stream().map(appletProdCategory -> new Dtree(String.valueOf(appletProdCategory.getId()), appletProdCategory.getName(), String.valueOf(appletProdCategory.getParentId()))).collect(Collectors.toList());
        return BooleanUtil.isTrue(level) ? DtreeUtil.converListToLevel(dtreeList, WeikbestConstant.DEFAULT_PARENT_ID) : dtreeList;
    }
}
