package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSubscribeConfigQO;

import java.util.List;

/**
 * <p>
 * 小程序订阅消息配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
public interface AppletSubscribeConfigService extends IService<AppletSubscribeConfig> {

    /**
     * 新增数据
     *
     * @param appletSubscribeConfigDTO appletSubscribeConfigDTO
     * @return 新增结果
     */
    boolean insert(AppletSubscribeConfigDTO appletSubscribeConfigDTO);

    /**
     * 更新数据
     *
     * @param id                       ID
     * @param appletSubscribeConfigDTO appletSubscribeConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletSubscribeConfigDTO appletSubscribeConfigDTO);

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
    AppletSubscribeConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param appletSubscribeConfigQO
     * @param pageReq
     * @return
     */
    IPage<AppletSubscribeConfig> queryPage(AppletSubscribeConfigQO appletSubscribeConfigQO, PageReq pageReq);

    /**
     * 获取消息订阅模板
     *
     * @param appId
     * @param bindUrl
     * @return
     */
    AppletSubscribeConfig getAppletSubscribeConfigByBindUrl(String appId, String bindUrl);

    /**
     * 发送一条消息
     *
     * @param appId
     * @param bindUrl
     * @param openid
     * @param orderNumber
     * @param subscribeParam
     */
    void sendOneSubscribeAndSaveRecord(String appId, String bindUrl, String openid, String orderNumber, String... subscribeParam);
}
