package com.weikbest.pro.saas.merchat.category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dtree.Dtree;
import com.weikbest.pro.saas.merchat.category.entity.AppletProdCategory;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryInsertDTO;
import com.weikbest.pro.saas.merchat.category.module.dto.AppletProdCategoryUpdateDTO;
import com.weikbest.pro.saas.merchat.category.module.qo.AppletProdCategoryQO;
import com.weikbest.pro.saas.merchat.category.module.vo.AppletProdCategoryVO;

import java.util.List;

/**
 * <p>
 * 小程序商品类目表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-10-02
 */
public interface AppletProdCategoryService extends IService<AppletProdCategory> {

    /**
     * 新增数据
     *
     * @param appletProdCategoryDTO appletProdCategoryDTO
     * @return 新增结果
     */
    boolean insert(AppletProdCategoryDTO appletProdCategoryDTO);

    /**
     * 更新数据
     *
     * @param id                    ID
     * @param appletProdCategoryDTO appletProdCategoryDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletProdCategoryDTO appletProdCategoryDTO);

    /**
     * 更新数据
     *
     * @param id                          ID
     * @param appletProdCategoryUpdateDTO appletProdCategoryUpdateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, AppletProdCategoryUpdateDTO appletProdCategoryUpdateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    AppletProdCategory findById(Long id);

    /**
     * 分页查询
     *
     * @param appletProdCategoryQO
     * @param pageReq
     * @return
     */
    IPage<AppletProdCategoryVO> queryPage(AppletProdCategoryQO appletProdCategoryQO, PageReq pageReq);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    AppletProdCategoryVO findVOById(Long id);

    /**
     * 根据ID列表删除
     *
     * @param ids
     */
    void deleteBatchByIds(List<Long> ids);

    /**
     * 新增数据
     *
     * @param appletProdCategoryInsertDTO
     * @return
     */
    boolean insert(AppletProdCategoryInsertDTO appletProdCategoryInsertDTO);

    /**
     * 查询全部数据，返回树形结构
     *
     * @param level 是否返回层级数据
     * @return
     */
    List<Dtree> queryTree(Boolean level);
}
