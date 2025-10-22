package com.weikbest.pro.saas.merchat.prod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.merchat.prod.entity.ProdServiceCommitment;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdServiceCommitmentDTO;

/**
 * <p>
 * 商品服务承诺表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-03-22
 */
public interface ProdServiceCommitmentService extends IService<ProdServiceCommitment> {

    /**
     * 新增商品承诺服务
     * @param prodId
     * @param prodServiceCommitmentDTO
     */
    void saveOrUpdateWithProd(Long prodId, ProdServiceCommitmentDTO prodServiceCommitmentDTO);

    /**
     * 根据商品id查询
     * @param id
     * @return
     */
    ProdServiceCommitmentDTO getByProdId(Long id);

    /**
     * 根据商品Id查询商品服务承诺信息
     * @param id
     * @return
     */
    ProdServiceCommitment selectByProdId(Long id);
}
