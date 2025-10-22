package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdAppletRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdAppletRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdAppletRelateQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdAppletRelateVO;

import java.util.List;

/**
 * <p>
 * 商品关联小程序拆分表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdAppletRelateService extends IService<ProdAppletRelate> {

    /**
     * 新增数据
     *
     * @param id id
     * @param prodAppletRelateDTO prodAppletRelateDTO
     * @return 新增结果
     */
    boolean insert(Long id, ProdAppletRelateDTO prodAppletRelateDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param prodAppletRelateDTO prodAppletRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdAppletRelateDTO prodAppletRelateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdAppletRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param prodAppletRelateQO
     * @param pageReq
     * @return
     */
    IPage<ProdAppletRelate> queryPage(ProdAppletRelateQO prodAppletRelateQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品关联小程序信息
     *
     * @param prodId
     * @param prodAppletRelateDTO
     */
    void insertWithProd(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO);

    /**
     * 根据商品ID查询商品关联小程序信息
     *
     * @param prodId
     * @return
     */
    ProdAppletRelateVO findVOById(Long prodId);

    /**
     * 根据小程序落地页链接查询商品关联小程序信息
     *
     * @param appletPageUrl
     * @return
     */
    ProdAppletRelate findByAppletPageUrl(String appletPageUrl);

    /**
     * 新增或更新商品关联小程序信息
     *
     * @param prodId
     * @param prodAppletRelateDTO
     * @return
     */
    boolean saveOrUpdateProdAppletRelate(Long prodId, ProdAppletRelateDTO prodAppletRelateDTO);
}
