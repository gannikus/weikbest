package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdMainimg;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdMainimgDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdMainimgQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdMainimgVO;

import java.util.List;

/**
 * <p>
 * 商品详情页轮播图拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdMainimgService extends IService<ProdMainimg> {

    /**
     * 新增数据
     *
     * @param prodMainimgDTO prodMainimgDTO
     * @return 新增结果
     */
    boolean insert(ProdMainimgDTO prodMainimgDTO);

    /**
     * 更新数据
     *
     * @param id             ID
     * @param prodMainimgDTO prodMainimgDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdMainimgDTO prodMainimgDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdMainimg findById(Long id);

    /**
     * 分页查询
     *
     * @param prodMainimgQO
     * @param pageReq
     * @return
     */
    IPage<ProdMainimg> queryPage(ProdMainimgQO prodMainimgQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品的主图信息
     *
     * @param prodId
     * @param prodMainimgDTOList
     */
    void insertBatchWithProd(Long prodId, List<ProdMainimgDTO> prodMainimgDTOList);

    /**
     * 根据商品ID查询
     *
     * @param prodId
     * @return
     */
    List<ProdMainimgVO> queryVOByProdId(Long prodId);

    /**
     * 根据商品Id查询商品详情页轮播图信息
     * @param id
     * @return
     */
    List<ProdMainimg> selectByProdId(Long id);
}
