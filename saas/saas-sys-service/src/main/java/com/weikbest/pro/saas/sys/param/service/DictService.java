package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.Dict;
import com.weikbest.pro.saas.sys.param.module.dto.DictDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DictQO;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-03
 */
public interface DictService extends IService<Dict> {

    /**
     * 新增数据
     *
     * @param dictDTO dictDTO
     * @return 新增结果
     */
    boolean insert(DictDTO dictDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param dictDTO dictDTO
     * @return 更新结果
     */
    boolean updateById(Long id, DictDTO dictDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Dict findById(Long id);

    /**
     * 分页查询
     *
     * @param dictQO
     * @param pageReq
     * @return
     */
    IPage<Dict> queryPage(DictQO dictQO, PageReq pageReq);

    /**
     * 查询全部数据，转为数据字典
     *
     * @return
     */
    List<DictEntry> queryDict();

    /**
     * 查询数据，转为数据字典
     *
     * @param dictTypeId
     * @return
     */
    List<DictEntry> queryDictByDictTypeId(Long dictTypeId);
}
