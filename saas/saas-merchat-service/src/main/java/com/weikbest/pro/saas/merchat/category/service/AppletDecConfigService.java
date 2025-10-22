package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.category.entity.AppletDecConfig;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletDecConfigDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletDecConfigQO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletDecConfigVO;

/**
 * <p>
 * 小程序装修配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
public interface AppletDecConfigService extends IService<AppletDecConfig> {

    /**
     * 新增数据
     *
     * @param appletDecConfigDTO appletDecConfigDTO
     * @return 新增结果
     */
    boolean insert(AppletDecConfigDTO appletDecConfigDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param appletDecConfigDTO appletDecConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletDecConfigDTO appletDecConfigDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    AppletDecConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param appletDecConfigQO
     * @param pageReq
     * @return
     */
    IPage<AppletDecConfig> queryPage(AppletDecConfigQO appletDecConfigQO, PageReq pageReq);

    /**
     * 查询小程序装修配置表
     *
     * @return
     */
    AppletDecConfigVO findAppletDecConfigVO();

    /**
     * 新增或更新数据
     *
     * @param appletDecConfigDTO
     * @return
     */
    boolean saveOrUpdate(AppletDecConfigDTO appletDecConfigDTO);
}
