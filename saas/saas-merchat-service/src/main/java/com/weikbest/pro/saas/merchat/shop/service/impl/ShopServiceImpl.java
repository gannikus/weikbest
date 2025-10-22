package com.weikbest.pro.saas.merchat.shop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weikbest.pro.saas.common.constant.BasicConstant;
import com.weikbest.pro.saas.common.exception.WeikbestException;
import com.weikbest.pro.saas.common.transfervo.req.base.PageReq;
import com.weikbest.pro.saas.common.transfervo.resp.ResultConstant;
import com.weikbest.pro.saas.common.transfervo.resp.dict.DictEntry;
import com.weikbest.pro.saas.common.util.GenerateIDUtil;
import com.weikbest.pro.saas.merchat.busi.entity.BusiUser;
import com.weikbest.pro.saas.merchat.busi.entity.Business;
import com.weikbest.pro.saas.merchat.busi.service.BusiUserService;
import com.weikbest.pro.saas.merchat.busi.service.BusinessService;
import com.weikbest.pro.saas.merchat.shop.entity.Shop;
import com.weikbest.pro.saas.merchat.shop.entity.ShopAppletRelated;
import com.weikbest.pro.saas.merchat.shop.entity.ShopFinanceAccount;
import com.weikbest.pro.saas.merchat.shop.entity.ShopThird;
import com.weikbest.pro.saas.merchat.shop.mapper.ShopMapper;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopDTO;
import com.weikbest.pro.saas.merchat.shop.module.dto.ShopSetupDTO;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.mapstruct.ShopThirdMapStruct;
import com.weikbest.pro.saas.merchat.shop.module.qo.ShopQO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopDetailVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopListVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopThirdVO;
import com.weikbest.pro.saas.merchat.shop.module.vo.ShopVO;
import com.weikbest.pro.saas.merchat.shop.service.ShopAppletRelatedService;
import com.weikbest.pro.saas.merchat.shop.service.ShopFinanceAccountService;
import com.weikbest.pro.saas.merchat.shop.service.ShopService;
import com.weikbest.pro.saas.merchat.shop.service.ShopThirdService;
import com.weikbest.pro.saas.sys.common.constant.CodeRuleConstant;
import com.weikbest.pro.saas.sys.common.constant.DictConstant;
import com.weikbest.pro.saas.sys.param.entity.AppletConfig;
import com.weikbest.pro.saas.sys.param.service.AppletConfigService;
import com.weikbest.pro.saas.sys.param.service.CodeRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 商户店铺表 服务实现类
 * </p>
 *
 * @author wisdomelon
 * @since 2022-09-16
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Resource
    private BusinessService businessService;

    @Resource
    private BusiUserService busiUserService;

    @Resource
    private ShopFinanceAccountService shopFinanceAccountService;

    @Resource
    private ShopThirdService shopThirdService;

    @Resource
    private CodeRuleService codeRuleService;

    @Resource
    private ShopAppletRelatedService shopAppletRelatedService;

    @Resource
            private AppletConfigService appletConfigService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ShopDTO shopDTO) {
        Long shopId = GenerateIDUtil.nextId();
        Shop shop = ShopMapStruct.INSTANCE.converToEntity(shopDTO);
        shop.setId(shopId);
        shop.setNumber(codeRuleService.nextNum(CodeRuleConstant.T_MMDM_SHOP));
        boolean save = this.save(shop);

        // 添加第三方支付信息
        shopThirdService.insertWithShop(shopId, shopDTO.getShopThirdDTO());

        // 添加店铺资金账户信息
        shopFinanceAccountService.insertWithShop(shopId);
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(ShopSetupDTO shopSetupDTO) {
        Long shopId = GenerateIDUtil.nextId();
        Shop shop = ShopMapStruct.INSTANCE.converToEntity(shopSetupDTO);
        shop.setId(shopId);
        shop.setNumber(codeRuleService.nextNum(CodeRuleConstant.T_MMDM_SHOP));
//        shopThirdService.insertWithShop(shopId, shopSetupDTO);

        // 添加店铺资金账户信息
        shopFinanceAccountService.insertWithShop(shopId);
        return this.save(shop);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Long id, ShopDTO shopDTO) {
        Shop shop = this.findById(id);
        ShopMapStruct.INSTANCE.copyProperties(shopDTO, shop);
        shop.setId(id);
        boolean update = this.updateById(shop);

        // 更新第三方支付信息
        shopThirdService.updateWithShop(id, shopDTO.getShopThirdDTO());
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCreateBusiFavorCallbacks(Long id) {
        return shopThirdService.updateCreateBusiFavorCallbacks(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateWxBusiness(Long id, String wxBusiness) {
        return shopThirdService.updateWxBusiness(id, wxBusiness);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        this.removeById(id);

        // 删除第三方支付信息和店铺资金账户信息
        shopThirdService.remove(new QueryWrapper<ShopThird>().eq(ShopThird.ID, id));
        shopFinanceAccountService.remove(new QueryWrapper<ShopFinanceAccount>().eq(ShopFinanceAccount.SHOP_ID, id));
    }

    @Override
    public Shop findById(Long id) {
        Shop shop = this.getById(id);
        if (ObjectUtil.isNull(shop)) {
            throw new WeikbestException(ResultConstant.DATA_NOT_FOUND);
        }
        return shop;
    }


    @Override
    public ShopDetailVO findDetailVOById(Long id) {
        Shop shop = findById(id);
        ShopThird shopThird = shopThirdService.getById(id);

        ShopDetailVO shopDetailVO = ShopMapStruct.INSTANCE.converToDetailVO(shop);
        ShopThirdVO shopThirdVO = ShopThirdMapStruct.INSTANCE.converToVO(shopThird);
        shopDetailVO.setShopThirdVO(shopThirdVO);

        // 查询商家信息
        Business business = businessService.findById(shop.getBusinessId());
        BusiUser busiUser = busiUserService.findMainByBusinessId(shop.getBusinessId());

        shopDetailVO.setBusinessName(business.getName());
        shopDetailVO.setBusinessType(business.getBusinessType());
        shopDetailVO.setBusiUserName(busiUser.getName());
        shopDetailVO.setBusiUserId(busiUser.getId());
        shopDetailVO.setBusiPhone(busiUser.getPhone());
        return shopDetailVO;
    }

    @Override
    public List<ShopVO> queryVOByIds(Set<Long> shopIdSet) {
        List<Shop> shopList = this.list(new QueryWrapper<Shop>().in(Shop.ID, shopIdSet).eq(Shop.DATA_STATUS, DictConstant.Status.enable.getCode()));
        return shopList.stream().map(ShopMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Override
    public List<ShopVO> queryVOByBusinessId(Long businessId) {
        List<Shop> shopList = this.list(new QueryWrapper<Shop>().eq(Shop.BUSINESS_ID, businessId).eq(Shop.DATA_STATUS, DictConstant.Status.enable.getCode()));
        return shopList.stream().map(ShopMapStruct.INSTANCE::converToVO).collect(Collectors.toList());
    }

    @Override
    public boolean updateAdcallbackSwitchStatus(Long id, String switchStatus) {
        Shop shop = this.findById(id);
        shop.setIsControlAdCallback(switchStatus);
        return this.updateById(shop);
    }

    @Override
    public List<AppletConfig> listByShopId(Long shopId) {

        List<ShopAppletRelated> list = shopAppletRelatedService.list(new LambdaQueryWrapper<ShopAppletRelated>().eq(ShopAppletRelated::getShopId, shopId));
        if (CollectionUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<Long> appIds = list.stream().map(ShopAppletRelated::getAppletId).collect(Collectors.toList());

        return appletConfigService.list(new QueryWrapper<AppletConfig>().in(AppletConfig.ID, appIds));
    }

    @Override
    public boolean updateSwitchSwitchManualAdCallback(Long id, String switchStatus) {
        Shop shop = this.findById(id);
        shop.setSwitchManualAdCallback(switchStatus);
        return this.updateById(shop);
    }

    @Override
    public IPage<ShopListVO> queryPage(ShopQO shopQO, PageReq pageReq) {
        IPage<ShopListVO> shopListVOIPage = this.baseMapper.queryPage(new Page<>(pageReq.getPage(), pageReq.getLimit()), shopQO);
        return shopListVOIPage.convert(shopListVO ->
            shopListVO.setIsControlAdCallback(StrUtil.isBlank(shopListVO.getIsControlAdCallback()) ? BasicConstant.STATE_0 : shopListVO.getIsControlAdCallback())
        );
    }

    @Override
    public List<ShopListVO> queryPageMerge(List<Shop> records) {
        if (CollectionUtil.isEmpty(records)) {
            return new ArrayList<>();
        }

        List<Long> shopIdList = records.stream().map(Shop::getId).collect(Collectors.toList());

        // 查询店铺第三方平台拆分表
        Map<Long, ShopThird> shopThirdMap = shopThirdService.queryMapByIds(shopIdList);

        return records.stream().map(shop -> {
            Long id = shop.getId();
            ShopThird shopThird = shopThirdMap.getOrDefault(id, new ShopThird());
            return ShopMapStruct.INSTANCE.converToListVO(shop, shopThird);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean updateDataStatusById(Long id, String dataStatus) {
        Shop shop = this.findById(id);
        shop.setDataStatus(dataStatus);
        return this.updateById(shop);
    }

    @Override
    public List<Shop> queryByBusinessId(Long busienssId) {
        return this.list(new QueryWrapper<Shop>().eq(Shop.BUSINESS_ID, busienssId).eq(Shop.DATA_STATUS, DictConstant.Status.enable.getCode()).eq(Shop.FLAG,DictConstant.Whether.no.getCode()));
    }

    @Override
    public List<DictEntry> queryDictByBusinessId(Long busienssId) {
        List<Shop> shopList = this.list(new QueryWrapper<Shop>().eq(Shop.BUSINESS_ID, busienssId).eq(Shop.DATA_STATUS, DictConstant.Status.enable.getCode()));
        if (CollectionUtil.isEmpty(shopList)) {
            return new ArrayList<>();
        }
        return shopList.stream().map(ShopMapStruct.INSTANCE::converToDictEntry).collect(Collectors.toList());
    }
}
