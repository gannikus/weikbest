package com.weikbest.pro.saas.merchat.feight.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.feight.entity.FeightTemplate;
import com.weikbest.pro.saas.merchat.feight.module.dto.FeightTemplateDTO;
import com.weikbest.pro.saas.merchat.feight.module.qo.FeightTemplateQO;
import com.weikbest.pro.saas.merchat.feight.module.vo.FeightTemplateVO;

import java.util.List;

/**
 * <p>
 * 运费模板表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface FeightTemplateService extends IService<FeightTemplate> {

    /**
     * 新增数据
     *
     * @param feightTemplateDTO feightTemplateDTO
     * @return 新增结果
     */
    boolean insert(FeightTemplateDTO feightTemplateDTO);

    /**
     * 新增数据
     *
     * @param feightTemplateDTO feightTemplateDTO
     * @return 新增结果
     */
    Long insertReturnId(FeightTemplateDTO feightTemplateDTO);

    /**
     * 更新数据
     *
     * @param id                ID
     * @param feightTemplateDTO feightTemplateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, FeightTemplateDTO feightTemplateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    FeightTemplate findById(Long id);

    /**
     * 分页查询
     *
     * @param feightTemplateQO
     * @param pageReq
     * @return
     */
    IPage<FeightTemplateVO> queryPage(FeightTemplateQO feightTemplateQO, PageReq pageReq);

    /**
     * 根据模板ID查询
     *
     * @param id
     * @return
     */
    FeightTemplateVO findVOById(Long id);

    /**
     * 根据ID列表删除
     *
     * @param ids
     */
    void deleteBatchByIds(List<Long> ids);
}
