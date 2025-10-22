package com.weikbest.pro.saas.merchat.prod.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.dto.*;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdService extends IService<Prod> {

    /**
     * 新增数据
     *
     * @param prodDTO prodDTO
     * @return 新增结果
     */
    boolean insert(ProdDTO prodDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param prodDTO prodDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ProdDTO prodDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Prod findById(Long id);

    /**
     * 分页查询
     *
     * @param prodQO
     * @param pageReq
     * @return
     */
    IPage<ProdListVO> queryPage(ProdQO prodQO, PageReq pageReq);

    /**
     * 根据商品ID集合更新商品状态
     *
     * @param ids
     * @param prodStatus
     * @return
     */
    boolean updateProdStatus(List<Long> ids, String prodStatus);

    /**
     * 根据商品ID集合更新商品店铺首页状态
     *
     * @param ids
     * @param joinShopMainpage
     * @return
     */
    boolean updateJoinShopMainpage(List<Long> ids, String joinShopMainpage);

    /**
     * 根据商品ID集合更新商品运费信息
     *
     * @param ids
     * @param prodFeightDTO
     * @return
     */
    boolean updateProdFeight(List<Long> ids, ProdFeightDTO prodFeightDTO);

    /**
     * 根据ID查询装修落地页
     *
     * @param id
     * @return
     */
    ProdDecFloorVO getProdDecFloorVOById(Long id);

    /**
     * 新增或更新装修落地页信息
     *
     * @param id
     * @param prodDecFloorDTO
     * @return
     */
    boolean saveOrUpdateProdDecFloor(Long id, ProdDecFloorDTO prodDecFloorDTO);

    /**
     * 根据ID查询左滑设置
     *
     * @param id
     * @return
     */
    ProdLeftSlideVO getProdLeftSlideVOById(Long id);

    /**
     * 新增或更新左滑信息
     *
     * @param id
     * @param prodLeftSlideDTO
     * @return
     */
    boolean saveOrUpdateProdLeftSlide(Long id, ProdLeftSlideDTO prodLeftSlideDTO);

    /**
     * 根据ID查询商品回流劵
     *
     * @param id
     * @return
     */
    List<ProdCouponVO> queryProdCouponVOById(Long id);

    /**
     * 新增或商品回流劵信息
     *
     * @param id
     * @param prodCouponDTO
     * @return
     */
    boolean saveOrUpdateProdCouponDTO(Long id, ProdCouponDTO prodCouponDTO);

    /**
     * 根据ID查询商品关联小程序信息
     *
     * @param id
     * @return
     */
    ProdAppletRelateVO findProdAppletRelateVOById(Long id);

    /**
     * 新增或更新商品关联小程序数据
     *
     * @param id
     * @param prodAppletRelateDTO
     * @return
     */
    boolean saveOrUpdateProdAppletRelate(Long id, ProdAppletRelateDTO prodAppletRelateDTO);

    /**
     * 根据商品ID查询
     *
     * @param id
     * @return
     */
    ProdShowDetailVO findDetailVOById(Long id);

    /**
     * 根据商品ID查询
     *
     * @param prodIdList
     * @return
     */
    List<ProdShowDetailVO> queryDetailVOById(List<Long> prodIdList);

    /**
     * 根据商品ID查询商品最低销售套餐
     *
     * @param id
     * @return
     */
    MinProdComboVO findMinProdComboVOById(Long id);

    /**
     * 更新商品最低销售套餐
     *
     * @param minProdComboDTO
     * @return
     */
    boolean updateMinProdCombo(MinProdComboDTO minProdComboDTO);


    /**
     * 通过优惠券ID获取立减优惠券关联的商品信息
     * @param paramMap
     * @return
     */
    List<AppProdDTO> queryPageCommodityByCouponId(Map<String, Object> paramMap);


    /**
     * 新增广告链接
     * @param adLinksDTO
     * @return
     */
    int addAdLinks(AdLinksDTO adLinksDTO);


    /**
     * 卷后订单回传
     * @param backHaulDTO
     * @return
     */
    int backHaul(BackHaulDTO backHaulDTO);

    /**
     * 广告链接回显
     * @param id
     * @return
     */
    AdLinksDTO adLinksEcho(Long id);

    /**
     * 根据商品Id获取套餐或者多规格信息
     * @param id
     * @param setMealType
     * @return
     */
    Map<String,List<ProdShowDetailVO.EchoMuchSize>> getComboByProdId(Long id, Integer setMealType);

    /**
     * 获取商品随手购列表
     *
     * @param prodQO
     * @param pageReq
     * @return
     */
    IPage<ProdShoppingListVo> getShoppingList(ProdQO prodQO, PageReq pageReq);

    /**
     * 商品关联随手购商品
     *
     * @param id
     * @param shoppingId
     * @param shoppingComboId
     */
    void IdRelevancyShoppingId(Long id, Long shoppingId, Long shoppingComboId);

    /**
     * 删除商品
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除商品
     * @param ids
     */
    void deletes(List<Long> ids);

    int addAdLinksCallback(AdLinksDTO adLinksDTO);
}
