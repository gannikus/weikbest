package com.weikbest.pro.saas.merchat.feight.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateRegion;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateRegionDTO;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateRegionQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateRegionVO;

import java.util.List;

/**
 * <p>
 * 运费模板可配送地区详情拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface FeightTemplateRegionService extends IService<FeightTemplateRegion> {

    /**
     * 新增数据
     *
     * @param feightTemplateRegionDTO feightTemplateRegionDTO
     * @return 新增结果
     */
    boolean insert(FeightTemplateRegionDTO feightTemplateRegionDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param feightTemplateRegionDTO feightTemplateRegionDTO
     * @return 更新结果
     */
    boolean updateById(Long id, FeightTemplateRegionDTO feightTemplateRegionDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    FeightTemplateRegion findById(Long id);

    /**
     * 分页查询
     *
     * @param feightTemplateRegionQO
     * @param pageReq
     * @return
     */
    IPage<FeightTemplateRegion> queryPage(FeightTemplateRegionQO feightTemplateRegionQO, PageReq pageReq);

    /**
     * 根据模板ID和模板详情ID查询
     *
     * @param feightTemplateIdList
     * @param feightTemplateDetailEntryIdList
     * @return
     */
    List<FeightTemplateRegionVO> queryVOByFeightTemplateIdListAndDetailEntryIdList(List<Long> feightTemplateIdList, List<Long> feightTemplateDetailEntryIdList);

    /**
     * 保存运费模板关联地区信息
     *
     * @param feightTemplateId
     * @param entryId
     * @param feightTemplateDetailDTO
     */
    void saveWithFeightTemplateAndDetail(Long feightTemplateId, Long entryId, FeightTemplateDetailDTO feightTemplateDetailDTO);

    /**
     * 根据运费模板ID删除
     *
     * @param feightTemplateIdList
     */
    void removeByFeightTemplateIds(List<Long> feightTemplateIdList);

    /**
     * 查询可配送模板
     *
     * @param id
     * @param entryId
     * @return
     */
    List<Dtree> queryChooseTree(Long id, Long entryId);
}
