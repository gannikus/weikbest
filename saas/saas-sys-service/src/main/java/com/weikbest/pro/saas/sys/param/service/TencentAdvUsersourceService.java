package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.TencentAdvUsersource;
import com.weikbest.pro.saas.sys.param.module.dto.TencentAdvUsersourceDTO;
import com.weikbest.pro.saas.sys.param.module.qo.TencentAdvUsersourceQO;

import java.util.List;

/**
 * <p>
 * 腾讯广告数据上报用户行为数据源表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-12-28
 */
public interface TencentAdvUsersourceService extends IService<TencentAdvUsersource> {

    /**
     * 新增数据
     *
     * @param tencentAdvUsersourceDTO tencentAdvUsersourceDTO
     * @return 新增结果
     */
    boolean insert(TencentAdvUsersourceDTO tencentAdvUsersourceDTO);

    /**
     * 更新数据
     *
     * @param id                      ID
     * @param tencentAdvUsersourceDTO tencentAdvUsersourceDTO
     * @return 更新结果
     */
    boolean updateById(Long id, TencentAdvUsersourceDTO tencentAdvUsersourceDTO);

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
    TencentAdvUsersource findById(Long id);

    /**
     * 分页查询
     *
     * @param tencentAdvUsersourceQO
     * @param pageReq
     * @return
     */
    IPage<TencentAdvUsersource> queryPage(TencentAdvUsersourceQO tencentAdvUsersourceQO, PageReq pageReq);

    /**
     * 根据clientId查询
     *
     * @param clientId
     * @return
     */
    TencentAdvUsersource findByClientId(Long clientId);
}
