package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdComboAttrRelate;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboAttrRelateDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdComboAttrRelateQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboAttrRelateVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品销售套餐规格属性关联表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdComboAttrRelateService extends IService<ProdComboAttrRelate> {

    /**
     * 新增数据
     *
     * @param prodComboAttrRelateDTO prodComboAttrRelateDTO
     * @return 新增结果
     */
    boolean insert(ProdComboAttrRelateDTO prodComboAttrRelateDTO);

    /**
     * 更新数据
     *
     * @param id                     ID
     * @param prodComboAttrRelateDTO prodComboAttrRelateDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdComboAttrRelateDTO prodComboAttrRelateDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdComboAttrRelate findById(Long id);

    /**
     * 分页查询
     *
     * @param prodComboAttrRelateQO
     * @param pageReq
     * @return
     */
    IPage<ProdComboAttrRelate> queryPage(ProdComboAttrRelateQO prodComboAttrRelateQO, PageReq pageReq);

    /**
     * 添加商品时，保存 商品销售套餐关联规格属性信息
     *
     * @param prodId
     * @param prodComboId
     * @param prodComboAttrRelateDTOList
     */
    void insertBatchWithProd(Long prodId, Long prodComboId, List<ProdComboAttrRelateDTO> prodComboAttrRelateDTOList);

    /**
     * 根据商品ID查询
     *
     * @param prodId
     * @return
     */
    Map<Long, List<ProdComboAttrRelateVO>> queryVOByProdIdGroupByProdCombo(Long prodId);

    /**
     * 根据商品Id查询套餐规格Ids
     * @param prodId
     * @return
     */
    List<Long> getIdByProdId(Long prodId);
}
