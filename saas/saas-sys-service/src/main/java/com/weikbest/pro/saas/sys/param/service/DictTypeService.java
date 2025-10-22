package com.weikbest.pro.saas.sys.param.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.sys.param.entity.DictType;
import com.weikbest.pro.saas.sys.param.module.dto.DictTypeDTO;
import com.weikbest.pro.saas.sys.param.module.qo.DictTypeQO;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-02
 */
public interface DictTypeService extends IService<DictType> {

    /**
     * 新增数据
     *
     * @param dictTypeDTO dictTypeDTO
     * @return 新增结果
     */
    boolean insert(DictTypeDTO dictTypeDTO);

    /**
     * 更新数据
     *
     * @param id          ID
     * @param dictTypeDTO dictTypeDTO
     * @return 更新结果
     */
    boolean updateById(Long id, DictTypeDTO dictTypeDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    DictType findById(Long id);

    /**
     * 分页查询
     *
     * @param dictTypeQO
     * @param pageReq
     * @return
     */
    IPage<DictType> queryPage(DictTypeQO dictTypeQO, PageReq pageReq);

    /**
     * 查询全部数据，转为数据字典
     *
     * @return
     */
    List<DictEntry> queryDict();
}
