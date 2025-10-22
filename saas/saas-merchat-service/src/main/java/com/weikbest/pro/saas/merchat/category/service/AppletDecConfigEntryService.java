package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfigEntry;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigEntryDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletDecConfigEntryQO;

/**
 * <p>
 * 小程序装修配置分录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
public interface AppletDecConfigEntryService extends IService<AppletDecConfigEntry> {

    /**
     * 新增数据
     *
     * @param appletDecConfigEntryDTO appletDecConfigEntryDTO
     * @return 新增结果
     */
    boolean insert(AppletDecConfigEntryDTO appletDecConfigEntryDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param appletDecConfigEntryDTO appletDecConfigEntryDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletDecConfigEntryDTO appletDecConfigEntryDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    AppletDecConfigEntry findById(Long id);

    /**
     * 分页查询
     *
     * @param appletDecConfigEntryQO
     * @param pageReq
     * @return
     */
    IPage<AppletDecConfigEntry> queryPage(AppletDecConfigEntryQO appletDecConfigEntryQO, PageReq pageReq);

    /**
     * 保存小程序装修页分录表
     *
     * @param id
     * @param appletDecConfigDTO
     */
    void saveBatchAppletDecConfigEntry(Long id, AppletDecConfigDTO appletDecConfigDTO);
}
