package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvConfigQO;

import java.util.List;

/**
 * <p>
 * 腾讯广告第三方应用配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
public interface TencentAdvConfigService extends IService<TencentAdvConfig> {

    /**
     * 新增数据
     *
     * @param tencentAdvConfigDTO tencentAdvConfigDTO
     * @return 新增结果
     */
    boolean insert(TencentAdvConfigDTO tencentAdvConfigDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param tencentAdvConfigDTO tencentAdvConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, TencentAdvConfigDTO tencentAdvConfigDTO);

    /**
     * 删除数据
     *
     * @param ids ID集合
     */
    boolean deleteBatchByIds(List<Long> ids);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    TencentAdvConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param tencentAdvConfigQO
     * @param pageReq
     * @return
     */
    IPage<TencentAdvConfig> queryPage(TencentAdvConfigQO tencentAdvConfigQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @return
     */
    TencentAdvConfig findTencentAdvConfig();
}
