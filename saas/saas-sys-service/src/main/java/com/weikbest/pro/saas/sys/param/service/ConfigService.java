package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.Config;
import com.weikbest.pro.saas.sys.param.module.dto.ConfigDTO;
import com.weikbest.pro.saas.sys.param.module.qo.ConfigQO;

import java.util.List;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface ConfigService extends IService<Config> {

    /**
     * 新增数据
     *
     * @param configDTO configDTO
     * @return 新增结果
     */
    boolean insert(ConfigDTO configDTO);

    /**
     * 更新数据
     *
     * @param id        ID
     * @param configDTO configDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ConfigDTO configDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Config findById(Long id);

    /**
     * 分页查询
     *
     * @param configQO
     * @param pageReq
     * @return
     */
    IPage<Config> queryPage(ConfigQO configQO, PageReq pageReq);

    /**
     * 查询全部数据，转为数据字典
     *
     * @return
     */
    List<DictEntry> queryDict();
}
