package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdJumpLink;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdJumpLinkDTO;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdLeftSlideDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdJumpLinkQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdJumpLinkVO;

import java.util.List;

/**
 * <p>
 * 商品跳转链接拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdJumpLinkService extends IService<ProdJumpLink> {

    /**
     * 新增数据
     *
     * @param prodJumpLinkDTO prodJumpLinkDTO
     * @return 新增结果
     */
    boolean insert(ProdJumpLinkDTO prodJumpLinkDTO);

    /**
     * 更新数据
     *
     * @param id              ID
     * @param prodJumpLinkDTO prodJumpLinkDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdJumpLinkDTO prodJumpLinkDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdJumpLink findById(Long id);

    /**
     * 分页查询
     *
     * @param prodJumpLinkQO
     * @param pageReq
     * @return
     */
    IPage<ProdJumpLink> queryPage(ProdJumpLinkQO prodJumpLinkQO, PageReq pageReq);

    /**
     * 保存商品时，保存商品跳转链接信息
     *
     * @param prodId
     * @param prodJumpLinkDTOList
     */
    void insertBatchWithProd(Long prodId, List<ProdJumpLinkDTO> prodJumpLinkDTOList);

    /**
     * 根据商品ID查询链接信息
     *
     * @param id
     * @return
     */
    List<ProdJumpLinkVO> queryVOById(Long id);

    /**
     * 更新商品链接信息
     *
     * @param id
     * @param prodLeftSlideDTO
     */
    void updateProdJumpLink(Long id, ProdLeftSlideDTO prodLeftSlideDTO);
}
