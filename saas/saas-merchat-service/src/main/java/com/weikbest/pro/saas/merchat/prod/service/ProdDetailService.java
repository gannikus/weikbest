package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdDetail;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdDetailDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdDetailQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdDetailVO;

import java.util.List;

/**
 * <p>
 * 商品详情拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdDetailService extends IService<ProdDetail> {

    /**
     * 新增数据
     *
     * @param prodDetailDTO prodDetailDTO
     * @return 新增结果
     */
    boolean insert(ProdDetailDTO prodDetailDTO);

    /**
     * 更新数据
     *
     * @param id            ID
     * @param prodDetailDTO prodDetailDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdDetailDTO prodDetailDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdDetail findById(Long id);

    /**
     * 分页查询
     *
     * @param prodDetailQO
     * @param pageReq
     * @return
     */
    IPage<ProdDetail> queryPage(ProdDetailQO prodDetailQO, PageReq pageReq);

    /**
     * 保存商品时，关联添加商品详情信息
     *
     * @param prodId
     * @param prodDetailDTOList
     */
    void insertBatchWithProd(Long prodId, List<ProdDetailDTO> prodDetailDTOList);

    /**
     * 根据商品ID查询
     *
     * @param prodId
     * @return
     */
    List<ProdDetailVO> queryVOByProdId(Long prodId);

    /**
     * 根据商品Id查询商品详情信息
     * @param id
     * @return
     */
    List<ProdDetail> selectByProdId(Long id);
}
