package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.AppletSmsConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSmsConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSmsConfigQO;

import java.util.List;

/**
 * <p>
 * 小程序绑定短信配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-22
 */
public interface AppletSmsConfigService extends IService<AppletSmsConfig> {

    /**
     * 新增数据
     *
     * @param appletSmsConfigDTO appletSmsConfigDTO
     * @return 新增结果
     */
    boolean insert(AppletSmsConfigDTO appletSmsConfigDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param appletSmsConfigDTO appletSmsConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletSmsConfigDTO appletSmsConfigDTO);

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
    AppletSmsConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param appletSmsConfigQO
     * @param pageReq
     * @return
     */
    IPage<AppletSmsConfig> queryPage(AppletSmsConfigQO appletSmsConfigQO, PageReq pageReq);

    /**
     * 根据短信模板ID查询
     *
     * @param smsTemplateId
     * @param appId
     * @return
     */
    AppletSmsConfig findBySmsTemplateIdAndAppId(Long smsTemplateId, String appId);
}
