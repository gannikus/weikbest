package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.param.entity.Log;
import com.weikbest.pro.saas.sys.param.module.dto.LogDTO;
import com.weikbest.pro.saas.sys.param.module.qo.LogQO;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface LogService extends IService<Log> {

    /**
     * 新增数据
     *
     * @param logDTO logDTO
     * @return 新增结果
     */
    boolean insert(LogDTO logDTO);

    /**
     * 更新数据
     *
     * @param id     ID
     * @param logDTO logDTO
     * @return 更新结果
     */
    boolean updateById(Long id, LogDTO logDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Log findById(Long id);

    /**
     * 分页查询
     *
     * @param logQO
     * @param pageReq
     * @return
     */
    IPage<Log> queryPage(LogQO logQO, PageReq pageReq);
}
