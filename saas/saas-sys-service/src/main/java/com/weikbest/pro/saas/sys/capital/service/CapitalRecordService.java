package com.weikbest.pro.saas.sys.capital.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.sys.capital.entity.CapitalRecord;
import com.weikbest.pro.saas.sys.capital.module.dto.CapitalRecordDTO;
import com.weikbest.pro.saas.sys.capital.module.qo.CapitalRecordQO;

/**
 * <p>
 * 平台资金出入账记录表  服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface CapitalRecordService extends IService<CapitalRecord> {

    /**
     * 新增数据
     *
     * @param capitalRecordDTO capitalRecordDTO
     * @return 新增结果
     */
    boolean insert(CapitalRecordDTO capitalRecordDTO);

    /**
     * 更新数据
     *
     * @param id               ID
     * @param capitalRecordDTO capitalRecordDTO
     * @return 更新结果
     */
    boolean updateById(Long id, CapitalRecordDTO capitalRecordDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    CapitalRecord findById(Long id);

    /**
     * 分页查询
     *
     * @param capitalRecordQO
     * @param pageReq
     * @return
     */
    IPage<CapitalRecord> queryPage(CapitalRecordQO capitalRecordQO, PageReq pageReq);
}
