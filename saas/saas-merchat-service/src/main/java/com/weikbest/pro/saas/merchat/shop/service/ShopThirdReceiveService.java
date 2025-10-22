package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThirdReceive;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopThirdReceiveDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopThirdReceiveQO;

/**
 * <p>
 * 店铺第三方平台分账接收方拆分多行表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-18
 */
public interface ShopThirdReceiveService extends IService<ShopThirdReceive> {

    /**
     * 新增数据
     *
     * @param shopThirdReceiveDTO shopThirdReceiveDTO
     * @return 新增结果
     */
    boolean insert(ShopThirdReceiveDTO shopThirdReceiveDTO);

    /**
     * 更新数据
     *
     * @param id                  ID
     * @param shopThirdReceiveDTO shopThirdReceiveDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopThirdReceiveDTO shopThirdReceiveDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    ShopThirdReceive findById(Long id);

    /**
     * 分页查询
     *
     * @param shopThirdReceiveQO
     * @param pageReq
     * @return
     */
    IPage<ShopThirdReceive> queryPage(ShopThirdReceiveQO shopThirdReceiveQO, PageReq pageReq);

    /**
     * 开店时添加店铺第三方平台分账信息
     *
     * @param shopId
     * @param shopThirdReceiveDTO
     * @return
     */
    boolean insertWithShop(Long shopId, ShopThirdReceiveDTO shopThirdReceiveDTO);

    /**
     * 在店铺信息中删除第三方平台分账信息
     *
     * @param shopId
     * @param id
     */
    void removeWithShop(Long shopId, Long id);
}
