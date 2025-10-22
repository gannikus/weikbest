package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.ProdCombo;
import com.weikbest.pro.saas.merchat.prod.module.dto.ProdComboDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdComboQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdComboVO;

import java.util.List;

/**
 * <p>
 * 商品销售套餐表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdComboService extends IService<ProdCombo> {

    /**
     * 新增数据
     *
     * @param prodComboDTO prodComboDTO
     * @return 新增结果
     */
    boolean insert(ProdComboDTO prodComboDTO);

    /**
     * 更新数据
     *
     * @param id           ID
     * @param prodComboDTO prodComboDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdComboDTO prodComboDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ProdCombo findById(Long id);

    /**
     * 分页查询
     *
     * @param prodComboQO
     * @param pageReq
     * @return
     */
    IPage<ProdCombo> queryPage(ProdComboQO prodComboQO, PageReq pageReq);

    /**
     * 商品保存时批量保存套餐信息
     *
     * @param prodId
     * @param prodComboDTOList
     * @return
     */
    ProdCombo insertBatchWithProd(Long prodId, Integer setMealType, String goodsType, List<ProdComboDTO> prodComboDTOList);

    /**
     * 根据商品ID查询
     *
     * @param prodId
     * @return
     */
    List<ProdComboVO> queryVOByProdId(Long prodId , Integer setMealType);

    /**
     * 根据商品ID查询最低销售套餐
     *
     * @param prodId
     * @return
     */
    ProdCombo findMinProdComboByProdId(Long prodId, Integer setMealType);

    /**
     * 根据商品商品Id查询DTO的数据
     * @param prodId
     * @param setMealType
     * @return
     */
    List<ProdComboDTO> queryDTOByProdId(Long prodId, Integer setMealType);

    /**
     * 获取随手购下商品对应的套餐信息
     * @param prodIds
     * @return
     */
    List<ProdComboVO> getShoppingList(List<Long> prodIds);
}
