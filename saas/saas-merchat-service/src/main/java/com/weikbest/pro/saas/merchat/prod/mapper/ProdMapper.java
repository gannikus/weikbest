package com.weikbest.pro.saas.merchat.prod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weikbest.pro.saas.merchat.prod.entity.Prod;
import com.weikbest.pro.saas.merchat.prod.module.dto.AppProdDTO;
import com.weikbest.pro.saas.merchat.prod.module.qo.ProdQO;
import com.weikbest.pro.saas.merchat.prod.module.qo.SlideFloorQO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdListVO;
import com.weikbest.pro.saas.merchat.prod.module.vo.ProdShoppingListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 Mapper 接口
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-11
 */
public interface ProdMapper extends BaseMapper<Prod> {

    /**
     * 获取小程序商品列表
     *
     * @param paramMap
     * @return
     */
    List<AppProdDTO> queryPageCommodity(Map<String, Object> paramMap);

    /**
     * 获取小程序商品列表
     *
     * @param paramMap
     * @return
     */
    List<AppProdDTO> queryPageCommodityByShopId(Map<String, Object> paramMap);

    /**
     * 获取小程序商品列表
     *
     * @param paramMap
     * @return
     */
    List<AppProdDTO> queryPageCommodityByShopIdAndAggregationPage(Map<String, Object> paramMap);

    /**
     * 通过优惠券ID获取立减优惠券关联的商品信息
     * @param paramMap
     * @return
     */
    List<AppProdDTO> queryPageCommodityByCouponId(Map<String, Object> paramMap);

    /**
     * 通过商品名称模楜查询商品列表
     *
     * @param paramMap
     * @return
     */
    List<Long> queryPageProdsByName(Map<String, Object> paramMap);

    /**
     * 列表分页查询数据
     *
     * @param page
     * @param prodQO
     * @return
     */
    IPage<ProdListVO> queryPage(IPage<ProdListVO> page, @Param("prod") ProdQO prodQO);

    AppProdDTO getByProdId(Long id);

    /**
     * 根据商品Id 获取对应的左滑和落地页信息
     * @param ids
     * @return
     */
    List<SlideFloorQO> getSlideFloorByIds(@Param("ids") List<Long> ids);

    /**
     * 获取随手购商品信息
     * @param page
     * @param prodQO
     * @return
     */
    IPage<ProdShoppingListVo> getShoppingList(IPage<ProdShoppingListVo> page, @Param("prod") ProdQO prodQO);

    /**
     * 商品关联随手购商品信息
     * @param id
     * @param shoppingId
     * @param shoppingComboId
     */
    int IdRelevancyShoppingId(@Param("id") Long id, @Param("shoppingId") Long shoppingId, @Param("shoppingComboId") Long shoppingComboId);

    /**
     * 根据随手购Ids修改商品
     *
     * @param comboIds
     * @param newComboId
     */
    int updateByShoppingComboIds(@Param("comboIds") List<Long> comboIds, @Param("newComboId") Long newComboId);

    /**
     * 清除
     * @param id
     */
    int removeShoppingId(@Param("id") Long id);

    /**
     * 批量删除
     * @param ids
     */
    int removeShoppingIds(@Param("ids") List<Long> ids);
}
