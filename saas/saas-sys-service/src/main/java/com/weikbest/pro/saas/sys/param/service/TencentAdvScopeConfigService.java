package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvScopeConfig;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvScopeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvScopeConfigQO;

import java.util.List;

/**
 * <p>
 * 腾讯广告主授权腾讯广告第三方应用表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
public interface TencentAdvScopeConfigService extends IService<TencentAdvScopeConfig> {

    /**
     * 新增数据
     *
     * @param tencentAdvScopeConfigDTO tencentAdvScopeConfigDTO
     * @return 新增结果
     */
    boolean insert(TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO);

    /**
     * 更新数据
     *
     * @param id                       ID
     * @param tencentAdvScopeConfigDTO tencentAdvScopeConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, TencentAdvScopeConfigDTO tencentAdvScopeConfigDTO);

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
    TencentAdvScopeConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param tencentAdvScopeConfigQO
     * @param pageReq
     * @return
     */
    IPage<TencentAdvScopeConfig> queryPage(TencentAdvScopeConfigQO tencentAdvScopeConfigQO, PageReq pageReq);

    /**
     * 腾讯广告获取accessToken
     *
     * @param authorizationCode
     * @return
     */
    String getAccessToken(String authorizationCode);

    /**
     * 根据clientId查询
     *
     * @param clientId
     * @return
     */
    TencentAdvScopeConfig findByClientId(Long clientId);
}
