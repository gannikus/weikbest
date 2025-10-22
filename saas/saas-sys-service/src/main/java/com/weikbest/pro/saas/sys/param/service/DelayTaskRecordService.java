package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskRecord;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskRecordDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskRecordQO;

import java.util.List;

/**
 * <p>
 * 系统延时任务执行记录表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
public interface DelayTaskRecordService extends IService<DelayTaskRecord> {

    /**
     * 新增数据
     *
     * @param delayTaskRecordDTO delayTaskRecordDTO
     * @return 新增结果
     */
    boolean insert(DelayTaskRecordDTO delayTaskRecordDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param delayTaskRecordDTO delayTaskRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, DelayTaskRecordDTO delayTaskRecordDTO);

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
    DelayTaskRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param delayTaskRecordQO
     * @param pageReq
     * @return
     */
    IPage<DelayTaskRecord> queryPage(DelayTaskRecordQO delayTaskRecordQO, PageReq pageReq);

    /**
     * 查询类似的记录
     *
     * @param delayTask
     * @return
     */
    List<DelayTaskRecord> queryLikeDelayTask(String delayTask);
}
