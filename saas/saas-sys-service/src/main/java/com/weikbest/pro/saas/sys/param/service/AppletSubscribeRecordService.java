package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeConfig;
import com.weikbest.pro.saas.sys.param.entity.AppletSubscribeRecord;
import com.weikbest.pro.saas.sys.param.module.dto.AppletSubscribeRecordDTO;
import com.weikbest.pro.saas.sys.param.module.qo.AppletSubscribeRecordQO;

import java.util.List;

/**
 * <p>
 * 小程序订阅消息发送记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-18
 */
public interface AppletSubscribeRecordService extends IService<AppletSubscribeRecord> {

    /**
     * 新增数据
     *
     * @param appletSubscribeRecordDTO appletSubscribeRecordDTO
     * @return 新增结果
     */
    boolean insert(AppletSubscribeRecordDTO appletSubscribeRecordDTO);

    /**
     * 更新数据
     *
     * @param id                       ID
     * @param appletSubscribeRecordDTO appletSubscribeRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletSubscribeRecordDTO appletSubscribeRecordDTO);

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
    AppletSubscribeRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param appletSubscribeRecordQO
     * @param pageReq
     * @return
     */
    IPage<AppletSubscribeRecord> queryPage(AppletSubscribeRecordQO appletSubscribeRecordQO, PageReq pageReq);

    /**
     * 保存发送记录
     *
     * @param appletSubscribeConfig
     * @param openid
     * @param sendContent
     * @param sendParam
     * @param response
     */
    void saveRecord(AppletSubscribeConfig appletSubscribeConfig, String openid, String sendContent, String sendParam, String response);
}
