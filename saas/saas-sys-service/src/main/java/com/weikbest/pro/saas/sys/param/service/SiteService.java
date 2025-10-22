package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.sys.param.entity.Site;
import com.weikbest.pro.saas.sys.param.module.dto.SiteDTO;

/**
 * <p>
 * 系统站点设置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-11-20
 */
public interface SiteService extends IService<Site> {

    /**
     * 新增数据
     *
     * @param siteDTO siteDTO
     * @return 新增结果
     */
    boolean insert(SiteDTO siteDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param siteDTO siteDTO
     * @return 更新结果
     */
    boolean updateById(Long id, SiteDTO siteDTO);

    /**
     * 添加或更新数据
     *
     * @param siteDTO
     * @return
     */
    boolean saveOrUpdate(SiteDTO siteDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Site findById(Long id);

    /**
     * 查询单条数据
     *
     * @return
     */
    Site findSite();

    /**
     * 查找记录,不存在则报错
     *
     * @param notnullFlag
     * @return
     */
    Site findSite(boolean notnullFlag);

}
