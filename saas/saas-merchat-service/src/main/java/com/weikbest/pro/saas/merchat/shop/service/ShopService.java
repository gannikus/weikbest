package com.weikbest.pro.saas.merchat.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopSetupDTO;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 商户店铺表 服务类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
public interface ShopService extends IService<Shop> {

    /**
     * 新增数据
     *
     * @param shopDTO shopDTO
     * @return 新增结果
     */
    boolean insert(ShopDTO shopDTO);

    /**
     * 新增数据
     *
     * @param shopSetupDTO shopSetupDTO
     * @return 新增结果
     */
    boolean insert(ShopSetupDTO shopSetupDTO);

    /**
     * 更新数据
     *
     * @param id      ID
     * @param shopDTO shopDTO
     * @return 更新结果
     */
    boolean updateById(Long id, ShopDTO shopDTO);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return 查询结果
     */
    Shop findById(Long id);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ShopDetailVO findDetailVOById(Long id);

    /**
     * 分页查询
     *
     * @param shopQO
     * @param pageReq
     * @return
     */
    IPage<ShopListVO> queryPage(ShopQO shopQO, PageReq pageReq);

    /**
     * 分页查询结果合并拆分表或其他表数据
     *
     * @param records
     * @return
     */
    List<ShopListVO> queryPageMerge(List<Shop> records);

    /**
     * 更新数据状态
     *
     * @param id
     * @param dataStatus
     * @return
     */
    boolean updateDataStatusById(Long id, String dataStatus);

    /**
     * 根据商户ID查询商户店铺表数据
     *
     * @param busienssId
     * @return
     */
    List<Shop> queryByBusinessId(Long busienssId);

    /**
     * 根据商户ID查询商户店铺表数据
     *
     * @param busienssId
     * @return
     */
    List<DictEntry> queryDictByBusinessId(Long busienssId);

    /**
     * 根据店铺ID删除店铺
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 配置商家券事件通知
     *
     * @param id
     * @return
     */
    boolean updateCreateBusiFavorCallbacks(Long id);

    /**
     * 配置企业微信客服
     *
     * @param id
     * @param wxBusiness
     * @return
     */
    boolean updateWxBusiness(Long id, String wxBusiness);

    /**
     * 根据店铺ID列表查询
     *
     * @param shopIdSet
     * @return
     */
    List<ShopVO> queryVOByIds(Set<Long> shopIdSet);

    /**
     * 根据商户ID列表查询
     * @param businessId
     * @return
     */
    List<ShopVO> queryVOByBusinessId(Long businessId);

    /**
     * 更新店铺开关广告回传控制开关状态
     * @param id
     * @param switchStatus
     * @return
     */
    boolean updateAdcallbackSwitchStatus(Long id, String switchStatus);

    /**
     * 查询关联店铺的小程序
     * @param shopId
     * @return
     */
    List<AppletConfig> listByShopId(Long shopId);
    /**
     * 更新店铺开关手动回传广告回传控制开关状态
     * @param id
     * @param switchStatus
     * @return
     */
    boolean updateSwitchSwitchManualAdCallback(Long id, String switchStatus);
}
