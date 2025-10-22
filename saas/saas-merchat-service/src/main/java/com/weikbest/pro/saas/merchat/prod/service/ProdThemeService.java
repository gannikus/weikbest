package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdTheme;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdThemeDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdThemeQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdThemeVO;

/**
 * <p>
 * 商品样式拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdThemeService extends IService<ProdTheme> {

    /**
     * 新增数据
     *
     * @param prodThemeDTO prodThemeDTO
     * @return 新增结果
     */
    boolean insert(ProdThemeDTO prodThemeDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param prodThemeDTO prodThemeDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdThemeDTO prodThemeDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdTheme findById(Long id);

    /**
     * 分页查询
     *
     * @param prodThemeQO
     * @param pageReq
     * @return
     */
    IPage<ProdTheme> queryPage(ProdThemeQO prodThemeQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品样式信息
     *
     * @param prodId
     * @param prodThemeDTO
     */
    void saveOrUpdateWithProd(Long prodId, ProdThemeDTO prodThemeDTO);

    /**
     * 根据商品ID查询
     *
     * @param id
     * @return
     */
    ProdThemeVO getVOById(Long id);
}
