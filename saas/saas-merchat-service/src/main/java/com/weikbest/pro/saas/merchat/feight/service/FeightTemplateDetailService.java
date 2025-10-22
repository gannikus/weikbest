package com.weikbest.pro.saas.merchat.feight.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplateDetail;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDetailDTO;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateDetailQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateDetailVO;

import java.util.List;

/**
 * <p>
 * 运费模板详情拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface FeightTemplateDetailService extends IService<FeightTemplateDetail> {

    /**
     * 新增数据
     *
     * @param feightTemplateDetailDTO feightTemplateDetailDTO
     * @return 新增结果
     */
    boolean insert(FeightTemplateDetailDTO feightTemplateDetailDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param feightTemplateDetailDTO feightTemplateDetailDTO
     * @return 更新结果
     */
    boolean updateById(Long id, FeightTemplateDetailDTO feightTemplateDetailDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    FeightTemplateDetail findById(Long id);

    /**
     * 分页查询
     *
     * @param feightTemplateDetailQO
     * @param pageReq
     * @return
     */
    IPage<FeightTemplateDetail> queryPage(FeightTemplateDetailQO feightTemplateDetailQO, PageReq pageReq);

    /**
     * 根据模板ID查询模板详情返回VO
     *
     * @param feightTemplateIdList
     * @return
     */
    List<FeightTemplateDetailVO> queryVOByFeightTemplateIdList(List<Long> feightTemplateIdList);

    /**
     * 保存运费详情信息
     *
     * @param feightTemplateId
     * @param feightTemplateDTO
     */
    void saveWithFeightTemplate(Long feightTemplateId, FeightTemplateDTO feightTemplateDTO);

    /**
     * 根据运费模板ID删除
     *
     * @param feightTemplateIdList
     */
    void removeByFeightTemplateIds(List<Long> feightTemplateIdList);
}
