package com.weikbest.pro.saas.sys.param.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.module.dto.AppletConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletConfigQO;

import java.util.List;

/**
 * <p>
 * 小程序配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
public interface AppletConfigService extends IService<AppletConfig> {

    /**
     * 新增数据
     *
     * @param appletConfigDTO appletConfigDTO
     * @return 新增结果
     */
    boolean insert(AppletConfigDTO appletConfigDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param appletConfigDTO appletConfigDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletConfigDTO appletConfigDTO);

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
    AppletConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param appletConfigQO
     * @param pageReq
     * @return
     */
    IPage<AppletConfig> queryPage(AppletConfigQO appletConfigQO, PageReq pageReq);

    /**
     * 根据小程序ID和小程序类型查询
     *
     * @param appId
     * @param appletType
     * @return
     */
    AppletConfig findByAppIdAndAppletType(String appId, String appletType);

    /**
     * 获取微信小程序配置服务
     *
     * @param appId
     * @return
     */
    WxMaService wxMaService(String appId);

    /**
     * 查询小程序数据，转化为数据字典
     * @return
     */
    List<DictEntry> queryDict();

    /**
     * 获取随机健康的配置
     * @return
     */
    AppletConfig getStochasticAllocation();

}
