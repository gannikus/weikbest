package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.DelayTaskConfig;
import com.weikbest.pro.saas.sys.param.module.dto.DelayTaskConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DelayTaskConfigQO;

import java.util.List;

/**
 * <p>
 * 系统延时任务表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-23
 */
public interface DelayTaskConfigService extends IService<DelayTaskConfig> {

    /**
     * 新增数据
     *
     * @param delayTaskConfigDTO delayTaskDTO
     * @return 新增结果
     */
    boolean insert(DelayTaskConfigDTO delayTaskConfigDTO);

    /**
     * 更新数据
     *
     * @param id                 ID
     * @param delayTaskConfigDTO delayTaskDTO
     * @return 更新结果
     */
    boolean updateById(Long id, DelayTaskConfigDTO delayTaskConfigDTO);

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
    DelayTaskConfig findById(Long id);

    /**
     * 分页查询
     *
     * @param delayTaskConfigQO
     * @param pageReq
     * @return
     */
    IPage<DelayTaskConfig> queryPage(DelayTaskConfigQO delayTaskConfigQO, PageReq pageReq);

    /**
     * 根据number查询
     *
     * @param number
     * @return
     */
    DelayTaskConfig findByNumber(String number);
}
