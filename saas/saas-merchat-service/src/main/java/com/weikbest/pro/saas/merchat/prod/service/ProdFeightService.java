package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdFeight;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdFeightDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdFeightQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdFeightVO;

import java.util.List;

/**
 * <p>
 * 商品运费信息表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdFeightService extends IService<ProdFeight> {

    /**
     * 新增数据
     *
     * @param prodFeightDTO prodFeightDTO
     * @return 新增结果
     */
    boolean insert(ProdFeightDTO prodFeightDTO);

    /**
     * 更新数据
     *
     * @param id            ID
     * @param prodFeightDTO prodFeightDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdFeightDTO prodFeightDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdFeight findById(Long id);

    /**
     * 分页查询
     *
     * @param prodFeightQO
     * @param pageReq
     * @return
     */
    IPage<ProdFeight> queryPage(ProdFeightQO prodFeightQO, PageReq pageReq);

    /**
     * 保存商品信息时，保存商品的运费信息
     *
     * @param prodId
     * @param prodFeightDTO
     */
    void saveOrUpdateWithProd(Long prodId, ProdFeightDTO prodFeightDTO);

    /**
     * 更新商品运费信息
     *
     * @param ids
     * @param prodFeightDTO
     * @return
     */
    boolean updateProdFeight(List<Long> ids, ProdFeightDTO prodFeightDTO);

    /**
     * 根据商品ID查询
     *
     * @param id
     * @return
     */
    ProdFeightVO getVOById(Long id);
}
